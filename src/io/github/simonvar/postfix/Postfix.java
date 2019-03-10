package io.github.simonvar.postfix;

public class Postfix {

    public static void main(String[] args) {
        if (args.length < 1) return;

        Parser parser = new Parser(args[0]);
        parser.parse();
        System.out.println();
    }
}
