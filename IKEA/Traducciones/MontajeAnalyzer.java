import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

/**
 * MontajeAnalyzer
 *
 * Lee uno o varios archivos de instrucciones de montaje (txt) y:
 *  - extrae herramientas declaradas
 *  - extrae herrajes declarados (con cantidad o "Usable")
 *  - analiza los PASOS y extrae por paso: herramientas usadas, herrajes utilizados y notas
 *  - muestra listados agregados y comprobaciones (ej. usado > declarado)
 *
 * Uso:
 *   javac MontajeAnalyzer.java
 *   java MontajeAnalyzer Flysta.txt Hemnes.txt
 *
 * El parser es robusto ante ciertas variantes (mayúsculas/minúsculas, "HERRRAJE" con errores,
 * formatos HERRAJE 12345*2, HERRAJE 12345, 6 o HERRAJE 12345*Usable).
 */
public class MontajeAnalyzer {

    // Símbolos globales
    static Set<String> herramientasDeclaradas = new LinkedHashSet<>();
    static Map<String, Herraje> herrajesDeclarados = new LinkedHashMap<>(); // id -> info
    static Map<String, Integer> herrajesUsados = new TreeMap<>(); // id -> cantidad total usada
    static Set<String> herramientasUsadasEnPasos = new LinkedHashSet<>();

    static class Herraje {
        String id;
        // cantidad declarada: null = no declarado, -1 = Usable, >=0 numeric
        Integer declarada;
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
            System.out.println("=== Analizando archivo: " + f + " ===\n");
            String text = new String(Files.readAllBytes(p));
            analizaryAcumular(text);
            System.out.println();
        }

