package com.example.stack;

/**
 * 逆波兰表达式求值问题（中缀表达式）
 */
public class ReversePolishNotationTest {
    public static void main(String[] args) {
        String[] notation = {"3", "17", "15", "-", "*", "18", "6", "/", "+"};
        int result = caculate(notation);
        System.out.println("逆波兰表达式的结果为：" + result);
    }

    /**
     * @param notation 逆波兰表达式的数组表示方式
     * @return 逆波兰表达式的计算结果
     */
    public static int caculate(String[] notation) {
        Stack<Integer> oprands = new Stack<>();
        for (int i = 0; i < notation.length; i++) {
            String curr = notation[i];
            Integer o1;
            Integer o2;
            Integer result;
            switch (curr) {
                case "+":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2+o1;
                    oprands.push(result);
                    break;
                case "-":
                    // 运算符 - ，从栈中弹出两个操作数，完成运 - 算，运算完的结果再压入栈中
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 - o1;
                    oprands.push(result);
                    break;
                case "*":
                    // 运算符 * ，从栈中弹出两个操作数，完成 * 运算，运算完的结果再压入栈中
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 * o1;
                    oprands.push(result);
                    break;
                case "/":
                    // 运算符 / ，从栈中弹出两个操作数，完成 / 运算，运算完的结果再压入栈中
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 / o1;
                    oprands.push(result);
                    break;
                default:
                    // 操作数，把该操作数放入到栈中；
                    oprands.push(Integer.parseInt(curr));
                    break;
            }
        }
        // 得到栈中最后一个元素，就是逆波兰表达式的结果
        return oprands.pop();
    }
}
