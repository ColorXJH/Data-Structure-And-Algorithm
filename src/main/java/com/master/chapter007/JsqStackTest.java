package com.master.chapter007;

/**
 * @ClassName: JsqStackTest
 * @Package: com.master.chapter007
 * @Description: 用栈实现综合计算器
 * @Datetime: 2021/5/18 22:56
 * @author: ColorXJH
 */
public class JsqStackTest {
    public static void main(String[] args) {
        //根据前面的思路，完成表达式的运算(中缀表达式)
        String expression="30+9*6-2"; //不能处理多为数的为题：70+2*3-6（如何处理多位数的问题）
        //创建两个栈，一个是数栈，一个是符号栈
        ArrayStack2 numStack=new ArrayStack2(10);
        ArrayStack2 operStack=new ArrayStack2(10);
        //定义需要的相关变量
        int index=0;//用于扫描
        int num1=0,num2=0,oper=0,res=0;//两个弹出数字，一个操作符，一个结果
        char ch=' ';//将每次扫描得到的char保存到ch
        String keepNum="";//用于拼接多位数

        //开始while循环扫描expressoion
        while(true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应处理
            //如果是运算符
            if(operStack.isOper(ch)){
                //判断当前的符号栈是否为空
                if(!operStack.empty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符优先级小于或等于栈中操作符的优先级
                    //就需要从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //当前的操作符入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中操作符的优先级，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //为空直接入符号栈
                    operStack.push(ch);
                }
            }else{
                //如果是数，则直接入数栈:注意字符和数字的转换 char -48=int(相差48)
                //numStack.push(ch-48);
                    //完善处理多位数的问题
                    //在处理数时需要向expression后再看一位，如果是数字就继续扫描，如果是符号则入栈
                    //需要定义一个变量字符串，用于拼接
                keepNum+=ch;
                //如果ch是expression的最后一位则直接入栈
                if(index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是否为数字，如果是数字则继续扫描，如果是符号则入栈
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //后一位是运算符则入栈 keepNum "12"
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum="";
                    }
                }

            }
            //index++;并判断是否扫描到expression最后
            index++;
            if(index>=expression.length()){
                //扫描结束
                break;
            }

        }


        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的符号和数，并运行
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中之只有一个数字，即为结果
            if(operStack.empty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            //把运算的结果入数栈
            numStack.push(res);
        }
        System.out.printf("表达式%s的结果为：%d",expression,numStack.pop());
    }
}

//思路分析：使用栈完成计算一个表达式的结果
    //1：有两个栈 1：数栈  2：符号栈
    //2:先创建一个index指针来遍历表达式，如果我们发现索引对应的值是一个数字，就入数栈，如果发现扫描到的是一个符号，分一下两种情况
        //1：如果发现当前符号栈为空，则直接入栈
        //2：如果符号栈不为空（有操作符），就进行比较，如果当前的操作符的优先级小于或等于栈中的操作符，就需要从数栈中pop两个数，再从符号栈中一个符号，进行运算
            //将得到的结果入数栈，然后将当前的操作符入符号栈
        //3:如果当前的符号大于符号栈中的操作符的优先级（栈顶的），直接入符号栈
    //3：当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行，然后将值放入数栈
    //4：最后在数栈中只有一个数字，就是表达式的结果




//先创建一个栈，需要扩展功能

//数组模拟
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组中
    private int top=-1;//top表示栈顶，初始化为-1；

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[maxSize];
    }

    //判断栈满
    public boolean full(){
        return top==maxSize-1;
    }
    //栈空
    public boolean empty(){
        return top ==-1;
    }

    //入栈
    public void push(int num){
        if(full()){
            return ;
        }else{
            top++;
            stack[top]=num;
        }
    }
    //出栈
    public int pop(){
        if(empty()){
            return -1;
        }else{
            int value=stack[top];
            top--;
            return value;
        }
    }

    //遍历栈
    public void list(){
        if(empty()){
            return;
        }else{
            int num=top;
            while(num>-1){
                System.out.println(stack[num]);
                num--;
            }

        }
    }

    //扩展功能
    //1:返回运算符的优先级，优先级是程序员来定的，优先级使用数字表示，数字越大，则优先级越高
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;//假定目前表达式只有加减乘除
        }
    }

    //2:判断是不是一个运算符
    public boolean isOper(char var){
        return var=='+'||var=='-'||var=='*'||var=='/';
    }

    //3:计算方法
    public int cal(int num1,int num2,int oper){
        int result=0;//用于存放计算的结果
        switch (oper){
            case '+':
                result=num1+num2;
                break;
            case '-':
                result =num2-num1;//注意顺序，后弹出来的数作为被减数
                break;
            case '*':
                result =num1*num2;
                break;
            case '/':
                result =num2/num1;//注意顺序，后弹出来的数作为被除数
                break;
            default:
                break;
        }
        return result;
    }

    //4:查看当前栈顶元素
    public int peek(){
        return stack[top];
    }
}

//扩展，可以考虑自己加入小括号符号