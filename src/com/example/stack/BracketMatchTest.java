package com.example.stack;

/**
 * 括号匹配问题
 */
public class BracketMatchTest {
    public static void main(String[] args) {
        String str = "上海(长安)()";
        boolean match = isMatch(str);
        System.out.println(str + "中的括号是否匹配：" + match);
    }

    /**
     * 判断 str 中的括号是否匹配
     *
     * @param str 括号组成的字符串
     * @return 如果匹配，返回true，如果不匹配，返回false
     */
    public static boolean isMatch(String str) {
        Stack<String> charStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            String currChar = str.charAt(i) + "";
            if (currChar.equals("(")){
                charStack.push(currChar);
            } else if (currChar.equals(")")) {
                // 如果为null证明没有匹配的左括号，如果不为null，则证明有匹配的左括号
                String pop = charStack.pop();
                if (pop==null){
                    return false;
                }
            }
        }
        // 判断栈中还有没有剩余的左括号，如果有，则证明括号不匹配
        return charStack.size() == 0;
    }
}
