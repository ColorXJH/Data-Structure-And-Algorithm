package com.master.chapter003;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ColorXJH
 * @version 1.0
 * @description 队列
 * @date 2021/3/25 13:47
 */
public class Queue {
    public static void main(String[] args) {
        //数组模拟队列
        //ArrayQueue queue=new ArrayQueue(3);
        //环形队列（预留位置）
        //CircleArrayQueue1 queue=new CircleArrayQueue1(5);
        //环形队列（标志法）
        ArrayCircleQueue queue=new ArrayCircleQueue(5);
        //递增队列
        //CircleArrayQueue2 queue=new CircleArrayQueue2();
        char key=' ';//接收用户输入
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        //输出一个菜单
        while(loop){
                System.out.println("s(show):显示队列");
                System.out.println("e(exit):退出程序");
                System.out.println("a(add):添加数据到队列");
                System.out.println("g(get):从队列取数据");
                System.out.println("p(pick):显示队列头数据");
                key=scanner.next().charAt(0);
                switch (key){
                    case 's':
                        queue.showQueue();
                        break;
                    case 'a':
                        System.out.println("请输入一个数");
                        int value=scanner.nextInt();
                        queue.addqueue(value);
                        break;
                    case 'g':
                        try{
                            int res=queue.getQueue();
                            System.out.printf("取出的数据是：%d\n",res);
                        }catch (Exception e){
                            System.out.println(e.getMessage());

                        }
                        break;
                    case 'p':
                        try{
                            int res=queue.pick();
                            System.out.printf("队列头的数据为%d\n",res);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'e':
                        scanner.close();
                        loop=false;
                        break;
                    default:

                        break;
                }
        }
        System.out.println("程序退出");

    }
}

//队列的使用场景：银行叫号
//队列的基本介绍：
    //1：队列是一个有序列表，可以用数组或链表来实现
    //2：遵循先进先出的原则FIFO
    //int[] queue=new int[100]; 下标为maxsize-1，int rear=-1(尾部)，int font=-1(头部)
        //初始化头尾都为-1，从队列加数据，就增加real,从队列取数据，就增加font

//实现方式：
    //1：数组模拟队列
        //1：队列本身是有许列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图，其中maxSize是该队列的最大容量
        //2：因为队列的输出输入，分别是从前后端来处理，因此需要两个变量font和rear分别记录队列前后端下标，font会随着数据的输出而改变
            //而rear则会随着数据的输入而改变
        //3：当我们将数据存入队列时称为”addQueue“,addQueue的处理需要两个步骤：思路如下
            //1：将尾指针向后移：rear+1,当font==rear[空]
            //2：若尾指针rear小于队列的最大下标maxSize-1,则将数据存入rear所指的数组元素中，否则无法存入数据
                //rear=maxSize-1[队列满]
                    //注意：rear是队列最后【包含】，font是队列最前元素【不包含】

//使用数组模拟队列代码实现
class ArrayQueue{

    private int maxSize;//数组最大容量
    private int font;//队列头
    private int rear;//队列尾部
    private int[] arr;//用于存放数据

    //创建队列的构造器
    public  ArrayQueue(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
        font=-1;//指向队列头部的前一个位置（不包含）
        rear=-1;//指向队列尾部的位置（包含，即就是队列最后一个数据）
    }


    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==font;
    }

    //添加数据到队列，入队列
    public void addqueue(int n){
        //判断队列是否为满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return ;
        }
        rear++;
        arr[rear]=n;//等价于：arr[++rear]=n;
    }

    //获取队列数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        font++;
        int result=arr[font];
        arr[font]=0;
        return result;//等价于：return arr[++font];
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
          System.out.println("队列空，无数据");
          return;
        }
        System.out.println(Arrays.toString(arr));
    }

    //显示队列头数据（不是取数据）
    public int pick(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无数据");
        }
        return arr[font+1];
    }
}


//数组模拟环形队列
    //1：对前面数组模拟队列的优化，充分利用数组，因此将数组看成是一个环形的（通过取模的方式来实现即可）
//分析说明：
    //1：尾索引的下一个为头索引时表示队列满，即将队列容量空出一个作为约定，这个在做判断队列满的时候需要注意（rear+1）%maxSize==font[满]
    //2：rear==font[空]
    //思路如下：
        //1：font变量的含义做一个调整：font指向队列的第一个元素，，也就是说array[font]就是队列的第一个元素,
            //font的初始值为0，
        //2：rear变量的含义也做一个调整：rear指向队列的最后一个元素的后一个位置，因为我希望空出一个空间作为约定
            //rear的初始值为0，（总是会预留一个空间，这个空间作为约定，在动态变化，这样整个队列满时，数组依然有一个空位）
                //当然有的算法也可以不预留一个空位，
        //3：当队列满时，条件是：(rear+1)%maxsize==font,
        //4：当队列为空的条件：rear==font,
        //5：这样队列中有效数据的个数为：(rear+maxsize-font)%maxsize
        //6：在原来的队列上修改，得到环形队列

//环形队列（预留1空位）：代码如下、
/*环形队列的关键是判断队列为空，还是为满。当tail追上head时，队列为满时，当head追上tail时，队列为空。但如何知道谁追上谁。还需要一些辅助的手段来判断.

   如何判断环形队列为空，为满有两种判断方法。

一.附加一个标志位tag

当head赶上tail，队列空，则令tag=0,
当tail赶上head，队列满，则令tag=1,

二.限制tail赶上head，即队尾结点与队首结点之间至少留有一个元素的空间。

队列空：   head==tail
队列满：   (tail+1)% MAXN ==head
*/
class CircleArrayQueue1{
    private int maxSize;//数组最大容量
    private int font;//队列头，初始值为0
    private int rear;//指向队列尾部后一个位置，初始值为0
    private int[] arr;//用于存放数据

