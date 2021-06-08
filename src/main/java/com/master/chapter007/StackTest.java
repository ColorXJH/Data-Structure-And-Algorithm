package com.master.chapter007;

/**
 * @ClassName: StackTest
 * @Package: com.master.chapter007
 * @Description:  栈的实际需求应用
 * @Datetime: 2021/5/12 20:57
 * @author: ColorXJH
 */
public class StackTest {
    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(10);
        stack.push(11);
        stack.push(10);
        stack.push(20);
        stack.push(13);
        stack.push(17);
        stack.push(30);
        stack.list();
        System.out.println("====================");
        System.out.println(stack.pop());
        while(!stack.empty()){
            System.out.println(stack.pop());
        }

        System.out.println("---链表实现--");
        LinkedStack linkedStack=new LinkedStack(5);
        linkedStack.push(12);
        linkedStack.push(7);
        linkedStack.push(0);
        linkedStack.push(3);
        linkedStack.push(56);
        linkedStack.push(2323);
        System.out.println(linkedStack.pop());
        linkedStack.list();
    }
}


//栈的一个实际需求
    //请输入一个表达式：计算式：7*2*2-5+1-5+3+3 点击计算，请问计算机底层是如何运算得到结果的
    //注意不是简单的把算式列出运算，因为我们看到这个算式是运算式，但是计算机他接受到的是一个字符串，这就是我们讨论的问题--》栈

//栈的基本介绍
    //1：栈英文名stack
    //2: 栈是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表，允许插入和删除的一段成为变化端，称为栈顶（top）
            //另一端称为固定的一端，称为栈底（bottom）
    //3: 栈是一个先入后出的先行列表FILO
    //4: 根据栈的定义可知，最先放入栈中的元素在栈底，最后一放入战中的元素在栈顶

//栈的应用场景
    //1:子程序的调用：在跳往子程序之前，会先将下个指令的地址存到堆栈中，直到子程序执行完成后再将地址取出，以回到原来的程序中
    //2:处理递归调用：和子程序的调用类似，只是除了存储下一个指令地址外，也将参数，区域变量等数据存入堆栈中
    //3:表达式的转换和求值（上面实际需求）[中缀表达式转后缀表达式]
    //4:二叉树的遍历
    //5:图形的深度优先搜索法（depth-first）
    //入栈push 出栈pop


//栈的快速入门：（用数组模拟栈的使用）：入栈，出栈，遍历
    //思路分析
        //使用数组来模拟栈，定义一个变量top表示为栈顶，初始化为-1，当有数据入栈时，top++,stack[top]=data;
        //出栈的操作，从栈顶取出一个数据并返回，定义一个临时变量value,int value=stack[top];top--,return value;

//数组模拟
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组中
    private int top=-1;//top表示栈顶，初始化为-1；

    //构造器
    public ArrayStack(int maxSize){
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
}


//链表模拟
    //使用单链表实现
class LinkedStack{
    private Node head=new Node(-1);//单向链表头节点，栈的起始位置(输入值不能为-1)
    private int maxSize=0;//栈的大小
    Node temp=head;
    int top=0;

    public LinkedStack(int maxSize){
        this.maxSize=maxSize;
    }
    //栈空
    public boolean empty(){
       return temp.getNum()==-1;
    }
    //栈满
    public boolean full(){
        return top==maxSize;
    }
    //入栈
    public void push(int num){
        if(full()){return;}
        Node node=new Node(num);
        node.setPre(temp);
        top++;
        temp=node;
    }
    //出栈
    public int pop(){
        if(empty()){return -1;}
        top--;
        int value=temp.getNum();
        temp=temp.getPre();
        return value;
    }
    //遍历
    public void list(){
        if(empty()){
            return ;
        }
        Node tp=temp;
        while(tp.getNum()!=-1){
            System.out.println(tp.getNum());
            tp=tp.getPre();
        }
    }

}
class Node{
    private int num;
    private Node pre;
    public Node( int num){
        this.num=num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                '}';
    }
}