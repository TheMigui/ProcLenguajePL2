import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.LinkedList;

public class Analizador {

    private static bool  inInstruction = false;
    private static bool  declaration = true;
    private static bool  inString = false;
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
            }
            else if (token.equals(";")){
                if (value[3].equals("")){
                    value[3] = "NotUsable";
                }
                simbolTable.add(new String[]{value[0], value[1], value[2], value[3]});
                value = new String[]{"","","",""};
            }
            else if (token.equals("HERRAMIENTA") || token.equals("HERRAJE")){
                value[0] = token;
            }
            else {
                if (value[0].equals("HERRAMIENTA")){
                    value[1] = token;
                } else if (value[0].equals("HERRAJE") && value[1].equals("")){
                    value[1] = token;
                } else if (value[0].equals("HERRAJE") && value[2].equals("") && !token.equals("*")){
                    value[2] = token;
                } else if (value[0].equals("HERRAJE") && token.equals("Usable")){
                    value[3] = token;
                }
            }
        }
        else{
            if (token.equals("")){}
        }
    }

    public static void printDeclaration(){
        System.out.println("Herramientas y Herrajes Declarados:");
        for (String[] entry : simbolTable){
            System.out.println(entry[0] + " " + entry[1] + (entry[0].equals("HERRAJE") ? (" " + entry[2] + " " + entry[3]) : ""));
        }
        System.out.println();
    }
}
