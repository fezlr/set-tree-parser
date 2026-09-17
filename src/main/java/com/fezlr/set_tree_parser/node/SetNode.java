package com.fezlr.set_tree_parser.node;

import java.util.List;

public record SetNode(

        List<Node> nodes

) implements Node {
}
