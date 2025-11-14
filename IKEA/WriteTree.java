import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTree {
    public static void main(String[] args) throws Exception {
        if (args.length>0 && args[0].length()>3 && args[0].substring(args[0].length()-4).equals(".txt")){
            System.out.println("Reading from file: " + args[0]);
            CharStream cs = CharStreams.fromFileName(args[0]);
            IKEALexer lexer = new IKEALexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            IKEAParser parser = new IKEAParser(tokens);

            ParseTree tree = parser.prog();

            // writeToFile(tree.toStringTree(parser));
            System.out.println(tree.toStringTree(parser));
            return;
        } else{
            String input = args.length > 0 ? args[0] : "\"a b\", 1\n";
            System.out.println("Input: " + input);
            CharStream cs = CharStreams.fromString(input);
            IKEALexer lexer = new IKEALexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            IKEAParser parser = new IKEAParser(tokens);

            ParseTree tree = parser.prog();

            writeToFile(tree.toStringTree(parser));
            // System.out.println(tree.toStringTree(parser));
        }
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
            boolean outOfLevel = false;
            for (int i = 0; i < input.length(); i++){
                crntChar = input.charAt(i);
                switch (crntChar){
                    case ' ':
                        if (!inString && !crntWord.equals("")){
                            if (crntWord.charAt(0) == '('){
                                crntWord = crntWord.substring(1);
                                write(crntWord, writer, depth, true, outOfLevel);
                                depth++;
                            } else{
                                write(crntWord, writer, depth, false, outOfLevel);
                            }
                            crntWord = "";
                            outOfLevel = false;
                        } else if (inString){
                            crntWord += crntChar;
                        }
                        break;
                    
                    case ')':
                        if (!inString){
                            if (!crntWord.equals("")){
                                write(crntWord, writer, depth, false, outOfLevel);
                                crntWord = "";
                                outOfLevel = true;
                            }
                            depth--;
                        } else{
                            crntWord += crntChar;
                        }
                        break;

                    case '"':
                        crntWord += crntChar;
                        if (inString){
                            write(crntWord, writer, depth, false, outOfLevel);
                            crntWord = "";
                            outOfLevel = false;
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

    public static void write(String input, FileWriter writer, int depth, boolean newDepth, boolean outOfLevel) throws IOException{
        writer.write((newDepth||outOfLevel?"\n" + "  ".repeat(depth):"") + input + (newDepth? ":  ": "  "));
        System.out.print((newDepth||outOfLevel?"\n" + "  ".repeat(depth):"") + input + (newDepth? ":  ": "  "));
    }
}
