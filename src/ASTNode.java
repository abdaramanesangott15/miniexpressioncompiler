public abstract class ASTNode {
    public abstract double evaluate() throws ParseException;
    public abstract String prettyPrint(String prefix, boolean isLeft);

    protected String formatNumber(double value) {
        if (value == (long) value) {
            return Long.toString((long) value);
        }
        return Double.toString(value);
    }
}