        // Al final mostrar resultados globales
        imprimirResumen();
    }

    static void analizaryAcumular(String text) {
        String lower = text.toLowerCase();

        int declIndex = lower.indexOf("declaracion");
        int pasosIndex = lower.indexOf("pasos");
        String declarationText = declIndex >= 0 && pasosIndex > declIndex
                ? text.substring(declIndex, pasosIndex)
                : (declIndex >= 0 ? text.substring(declIndex) : "");

        String pasosText = pasosIndex >= 0 ? text.substring(pasosIndex) : "";

        // 1) herramientas declaradas en la sección de declaración
        Pattern herramientaDecl = Pattern.compile("(?i)\\bHERRAMIENTA\\s+([A-Za-z0-9_\\u00C0-\\u017F]+)\\s*;");
        Matcher mh = herramientaDecl.matcher(declarationText);
        while (mh.find()) {
            String nombre = mh.group(1).trim();
            herramientasDeclaradas.add(nombre);
        }

        // 2) herrajes declarados en sección declaración
        // Acepta variantes: HERRAJE, HERRRAJE, HERRRAJE... (case-insensitive)
        Pattern herrajeDecl = Pattern.compile("(?i)\\bHER+RAJE\\s*(\\d{1,9})(?:\\s*[*,\\s]\\s*(\\d+|Usable))?\\s*;");
        Matcher hh = herrajeDecl.matcher(declarationText);
        while (hh.find()) {
            String id = hh.group(1);
            String q = hh.group(2);
            Integer cantidad = null;
            if (q != null) {
                if (q.equalsIgnoreCase("Usable")) cantidad = -1;
                else {
                    try { cantidad = Integer.parseInt(q); } catch (NumberFormatException e) { cantidad = null; }
                }
            }
            herrajesDeclarados.putIfAbsent(id, new Herraje(id, cantidad));
        }

        // 3) dividir en pasos y analizar cada paso
        // Split por PASO N: (acepta PASO con mayúsculas/minúsculas)
        String[] pasos = pasosText.split("(?i)\\bPASO\\s+\\d+\\s*:");
        // El primer split antes del primer PASO puede ser vacío; ignorar
        int pasoNum = 0;
        for (String pasoBlock : pasos) {
            pasoBlock = pasoBlock.trim();
            if (pasoBlock.isEmpty()) continue;
            pasoNum++;

            // Notas dentro del paso
            List<String> notas = extraerNotas(pasoBlock);

            // Herramientas usadas en el paso (CON HERRAMIENTA X)
            Pattern herramientaInStep = Pattern.compile("(?i)CON\\s+HERRAMIENTA\\s+([A-Za-z0-9_\\u00C0-\\u017F]+)");
            Matcher mhi = herramientaInStep.matcher(pasoBlock);
            Set<String> herramientasPaso = new LinkedHashSet<>();
            while (mhi.find()) {
                String hname = mhi.group(1).trim();
                herramientasPaso.add(hname);
                herramientasUsadasEnPasos.add(hname);
            }

            // También podría aparecer "CON HERRAMIENTA Nivel{}" or similar without space; handled above.
            // Además podemos tener declaraciones "MONTAJE ... CON HERRAMIENTA X { ... }"

            // Buscar todos los herrajes usados en el paso (varias formas)
            // Formatos posibles observados:
            //   HERRAJE 115753*2
            //   HERRAJE 121030
            //   HERRAJE 122723, 6
            //   HERRRAJE 119256*2  (typo: HERRRAJE)
            // Eliminar NOTAS antes de buscar herrajes para evitar falsos positivos
            String pasoSinNotas = pasoBlock.replaceAll("(?i)NOTA\\s*(\"[^\"]*\"|[^\\n\\r;]*)", "");

            // Buscar herrajes usados (solo sobre texto limpio)
            Pattern herrajeAny = Pattern.compile("(?i)\\bHER+RAJE\\s*(\\d{1,9})(?:\\s*[*,\\s]\\s*(\\d+|Usable))?");
            Matcher mh2 = herrajeAny.matcher(pasoSinNotas);

            Map<String, Integer> usadosEnEstePaso = new LinkedHashMap<>();
            while (mh2.find()) {
                String id = mh2.group(1);
                String q = mh2.group(2);
                int qty = 1;
                if (q != null) {
                    if (q.equalsIgnoreCase("Usable")) {
                        qty = 0;
                        herrajesDeclarados.putIfAbsent(id, new Herraje(id, -1));
                    } else {
                        try { qty = Integer.parseInt(q); } catch (NumberFormatException e) { qty = 1; }
                    }
                }

                if (q != null && q.equalsIgnoreCase("Usable")) {
                    herrajesDeclarados.putIfAbsent(id, new Herraje(id, -1));
                } else {
                    usadosEnEstePaso.merge(id, qty, Integer::sum);
                    herrajesUsados.merge(id, qty, Integer::sum);
                }
            }


            // Mostrar resumen del paso (en consola inmediatamente)
            System.out.println("Paso " + pasoNum + ":");
            if (!herramientasPaso.isEmpty()) {
                System.out.println("  Herramientas usadas en este paso: " + String.join(", ", herramientasPaso));
            } else {
                System.out.println("  Herramientas usadas en este paso: (ninguna explícita)");
            }

            if (!usadosEnEstePaso.isEmpty()) {
                System.out.println("  Herrajes usados en este paso:");
                for (Map.Entry<String,Integer> e : usadosEnEstePaso.entrySet()) {
                    System.out.println("    - " + e.getKey() + "  x " + e.getValue());
                    // si no estaba declarado previamente, añadimos con declarada=null
                    herrajesDeclarados.putIfAbsent(e.getKey(), new Herraje(e.getKey(), null));
                }
            } else {
                System.out.println("  Herrajes usados en este paso: (ninguno o todos Usable)");
            }

            if (!notas.isEmpty()) {
                System.out.println("  NOTAS:");
                for (String n : notas) System.out.println("    * " + n);
            }
            System.out.println();
        }
    }

    static List<String> extraerNotas(String block) {
        List<String> notas = new ArrayList<>();
        // NOTA ... ; or NOTA "..." or NOTA Texto hasta final de linea
        Pattern notaPat = Pattern.compile("(?i)NOTA\\s*(?:\"([^\"]+)\"|([^;\\n\\r]+))");
        Matcher m = notaPat.matcher(block);
        while (m.find()) {
            String a = m.group(1);
            if (a == null) a = m.group(2);
            if (a != null) notas.add(a.trim());
        }
        return notas;
    }

    static void imprimirResumen() {
        System.out.println("============================================");
        System.out.println("RESUMEN GLOBAL");
        System.out.println("============================================\n");

        // Herramientas declaradas
        System.out.println("Herramientas declaradas (sección 'declaracion'):");
        if (herramientasDeclaradas.isEmpty()) {
            System.out.println("  (ninguna declarada)");
        } else {
            for (String h : herramientasDeclaradas) System.out.println("  - " + h);
        }
        System.out.println();

        // Herramientas usadas en pasos
        System.out.println("Herramientas usadas en pasos:");
        if (herramientasUsadasEnPasos.isEmpty()) {
            System.out.println("  (ninguna registrada en pasos)");
        } else {
            for (String h : herramientasUsadasEnPasos) {
                String declaredMark = herramientasDeclaradas.contains(h) ? "" : " (no declarada)";
                System.out.println("  - " + h + declaredMark);
            }
        }
        System.out.println();

        // Herrajes declarados
        System.out.println("Herrajes declarados (en 'declaracion'):");
        if (herrajesDeclarados.isEmpty()) {
            System.out.println("  (ninguno declarado explícitamente)");
        } else {
            for (Herraje hr : herrajesDeclarados.values()) {
                String usedStr = herrajesUsados.containsKey(hr.id) ? " - usados: " + herrajesUsados.get(hr.id) : "";
                System.out.println("  - " + hr.id + "  (declarado: " + hr.declaradaStr() + ")" + usedStr);
            }
        }
        System.out.println();

        // Herrajes usados que no estaban declarados
        System.out.println("Herrajes usados en pasos (agregado):");
        if (herrajesUsados.isEmpty()) {
            System.out.println("  (ningún herraje con cantidad numerica usado)");
        } else {
            for (Map.Entry<String,Integer> e : herrajesUsados.entrySet()) {
                String id = e.getKey();
                int totalUsado = e.getValue();
                String decl = herrajesDeclarados.containsKey(id) ? herrajesDeclarados.get(id).declaradaStr() : "No declarado";
                System.out.print("  - " + id + "  total usado: " + totalUsado + "  (declarado: " + decl + ")");
                // comprobación
                Herraje hr = herrajesDeclarados.get(id);
                if (hr != null && hr.declarada != null && hr.declarada >= 0 && totalUsado > hr.declarada) {
                    System.out.print("  <-- AVISO: usado más que declarado!");
                } else if (hr != null && hr.declarada != null && hr.declarada == -1) {
                    System.out.print("  (declarado Usable)");
                } else if (hr == null) {
                    System.out.print("  (no estaba declarado)");
                }
                System.out.println();
            }
        }
        System.out.println();

        System.out.println("FIN DEL RESUMEN");
    }
}