    //创建队列的构造器
    public  CircleArrayQueue1(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
        font=0;//指向队列头部位置（包含）
        rear=0;//指向队列尾部的下一个位置（不包含包含）
    }


    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==font;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==font;
    }

    //添加数据到队列，入队列
    public void addqueue(int n){
        //判断队列是否为满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return ;
        }

        arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear=(rear+1)%maxSize;

    }

    //获取队列数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出font是指向队列的第一个元素
        //1：先把font对应的值保存到一个临时的变量
        //2：将font后移,考虑取模
        //3：将临时保存的变量返回
        int value=arr[font];
        font=(font+1)%maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空，无数据");
            return;
        }
        //从font开始，遍历多少个元素
        for(int i=font;i<font+size();i++){
            //下标可能超过了i
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //求出当前队列有效数据个数
    public int size(){
        return (rear+maxSize-font)%maxSize;
    }

    //显示队列头数据（不是取数据）
    public int pick(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无数据");
        }
        return arr[font];
    }


}
//无限扩大队列
class CircleArrayQueue2{
    private int currentSize;//当前容量
    private int font;//队列头，初始值为0
    private int rear;//队列尾，初始值为-1
    private int[] arr;//用于存放数据
    private static final int DEFAULT_CAPACITY = 3;

    public CircleArrayQueue2(){
        arr=new int[DEFAULT_CAPACITY];
        makeEmpty();
    }
    //初始化队列
    public void makeEmpty(){
        currentSize=0;
        font=0;
        rear=-1;
    }

    public boolean isEmpty(){
        return currentSize==0;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        currentSize--;
        int revalue=arr[font];
        font=increment(font);
        return revalue;
    }
    //移动下标
    public int increment(int x){
        if(++x==arr.length){
            x=0;
        }
        return x;
    }

    public int pick(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[font];
    }

    public void addQueue(int x){
        if(currentSize==arr.length){
            doubleQueue();
        }
        rear=increment(rear);
        arr[rear]=x;
        currentSize++;
    }

    //增大队列容量
    public void doubleQueue(){
        int[] newArr;
        newArr=new int[arr.length*2];
        //拷贝元素到新的数组中
        for(int i=0;i<currentSize;i++,font=increment(font)){
            newArr[i]=arr[font];
        }
        arr=newArr;
        font=0;
        rear=currentSize-1;
    }
}

/**
 * 用数组实现环形队列
 *      附加标志位法
 */

/*环形队列的关键是判断队列为空，还是为满。当tail追上head时，队列为满时，当head追上tail时，队列为空。但如何知道谁追上谁。还需要一些辅助的手段来判断.

   如何判断环形队列为空，为满有两种判断方法。

一.附加一个标志位tag

当head赶上tail，队列空，则令tag=0,
当tail赶上head，队列满，则令tag=1,
二.限制tail赶上head，即队尾结点与队首结点之间至少留有一个元素的空间。

队列空：   head==tail
队列满：   (tail+1)% MAXN ==head
*/
class ArrayCircleQueue {
    private int[] arr;
    private int maxSize;
    private int rear;
    private int front;
    // 当front赶上rear，说明队列空，令flag=1；
    // 当rear赶上front，说明队列满，令flag=0
    private int flag;

    public ArrayCircleQueue(int size) {
        maxSize = size;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
        flag = 1;
    }

    public boolean isFull() {
        return front == rear && flag == 0;
    }

    public boolean isEmpty() {
        return front == rear && flag == 1;
    }

    public int pick() {
        if (isEmpty()) throw new RuntimeException("queue is empty!!!");
        return arr[front];
    }

    public void addqueue(int v) {
        if (isFull()) {
            System.out.println("queue is full, could not add.");
            return;
        }
        arr[rear] = v;
        rear = (rear + 1) % maxSize;
        if (rear == front) flag = 0;
    }

    public int getQueue() {
        if (isEmpty()) throw new RuntimeException("queue is empty!");
        int res = arr[front];
        front = (front + 1) % maxSize;
        if (front == rear) flag = 1;
        return res;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("The queue is empty!!!");
            return;
        }
        System.out.println("front -> rear");
        for (int i = front; i < front + size(); i++) {
            System.out.print(arr[i % maxSize] + " ");
        }
        System.out.println();
    }

    public int size() {
        if (rear > front) return rear - front;
        return rear + maxSize - front;
    }
}

/*用数组实现环形队列的特点是高效。

能快速判断队列是否 满/空；
能快速存取数据。
因为简单高效，所以甚至在硬件中都实现了环形队列。

       环形队列广泛应用于网络数据的收发，和不同应用间数据交换（内核和应用程序大量交换数据，从硬件接受大量数据）

内存上没有环形结构，因此环形队列实际上用数组的线性空间来实现。

      但是当数据到了尾部如何处理呢？它将转回到0位置来处理。这个转回是通过取模操作来执行的。

      因此，环形队列是逻辑上将数组元素q[0]和q[maxSize - 1]连接，形成一个存放队列的环形空间。

为了方便读写，还要用数组下标来指明队列的读写位置。head/tail.其中head指向可以读的位置，tail指向可以写的位置。
*/