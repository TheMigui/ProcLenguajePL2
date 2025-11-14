import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class MontajeAnalyzer {

    // Símbolos globales
    static Set<String> herramientasDeclaradas = new LinkedHashSet<>();
    static Map<String, Herraje> herrajesDeclarados = new LinkedHashMap<>();
    static Map<String, Integer> herrajesUsados = new TreeMap<>();
    static Set<String> herramientasUsadasEnPasos = new LinkedHashSet<>();

    static class Herraje {
        String id;
        Integer declarada; // null = no declarado, -1 = usable
        Herraje(String id, Integer declarada) { this.id = id; this.declarada = declarada; }

        String declaradaStr() {
            if (declarada == null) return "No declarado";
            if (declarada == -1) return "Usable";
            return declarada.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Uso: java MontajeAnalyzer <archivo1.txt> [archivo2.txt ...]");
            System.exit(1);
        }

        for (String f : args) {
            Path p = Paths.get(f);
            if (!Files.exists(p)) {
                System.err.println("No existe: " + f);
                continue;
            }

            String text = new String(Files.readAllBytes(p));
            analizaryAcumular(text);
        }

        // Solo mostrar el resumen final
        imprimirResumen();
    }

    static void analizaryAcumular(String text) {

        boolean enDeclaracion = true;  // Se empieza asumiendo que estamos en declaración

        // Dividir por bloques paso a paso
        // Cada vez que aparezca un PASO, pasamos a modo "pasos"
        // Así podemos analizar línea por línea
        String[] lineas = text.split("\\R+");

        Pattern herramientaDecl = Pattern.compile("(?i)\\bHERRAMIENTA\\s+([A-Za-z0-9_\\u00C0-\\u017F]+)\\s*;?");
        Pattern herrajeDecl = Pattern.compile("(?i)\\bHERRAJE\\s*(\\d{1,9})(?:\\s*[*,\\s]\\s*(\\d+|Usable))?\\s*;?");
        Pattern herramientaPaso = Pattern.compile("(?i)CON\\s+HERRAMIENTA\\s+([\\p{L}0-9_]+)");
        Pattern herrajePaso = Pattern.compile("(?i)\\bHERRAJE\\s*(\\d{1,9})(?:\\s*[*,\\s]\\s*(\\d+|Usable))?");

        for (String linea : lineas) {
            String l = linea.trim();
            if (l.isEmpty()) continue;

            // ----------- detectar inicio de PASOS -------------
            if (l.matches("(?i)^PASO\\s+\\d+\\s*:?.*")) {
                enDeclaracion = false;
            }

            // =======================================================
            //                     DECLARACIÓN
            // =======================================================
            if (enDeclaracion) {

                Matcher mh = herramientaDecl.matcher(l);
                while (mh.find()) {
                    herramientasDeclaradas.add(mh.group(1).trim());
                }

                Matcher hh = herrajeDecl.matcher(l);
                while (hh.find()) {
                    String id = hh.group(1);
                    String q = hh.group(2);
                    Integer cantidad = null;

                    if (q != null) {
                        if (q.equalsIgnoreCase("Usable")) cantidad = -1;
                        else cantidad = Integer.parseInt(q);
                    }

                    herrajesDeclarados.putIfAbsent(id, new Herraje(id, cantidad));
                }

                continue;
            }

            // =======================================================
            //                         PASOS
            // =======================================================

            // Herramientas usadas
            Matcher mhi = herramientaPaso.matcher(l);
            while (mhi.find()) {
                herramientasUsadasEnPasos.add(mhi.group(1).trim());
            }

            // Eliminar NOTA para no contar herrajes dentro
            String sinNotas = l.replaceAll("(?i)NOTA\\s*(\"[^\"]*\"|[^\\n\\r;]*)", "");

            Matcher mh2 = herrajePaso.matcher(sinNotas);
            while (mh2.find()) {
                String id = mh2.group(1);
                String q = mh2.group(2);
                int qty = 1;

                if (q != null) {
                    if (q.equalsIgnoreCase("Usable")) {
                        qty = 0;
                        herrajesDeclarados.putIfAbsent(id, new Herraje(id, -1));
                    } else qty = Integer.parseInt(q);
                }

                if (qty > 0) {
                    herrajesUsados.merge(id, qty, Integer::sum);
                    herrajesDeclarados.putIfAbsent(id, new Herraje(id, null));
                }
            }
        }
    }

    static void imprimirResumen() {
        System.out.println("============================================");
        System.out.println("RESUMEN GLOBAL");
        System.out.println("============================================\n");

        System.out.println("Herramientas declaradas:");
        if (herramientasDeclaradas.isEmpty()) System.out.println("  (ninguna)");
        else herramientasDeclaradas.forEach(h -> System.out.println("  - " + h));

        System.out.println("\nHerramientas usadas en pasos:");
        if (herramientasUsadasEnPasos.isEmpty()) System.out.println("  (ninguna)");
        else herramientasUsadasEnPasos.forEach(h ->
                System.out.println("  - " + h + (herramientasDeclaradas.contains(h) ? "" : " (no declarada)"))
        );

        System.out.println("\nHerrajes declarados:");
        for (Herraje hr : herrajesDeclarados.values()) {
            String used = herrajesUsados.containsKey(hr.id) ? " - usados: " + herrajesUsados.get(hr.id) : "";
            System.out.println("  - " + hr.id + " (declarado: " + hr.declaradaStr() + ")" + used);
        }

        System.out.println("\nHerrajes usados (agregado):");
        for (var e : herrajesUsados.entrySet()) {
            String id = e.getKey();
            int totalUsado = e.getValue();
            Herraje hr = herrajesDeclarados.get(id);

            String decl = hr != null ? hr.declaradaStr() : "No declarado";
            System.out.print("  - " + id + " total usado: " + totalUsado + " (declarado: " + decl + ")");

            if (hr != null && hr.declarada != null && hr.declarada >= 0 && totalUsado > hr.declarada)
                System.out.print(" <-- AVISO: usado más que declarado!");

            System.out.println();
        }

        System.out.println("\nFIN DEL RESUMEN");
    }
}
