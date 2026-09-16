package com.fezlr.set_tree_parser.lexer;

public record Token(

        TokenType tokenType,

        String lexeme,

        int position

) {
}
