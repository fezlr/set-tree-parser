package com.fezlr.set_tree_parser.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    private boolean isAtEnd() {
        return pos >= input.length();
    }

    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }

        return input.charAt(pos);
    }

    private char advance() {
        char currentChar = peek();
        pos++;
        return currentChar;
    }

    public List<Token> tokenize() {
        List<Token> list = new ArrayList<>();

        while (!isAtEnd()) {
            if (peek() == ' ') {
                advance();

            } else {
                switch(peek()) {
                    case '{' -> {
                        list.add(new Token(TokenType.LEFT_BRACE, "{", pos));
                        advance();
                    }
                    case '}' -> {
                        list.add(new Token(TokenType.RIGHT_BRACE, "}", pos));
                        advance();
                    }
                    case ',' -> {
                        list.add(new Token(TokenType.COMMA, ",", pos));
                        advance();
                    }
                    default -> {
                        if (Character.isLetter(peek())) {
                            int startPos = pos;
                            StringBuilder stringBuilder = new StringBuilder();

                            while(!isAtEnd() && (Character.isLetter(peek()) || Character.isDigit(peek()))) {
                                stringBuilder.append(advance());
                            }
                            list.add(new Token(TokenType.IDENTIFIER, stringBuilder.toString(), startPos));

                        }
                        else if (Character.isDigit(peek())) {
                            int startPos = pos;
                            StringBuilder stringBuilder = new StringBuilder();

                            while(!isAtEnd() && (Character.isDigit(peek()) || peek() == '.')) {
                                stringBuilder.append(advance());
                            }
                            list.add(new Token(TokenType.NUMBER, stringBuilder.toString(), startPos));

                        }
                        else if (peek() == '"') {
                            int startPos = pos;
                            StringBuilder stringBuilder = new StringBuilder();

                            // skip " and then while-loop
                            advance();

                            while(!isAtEnd() && peek() != '"') {
                                stringBuilder.append(advance());
                            }

                            // skip "
                            advance();

                            list.add(new Token(TokenType.STRING, stringBuilder.toString(), startPos));

                        }
                    }
                }
            }
        }

        list.add(new Token(TokenType.EOF, "", pos));
        return list;
    }
}
