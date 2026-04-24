package compiler;

public class UnaryOpNode extends Node {
    public Token operator;
    public Node expression;

    public UnaryOpNode(Token operator, Node expression) {
        this.operator = operator;
        this.expression = expression;
    }
}
