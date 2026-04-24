public class NumberNode extends ASTNode {
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String prettyPrint(String prefix, boolean isLeft) {
        return prefix + formatNumber(value) + "\n";
    }
}
