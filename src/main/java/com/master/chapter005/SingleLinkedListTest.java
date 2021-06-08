package com.master.chapter005;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * ClassName: SingleLinkedListTest
 * Package: com.master.chapter005
 * Description: 一些单链表的公司面试题
 * Datetime: 2021/5/9 19:20
 * author: ColorXJH
 *
 * @author ColorXJH
 */
public class SingleLinkedListTest {

    public static void main(String[] args) {
        SingleLinkedListTest test=new SingleLinkedListTest();
        Node head=test.new Node("head",test.new Node(),0 );
        head.next=test.new Node("first",test.new Node(),1);
        head.next.next=test.new Node("second",test.new Node("third",null,3),2);
        System.out.println(test.getLinkedListNodeCount(head));
        System.out.println(findLastIndexNode(head,3));
        test.pringtlistNode(head);
        System.out.println("-------------------------");
        //test.pringtlistNode(test.reverseNode(head));
        //test.printReverseLinkedList(head);
        Node another=test.new Node("another",test.new Node(),1 );
        another.next=test.new Node("afirst",test.new Node(),2);
        another.next.next=test.new Node("asecond",test.new Node("athird",null,7),5);
        System.out.println("------>==tttt====>-------");
        test.pringtlistNode(head);
        System.out.println("------>======>-------");
        test.pringtlistNode(another);
        Node combines=test.mergeTwoLists(head,another);
        System.out.println("------>?????>-------");
        test.pringtlistNode(combines);
    }

    //求单链表中有效节点的个数
    //查找单链表中地k个节点
    //单链表的反转
    //从头到尾打印单链表[要求：方式1：反向遍历，2：stack栈]
    //合并两个有序的单链表，合并之后链表依然有序

    //1：求单链表中有效节点个数（如果有头节点需要去掉头节点，因为头节点不存放任何数据）

