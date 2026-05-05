package compiler;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private String text;
    private int pos;

    public Tokenizer(String text) {
        this.text = text;
        this.pos = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (pos < text.length()) {
            char current = text.charAt(pos);

            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            if (Character.isDigit(current)) {
                int start = pos;
                StringBuilder number = new StringBuilder();

                while (pos < text.length() && Character.isDigit(text.charAt(pos))) {
                    number.append(text.charAt(pos));
                    pos++;
                }

                tokens.add(new Token(Token.Type.NUMBER, number.toString(), start));
                continue;
            }

            switch (current) {
                case '+': tokens.add(new Token(Token.Type.PLUS, "+", pos)); break;
                case '-': tokens.add(new Token(Token.Type.MINUS, "-", pos)); break;
                case '*': tokens.add(new Token(Token.Type.MUL, "*", pos)); break;
                case '/': tokens.add(new Token(Token.Type.DIV, "/", pos)); break;
                case '(': tokens.add(new Token(Token.Type.LPAREN, "(", pos)); break;
                case ')': tokens.add(new Token(Token.Type.RPAREN, ")", pos)); break;
                default:
                    throw new RuntimeException("Invalid character '" + current + "' at position " + pos);
            }

            pos++;
        }

        tokens.add(new Token(Token.Type.EOF, "EOF", pos));
        return tokens;
    }
}
