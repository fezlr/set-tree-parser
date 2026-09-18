package com.fezlr.set_tree_parser.stats;

import com.fezlr.set_tree_parser.node.*;

public class StatsAssemble {
    private int totalElements = 0;
    private int subsets = 0;
    private int maxDepth = 0;
    private int numberCount = 0;
    private int identifierCount = 0;
    private int stringCount = 0;
    private int setCount = 0;

    public Stats assemble(Node node) {
        check(node, 1);

        return new Stats(
                totalElements,
                subsets,
                maxDepth,
                numberCount,
                identifierCount,
                stringCount,
                setCount
        );
    }

    private void check(Node node, int depth) {
        maxDepth = Math.max(maxDepth, depth);

        if (node instanceof SetNode(java.util.List<Node> nodes)) {
            setCount++;

            if (depth > 1) {
                subsets++;
            }

            for (Node n : nodes) {
                if (n instanceof SetNode) {
                    check(n, depth + 1);
                } else {
                    check(n, depth);
                }
            }
        }
        else if (node instanceof NumberNode) {
            numberCount++;
            totalElements++;
        }
        else if (node instanceof IdentifierNode) {
            identifierCount++;
            totalElements++;
        }
        else if (node instanceof StringNode) {
            stringCount++;
            totalElements++;
        }
    }

}
