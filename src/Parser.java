import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int current;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.current = 0;
    }

    public ASTNode parse() throws ParseException {
        ASTNode expression = expression();

        if (!isAtEnd()) {
            Token token = peek();
            throw new ParseException("Syntax error: unexpected token '" + token.getLexeme() + "' at position " + token.getPosition() + ".");
        }

        return expression;
    }

    private ASTNode expression() throws ParseException {
        ASTNode node = term();

        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            ASTNode right = term();
            node = new BinaryOpNode(operator.getLexeme(), node, right);
        }

        return node;
    }

    private ASTNode term() throws ParseException {
        ASTNode node = factor();

        while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            Token operator = previous();
            ASTNode right = factor();
            node = new BinaryOpNode(operator.getLexeme(), node, right);
        }

        return node;
    }

    private ASTNode factor() throws ParseException {
        if (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            ASTNode operand = factor();
            return new UnaryOpNode(operator.getLexeme(), operand);
        }

        if (match(TokenType.NUMBER)) {
            return new NumberNode(Double.parseDouble(previous().getLexeme()));
        }

        if (match(TokenType.LPAREN)) {
            Token leftParen = previous();
            if (check(TokenType.RPAREN)) {
                Token rightParen = peek();
                throw new ParseException("Syntax error: empty parentheses '()' are not allowed at positions " + leftParen.getPosition() + " and " + rightParen.getPosition() + ".");
            }

            ASTNode node = expression();
            consume(TokenType.RPAREN, "Syntax error: expected ')' to match '(' at position " + leftParen.getPosition() + ".");
            return node;
        }

        Token token = peek();
        if (token.getType() == TokenType.EOF) {
            throw new ParseException("Syntax error: unexpected end of input.");
        }

        throw new ParseException("Syntax error: unexpected token '" + token.getLexeme() + "' at position " + token.getPosition() + ".");
    }

    private Token consume(TokenType type, String message) throws ParseException {
        if (check(type)) {
            return advance();
        }
        throw new ParseException(message);
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) {
            return type == TokenType.EOF;
        }
        return peek().getType() == type;
    }

    private Token advance() {
        if (!isAtEnd()) {
            current++;
        }
        return previous();
    }

    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}
