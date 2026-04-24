public class UnaryOpNode extends ASTNode {
    private final String operator;
    private final ASTNode operand;

    public UnaryOpNode(String operator, ASTNode operand) {
        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public double evaluate() throws ParseException {
        double value = operand.evaluate();

        switch (operator) {
            case "+":
                return value;
            case "-":
                return -value;
            default:
                throw new ParseException("Unknown unary operator: " + operator);
        }
    }

    @Override
    public String prettyPrint(String prefix, boolean isLeft) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix).append(operator).append(" (unary)").append("\n");
        builder.append(operand.prettyPrint(prefix + (isLeft ? "|   " : "    "), true));
        return builder.toString();
    }
}
