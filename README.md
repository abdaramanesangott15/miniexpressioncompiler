# Mini Expression Compiler: From Expression to Evaluation

## Student Name
Add your name here before submitting.

## Project Summary
This Java project simulates key compiler phases for arithmetic expressions. It takes an input expression, tokenizes it, parses it using recursive descent parsing, builds an abstract syntax tree (AST), and evaluates the final result.

## Features
- Lexical analysis (tokenization)
- Recursive descent parsing
- AST generation
- Expression evaluation
- Trace output for tokens, parse result, expression tree, and evaluation result
- Support for unary operators such as `-3` and `+4`
- Clear error messages for invalid expressions

## Supported Input
The program supports:
- Numbers
- Operators: `+`, `-`, `*`, `/`
- Parentheses: `(` and `)`
- Unary plus and minus

Examples:
- `3 + 4 * 2`
- `(1 + 2) * (3 + 4)`
- `-3 + 5`
- `((3))`

## File Structure
```text
MiniExpressionCompiler/
├── src/
│   ├── Main.java
│   ├── Token.java
│   ├── TokenType.java
│   ├── Lexer.java
│   ├── Parser.java
│   ├── ParseException.java
│   ├── ASTNode.java
│   ├── NumberNode.java
│   ├── BinaryOpNode.java
│   ├── UnaryOpNode.java
│   └── Evaluator.java
└── README.md
```

## Setup Instructions
1. Make sure Java is installed.
2. Open a terminal in the project folder.
3. Compile the program:

```bash
javac src/*.java
```

## How to Run
### Option 1: Run with a command-line expression
```bash
java -cp src Main "(3 + 2) * 5 - 1"
```

### Option 2: Run and type the expression manually
```bash
java -cp src Main
```
Then enter an expression when prompted.

## Example Inputs and Outputs
### Valid input
```bash
java -cp src Main "3 + 4 * 2"
```
Example output:
```text
Expression: 3 + 4 * 2
Tokens: [3, +, 4, *, 2]
Parse Result: Success
Expression Tree:
+
|   3
    *
    |   4
        2
Evaluation Result: 11
```

### Input with parentheses
```bash
java -cp src Main "(1 + 2) * (3 + 4)"
```

### Input with unary operator
```bash
java -cp src Main "-3 + 5"
```

### Invalid input
```bash
java -cp src Main "3 + * 5"
```
Example output:
```text
Expression: 3 + * 5
Tokens: [3, +, *, 5]
Parse Result: Failure
Syntax error: unexpected token '*' at position 4.
```

## Design Notes
- `Lexer` converts the input string into tokens.
- `Parser` checks grammar rules and builds the AST.
- `ASTNode` and its subclasses represent the expression tree.
- `Evaluator` traverses the AST to compute the final result.
- `ParseException` is used for parser and evaluation errors.

## Grammar Used
```text
Expression -> Term ((+ | -) Term)*
Term       -> Factor ((* | /) Factor)*
Factor     -> (+ | -) Factor | NUMBER | '(' Expression ')'
```

## Suggested Demo Video Checklist
In the 1–3 minute video, show:
1. A valid expression being tokenized, parsed, and evaluated.
2. A parenthesized expression building the correct tree.
3. A unary expression such as `-3 + 5`.
4. An invalid expression showing a clear error message.

## Submission Notes
- Upload the full Java project to GitHub.
- Include this README.md.
- Record and upload a 1–3 minute video demonstrating the program.
