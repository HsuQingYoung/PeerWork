import java.util.Stack;

public class GetResultFromArray {
    public static Fraction calculateStringArray(String[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return new Fraction(0, 1); // 返回默认分数 0/1
        }

        Stack<Fraction> numStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (String token : inputArray) {
            if (isFraction(token)) {
                numStack.push(new Fraction(token));
            } else if (token.equals("(")) {
                operatorStack.push('(');
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    if (performOperation(numStack, operatorStack)) {
                        return null;
                    }
                }
                operatorStack.pop(); // Pop the opening parenthesis
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && hasPrecedence(token.charAt(0), operatorStack.peek())) {
                    if (performOperation(numStack, operatorStack)) {
                        return null;
                    }
                }
                operatorStack.push(token.charAt(0));
            }
        }

        while (!operatorStack.isEmpty()) {
            if (performOperation(numStack, operatorStack)) {
                return null;
            }
        }

        return numStack.pop();
    }

    private static boolean isFraction(String str) {
        str = str.trim();
        int indexOfApostrophe = str.indexOf("'");
        int indexOfSlash = str.indexOf("/");

        if (indexOfApostrophe != -1) {
            if (indexOfSlash != -1 && indexOfApostrophe < indexOfSlash && indexOfApostrophe > 0 && indexOfApostrophe < str.length() - 1) {
                return true;
            }
        } else if (indexOfSlash != -1 && indexOfSlash > 0 && indexOfSlash < str.length() - 1) {
            return true;
        } else {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return false;
    }

    private static boolean performOperation(Stack<Fraction> operandStack, Stack<Character> operatorStack) {
        char operator = operatorStack.pop();
        Fraction right = operandStack.pop();
        Fraction left = operandStack.pop();
        Fraction result = calculate(left, right, operator);
        if (result == null) {
            return true;
        }
        operandStack.push(result);
        return false;
    }

    private static Fraction calculate(Fraction left, Fraction right, char operator) {
        if (operator == '+') {
            return left.add(right);
        } else if (operator == '-') {
            return left.sub(right);
        } else if (operator == '*') {
            return left.mul(right);
        } else if (operator == '÷') {
            if (right.numerator == 0) {
                return null;
            }
            return left.div(right);
        }
        return null;
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("÷");
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op1 == '*' || op1 == '÷') && (op2 == '+' || op2 == '-');
    }
}
