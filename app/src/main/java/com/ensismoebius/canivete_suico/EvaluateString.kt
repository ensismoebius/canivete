package com.ensismoebius.canivete_suico

import java.util.*

class EvaluateString {

    /**
     * Evaluate given expression and returns the result
     *
     * @param expression
     * @return
     */
    fun evaluate(expression: String): String {

        // Ignores empty expressions
        if (expression.trim().isEmpty()) {
            return ""
        }

        // An array of tokens
        val tokens = expression.toCharArray()

        // Stack for numbers: 'values'
        val values: Stack<String> = Stack<String>()

        // Stack for Operators: 'ops'
        val ops: Stack<Char> = Stack<Char>()

        // Cycles over all tokens in expression
        var i = -1

        while (++i < tokens.size) {

            // Current token is a whitespace, skip it
            if (tokens[i] == ' ') {
                continue
            }

            // Current token is a number, push it to stack for numbers
            if (tokens[i] in '0'..'9' || Character.isAlphabetic(tokens[i].code)) {

                // There may be more than one digits in number
                val buffer = StringBuffer()
                while (i < tokens.size && (isPartOfANumber(tokens[i]) || isPartOfAVariable(tokens[i]))) {
                    buffer.append(tokens[i++])
                }

                // After number has been built push it into the numbers stack
                values.push(buffer.toString())

                // We must subtract 1 at the index because the next
                // interaction will add 1, doing this we can keep track
                // of the tokens
                i--

                // After build the number goto to next char/iteration
                continue
            }

            // Current token is an opening brace, push it to 'ops'
            if (tokens[i] == '(') {
                ops.push(tokens[i])
                continue
            }

            // Closing brace encountered, solve entire brace
            if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()))
                }
                ops.pop()
                continue
            }

            // Current token is an operator.
            if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '=') {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()))
                }

                // Push current token to 'ops'.
                ops.push(tokens[i])
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()) {
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()))
        }

        // Top of 'values' contains result, return it
        return values.pop()
    }

    private fun isPartOfAVariable(c: Char): Boolean {
        return Character.isAlphabetic(c.code)
    }

    /**
     * Verify if a char may be a part of a number
     *
     * @param c
     * @return true if is a part of a number, false if its not
     */
    private fun isPartOfANumber(c: Char): Boolean {
        if (c in '0'..'9') {
            return true
        }
        return c == '.'
    }

    /**
     * Returns true if 'op2' has higher or same precedence as 'op1', otherwise
     * returns false.
     *
     * @param op1
     * @param op2
     * @return
     */
    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '=') return false
        if (op2 == '(' || op2 == ')') return false
        return !((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
    }

    private fun isNumber(value: String?): Boolean {
        try {
            value!!.toDouble()
            return true
        } catch (e: Exception) {
        }
        return false
    }

    /**
     * A utility method to apply an operator 'op' on operands 'a' and 'b'. Return
     * the result
     *
     * @param operation
     * @param valueTwo
     * @param valueOne
     * @return
     */
    private fun applyOperation(operation: Char, valueTwo: String, valueOne: String): String {
        var firstNumber = 0.0
        var secondNumber = 0.0
        
        if (operation != '=') {
            if (isNumber(valueOne)) {
                firstNumber = valueOne.toDouble()
            }
        }
        if (isNumber(valueTwo)) {
            secondNumber = valueTwo.toDouble()
        }

        var result = 0.0
        when (operation) {
            '+' -> result = firstNumber + secondNumber
            '-' -> result = firstNumber - secondNumber
            '*' -> result = firstNumber * secondNumber
            '=' -> {
                result = 0.0
            }
            '/' -> {
                if (secondNumber == 0.0) {
                    throw UnsupportedOperationException("Cannot divide by zero")
                }
                result = firstNumber / secondNumber
            }
        }
        return result.toString()
    }
}
