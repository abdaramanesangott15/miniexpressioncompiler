public class Evaluator {
    public double evaluate(ASTNode root) throws ParseException {
        return root.evaluate();
    }

    public String formatResult(double value) {
        if (value == (long) value) {
            return Long.toString((long) value);
        }
        return Double.toString(value);
    }
}
