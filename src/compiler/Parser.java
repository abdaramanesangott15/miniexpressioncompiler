package compiler;

import java.util.List;

public class Parser {
    private List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    private Token currentToken() {
        return tokens.get(pos);
    }

    private void eat(Token.Type expectedType) {
        if (currentToken().type == expectedType) {
            pos++;
        } else {
            throw new RuntimeException(
                "Unexpected token '" + currentToken().value +
                "' at position " + currentToken().position
            );
        }
    }

    public Node parse() {
        Node tree = expression();

        if (currentToken().type != Token.Type.EOF) {
            throw new RuntimeException(
                "Unexpected token '" + currentToken().value +
                "' at position " + currentToken().position
            );
        }

        return tree;
    }

    // E -> T ((+ | -) T)*
    private Node expression() {
        Node node = term();

        while (currentToken().type == Token.Type.PLUS || currentToken().type == Token.Type.MINUS) {
            Token operator = currentToken();
            eat(operator.type);
            node = new BinaryOpNode(node, operator, term());
        }

        return node;
    }

    // T -> F ((* | /) F)*
    private Node term() {
        Node node = factor();

        while (currentToken().type == Token.Type.MUL || currentToken().type == Token.Type.DIV) {
            Token operator = currentToken();
            eat(operator.type);
            node = new BinaryOpNode(node, operator, factor());
        }

        return node;
    }

    // F -> number | (E) | -F | +F
    private Node factor() {
        Token token = currentToken();

        if (token.type == Token.Type.PLUS) {
            eat(Token.Type.PLUS);
            return new UnaryOpNode(token, factor());
        }

        if (token.type == Token.Type.MINUS) {
            eat(Token.Type.MINUS);
            return new UnaryOpNode(token, factor());
        }

        if (token.type == Token.Type.NUMBER) {
            eat(Token.Type.NUMBER);
            return new NumberNode(Integer.parseInt(token.value));
        }

        if (token.type == Token.Type.LPAREN) {
            eat(Token.Type.LPAREN);
            Node node = expression();
            eat(Token.Type.RPAREN);
            return node;
        }

        throw new RuntimeException(
            "Unexpected token '" + token.value + "' at position " + token.position
        );
    }
}
