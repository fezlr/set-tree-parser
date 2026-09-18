package com.fezlr;

import com.fezlr.set_tree_parser.lexer.Lexer;
import com.fezlr.set_tree_parser.lexer.Token;
import com.fezlr.set_tree_parser.node.Node;
import com.fezlr.set_tree_parser.parser.Parser;
import com.fezlr.set_tree_parser.printer.TreePrinter;
import com.fezlr.set_tree_parser.stats.Stats;
import com.fezlr.set_tree_parser.stats.StatsAssemble;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        run("{a, {b, c}, d}");
        run("{{1, 2}, {3, {4, 5}}, 6}");
    }

    private static void run(String input) {
        try {

            List<Token> list = new Lexer(input).tokenize();
            Node node = new Parser(list).parse();

            System.out.println(new TreePrinter().render(node));

            Stats stats = new StatsAssemble().assemble(node);
            System.out.println("Total elements: " + stats.totalElements());
            System.out.println("Subsets: " + stats.subsets());
            System.out.println("Max depth: " + stats.maxDepth());
            System.out.println("Numbers: " + stats.numberCount());
            System.out.println("Identifiers: " + stats.identifierCount());
            System.out.println("Sets: " + stats.setCount());

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
    }
}
