package io.github.simonvar.lexer;

import io.github.simonvar.lexer.tokens.*;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {

    public int line = 1;
    private char peek = ' ';
    private Hashtable<String, Word> words = new Hashtable<>();

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
    }

    public Token scan() throws IOException {

        for (;; readch()) {

            if (peek == ' ' || peek == '\t' || peek == '\n') continue;

            if (peek == '/') {
                peek = (char)System.in.read();

                if (peek == '/') {
                    skipWhileNot('\n');
                } else if (peek == '*') {
                    inlineCommentProceed();
                }

            } else {
                break;
            }
        }


        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            } while (Character.isDigit(peek));

            if (peek != '.') return new Num(v);

            return realProceed(v);
        }

        if (peek == '.') {
            return realProceed(0);
        }

        if (Character.isLetter(peek)) {
            StringBuilder b = new StringBuilder();
            do {
                b.append(peek);
                readch();
            } while (Character.isLetterOrDigit(peek));

            String s = b.toString();
            Word w = words.get(s);
            if (w != null) return w;

            w = new Word(Tag.ID, s);
            words.put(s, w);
            return w;
        }

        throw new Error("syntax error");
    }

    private Real realProceed(int natural) throws IOException {
        float x = natural;
        float d = 10;
        for (;;) {
            readch();
            if (!Character.isDigit(peek)) break;
            x = x + Character.digit(peek, 10) / d;
            d = d * 10;
        }
        return new Real(x);
    }

    private void inlineCommentProceed() throws IOException {
        skipWhileNot('*');
        if (!readch('/')) inlineCommentProceed();
    }

    private void skipWhileNot(char c) throws IOException {
        do {
            peek = (char) System.in.read();
        } while (peek != c);
    }

    private void readch() throws IOException {
        peek = (char) System.in.read();
        if (peek == '\n') line = line + 1;
    }

    private boolean readch(char c) throws IOException {
        readch();
        return peek == c;
    }

}
