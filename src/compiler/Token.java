package compiler;

public class Token {
    public enum Type {
        NUMBER, PLUS, MINUS, MUL, DIV, LPAREN, RPAREN, EOF
    }

    public Type type;
    public String value;
    public int position;

    public Token(Type type, String value, int position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public String toString() {
        return value;
    }
}
