package com.master.chapter006;

/**
 * ClassName: DoublyLinkedList
 * Package: com.master.chapter006
 * Description:  双向链表的增删改查实现
 * Datetime: 2021/5/12 12:14
 * @author ColorXJH
 */
public class DoublyLinkedListTest {
    public static void main(String[] args) {
        DoubleLinkedList mylist=new DoubleLinkedList();
        mylist.addByOrder(new HeroNode(1,"XJH","XHH"));
        mylist.addByOrder(new HeroNode(4,"WXY","XYY"));
        mylist.addByOrder(new HeroNode(2,"KCY","XCY"));
        mylist.update(new HeroNode(2,"KCY1","XCY1"));
        mylist.list();
        System.out.println("----------------------");
        mylist.delete(2);
        mylist.list();
    }
}

/**
 * 功能描述: <br>
 * 创建一个双向链表类
 */
class DoubleLinkedList{

    /**
     * 先初始化一个头节点，头节点不动，不存放具体数据
     */
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 功能描述: <br>
     * 〈〉双向链表的遍历
     * @Param: []
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/5/12 12:41
     */
    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while(true){
            //到最后了
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }



    /**
     * 功能描述: <br>
     * 〈〉
     * @Param: [node]
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/5/12 12:45
     */
    public void addList(HeroNode node){
        //指针
        HeroNode temp=head;
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=node;
        node.pre=temp;
    }

    /**
     * 功能描述: <br>
     * 〈〉 双向链表向按照顺序添加
     * @Param: []
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/5/12 13:02
     */
    public void addByOrder(HeroNode node){
        // 头节点不能动，通过一个辅助指针（变量）帮助找到需要添加的位置
        HeroNode temp = head;
        boolean flag = false;	// flag标志添加的编号是否存在，默认为false
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.num > node.num) {
                break;
            }
            if(temp.next.num == node.num) {
                flag = true;
                break;
            }
            temp = temp.next;	// 遍历链表
        }
        if(flag) {
            System.out.printf("输入的编号%d已经存在,不能加入\n", node.num);
        }
        else {
            // 为防止出现空指针的情况，需要对temp节点位置进行判断
            // 若双向链表尚未到达尾端，则需要将node节点与其相邻的后面的节点进行连接
            if(temp.next != null) {
                node.next = temp.next;
                temp.next.pre = node;
            }
            // 无论双向链表是否到达尾端，都需要将node节点与其相邻的前面的节点进行连接
            temp.next = node;
            node.pre = temp;
        }
    }


    /**
     * 功能描述: <br>
     * 〈〉  双向链表的修改
     * @Param: [node]
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/5/12 13:02
     */
    public void update(HeroNode node){
        if(head.next==null){
            System.out.println("原链表为空");
            return;
        }
        HeroNode temp=head.next;
        boolean flag=false;
        while(true){
             //遍历完链表
             if(temp==null){
                 break;
             }
             if(temp.num==node.num){
                 flag=true;
                 break;
             }
             temp=temp.next;
        }
        if(flag){
            temp.name= node.name;
            temp.nickname=node.nickname;

        }else{
            System.out.println("没有找到该节点");
            return;
        }

    }

    /**
     * 功能描述: <br>
     * 〈〉 从双向链表中删除一个节点
     * @Param: [node]
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/5/12 14:46
     */
    //对于双向链表，我们可以直接到找到要删除的这个节点，找到后自我删除即可
    public void delete(int no){
        //空链表
        if(head.next==null){
            System.out.println("链表为空不能删除");
            return ;
        }
        //辅助指针--直接找到需要删除的节点（这里不需要找到需要删除节点的前一个节点）
        HeroNode temp=head.next;
        boolean flag=false;
        while(true){
            //已经找到链表最后节点的next节点
            if(temp==null){
                break;
            }
            if(temp.num==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            //在删除的节点是最后一个节点是，第一句话是没有问题的，他表示将temp的前一个节点的next节点设置为null,(最后一个节点的temp.next=null)
            temp.pre.next=temp.next;
            if(temp.next!=null){
                //这里代码有些问题(如果不加条件判断)，如果是最后一个节点就不需要执行下面这句话，否则会出现空指针异常
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.println("要删除的节点不存在");
        }

    }

}
/**
 * 功能描述: <br>
 * 定义一个节点
 */
class HeroNode{
    public int num;
    public String name;
    public String nickname;
    public HeroNode pre;
    public HeroNode next;

    public HeroNode(int num, String name, String nickname) {
        this.num = num;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

