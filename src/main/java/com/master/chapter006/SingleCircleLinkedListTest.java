package com.master.chapter006;

import org.omg.Messaging.SyncScopeHelper;

/**
 * ClassName: SingleCircleLinkedListTest
 * Package: com.master.chapter006
 * Description: 单向环形链表应用场景=-=-约瑟夫环
 * Datetime: 2021/5/12 15:30
 * author: ColorXJH
 * @author ColorXJH
 */
public class SingleCircleLinkedListTest {
    public static void main(String[] args) {
        //构建环形链表和遍历
        CircleSingleLinkedList circleSingleLinkedList=new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        //2-4-1-5-3
        //出队列分析
        circleSingleLinkedList.countBoy(1,2,5);
    }
}


//约瑟夫问题（丢手帕问题）：假设1...n围坐一圈，约定编号k的人从1开始报数，数到m的那个人出列，他的下一位又从1开始报数，数到m的人出列，
//以此循环,知道所有的人出列，求此处队列的编号
    //提示：用一个不带头节点的环形链表来处理该问题，先构成有n个节点的单循环链表，然后由k节点起从1开始计数，计数到M时，对应节点从链
    //表中删除，然后再从被删除节点的下一个节点从1开始计数，直到最后一个节点从链表删除
        //假设： n=5:有5个人 k=1:从第一个开始报数 m=2:数两下


    //构建一个单向环形链表的思路：
        //1:先创建第一个节点，让first指向该节点，并形成环状
        //2:当我们每创建一个新的节点，就把该节点加入到已有的环形链表即可
    //遍历环形节点：
        //先让辅助变量指向first节点，然后通过一个while循环遍历该环形链表，遍历完成的标志是：current.next=first时表示遍历完毕


    //出圈的思路分析（根据用户的输入，生成一个小孩出圈的顺序）
        //1:需要创建一个辅助指针helper，事先应该指向环形链表的最后这个节点
        //2：小孩报数前，先让first和helper移动k-1次（移动到k这个小孩这里来）
        //3:当小孩报数时，让first和helper指针同时移动，移动和m-1次
        //4:这时我们就可以将first指向的小孩节点出圈 ==》first=first.next; helper.next=first ,原来first指向的节点就没有任何引用了，就会被回收
/**
 * 功能描述: <br>
 * 〈〉创建单向环形链表
 */
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first=new Boy(-1);
    //添加小孩节点，构建环形链表
    public void addBoy(int no){
        if(no<2){
            System.out.println("数值不正确");
            return;
        }
        //辅助指针,帮助构建环形链表
        Boy currentBoy=null;

        //创建环形链表
        for(int i=1;i<=no;i++){
            //根据编号创建小孩节点
            Boy boy=new Boy(i);
            //如果是第一个小孩
            if(i==1){
                first=boy;
                //构成环
                first.setNext(first);
                //让currentBoy指向第一个小孩
                currentBoy=first;
            }else{
                //将新节点加入到当前节点后方
                currentBoy.setNext(boy);
                //将新节点与第一个节点相连构成环状
                boy.setNext(first);
                //将指针向后移动一位
                currentBoy=boy;
            }

        }
    }

    //遍历环形列表
    public void showBoy(){
        //判断链表是否为空
        if(first==null){
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy current=first;
        while(true){
            System.out.printf("小孩的编号%d \n",current.getNo());
            //遍历完毕
            if(current.getNext()==first){
                break;
            }
            //current后移
            current=current.getNext();

        }
    }

    //根据用户的输入，计算小孩出圈的顺序
    //startNo 表示从第几个小孩开始数数
    //countNo 表示数几下
    //nums 表示最初有几个小孩在圈中
    public void countBoy(int startNo,int countNo, int nums){
        //校验
        if(first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper=first;

        //1:将helper指向最后一个节点
        while(true){
            if(helper.getNext()==first){
                break;
            }
            //指针后移
            helper=helper.getNext();
        }
        //2：小孩报数前，先让first和helper移动k-1次（移动到k这个小孩这里来）
        for(int j=0;j<startNo-1;j++){
            first=first.getNext();
            helper=helper.getNext();
        }
        //3:当小孩报数时，让first和helper指针同时移动，移动和m-1次,然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while(true){
            if(helper==first ){//说明圈中只有一个节点
                break;
            }
            //让first,helper同时移动countNo-1次，然后出圈
            for(int j=0;j<countNo-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点就是小孩要出圈的节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时first指向的小孩节点出圈
            first=first.getNext();
            helper.setNext(first);
        }
        //退出while循环时，权重只有一个节点了first.getNo()或者helper.getNo()
        System.out.printf("最后留在圈中的小孩编号%d \n",first.getNo());

    }



}


class Boy{
    /**
     * 编号
     */
    private int no;

    /**
     * 指向下一个节点，默认为null
     */
    private Boy next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int no) {
        this.no = no;
    }
}