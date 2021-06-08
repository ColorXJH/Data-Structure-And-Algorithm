package com.master.chapter005;

/**
 * ClassName: LinkedListTwoNode
 * Package: com.master.chapter005
 * Description: 双向链表应用实例
 * Datetime: 2021/5/12 10:35
 * author: ColorXJH
 * @author ColorXJH
 */
public class LinkedListTwoNode {

    public static void main(String[] args) {
        LinkedListTwoNode test=new LinkedListTwoNode();
        Dnode node0=new Dnode("dnode0",0);
        Dnode node1=new Dnode("dnode1",1);
        Dnode node2=new Dnode("dnode2",2);
        Dnode node3=new Dnode("dnode3",3);
        node0.pre=null;node0.next=node1;
        node1.pre=node0;node1.next=node2;
        node2.pre=node1;node2.next=node3;
        node3.pre=node2;node3.next=null;
        System.out.println(test.getLength(node0));
        test.listLinked(test.deleteNode(node0,2));
    }



    public Dnode deleteNode(Dnode node,int index){
        int length=getLength(node);
        if(index>length-1||index<0){
            return node;
        }else{
            Dnode temp=node;
            while(true){
                if(index==0){
                    break;
                }
                temp=temp.next;
                index--;
            }
            temp.pre.next=temp.next;
            temp.next.pre=temp.pre;
            return node;
        }
    }
    public void listLinked(Dnode node){
        while(node!=null){
            System.out.println(node.name+node.num);
            node=node.next;
        }
    }
    public int getLength(Dnode node){
        if(node==null){
            return 0;
        }
        int length=0;
        while(true){
            if(node==null){
                break;
            }
            length++;
            node=node.next;

        }
        return length;
    }
}


class Dnode{
    public  String name;
    public int num;
    public Dnode pre;
    public Dnode next;

    public Dnode(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Dnode{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}


//单向链表缺点分析：
    //1:单向链表查找的方向只能是一个方向，而双向链表可以向前或者向后查找
    //单向链表不能自我删除，需要靠辅助节点，而双向链表可以自我删除，所以我们前面单向链表删除节点时，总是找到temp的下一个节点来删除的


//分析双向链表的遍历，添加，修改，删除的操作思路分析==》代码实现
    //1:遍历：遍历的方式和单链表相同，只是可以向前操作
    //2:添加（默认添加到双向链表的最后）：先找到双向链表的最后，==》temp.next=new Node， new Node.pre=temp
    //3:修改：思路和原来的单向链表一致
    //4:删除：双向链表可以实现自我删除某个节点，直接找到要删除的这个节点temp, temp.pre.next=temp.next, temp.next.pre=temp.pre