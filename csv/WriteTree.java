import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTree {
    public static void main(String[] args) throws Exception {
        String input = args.length > 0 ? args[0] : "\"a b\", 1\n";
        System.out.println("Input: " + input);
        CharStream cs = CharStreams.fromString(input);
        csvLexer lexer = new csvLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        csvParser parser = new csvParser(tokens);

        ParseTree tree = parser.prog();

        writeToFile(tree.toStringTree(parser));
        // System.out.println(tree.toStringTree(parser));
    }

    public static boolean writeToFile(String input){
        try{
            File f = new File("tree.txt");
            f.createNewFile();
            FileWriter writer = new FileWriter("tree.txt");

            String crntWord = "";
            char crntChar;
            int depth = 0;
            boolean inString = false;
            for (int i = 0; i < input.length(); i++){
                crntChar = input.charAt(i);
                switch (crntChar){
                    case ' ':
                        if (!inString && !crntWord.equals("")){
                            if (crntWord.charAt(0) == '('){
                                crntWord = crntWord.substring(1);
                                write(crntWord, writer, depth, true);
                                depth++;
                            } else{
                                write(crntWord, writer, depth, false);
                            }
                            crntWord = "";
                        } else if (inString){
                            crntWord += crntChar;
                        }
                        break;
                    
                    case ')':
                        if (!inString){
                            if (!crntWord.equals("")){
                                write(crntWord, writer, depth, false);
                                crntWord = "";
                            }
                            depth--;
                        } else{
                            crntWord += crntChar;
                        }
                        break;

                    case '"':
                        if (crntWord.length()>0 && crntWord.charAt(crntWord.length()-1) == '\\'){
                            crntWord = crntWord.substring(0, crntWord.length()-1) + crntChar;
                            break;
                        }

                        crntWord += crntChar;
                        if (inString){
                            write(crntWord, writer, depth, false);
                            crntWord = "";
                        }
                        inString = !inString;
                        break;
                
                    default:
                        crntWord += crntChar;
                        break;
                }
            }

            writer.close();
        } catch (IOException ioe){
            return false;
        }

        return true;
    }

    public static void write(String input, FileWriter writer, int depth, boolean newDepth) throws IOException{
        writer.write("  ".repeat(depth) + input + (newDepth? ":\n": "\n"));
        System.out.print("  ".repeat(depth) + input + (newDepth? ":\n": "\n"));
    }
}
