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
            System.out.println("Parse Tree:");
            printPictureTree(tree);

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
        boolean first = true;

        for (Token token : tokens) {
            if (token.type == Token.Type.EOF) {
                continue;
            }

            if (!first) {
                result.append(", ");
            }

            result.append(token.value);
            first = false;
        }

        result.append("]");
        return result.toString();
    }

    private static void printPictureTree(Node node) {
        if (node instanceof BinaryOpNode) {
            BinaryOpNode binary = (BinaryOpNode) node;

            System.out.println("        " + getNodeLabel(binary));
            System.out.println("       / \\");
            printPictureTreeHelper(binary.left, "    ");
            printPictureTreeHelper(binary.right, "          ");
        } else {
            System.out.println("        " + getNodeLabel(node));
        }
    }

    private static void printPictureTreeHelper(Node node, String indent) {
        if (node instanceof BinaryOpNode) {
            BinaryOpNode binary = (BinaryOpNode) node;

            System.out.println(indent + getNodeLabel(binary) + "   " + getNodeLabel(binary.right));
            System.out.println(indent + "/ \\");
            printPictureTreeHelper(binary.left, indent + "  ");
        } else if (node instanceof UnaryOpNode) {
            UnaryOpNode unary = (UnaryOpNode) node;
            System.out.println(indent + getNodeLabel(unary));
            System.out.println(indent + "|");
            printPictureTreeHelper(unary.expression, indent + "  ");
        } else {
            System.out.println(indent + getNodeLabel(node));
        }
    }

    private static String getNodeLabel(Node node) {
        if (node instanceof NumberNode) {
            return Integer.toString(((NumberNode) node).value);
        }

        if (node instanceof BinaryOpNode) {
            return ((BinaryOpNode) node).operator.value;
        }

        if (node instanceof UnaryOpNode) {
            return ((UnaryOpNode) node).operator.value;
        }

        return "?";
    }
}
