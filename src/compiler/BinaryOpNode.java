package compiler;

public class BinaryOpNode extends Node {
    public Node left;
    public Token operator;
    public Node right;

    public BinaryOpNode(Node left, Token operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
