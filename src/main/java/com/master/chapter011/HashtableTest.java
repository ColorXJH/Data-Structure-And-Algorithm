package com.master.chapter011;



import java.util.Scanner;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 数据结构-哈希表(散列)
 * @date 2021/7/1 14:41
 */
public class HashtableTest {
    public static void main(String[] args) {
        //创建一个hash表
        HashTab tab=new HashTab(7);
        //写一个简单的菜单
        String key="";
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            System.out.println("delet:删除雇员");
            key=sc.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id=sc.nextInt();
                    System.out.println("输入名字");
                    String name=sc.next();
                    //创建一个雇员
                    Emp emp= new Emp(id,name);
                    tab.add(emp);
                    break;
                case "list":
                    tab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int id2=sc.nextInt();
                    tab.findEmpId(id2);
                    break;
                case "delet":
                    System.out.println("请输入要删除的id");
                    int id3=sc.nextInt();
                    tab.deleteById(id3);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}
//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}


//创建hashtable,管理多条链表
class HashTab{
   private int size;//表示有多少条链表（取模使用）
   private EmpLinkedList[] empLinkedListArray;
   public HashTab(int size){
       //初始化数组链表
       this.size=size;
       empLinkedListArray=new EmpLinkedList[size];
       //留一个坑,这是不要忘了分别初始化我们的每一条链表
       for(int i=0;i<size;i++){
           empLinkedListArray[i]=new EmpLinkedList();
       }

   }

   //添加雇员
    public void add(Emp emp){
       //根据员工的id,得到该员工应当添加到那条链表
       int empNo=hashFun(emp.id);
       //将emp加入到对应的链表中即可
        empLinkedListArray[empNo].add(emp);
    }

    //遍历所有的链表,遍历hash表（数组+链表）
    public void list(){
       for(int i=0;i<size;i++){
           empLinkedListArray[i].list(i);
       }
    }
    //根据输入的id,查找雇员
    public void findEmpId(int id){
       //使用散列函数确定在那条链表中查找
        int empNo=hashFun(id);
        Emp emp=empLinkedListArray[empNo].findEmpById(id);
        if(emp!=null){
            System.out.printf("在第%d条链表中找到雇员：id=%d\n",empNo+1,id);
        }else{
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //按照id删除
    public void deleteById(int id){
        //根据员工的id,得到该员工应当添加到那条链表
        int empNo=hashFun(id);
        //删除
        empLinkedListArray[empNo].deleteEmpById(id);
    }

    //便些一个散列函数（取模法）
    public int hashFun(int id){
       return id%size;
    }

}



//创建EmpLinkedList，表示链表
class EmpLinkedList{
    //头指针，指向第一个雇员，因此这个链表的head是直接指向第一个雇员的
    private Emp head;//默认null
    //添加雇员到链表
        //假设添加雇员时，id是自增长的，即id的分配总是从小到大，
        //因此直接将该雇员加入到本链表末尾即可（可以考虑id不是自增的情况）
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head==null){
            head=emp;
            return;
        }
        //如果不知添加第一个雇员，则使用一个辅助指针，帮助我们定位到最后
        Emp current=head;
        while(true){
            if(current.next==null){//说明已经到最后一个节点
                break;
            }
            current=current.next;//后移一个节点
        }
        //退出时直接将emp加入即可
        current.next=emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head==null){//链表为空
            System.out.println("第"+no+"条链表为空");
            return;
        }
        System.out.println("第"+no+"条链表的信息为：");
        Emp current=head;//头节点的辅助指针
        while(true){
            System.out.printf("=>id=%d name=%s",current.id,current.name);
            if(current.next==null){//已经到最后节点
               break;
            }
            current=current.next;//后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到就返回emp,如果没找打就返回null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if(head==null){
            return null;
        }
        //辅助指针
        Emp current=head;
        while(true){
            if(current.id==id){//找到
                break;//这时current就指向要查找的雇员
            }
            //退出
            if(current.next==null){//说明遍历当前链表没有找到该雇员
                current=null;
                break;
            }
            current=current.next;//后移
        }
        return current;
    }


    //删除雇员
    public void deleteEmpById(int id){
        //找到需要删除的节点的前一个结点
        Emp temp=head;
        //判断链表是否为空
        if(temp==null){
            System.out.println("无数据");
            return;
        }
        //头节点
        if(head.id==id){
            head=head.next;
            return;
        }
        boolean flag=false;
        while(true){
            //非头节点
            if(temp.next==null){
                break;
            }
            if(temp.next.id== id){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("未找到相同编号%d的节点\r\n",id);
        }
    }

}













//案例：有一个公司，当有新的员工来报道时，要求将该员工的id加入，当输入员工id时。可以查找到员工信息
//要求：不适用数据库，尽量节约内存，速度越快越好=》哈希表【散列】

//哈希表的基本介绍：
    //1：哈希表也叫散列表，是根据关键码值（kv）而直接进行访问的数据结构，也就是说，它通过把关键码值映射到表中的一个位置来访问记录，
    //以加快查找的速度，整个映射函数叫做散列函数，存放记录的数组叫做散列表
        //1：使用链表来实现哈希表，该链表不带表头【即：链表的第一个节点就存放雇员信息】
        //2：实现思路及代码实现