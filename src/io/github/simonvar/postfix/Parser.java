package io.github.simonvar.postfix;

public class Parser {
    private String text;
    private int index = 0;

    private int lookahead;

    Parser(String text) {
        this.text = text;
        lookahead = next();
    }

    void parse() {
        expr();
    }

    private void expr() {
        term();
        while (true) {
            if (lookahead == '+') {
                match('+');
                term();
                System.out.write('+');
            } else if (lookahead == '-') {
                match('-');
                term();
                System.out.write('-');
            } else return;
        }
    }

    private void term() {
        if (Character.isDigit((char) lookahead)) {
            System.out.write((char) lookahead);
            match(lookahead);
        } else throw new Error("syntax error");
    }

    private void match(int t) {
        if (lookahead == t) lookahead = next();
        else throw new Error("syntax error");
    }

    private int next() {
        int lex = text.charAt(index);
        index++;
        return lex;
    }

}
