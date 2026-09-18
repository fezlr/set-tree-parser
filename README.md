# set-tree-parser

## Description
This project parses a string that represents a mathematical set.

It builds a tree representation of the set.

It also calculates additional statistics, including total elements, subsets, max depth, numbers, identifiers, sets.

The supported element types are numbers, identifiers, strings, nested sets.

## Stack
- Java 25
- Maven
- JUnit Jupiter 6.1.3

## Run

Tests:
```bash
mvn test
```
Compile:
```bash
mvn compile exec:java -Dexec.mainClass="com.fezlr.Main"
```

## Examples

`{a, {b, c}, d}`:
```
Set
├── a
├── Set
│   ├── b
│   └── c
└── d

Total elements: 4
Subsets: 1
Max depth: 2
Numbers: 0
Identifiers: 4
Sets: 2
```

`{{1, 2}, {3, {4, 5}}, 6}`:
```
Set
├── Set
│   ├── 1
│   └── 2
├── Set
│   ├── 3
│   └── Set
│       ├── 4
│       └── 5
└── 6

Total elements: 6
Subsets: 3
Max depth: 3
Numbers: 6
Identifiers: 0
Sets: 4
```

## Technical approach

`String -> Lexer -> Parser -> TreePrinter -> Output`

Directories:
- **`lexer`** - make tokens from a string. Contains `Lexer`, `Token`, `TokenType`.
- **`node`** - a tree model. Contains `Node` (a marker interface). `SetNode` , `NumberNode`, `IdentifierNode`, `StringNode` implement it.
- **`parser`** - a recursive parser building `Node` from tokens. Contains only Parser class.
- **`printer`** - renders a tree. Contains only TreePrinter.
- **`stats`** - stats of the finished tree. Prints total elements, subset count, max depth count, number count, identifier count, string count, set count.
