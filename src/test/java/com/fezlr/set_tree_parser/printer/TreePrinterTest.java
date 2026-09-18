package com.fezlr.set_tree_parser.printer;

import com.fezlr.set_tree_parser.lexer.Lexer;
import com.fezlr.set_tree_parser.node.Node;
import com.fezlr.set_tree_parser.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreePrinterTest {

    @Test
    void shouldRenderNestedTree_whenInput() {
        Node node = new Parser(new Lexer("{a, {b, c}, d}").tokenize()).parse();

        String expectedResult = """
                Set
                ├── a
                ├── Set
                │   ├── b
                │   └── c
                └── d
                """;

        assertEquals(expectedResult, new TreePrinter().render(node));
    }
}
