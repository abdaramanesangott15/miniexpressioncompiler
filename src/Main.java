import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String expression = getExpression(args);

        if (expression == null || expression.trim().isEmpty()) {
            System.out.println("Error: no expression provided.");
            System.out.println("Usage:");
            System.out.println("  java -cp src Main \"(3 + 2) * 5 - 1\"");
            return;
        }

        System.out.println("Expression: " + expression);

        try {
            Lexer lexer = new Lexer(expression);
            List<Token> tokens = lexer.tokenize();
            System.out.println("Tokens: " + formatTokens(tokens));

            Parser parser = new Parser(tokens);
            ASTNode root = parser.parse();
            System.out.println("Parse Result: Success");
            System.out.println("Expression Tree:");
            System.out.print(root.prettyPrint("", true));

            Evaluator evaluator = new Evaluator();
            double result = evaluator.evaluate(root);
            System.out.println("Evaluation Result: " + evaluator.formatResult(result));
        } catch (ParseException exception) {
            System.out.println("Parse Result: Failure");
            System.out.println(exception.getMessage());
        }
    }

    private static String getExpression(String[] args) {
        if (args.length > 0) {
            return String.join(" ", args);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an expression: ");
        String expression = scanner.nextLine();
        scanner.close();
        return expression;
    }

    private static String formatTokens(List<Token> tokens) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        boolean first = true;
        for (Token token : tokens) {
            if (token.getType() == TokenType.EOF) {
                continue;
            }
            if (!first) {
                builder.append(", ");
            }
            builder.append(token.getLexeme());
            first = false;
        }

        builder.append("]");
        return builder.toString();
    }
}