    /**
     * 功能描述: <br>
     * 〈〉
     *
     * @Param: [head]
     * @Return: int
     * @Author: ColorXJH
     * @Date: 2021/5/9 20:17
     */
    public static int getLinkedListNodeCount(Node head) {
        if (head.next == null) {//空链表（带头节点）
            return 0;
        }
        int length = 0;
        Node cur = head.next;//
        while(cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }




    //查找单链表的倒数第k个节点
    //思路：编写一个方法，接受一个head节点，同时接受index(倒数第index个节点)
    //先把链表从头到尾遍历一下，得到链表长度
    //得到size后，我们从链表第一个开始遍历，遍历（size-index），就可以得到该节点·

    public static Node findLastIndexNode(Node head,int index){
        if(head.next==null){//如果链表为空则返回null
            return null;
        }
        //第一次遍历链表的长度
        int size=getLinkedListNodeCount(head);
        //第二次遍历size-index位置，就是我们倒数的第k个节点
        if(index<=0||index>size){
            return null;
        }
        int position=size-index;
        Node temp=head.next;
        while(position>0){
            temp=temp.next;
            position--;
        }
        return temp;

    }


    //单链表的反转(不是说数据全部倒过来，是以中间为轴，旋转180度的反转)
    //思路1：先定义一个节点 Node reverseHead=new Node();
    //从头到尾遍历节点，每遍历一个节点就将其摘下并放在新的链表的最前端的位置
    //将原来的链表的head.next=reverseHead.next即可

    public  Node reverseNode(Node head){
        if(head.next==null||head.next.next==null){//无节点||只有一个节点，无需反转
            return head;
        }
        //定义一个辅助指针，帮助我们遍历原来的链表
        Node curr=head.next;
        Node next=null;//指向当前节点【curr】的下一个节点
        Node reverseHead=new Node("reverseHead",null,0);
        //遍历原来的链表，并完成思路工作
        while(curr!=null){
            next=curr.next;//暂时保存当前节点的下一个节点
            curr.next=reverseHead.next;//将curr的下一个节点指向新的链表的最前端
            reverseHead.next=curr; //将reserveHead头重新指向最新的链表上
            curr=next;//让curr后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转、
        head.next=reverseHead.next;
        return reverseHead;

    }


    //从尾到头打印单链表
    //思路：方式一：先将单链表反转，然后遍历打印即可,这样做的问题是会破坏原先单链表的结构--》不建议
    //方式二：可以利用栈，将各个节点压入栈中，利用栈的先进后厨的特点实现逆序打印
    public void  printReverseLinkedList(Node head){
        if(head.next==null){
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<Node>stack=new Stack<Node>();
        Node curr=head.next;
        //将链表的所有节点压入栈中
        while(curr!=null){
            stack.push(curr);
            curr=curr.next;
        }
        //将栈中的节点打印遍历
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //合并两个有序连标配使之合并之后仍然有序
    //两个有序链表的排序，实际上可以看成一个单链表使用归并排序的最后一个环节：
    //“将两个排好序的子序列合并为一个子序列：每次都是从未比较的两个子序列的最小值中选出一个更小值”。
    public Node mergeTwoLists(Node l1, Node l2) {
        Node temp=new Node("head",null,-1);
        Node head=temp;//保留头节点的引用
        while(l1!=null&&l2!=null){
            if(l1.num<l2.num)
            {
                temp.next=l1;
                l1=l1.next;
            }
            else
            {
                temp.next=l2;
                l2=l2.next;
            }
            temp=temp.next;
        }
        if(l1==null)  temp.next=l2;//l1子序列为空，则直接拼届l2
        if(l2==null)  temp.next=l1;
        return head.next;//返回头节点指向的序列
    }


    //打印链表内容
    public  void  pringtlistNode(Node head){
            if(head.next==null){
                return;
            }
            Node next=head;
            while(next!=null){
                System.out.println(next.name);
                System.out.println(next.num);
                next=next.next;
            }
    }



     class Node {
        String name;
        Node next;
        int num;
        public Node() {
        }
        public Node(String name, Node next,int num) {
            this.name = name;
            this.next = next;
            this.num=num;
        }

         @Override
         public String toString() {
             return "Node{" +
                     "name='" + name + '\'' +
                     ", num=" + num +
                     '}';
         }
     }

}

class 合并两个无序链表 {
    static Node l1;
    static Node l2;
    static class Node {
        int val;
        Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    private static void init() {
        Node vir1 = new Node();
        Node vir2 = new Node();
        Node p = vir1;
        Node q = vir2;
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            Node np = new Node(random.nextInt(30));
            p.next = np;
            p = np;
            Node nq = new Node(random.nextInt(30));
            q.next = nq;
            q = nq;
        }
        l1 = vir1.next;
        l2 = vir2.next;
    }

    private static void show(Node l1,Node l2){
        Node p = l1;
        Node q = l2;
        while (p != null){
            System.out.print(p.val+" ");
            p = p.next;
        }
        System.out.println();
        while (q != null){
            System.out.print(q.val + " ");
            q = q.next;
        }
        System.out.println();
    }


    private static Node merge(Node h1, Node h2) {
//        System.out.println("merge");
        //合并两个有序链表
        Node vir = new Node();
        Node sc =vir;
        Node p = h1;
        Node q = h2;
        while (p != null && q != null){
            if (p.val <= q.val){
                Node pn = p.next;
                sc.next = p;
                p = pn;
            }else {
                Node qn = q.next;
                sc.next = q;
                q = qn;
            }
            sc = sc.next;
//            sc.next = null;
        }
        if (p != null) sc.next = p;
        if (q != null) sc.next = q;
        return vir.next;
    }

    private static Node merge_sort(Node head) {
//        System.out.println("merge_sort");
        if (head == null || head.next == null) return head;
        Node fast = head;
        Node low = new Node(0,head);
        // 1 2
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            low = low.next;
        }
        //low所在的位置是右边头节点的前一个
        Node rhead = low.next;
        low.next = null;//断开连接
        Node lh = merge_sort(head);
        Node rh = merge_sort(rhead);
        Node merge = merge(lh, rh);
        return merge;
    }



    public static void main(String[] args) {
        init();
        show(l1,l2);
        l1 = merge_sort(l1);
        l2 = merge_sort(l2);
        Node res = merge(l1,l2);
        show(res,null);
    }

}
/**
 上述Node为链表节点、init()方法是对链表进行初始化，生成测试数据,show()是打印链表的内容。
 剩下就是核心方法merge_sort()和merge()方法。
 merge_sort()实际上就是通过快慢指针，快速定位到链表的中点、然后对链表进行分割，分为两个小链表进行排序，
 最终当链表为一个节点的时候则问题解决，这是分治的思路。
 merge()没什么好说的就是合并两个有序链表.
 然后我们来看看算法时间复杂度：对于每个merge_sort的方法，快慢指针需要O(n)的时间复杂度，
 合并两个有序链表也是O(n)的复杂度，然后最多调用logn层的merge_sort，所以最终的算法时间复杂度为O(nlogn)。
*/