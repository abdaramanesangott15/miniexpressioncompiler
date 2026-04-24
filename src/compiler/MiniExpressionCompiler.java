package compiler;

import java.util.List;
import java.util.Scanner;

public class MiniExpressionCompiler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Expression: ");
        String input = scanner.nextLine();

        try {
            Tokenizer tokenizer = new Tokenizer(input);
            List<Token> tokens = tokenizer.tokenize();

            System.out.println("Tokens: " + tokensWithoutEOF(tokens));

            Parser parser = new Parser(tokens);
            Node tree = parser.parse();

            System.out.println("Parse: SUCCESS");
            System.out.println("Expression Tree:");
            printTree(tree, "", true);

            Evaluator evaluator = new Evaluator();
            int result = evaluator.evaluate(tree);

            System.out.println("Evaluation Result: " + result);
        } catch (Exception e) {
            System.out.println("Parse: FAILED");
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static String tokensWithoutEOF(List<Token> tokens) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).type == Token.Type.EOF) {
                continue;
            }

            result.append(tokens.get(i).value);

            if (i < tokens.size() - 2) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    private static void printTree(Node node, String indent, boolean last) {
        if (node instanceof NumberNode) {
            System.out.println(indent + (last ? "└── " : "├── ") + ((NumberNode) node).value);
            return;
        }

        if (node instanceof UnaryOpNode) {
            UnaryOpNode unary = (UnaryOpNode) node;
            System.out.println(indent + (last ? "└── " : "├── ") + unary.operator.value);
            printTree(unary.expression, indent + "    ", true);
            return;
        }

        if (node instanceof BinaryOpNode) {
            BinaryOpNode binary = (BinaryOpNode) node;
            System.out.println(indent + (last ? "└── " : "├── ") + binary.operator.value);
            printTree(binary.left, indent + "    ", false);
            printTree(binary.right, indent + "    ", true);
        }
    }
}
