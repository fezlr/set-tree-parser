package com.fezlr.set_tree_parser.lexer;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {


    @Test
    void shouldReturnLeftBraceToken_whenInputIsSingleBrace() {
        Lexer lexer = new Lexer("{");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.LEFT_BRACE, "{", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 1), list.get(1));
    }

    @Test
    void shouldReturnRightBraceToken_whenInputIsSingleBrace() {
        Lexer lexer = new Lexer("}");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.RIGHT_BRACE, "}", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 1), list.get(1));
    }

    @Test
    void shouldReturnCommaToken_whenInputIsSingleComma() {
        Lexer lexer = new Lexer(",");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.COMMA, ",", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 1), list.get(1));
    }

    @Test
    void shouldReturnIdentifierToken_whenInputIsLetters() {
        Lexer lexer = new Lexer("abc");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.IDENTIFIER, "abc", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 3), list.get(1));
    }

    @Test
    void shouldReturnStringToken_whenInputIsWordInQuotes() {
        Lexer lexer = new Lexer("\"abc\"");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.STRING, "abc", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 5), list.get(1));
    }

    @Test
    void shouldReturnNumberToken_whenInputIsDigits() {
        Lexer lexer = new Lexer("123");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.NUMBER, "123", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 3), list.get(1));
    }

    @Test
    void shouldReturnIdentifierToken_whenInputContainsDigitsAfterLetters() {
        Lexer lexer = new Lexer("abc123");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.IDENTIFIER, "abc123", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 6), list.get(1));
    }

    @Test
    void shouldReturnNumberToken_whenInputContainsDecimalPoint() {
        Lexer lexer = new Lexer("1.23");
        List<Token> list = lexer.tokenize();

        assertEquals(2, list.size());
        assertEquals(new Token(TokenType.NUMBER, "1.23", 0), list.getFirst());
        assertEquals(new Token(TokenType.EOF, "", 4), list.get(1));
    }

    @Test
    void shouldIgnoreSpaces_whenInputHasSpacesBetweenTokens() {
        Lexer lexer = new Lexer("{ , }");
        List<Token> list = lexer.tokenize();

        assertEquals(4, list.size());
        assertEquals(new Token(TokenType.LEFT_BRACE, "{", 0), list.getFirst());
        assertEquals(new Token(TokenType.COMMA, ",", 2), list.get(1));
        assertEquals(new Token(TokenType.RIGHT_BRACE, "}", 4), list.get(2));
        assertEquals(new Token(TokenType.EOF, "", 5), list.get(3));
    }

    @Test
    void shouldReturnEOF_whenInputIsNothing() {
        Lexer lexer = new Lexer("");
        List<Token> list = lexer.tokenize();

        assertEquals(1, list.size());
        assertEquals(new Token(TokenType.EOF, "", 0), list.getFirst());
    }

    @Test
    void shouldReturnEOF_whenInputIsSpace() {
        Lexer lexer = new Lexer(" ");
        List<Token> list = lexer.tokenize();

        assertEquals(1, list.size());
        assertEquals(new Token(TokenType.EOF, "", 1), list.getFirst());

    }

    @Test
    void shouldThrowException_whenCharacterIsIllegal() {
        Lexer lexer = new Lexer("#");

        assertThrows(IllegalArgumentException.class, lexer::tokenize);
    }

    @Test
    void shouldThrowException_whenInputIsStringWithNoSecondQuote() {
        Lexer lexer = new Lexer("\"abc");

        assertThrows(IllegalArgumentException.class, lexer::tokenize);
    }

}
