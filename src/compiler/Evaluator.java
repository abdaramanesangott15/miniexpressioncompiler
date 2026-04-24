package compiler;

public class Evaluator {
    public int evaluate(Node node) {
        if (node instanceof NumberNode) {
            return ((NumberNode) node).value;
        }

        if (node instanceof UnaryOpNode) {
            UnaryOpNode unary = (UnaryOpNode) node;
            int value = evaluate(unary.expression);

            if (unary.operator.type == Token.Type.MINUS) {
                return -value;
            }

            return value;
        }

        if (node instanceof BinaryOpNode) {
            BinaryOpNode binary = (BinaryOpNode) node;

            int leftValue = evaluate(binary.left);
            int rightValue = evaluate(binary.right);

            switch (binary.operator.type) {
                case PLUS:
                    return leftValue + rightValue;
                case MINUS:
                    return leftValue - rightValue;
                case MUL:
                    return leftValue * rightValue;
                case DIV:
                    return leftValue / rightValue;
                default:
                    throw new RuntimeException("Unknown operator");
            }
        }

        throw new RuntimeException("Invalid expression tree");
    }
}
