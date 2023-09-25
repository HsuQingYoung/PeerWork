import java.util.Stack;
public class GetResultFromArray {
        public static Fraction calculateStringArray(String[] inputArray) {
            if (inputArray == null || inputArray.length == 0) {
                return new Fraction(0, 1); // 返回默认分数 0/1
            }

            Stack<Fraction> numStack = new Stack<>();
            Stack<String> opStack = new Stack<>();

            for (String token : inputArray) {
                if (token.equals("(")) {
                    opStack.push(token);
                } else if (token.equals(")")) {
                    while (!opStack.isEmpty() && !opStack.peek().equals("(")) {
                        evaluateTop(numStack, opStack);
                    }
                    opStack.pop();
                } else if (isOperator(token)) {
                    while (!opStack.isEmpty() && hasPrecedence(token, opStack.peek())) {
                        evaluateTop(numStack, opStack);
                    }
                    opStack.push(token);
                } else {
                    numStack.push(new Fraction(token));
                }
            }

            while (!opStack.isEmpty()) {
                evaluateTop(numStack, opStack);
            }

            if (numStack.size() == 1 && opStack.isEmpty()) {
                return numStack.pop();
            } else {
                throw new IllegalArgumentException("无效的输入数组");
            }
        }

        private static boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
        }

        private static boolean hasPrecedence(String op1, String op2) {
            return (op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"));
        }

        private static void evaluateTop(Stack<Fraction> numStack, Stack<String> opStack) {
            String operator = opStack.pop();
            Fraction operand2 = numStack.pop();
            Fraction operand1 = numStack.pop();

            if ("+".equals(operator)) {
                numStack.push(operand1.add(operand2));
            } else if ("-".equals(operator)) {
                numStack.push(operand1.sub(operand2));
            } else if ("*".equals(operator)) {
                numStack.push(operand1.mul(operand2));
            } else if ("/".equals(operator)) {
                numStack.push(operand1.div(operand2));
            }
        }
    }



