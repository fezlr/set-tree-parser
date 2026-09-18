package com.fezlr.set_tree_parser.printer;

import com.fezlr.set_tree_parser.node.*;

import java.util.List;

public class TreePrinter {

    public String render(Node node) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(define(node)).append("\n");

        if (node instanceof SetNode(List<Node> nodes)) {
            renderNodes(nodes, "", stringBuilder);
        }
        return stringBuilder.toString();
    }

    public void renderNodes(List<Node> nodes, String name, StringBuilder stringBuilder) {
        int lastIndex = nodes.size() - 1;

        for (int i = 0; i <= lastIndex; i++) {
            Node node = nodes.get(i);
            boolean isLast = ( i == lastIndex);

            String text = isLast ? "└── " : "├── ";
            stringBuilder.append(name);
            stringBuilder.append(text);
            stringBuilder.append(define(node));
            stringBuilder.append("\n");

            if (node instanceof SetNode(List<Node> setNode)) {
                String label = isLast ? "    " : "│   ";
                String nodeName = name + label;
                renderNodes(setNode, nodeName, stringBuilder);
            }

        }

    }


    public String define(Node node) {
        return switch (node) {
            case SetNode _ -> "Set";
            case NumberNode numberNode -> numberNode.value();
            case IdentifierNode identifierNode -> identifierNode.value();
            case StringNode stringNode -> stringNode.value();
            default -> throw new IllegalArgumentException("Unknown type of node=" + node);
        };
    }

    public void print(Node node) {
        System.out.println(render(node));
    }
}
