import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.LinkedList;

public class Analizador {

    private static boolean inInstruction = false;
    private static boolean declaration = true;
    private static boolean inString = false;
    private static int pasoActual = 0;
    private static String[] value = {"", "", "", ""};
    private static LinkedList<String[]> simbolTable = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        if (args.length>0 && args[0].length()>3 && args[0].substring(args[0].length()-4).equals(".txt")){
            System.out.println("Reading from file: " + args[0]);
            CharStream cs = CharStreams.fromFileName(args[0]);
            IKEALexer lexer = new IKEALexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            IKEAParser parser = new IKEAParser(tokens);

            ParseTree tree = parser.prog();

            ReadTokens(tree.toStringTree(parser));
            // System.out.println(tree.toStringTree(parser));
        } else{
            System.out.println("Please provide a .txt file as input.");
        }
    }

    public static void ReadTokens(String input){
        String crntWord = "";
        char crntChar;
        for (int i = 0; i < input.length(); i++){
            crntChar = input.charAt(i);
            switch (crntChar){
                case ' ':
                    if (!crntWord.equals("")){
                        handleToken(crntWord);
                        crntWord = "";
                    }
                    break;
                
                case '{':
                    inInstruction = true;
                    break;
                
                case '}':
                    inInstruction = false;
                    break;
                
                case '"':
                    inString = !inString;
                
                case '(':
                case ')':
                    break;
                
                default:
                    crntWord += crntChar;
                    break;
            }
        }
        if (!crntWord.equals("")){
            handleToken(crntWord);
        }
        System.out.println("End");
    }

    public static void handleToken(String token){
        if (inString){
            return;
        }
        if (declaration){
            if (token.equals("PASO")){
                declaration = false;
                printDeclaration();
                value[0] = new String(token);
            }
            else if (token.equals(";")){
                if (value[3].equals("")){
                    value[3] = "NoUsable";
                }
                simbolTable.add(new String[]{value[0], value[1], value[2], value[3]});
                value = new String[]{"","","",""};
            }
            else if (token.equals("HERRAMIENTA") || token.equals("HERRAJE")){
                value[0] = new String(token);
            }
            else {
                if (value[0].equals("HERRAMIENTA")){
                    value[1] = new String(token);
                } else if (value[0].equals("HERRAJE") && value[1].equals("")){
                    value[1] = new String(token);
                } else if (value[0].equals("HERRAJE") && value[2].equals("") && !token.equals("*") && !token.equals("cantidad")){
                    value[2] = new String(token);
                } else if (value[0].equals("HERRAJE") && token.equals("Usable")){
                    value[3] = new String(token);
                }
            }
        }
        else{
            if (token.equals("PASO")){
                value[0] = new String(token);
            }
            else if (value[0].equals("PASO")){
                int siguiente = Integer.parseInt(token);
                if (siguiente != pasoActual + 1){
                    System.out.println("Error: Se esperaba el paso " + (pasoActual + 1) + " pero se encontró el paso " + siguiente + ".");
                }
                else{
                    pasoActual = siguiente;
                }
                value[0] = "";
            }
            else if (token.equals("HERRAJE")){
                value[0] = new String(token);
            }
            else if (value[0].equals("HERRAJE") && value[1].equals("")){
                value[1] = new String(token);
            }
            else if (value[0].equals("HERRAJE") && value[2].equals("") && !token.equals("*") && !token.equals("cantidad")){
                value[2] = new String(token);
                if (inInstruction){
                    useHerraje(value[1], value[2]);
                    value = new String[]{"","","",""};
                }
                else{
                    toolHerraje();
                }
            }
            else if (token.equals("MONTAJE")){
                value[0] = new String(token);
            }
            else if (value[0].equals("MONTAJE")){
                value[1] = new String(token);
                String[] v = find(value[0], value[1]);
                if (inInstruction){
                    if (v == null){
                        System.out.println("Error: El montaje " + value[1] + " no ha sido declarado (paso " + pasoActual + ").");
                    }
                    else if(v[2].equals("Usado")){
                        System.out.println("El montaje " + value[1] + " ya ha sido usado en otro montaje (paso " + pasoActual + ").");
                    }
                    else{
                        useMontaje(value[1]);
                    }
                }
                else{
                    if (v == null){
                        value[2] = "Disponible";
                        simbolTable.add(new String[]{value[0], value[1], value[2], ""});
                    }
                    else if (v[2].equals("Usado")){
                        System.out.println("El montaje " + value[1] + " ya ha sido usado en otro montaje (paso " + pasoActual + ").");
                    }
                }
                value = new String[]{"","","",""};
            }
        }
    }

    public static void printDeclaration(){
        System.out.println("Herramientas y Herrajes Declarados:");
        String s = "HERRAMIENTA";
        for (String[] entry : simbolTable){
            if (!entry[0].equals(s)){
                System.out.println();
            }
            System.out.println(entry[0] + " " + entry[1] + (entry[0].equals("HERRAJE") ? (" *" + entry[2] + ", " + entry[3]) : ""));
            s = new String(entry[0]);
        }
        System.out.println();
    }

    public static void useHerraje(String id, String cantidad){
        for (String[] entry : simbolTable){
            if (entry[0].equals("HERRAJE") && entry[1].equals(id)){
                int i = Integer.parseInt(entry[2]) - Integer.parseInt(cantidad);
                if (i<0)
                    System.out.println("Error: No hay suficiente herraje " + id + " (paso " + pasoActual + ").");
                entry[2] = Integer.toString(i);
            }
        }
    }

    public static void toolHerraje(){
        for (String[] entry : simbolTable){
            if (entry[0].equals("HERRAJE") && entry[3].equals("NoUsable")){
                System.out.println("Herraje no es usable: " + entry[1] + " (paso " + pasoActual + ").");
            }
        }
        System.out.println();
    }

    public static String[] find(String tipo, String id){
        for (String[] entry : simbolTable){
            if (entry[0].equals(tipo) && entry[1].equals(id)){
                return entry;
            }
        }
        return null;
    }

    public static void useMontaje(String id){
        String[] montaje = find("MONTAJE", id);
        if (montaje != null){
            if (montaje[2].equals("Usado")){
                System.out.println("El montaje " + id + " ya ha sido usado en otro montaje (paso " + pasoActual + ").");
            } else{
                montaje[2] = "Usado";
            }
        } else{
            System.out.println("Error: El montaje " + id + " no ha sido declarado (paso " + pasoActual + ").");
        }
    }
}
