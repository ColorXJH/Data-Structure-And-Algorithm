package com.master.chapter004;

/**
 * ClassName: SingleLinkedList
 * Package: com.master.chapter004
 * Description:
 * Datetime: 2021/5/6 21:03
 * author: ColorXJH
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");
        //创建给定链表
        MySingleLinkedList mySingleLinkedList=new MySingleLinkedList();
        mySingleLinkedList.add(hero1);
        mySingleLinkedList.add(hero2);
        mySingleLinkedList.add(hero3);
        mySingleLinkedList.add(hero4);
        mySingleLinkedList.list();
        System.out.println("---------------方法二分割线-----------------");
        MySingleLinkedList mySingleLinkedList2=new MySingleLinkedList();
        mySingleLinkedList2.addByOrder(hero3);
        mySingleLinkedList2.addByOrder(hero1);
        mySingleLinkedList2.addByOrder(hero4);
        mySingleLinkedList2.addByOrder(hero2);
        mySingleLinkedList2.list();
        System.out.println("------------------测试修改系节点的代码-------------------------");
        HeroNode hero5=new HeroNode(2,"小卢","玉麒麟");
        mySingleLinkedList2.update(hero5);
        mySingleLinkedList2.list();
        System.out.println("------------------测试删除节点的代码-------------------------");
        mySingleLinkedList2.delete(2);
        mySingleLinkedList2.list();
        System.out.println("------------------测试查找节点的代码-------------------------");
        System.out.println(mySingleLinkedList2.query(4));
    }
}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    HeroNode next;

    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                //", next=" + next +
                '}';
    }
}

class MySingleLinkedList{
    //初始化一个头节点，头节点不要动
    private HeroNode head=new HeroNode(0,"","");//头节点不存放具体日的数据

    //添加节点到单向链表
    public void add(HeroNode node){
        //思路：当不考录编号的顺序时，找到当前链表的最后节点，将最后节点的next域指向新的节点
        //因为head节点不能动，因此我们需要一个辅助节点temp,遍历
        HeroNode temp=head;
        //遍历链表，找到最后
        while(true){
            //找到最后
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //当推出while循环时，temp就指向链表最后
        //将temp.next指向新的节点就ok了
        temp.next=node;
    }


    //第二种添加英雄的方式，按顺序
    public void addByOrder(HeroNode node){
        //因为head节点不能动，还是通过辅助指针temp来帮助我们找到要添加的位置
        //因为是单链表，因此需要找temp是位于添加位置的前一个结点，否则加不进去
        HeroNode temp=head;
        boolean flag=false;//标识添加的编号是否存在，默认false
        while(true){
            if(temp.next==null){//temp已经到链表最后了，不管找到与否都要break
                break;
            }
            if(temp.next.no>node.no){//位置已经找到，就在temp的后面插入
                break;
            }else if(temp.next.no==node.no){//说明希望添加的编号依然存在
                flag=true;//说明编号存在
                break;

            }
            temp=temp.next;//后移，遍历链表
        }
        if(flag){//编号存在不能添加
            System.out.printf("准备插入的英雄的编号%d已经存在，不能添加\r\n",node.no);
        }else{
            //位置已经找到，就在temp的后面插入
            node.next=temp.next;
            temp.next=node;
        }

    }

    //修改节点的信息，根据no来修改，即no编号不能修改
    public void update(HeroNode node){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点
        //定义一个辅助变量
        HeroNode temp=head;
        boolean flag=false;//是否找到该节点
        while(true){
            if(temp==null){//表示链表已经遍历结束了
                break;
            }
            if(temp.no== node.no){
                flag=true;//找到
                break;
            }
            temp=temp.next;
        }

        //根据flag判断是否找到需要修改的节点
        if(flag){
            temp.name= node.name;
            temp.nickname= node.nickname;
        }else{
            System.out.printf("没有找到编号为%d的节点\r\n",node.no);
        }
    }


    //删除节点
    public void delete(int no){
        //找到需要删除的节点的前一个结点
        HeroNode temp=head;
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        boolean flag=false;
        while(true){
            if(temp==null){
                break;
            }
            if(temp.next.no== no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("未找到相同编号%d的节点\r\n",no);
        }
    }


    //查找节点方法
    public HeroNode query(int no){
        if(head.next==null){
            System.out.println("链表为空");
            return null;
        }
        HeroNode temp=head;
        boolean flag=false;
        while(true){
            if(temp==null){//已经变了到结尾了
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            return temp;
        }else{
            System.out.printf("未查询到相同编号%d的节点\r\n",no);
            return null;
        }
    }

    //显示链表--遍历
    public void list(){
        if(head.next==null){return ;}
        //因为头节点不能动，因此需要一个辅助节点来遍历
        HeroNode temp=head.next;
        while(true){
            if(temp==null){break;}
            System.out.println(temp);
            //temp后移一位
            temp=temp.next;
        }
    }


}
