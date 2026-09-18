package com.fezlr.set_tree_parser.parser;

import com.fezlr.set_tree_parser.lexer.Lexer;
import com.fezlr.set_tree_parser.node.IdentifierNode;
import com.fezlr.set_tree_parser.node.Node;
import com.fezlr.set_tree_parser.node.NumberNode;
import com.fezlr.set_tree_parser.node.SetNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    private Node parse(String input) {
        return new Parser(new Lexer(input).tokenize()).parse();
    }

    @Test
    void shouldReturnSetWithNumbers_whenInputIsSimpleList() {
        Node result = parse("{1, 2, 3}");

        Node expectedResult = new SetNode(List.of(
                new NumberNode("1"),
                new NumberNode("2"),
                new NumberNode("3")
        ));

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnEmptySet_whenInputIsEmptyBraces() {
        Node result = parse("{}");

        assertEquals(new SetNode(List.of()), result);
    }

    @Test
    void shouldReturnNestedSet_whenInputContainsSets() {
        Node result = parse("{a, {b, c}, d}");
        Node expectedResult = new SetNode(List.of(
                new IdentifierNode("a"),
                new SetNode(List.of(new IdentifierNode("b"), new IdentifierNode("c"))),
                new IdentifierNode("d")
        ));

        assertEquals(expectedResult, result);
    }

    @Test
    void shouldThrowException_whenLastBraceIsMissing() {
        assertThrows(IllegalArgumentException.class, () -> parse("{a, b"));
    }

    @Test
    void shouldThrowException_whenCommaIsTrail() {
        assertThrows(IllegalArgumentException.class, () -> parse("{a, b,}"));
    }

    @Test
    void shouldThrowException_whenOpenBraceIsMissed() {
        assertThrows(IllegalArgumentException.class, () -> parse("a, b}"));
    }

    @Test
    void shouldThrowException_whenBracesUnbalanced() {
        assertThrows(IllegalArgumentException.class, () -> parse("{a, {b, c}"));
    }

    @Test
    void shouldThrowException_whenWordsAfterClosedBrace() {
        assertThrows(IllegalArgumentException.class, () -> parse("{a, b} cde"));
    }

}
