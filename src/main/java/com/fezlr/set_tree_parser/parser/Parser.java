package com.fezlr.set_tree_parser.parser;

import com.fezlr.set_tree_parser.lexer.Token;
import com.fezlr.set_tree_parser.lexer.TokenType;
import com.fezlr.set_tree_parser.node.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    List<Token> tokens;
    int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private boolean isAtEnd() {
        return pos >= tokens.size() || peek().tokenType() == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(pos);
    }

   private Token advance() {
        Token currentToken = tokens.get(pos);
        pos++;
        return currentToken;
   }

   public Node parse() {
        Node result = parseSet();

        if (!isAtEnd()) {
            throw new IllegalArgumentException("Unexpected token=" + peek() + " at position " + pos);
        }

        return result;
   }

   public Node parseElement() {
        if (peek().tokenType() == TokenType.LEFT_BRACE) {
            return parseSet();
        }
        else if (peek().tokenType() == TokenType.NUMBER) {
            Node numberNode = new NumberNode(peek().lexeme());
            advance();
            return numberNode;
        }
        else if (peek().tokenType() == TokenType.IDENTIFIER) {
            Node identifierNode = new IdentifierNode(peek().lexeme());
            advance();
            return identifierNode;
        }
        else if (peek().tokenType() == TokenType.STRING) {
            Node stringNode = new StringNode(peek().lexeme());
            advance();
            return stringNode;
        } else {
            throw new IllegalArgumentException("Not a legal token=" + peek() + " at position " + pos);
        }
   }

   public Node parseSet() {
        if (peek().tokenType() != TokenType.LEFT_BRACE) {
            throw new IllegalArgumentException("Expected '{' but found " + peek() + " at position " + pos);
        } else {
            advance();
        }

        List<Node> nodes = new ArrayList<>();

        if (peek().tokenType() != TokenType.RIGHT_BRACE) {
            nodes.add(parseElement());

            while (peek().tokenType() == TokenType.COMMA) {
                advance();
                nodes.add(parseElement());
            }
        }

        if (peek().tokenType() != TokenType.RIGHT_BRACE) {
            throw new IllegalArgumentException("Expected '}' but found " + peek() + " at position " + pos);
        } else {
            advance();
        }

        return new SetNode(nodes);
   }
}
