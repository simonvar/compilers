package io.github.simonvar.lexer;

import java.io.IOException;

public class LexerTest {

    public static void main(String[] args) throws IOException {
        Lexer l = new Lexer();
        var res = l.scan();
        var res2 = l.scan();
        System.out.println(res);
        System.out.println(res2);
    }

}
