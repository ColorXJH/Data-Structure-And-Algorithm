package com.master.chapter007;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName: StackExpressionTest
 * @Package: com.master.chapter007
 * @Description: 前缀，中缀，后缀表达式
 * @Datetime: 2021/6/1 11:59
 * @author: ColorXJH
 */
public class StackExpressionTest {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //(3+4)*5-6 ==>3 4 + 5 * 6 -
        //为了方便，逆波兰表达式数字和符号之间使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1：先将“3 4 + 5 * 6 -”放入到arraylist中
        //2:将arraylist传给一个方法，遍历arraylist配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        System.out.println(calculate(list));


        //将中缀表达式转换为后缀表示代码实现 1+（（2+3）*4）-5==》 1 2 3 + 4 * + 5 -
        //因为直接操作str不方便，因此先将“1+（（2+3）*4）-5”==》中缀表达式对应的list
        String expressiopn = "1+((2+3)*4)-5";
        List<String> zzlist=toInFixExpressionList(expressiopn);
        System.out.println(zzlist);
        //将中缀表达式对应的list转化为后缀表达式对应的list
        List<String> hzlist=parseToSuffixExpressionList(zzlist);
        System.out.println(hzlist);
        System.out.println("--------后缀表达式的结果----------");
        System.out.println(calculate(hzlist));

    }

    //
    public static List<String> parseToSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //Stack<String>s2=new Stack<String>();//存放中间结果的栈
        //因为s2这个栈在整个转换过程中没有pop操作。而且最后还需要逆序操作，比较麻烦，因此我们直接使用List代替s2，正常顺序输出即为逆波兰表达式
        List<String> s2 = new ArrayList<String>();
        //遍历ls
        for (String s : ls) {
            //如果是一个数就入栈s2
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s.equals("(")) {
                s1.push(s);
            } else if (s.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将“（”弹出s1栈，消除一对括号
            } else {
                //当s的优先级小于等于s1栈顶的运算符的优先级，将s1栈顶的运算符弹出并加入s2,再次转到4-1与s1中新的栈顶运算符比较
                while (s1.size() != 0 &&Operation.getValue(s1.peek())>=Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                //还需要将s压入栈中
                s1.push(s);
            }

        }
        //将s1中剩余的运算符加入到s2
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;//因为存放的是list。因此正常输出就是逆波兰表达式
    }

    //将一个中缀表达式转化成相应的list
    public static List<String> toInFixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        int i = 0;//指针，用于遍历中缀表达式字符穿
        String str;//对多位数的拼接操作
        char ch;//每遍历一个字符，就放入到ch中
        do {
            //如果c是一个非数字，需要加入到ls
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                ls.add(ch + "");
                i++;
            } else {
                //如果是一个数，需要考虑拼接
                str = "";
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str += ch;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到arraylist中
    public static List<String> getListString(String suffixExpression) {
        String[] str = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s : str) {
            list.add(s);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    public static int calculate(List<String> list) {
        //创建一个栈
        Stack<String> mystack = new Stack<String>();
        //遍历ls
        for (String s : list) {
            //使用正则表达式取出数
            if (s.matches("\\d+")) {
                mystack.push(s);
            } else {
                int s1 = Integer.parseInt(mystack.pop());
                int s2 = Integer.parseInt(mystack.pop());
                int result = 0;
                if (s.equals("+")) {
                    result = s2 + s1;
                } else if (s.equals("-")) {
                    result = s2 - s1;
                } else if (s.equals("*")) {
                    result = s2 * s1;
                } else if (s.equals("/")) {
                    result = s2 / s1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                mystack.push(result + "");
            }
        }
        //留在myStack中的就是运算结果
        return Integer.parseInt(mystack.pop());
    }
}

//前缀，中缀，后缀表达式（逆波兰表达式）

//1:前缀表达式（波兰表达式）：前缀表达式的运算符位于操作数之前：（3+4）*5-6对应的前缀表达式就是：- * + 3 4 5 6
    //前缀表达式的计算机求值：从右到左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对他们做相应的计算
    //（栈顶元素和次顶元素），并将结果入栈；重复上述过程直到表达式最左端。最后运算得出的值即为表达式的结果
    //1:从右到左扫描，入栈6 5 4 3
    //2:+：弹出3，4 运算锝7入栈
    //3:*: 弹出7，5 运算锝35入栈
    //4:-: 弹出35，6运算的29，得出结果（先弹出的数减掉后弹出的我数）
//2:中缀表达式：就是常见的运算表达式，：（3+4）*5-6
    //中追表达式的求值是我们人最熟悉的，但是对计算机来说却不好操作，因此在计算结果时，往往会将中缀表达式转换成其他的表达式来操作
    //一般转换成后缀表达式
//3:后缀表达式：又称为逆波兰表达式，与前缀表达式相似，只是运算符位于操作数之后，
    //例如（3+4）*5-6对应的后缀表达式为：3 4 + 5 * 6， a+b对应的逆波兰表达式为： ab+
    //a+(b-c)==> abc-+
    //a+(b-c)*d==>abc-d*+
    //a=1+3==>a 1 3+ =
//后缀表达式的计算机求值：
    //从左到右扫描表达式，遇到数字时将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对他们做相应的计算（次顶元素和栈顶元素）
    //并将结果入栈，重复上面的过程直到表达式最右端，最后计算出来的值即为表达式的结果
    //（3+4）*5-6对应的后缀表达式为：34 + 5*6 - ;表达式求值如下
//1:从左到右扫描，将3，4压入堆栈
//2:+:弹出4，3 计算3+4=7，将7入栈
//3:5入栈
//4:*:弹出5，7，计算7*5=35，将35入栈
//5:6入栈
//6:-:弹出6，35 计算35-6=29,得出结果


//完成逆波兰计算器，要求完成如下任务：
//1:输入一个逆波兰表达式，使用栈计算结果
//2:支持小括号和多位整数
//3:思路分析
//4:代码分析：见main


//如何将中缀表达式转换为后缀表达式
//后缀表达式适合计算机计算，中缀表达式适合人计算，具体的转化步骤如下
//1:初始化两个栈，运算符栈s1和存储中间结果的栈s2
//2:从左到右扫描中缀表达式
//3:遇到操作数时，将其压入s2
//4:遇到运算符时，比较其与s1栈顶运算符的优先级：
    //1:如果s1为空或栈顶运算符为左括号“（”，则直接将次运算符入栈
    //2:否则，若优先级比栈顶运算符的高，也将运算符压入s1
    //3:否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4：1与s1中新的栈顶运算符相比较

//5:遇到括号时：
    //1:如果是左括号“（”，则直接压入s1
    //2:如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
//6:重复步骤2-5,直到表达式的最右边
//7:将s1中剩余的运算符依次弹出并压入s2
//8：依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式

//中缀表达式：1+（（2+3）*4）-5 转换为后缀表达式：
//扫描到的元素        s2（栈底-》栈顶）      s1(栈底-》栈顶)          说明
//        1             1                   空                       数字，直接入栈s2
//        +             1                   +                       s1为空，直接入栈s1
//       （             1                    +（                      左括号，直接入栈
//       （             1                    +（（                     同上
//        2             12                   +（（                     数字
//        +             12                   +（（+                   s1栈顶为（，运算符直接入栈
//        3             123                  +（（+                    数字
//        ）            123+                  +（                      弹出s1栈顶元素，比把那个丢弃这一对双括号
//        *             123+                 +（*                      s1栈顶为左括号，运算符直接入栈
//        4             123+4                +（*                      数字
//        ）            123+4*                +                        弹出s1栈顶元素，比把那个丢弃这一对双括号
//        -             123+4*+               -                        4.3规则
//        5             123+4*+5              -                        数字
//      到达最右端        123+4*+5-             空                       s1中剩余的运算符依次弹出并压入s2

//s2弹出结果的逆序123+4*+5-即为后缀表达式

//中缀表达式转后缀表达式的代码实现
//见main 方法


//返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回对应的优先级数字
    public static int getValue(String operation) {
        int result=0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                //throw new RuntimeException("符号异常");
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}

enum Operations {
    ADD(1), SUB(1), MUL(2), DIV(2);

    public int getOrder() {
        return order;
    }

    private int order;

    private Operations(int order) {
        this.order = order;
    }

}

//扩展：逆波兰计算器的完整版：支持+-*/{}，多位数，小数，兼容处理空格等等。。。

