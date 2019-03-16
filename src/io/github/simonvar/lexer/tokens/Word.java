package io.github.simonvar.lexer.tokens;

public class Word extends Token {
    public final String lexeme;

    public Word(int t, String s) {
        super(t);
        lexeme = s;
    }

    @Override
    public String toString() {
        return lexeme + " [WORD]";
    }
}
