import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int current;

    public Lexer(String input) {
        this.input = input;
        this.current = 0;
    }

    public List<Token> tokenize() throws ParseException {
        List<Token> tokens = new ArrayList<>();

        while (!isAtEnd()) {
            char c = input.charAt(current);

            if (Character.isWhitespace(c)) {
                current++;
                continue;
            }

            int position = current;

            if (Character.isDigit(c)) {
                tokens.add(readNumber());
                continue;
            }

            switch (c) {
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+", position));
                    current++;
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-", position));
                    current++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*", position));
                    current++;
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIVIDE, "/", position));
                    current++;
                    break;
                case '(':
                    tokens.add(new Token(TokenType.LPAREN, "(", position));
                    current++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RPAREN, ")", position));
                    current++;
                    break;
                default:
                    throw new ParseException("Lexical error: unexpected character '" + c + "' at position " + position + ".");
            }
        }

        tokens.add(new Token(TokenType.EOF, "EOF", input.length()));
        return tokens;
    }

    private Token readNumber() {
        int start = current;
        boolean hasDecimalPoint = false;

        while (!isAtEnd()) {
            char c = input.charAt(current);
            if (Character.isDigit(c)) {
                current++;
            } else if (c == '.' && !hasDecimalPoint) {
                hasDecimalPoint = true;
                current++;
            } else {
                break;
            }
        }

        return new Token(TokenType.NUMBER, input.substring(start, current), start);
    }

    private boolean isAtEnd() {
        return current >= input.length();
    }
}
