public class BinaryOpNode extends ASTNode {
    private final String operator;
    private final ASTNode left;
    private final ASTNode right;

    public BinaryOpNode(String operator, ASTNode left, ASTNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public double evaluate() throws ParseException {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();

        switch (operator) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ParseException("Evaluation error: division by zero.");
                }
                return leftValue / rightValue;
            default:
                throw new ParseException("Unknown binary operator: " + operator);
        }
    }

    @Override
    public String prettyPrint(String prefix, boolean isLeft) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(operator).append("\n");
        builder.append(left.prettyPrint(prefix + (isLeft ? "|   " : "    "), true));
        builder.append(right.prettyPrint(prefix + (isLeft ? "|   " : "    "), false));
        return builder.toString();
    }
}
