package com.master.chapter011;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.awt.*;

/**
 * @ClassName: TreeTest
 * @Package: com.master.chapter011
 * @Description: 树结构
 * @Datetime: 2021/7/1 20:20
 * @author: ColorXJH
 */
public class TreeTest {
    public static void main(String[] args) {
        //现需要创建一棵二叉树
        BinaryTree tree=new BinaryTree();
        //创建需要的节点
        HeroNode root=new HeroNode(1,"宋江");
        HeroNode node2=new HeroNode(2,"吴用");
        HeroNode node3=new HeroNode(3,"卢俊义");
        HeroNode node4=new HeroNode(4,"林冲");
        HeroNode node5=new HeroNode(5,"关胜");

        //说明，我们先手动创建二叉树，以后可以使用递归方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        tree.setRoot(root);
        //测试
        System.out.println("前序遍历");
        tree.preOrder();
        System.out.println("中序遍历");
        tree.infixOrder();
        System.out.println("后序遍历");
        tree.postOrder();
        System.out.println("前序遍历查找");
        HeroNode node=tree.preOrderSearch(5);
        if(node!=null){
            System.out.println(node.getNo()+"---"+node.getName());
        }
        System.out.println("中序遍历查找");
        HeroNode nodes=tree.infixOrderSearch(5);
        if(nodes!=null){
            System.out.println(nodes.getNo()+"---"+nodes.getName());
        }
        System.out.println("后序遍历查找");
        HeroNode nodess=tree.postOrderSearch(5);
        if(nodess!=null){
            System.out.println(nodess.getNo()+"---"+nodess.getName());
        }
        System.out.println("--------------ooooo--------------");
        System.out.println("删除前：");
        tree.preOrder();
        System.out.println("删除节点");
        tree.deletNode(5);
        System.out.println("删除5后：");
        tree.preOrder();
        tree.deletNode(3);
        System.out.println("删除3后：");
        tree.preOrder();

        //顺序存储二叉树的遍历
        int[]arra={1,2,3,4,5,6,7};
        ArrayBinaryTree atree=new ArrayBinaryTree(arra);
        //atree.preOrder(0);//传入根节点
        atree.preOrder();

        //中序线索二叉树遍历
        HeroNode2 root1=new HeroNode2(1,"tom");
        HeroNode2 node21=new HeroNode2(3,"jack");
        HeroNode2 node22=new HeroNode2(6,"smith");
        HeroNode2 node23=new HeroNode2(8,"mary");
        HeroNode2 node24=new HeroNode2(10,"king");
        HeroNode2 node25=new HeroNode2(14,"dim");
        //建立联系
        root1.setLeft(node21);
        root1.setRight(node22);
        node21.setLeft(node23);
        node21.setRight(node24);
        node22.setLeft(node25);
        BinaryTree2 trees=new BinaryTree2();
        trees.setRoot(root1);
        trees.threadNodes();
        //测试，以10号节点测试（node24）
        HeroNode2 left=node24.getLeft();
        HeroNode2 right=node24.getRight();
        System.out.println("10号节点的前驱节点为"+left);
        System.out.println("10号节点的后继节点为"+right);
        System.out.println("使用线索化的方式遍历线索化二叉树");
        trees.threadList();//8,3,10,1,14,6
        //线索化二叉树的遍历不再使用递归，同时充分利用了空余的节点，
        //使用线性的连接代替那些不同的子树，
    }


}


//1:先创建节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //编写前中后序遍历方法
    //1:前序
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //递归向左左子树前序遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右右子树前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }

    }

    //2：中序
    public void infixOrder(){
        //先递归向左子树中序遍历
        if(this.left!=null){
            this.left.infixOrder();;
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    //3：后序
    public void postOrder(){
        //先递归左子树后续遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //递归右子树后续遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        System.out.println("次数");
        //1：先判断当前节点的no是否等于要查找的，如果相等，则返回当前节点
        //2：如果不等，则判断当前节点的左子节点是否为空，如果不为空则递归前序查找
        //3：如果左递归前序查找找到了节点，则返回，否则继续判断当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if(this.no==no){
            return this;
        }
        HeroNode res=null;
        if(this.left!=null){
            res=this.left.preOrderSearch(no);
        }
        if(res!=null){//说明我们左子树上找到了节点
            return res;
        }
        if(this.right!=null){
            res=this.right.preOrderSearch(no);
        }
        //向右不管是否找到，都返回
        return res;

    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        //1：判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        //2：如果左递归未找到节点，则和当前节点比较，如果相等则返回当前节点，否则继续进行右递归的中序查找
        //3：如果右递归中序查找找到则返回，否则返回空
        HeroNode res=null;
        if(this.left!=null){
            res=this.left.infixOrderSearch(no);
        }
        if(res!=null){
            return res;
        }
        System.out.println("次数");
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            res=this.right.infixOrderSearch(no);
        }
        return  res;

    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        //1：判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        //2：如果找到就返回，如果没找到就判断当前节点的右子节点是否为空，如果不为空，则右递归后序查找
        //3：如果找到就返回，否则就和当前节点进行比较，找到就返回，否则返回null
        HeroNode res=null;
        if(this.left!=null){
            res=this.left.postOrderSearch(no);
        }
        if(res!=null){
            return res;
        }
        if(this.right!=null){
            res=this.right.postOrderSearch(no);
        }
        if(res!=null){
            return res;
        }
        System.out.println("次数");
        if(this.no==no){
            return this;
        }
        return res;
    }


    //递归删除节点
    //如果叶子节点直接删除，如果非叶子节点删除子树
    public void deletNode(int no){
        //1：因为二叉树是单向的，所以我们判断当前节点的子节点是否需要删除，而不能判断当前节点是否需要删除
        //2：如果当前节点的左子节点不为空，并且左子节点的编号就是需要需要删除的节点，=》this.left=null当前，结束递归，返回
        //3：如果当前节点的右子节点不为空，并且右子节点的编号就是需要需要删除的节点，=》this.right=null当前，结束递归，返回
        //4：如果2，3步都没有删除节点，那么我们就向左子树递归删除
        //5：如果4步都没有删除节点，那么我们就向右子树递归删除
        if(this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.deletNode(no);
        }
        if(this.right!=null){
            this.right.deletNode(no);
        }

    }

    //删除节点
    public void deletNode2(int no){
        if(this.left!=null&&this.left.no==no){
            if(this.left.left!=null&&this.left.right!=null){
                this.left=this.left.left;
            }else if(this.left.left!=null&&this.left.right==null){
                this.left=this.left.left;
            }else if(this.left.right!=null&&this.left.left==null){
                this.left=this.left.right;
            }else{
                this.left=null;
            }
        }
        if(this.right!=null&&this.right.no==no){
            if(this.right.left!=null&&this.right.right!=null){
                this.right=this.right.left;
            }else if(this.right.left!=null&&this.right.right==null){
                this.right=this.right.left;
            }else if(this.right.right!=null&&this.right.left==null){
                this.right=this.right.right;
            }else{
                this.right=null;
            }
        }
        if(this.left!=null){
            this.left.deletNode(no);
        }
        if(this.right!=null){
            this.right.deletNode(no);
        }
    }

}


//2:定义一个二叉树 binaryTree
//二叉树提供遍历方案，真正的执行在hero中执行
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后续遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
    //删除节点
    public void deletNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else{
                root.deletNode(no);
            }
        }else{
            System.out.println("空树不能删除");
        }
    }
    //删除节点2
    //不希望删除整个子树，规定入轨下方只有一个节点，则让这个节点代替原来节点，如果该节点右两个子节点，则让左子节点代替该节点
    public void deletNode2(int no){
        if(root!=null){
            if(root.getNo()==no){
                if(root.getLeft()!=null){
                    root=root.getLeft();
                }
            }
           root.deletNode2(no);
        }else{
            System.out.println("空树不能删除");
        }
    }



}

//二叉树
    //为什么需要树这种数据结构
    //1：数组存储方式的分析
        //优点：通过下标方式访问元素，速度快，杜宇有序数组，还可以使用二分查找提高检索速度
        //缺点：如果要检索具体某个值，或者插入值（按一定顺序），会整体移动，效率较低
    //2:链式存储方式分析
        //优点：在一定程度上对数组存储有优化（比如：插入一个数值节点，只需要将插入节点，链接到链表即可，删除效率也很好）
        //缺点：在进行检索时，效率仍然很低，比如检索某个值，需要从头节点开始遍历
    //3:树存储方式的分析
        //能提高数据存储，读取的效率，比如利用二叉排序树（binary sort tree），既可以保证数据的检索速度，同时也可以保证数据的插入，删除，修改速度
    //实例：【7 3 10 1 5 9 12】
    //数组是需要扩容的，每次在底层都需要创建新数组，将原来的数据拷贝到数组，并插入新的数据
    //很多集合的底层也是在维护数组或者链表
        //                  7--》根节点
        //          3              10--》树枝节点
        //     1        5       9       12--》叶子节点
        //  比上一级节点小的放在左边，比上一级节点大的放在右边
    //查找12：1：先与根节点7比较，比7大，比较其右节点，2：再与10比较，比起大查找右节点，3；再与12比较，相等=》找到，不相等=》未找到
    //添加13：（首先找位置，找到了12），找到以后，直接将13节点挂到12右边

    //树即保证了修改，又保证了插入，查询的速度

//树的常用术语
    //1：节点
    //2：根节点
    //3：父节点
    //4：子节点
    //5：叶子节点（没有子节点的节点）
    //6：节点的权（节点值）
    //7：路径（从root节点找到该节点的路线）
    //8：层（从1开始 root为第一层）
    //9：子树
    //10：树的高度（最大层数）
    //11：森林：多颗子树构成森林

//二叉树的概念
    //1：树的种类有很多（b,b+,二叉树，平衡二叉树...），每个节点最多只能有两个子节点的一种形式称为二叉树
    //2：二叉树的子节点分为左节点和右节点
    //3：如果该二叉树的所有叶子节点都在最后一层，并且节点总数为2^n-1,n为层数，则我们程为满二叉树
    //4：如果该二叉树的所有叶子节点都在最后一层或者倒数第二层，而且最后一层的叶子节点在左边连续，
        //倒数第二层的叶子节点在右边连续，我们称为完全二叉树
            //                  11                          11
            //              21      31              21              31
            //          41     51  61  71        14     15      61      71
            //               满二叉树           81    91     完全二叉树，如果删除了61节点，则就不是完全二叉树了，因为叶子节点不连续了（中间间断了）
            //                              最后一层从左到右 81 91  倒数第二层从右到左71 61 15 ，如果去掉61，中间就断掉了，不连续了


//二叉树的遍历
    //前序遍历：先输出父节点，再遍历左子树和右子树
    //中序遍历：先遍历左子树，再输出父节点，再遍历右子树
    //后序遍历：先遍历左子树，再遍历右子树，最后输出父节点
        //（前，中，后 对应父节点的输出顺序）
    //使用前序，中序，后序对下面的二叉树进行遍历
        //                  hero(1,宋江)
        //       hero(2,吴用)         hero(3,卢俊义)
        //                      hero(5,关胜)       hero(4,林冲)


//思路：
        //1：先创建一颗二叉树
        //2：前序遍历：
                    //1：先输出当前节点（初始时为根节点），
                    //2：如果其左子节点不为空，则递归继续前序遍历
                    //3：如果它的右子节点，则递归继续前序遍历
        //3：中序遍历：
                    //1：如果当前节点的左子节点不为空，则递归继续中序遍历
                    //2：输出当前节点
                    //3：如果当前节点的右子节点不为空，则递归继续中序遍历
        //4：后续遍历
                    //1：如果当前节点的左子节点不为空，则递归继续后序遍历
                    //2：如果当前节点的右子节点不为空，则递归继续后序遍历
                    //3：输出当前节点

        //输出当前节点是确保输出包含叶子节点的所有节点的关键，因为当递归到最后是，栈一层一层返回时回输出当前的每个节点
        //每递归一次（方法调用一次），都会开辟一个栈空间，将方法中的代码由下往上压入栈，等到出栈时就是按顺序执行


//二叉树-查找指定的节点，要求：
    //1:前序查找，中序查找，后序查找方法
    //2：分别使用三种查找方式查早heroNo=5节点
    //3：分析各查找方式，各查找了多少上次
//思路分析
    //1：前序查找
        //1：先判断当前节点的no是否等于要查找的，如果相等，则返回当前节点
        //2：如果不等，则判断当前节点的左子节点是否为空，如果不为空则递归前序查找
        //3：如果左递归前序查找找到了节点，则返回，否则继续判断当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
    //2：中序查找
        //1：判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        //2：如果左递归未找到节点，则和当前节点比较，如果相等则返回当前节点，否则继续进行右递归的中序查找
        //3：如果右递归中序查找找到则返回，否则返回空
    //3：后序查找
        //1：判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        //2：如果找到就返回，如果没找到就判断当前节点的右子节点是否为空，如果不为空，则右递归后序查找
        //3：如果找到就返回，否则就和当前节点进行比较，找到就返回，否则返回null


//二叉树-删除节点
    //1：如果删除的是叶子节点，则删除该节点
    //2：如果删除的是非叶子节点，则删除该子树
    //3：测试删除5号叶子节点和3号子树（扩展：只删除节点，不删除其下面子节点，然后重新连接树：较难）
//思路
    //1：因为二叉树是单向的，所以我们判断当前节点的子节点是否需要删除，而不能判断当前节点是否需要删除
    //2：如果当前节点的左子节点不为空，并且左子节点的编号就是需要需要删除的节点，=》this.left=null当前，结束递归，返回
    //3：如果当前节点的右子节点不为空，并且右子节点的编号就是需要需要删除的节点，=》this.right=null当前，结束递归，返回
    //4：如果2，3步都没有删除节点，那么我们就向左子树递归删除
    //5：如果4步都没有删除节点，那么我们就向右子树递归删除
    //6：考虑如果树是空树或者只有一个root节点，则等价于把二叉树置空（首先判断）


//二叉树-顺序存储二叉树
    //从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组
    //                         1（0）
    //                 2（1）         3（2）
    //              4（3）  5（4） 6（5）    7（6）
    //          int[]array=[1,2,3,4,5,6,7];
    //要求：
        //1：上面的树的二叉树节点，用数组的方式来存放
        //2：在遍历数组的时候，我们仍然可以以树的遍历方式来遍历（前序/中序/后序）遍历的方式来完成节点遍历

//顺序存储二叉树的概念介绍
    //1：顺序二叉树通常只会考虑完全二叉树
    //2：第n个元素的左子节点为2*n+1;==》比如第一个元素2，他的左子节点为4，1*2+1=3，4是第三个元素，也是数组中下标（索引）为3的元素
    //3：第n个元素的右子节点为2*n+2==》对应数组访问下标（索引）
    //4：第n个元素的父节点为（n-1）/2
    //5：n表示二叉树中的第几个元素（按0开始编号）
        //完全二叉树的顺序存储，仅需从根节点开始，按照层次依次将树中节点存储到数组即可。
            //1 2 3 4 5 6  数值
            //0 1 2 3 4 5  下标

//顺序存储二叉树遍历
    //需求：给定一个数组【1，2，3，4，5，6，7】，要求以二叉树前序遍历方式进行遍历，前序遍历的结果为1，2，4，5，3，6，7
    //（从顺序表中还原完全二叉树）
class ArrayBinaryTree{
    int[]arr;//存储数据节点的数组
    public ArrayBinaryTree(int[]arr){
        this.arr=arr;
    }
    //编写一个方法完成顺序存储二叉树前序遍历
    //index:数组下标
    public void preOrder(int index){
        //如果数组为空或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空，不能按照二叉树前序遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归判断
        if((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if(index*2+2<arr.length){
            preOrder(index*2+2);
        }
    }
    //重载
    public void preOrder(){
        this.preOrder(0);
    }
}

//顺序存储二叉树的应用实例
    //八大排序算法的堆排序，就会使用到顺序存储二叉树


//线索化二叉树
    //先看一个问题：将数列【1，3，5，8，18，14】构成一个二叉树
    //            1
    //        3       6
    //      8  10  14
    //当对上面的二叉树进行中序遍历时，数列为 8.3.10，1，14 6，但是6，8，10，14这几个节点的左右指针，并没有完全的利用上
    //如果我们希望充分的利用各个节点的左右指针，让各个节点可以指向自己的前后节点，怎么办？
    //解决方案：线索二叉树
//线索化二叉树基本介绍：
    //1：n个节点的二叉链表中n+1 (公式2n-(n-1)=n+1)个空指针域，利用二叉链表中的空指针域。存放指向该节点在某种遍历次序下的前驱和后继节点的指针
        //这种附加的指针称为“线索”
    //2：这种加上了线索的二叉链表称为线索链表，相应的二叉树被称为线索二叉树（Threaded Binary Tree）,根据线索性值不同，线索二叉树可以分为
        //前序线索二叉树，中序线索二叉树，后序线索二叉树 三种
    //3：一个节点的前一个节点被称为前驱节点
    //4：一个节点的后一个节点被称为后继节点
//上图线索二叉树中序遍历结果=》【8，3，10，1，14，6】
    //中序线索化        8的后继节点指向3，3的左右指针都使用了，不能改动，10的前驱指针指向3，后继指针指向1，14的左指针指向1，右指针指向6

//当线索化二叉树后，Node节点的属性left,right有如下情况：
    //1：left指向的是左子树，也可能指向的是前驱节点，比如1节点left指向的左子树，而10节点的left指向的就是前驱节点
    //2：right指向的是右子树，也可能指向的是后继节点，比如1节点right指向的是右子树，

//中序线索二叉树代码实现
//1:先创建节点HeroNode2
class HeroNode2{
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    //如果leftType=0表示指向的是左子树，=1表示指向前驱节点；rightType=0表示指向右子树，=1表示指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //编写前中后序遍历方法
    //1:前序
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //递归向左左子树前序遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右右子树前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }

    }

    //2：中序
    public void infixOrder(){
        //先递归向左子树中序遍历
        if(this.left!=null){
            this.left.infixOrder();;
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }
    //3：后序
    public void postOrder(){
        //先递归左子树后续遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //递归右子树后续遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode2 preOrderSearch(int no){
        System.out.println("次数");
        //1：先判断当前节点的no是否等于要查找的，如果相等，则返回当前节点
        //2：如果不等，则判断当前节点的左子节点是否为空，如果不为空则递归前序查找
        //3：如果左递归前序查找找到了节点，则返回，否则继续判断当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if(this.no==no){
            return this;
        }
        HeroNode2 res=null;
        if(this.left!=null){
            res=this.left.preOrderSearch(no);
        }
        if(res!=null){//说明我们左子树上找到了节点
            return res;
        }
        if(this.right!=null){
            res=this.right.preOrderSearch(no);
        }
        //向右不管是否找到，都返回
        return res;

    }

    //中序遍历查找
    public HeroNode2 infixOrderSearch(int no){
        //1：判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        //2：如果左递归未找到节点，则和当前节点比较，如果相等则返回当前节点，否则继续进行右递归的中序查找
        //3：如果右递归中序查找找到则返回，否则返回空
        HeroNode2 res=null;
        if(this.left!=null){
            res=this.left.infixOrderSearch(no);
        }
        if(res!=null){
            return res;
        }
        System.out.println("次数");
        if(this.no==no){
            return this;
        }
        if(this.right!=null){
            res=this.right.infixOrderSearch(no);
        }
        return  res;

    }
    //后序遍历查找
    public HeroNode2 postOrderSearch(int no){
        //1：判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        //2：如果找到就返回，如果没找到就判断当前节点的右子节点是否为空，如果不为空，则右递归后序查找
        //3：如果找到就返回，否则就和当前节点进行比较，找到就返回，否则返回null
        HeroNode2 res=null;
        if(this.left!=null){
            res=this.left.postOrderSearch(no);
        }
        if(res!=null){
            return res;
        }
        if(this.right!=null){
            res=this.right.postOrderSearch(no);
        }
        if(res!=null){
            return res;
        }
        System.out.println("次数");
        if(this.no==no){
            return this;
        }
        return res;
    }


    //递归删除节点
    //如果叶子节点直接删除，如果非叶子节点删除子树
    public void deletNode(int no){
        //1：因为二叉树是单向的，所以我们判断当前节点的子节点是否需要删除，而不能判断当前节点是否需要删除
        //2：如果当前节点的左子节点不为空，并且左子节点的编号就是需要需要删除的节点，=》this.left=null当前，结束递归，返回
        //3：如果当前节点的右子节点不为空，并且右子节点的编号就是需要需要删除的节点，=》this.right=null当前，结束递归，返回
        //4：如果2，3步都没有删除节点，那么我们就向左子树递归删除
        //5：如果4步都没有删除节点，那么我们就向右子树递归删除
        if(this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.deletNode(no);
        }
        if(this.right!=null){
            this.right.deletNode(no);
        }

    }

    //删除节点
    public void deletNode2(int no){
        if(this.left!=null&&this.left.no==no){
            if(this.left.left!=null&&this.left.right!=null){
                this.left=this.left.left;
            }else if(this.left.left!=null&&this.left.right==null){
                this.left=this.left.left;
            }else if(this.left.right!=null&&this.left.left==null){
                this.left=this.left.right;
            }else{
                this.left=null;
            }
        }
        if(this.right!=null&&this.right.no==no){
            if(this.right.left!=null&&this.right.right!=null){
                this.right=this.right.left;
            }else if(this.right.left!=null&&this.right.right==null){
                this.right=this.right.left;
            }else if(this.right.right!=null&&this.right.left==null){
                this.right=this.right.right;
            }else{
                this.right=null;
            }
        }
        if(this.left!=null){
            this.left.deletNode(no);
        }
        if(this.right!=null){
            this.right.deletNode(no);
        }
    }

}

//2:定义一个二叉树 binaryTree2
//实现线索化二叉树功能
class BinaryTree2{
    private HeroNode2 root;

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode2 pre=null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }
    //重载
    public void threadNodes(){
        this.threadNodes(root);
    }

    //注意这个方法！
    //编写对二叉树进行中序线索化的方法
    //node为当前需要线索化的节点
    public void threadNodes(HeroNode2 node){
        //如果node为null,不能线索化
        if(node==null){
            return;
        }
        //1:先线索化左子树
        threadNodes(node.getLeft());
        //2:线索化当前节点(有难度)

        //处理当前节点的前驱节点
        //以8节点为例子，来理解，8节点left=null,8节点的leftType=1
        if(node.getLeft()==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //处理后继节点（不太好来理解，大致为8的下一级别来想象）
        //如果上方node为8，则此时pre则为8的下一个前驱节点，所以它的后继节点是node（中序遍历）
        if(pre!=null&&pre.getRight()==null){
            //让前驱节点的右指针，指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点是下一个节点的前驱节点(注意)
        pre=node;

        //3：线索化右子树
        threadNodes(node.getRight());
    }


    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后续遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode2 preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    //中序遍历查找
    public HeroNode2 infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    //后序遍历查找
    public HeroNode2 postOrderSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
    //删除节点
    public void deletNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else{
                root.deletNode(no);
            }
        }else{
            System.out.println("空树不能删除");
        }
    }
    //删除节点2
    //不希望删除整个子树，规定入轨下方只有一个节点，则让这个节点代替原来节点，如果该节点右两个子节点，则让左子节点代替该节点
    public void deletNode2(int no){
        if(root!=null){
            if(root.getNo()==no){
                if(root.getLeft()!=null){
                    root=root.getLeft();
                }
            }
            root.deletNode2(no);
        }else{
            System.out.println("空树不能删除");
        }
    }

    //遍历线索化二叉树的方法（中序）
    public void threadList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode2 node=root;
        while(node!=null){
            //循环的找到leftType=1的节点，第一个找到的就是8节点，后面随着遍历而变化
            //当leftType=1时，说明该节点是按照线索化处理后的有效节点
            while(node.getLeftType()==0){
                node=node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType()==1){
                //获取当前节点的后继节点
                node=node.getRight();
                System.out.println(node);
            }
            //到这说明找到了类型不为1的节点
            //替换这个遍历的节点
            node=node.getRight();
        }
    }
}


//遍历线索化二叉树
    //说明：对前面的中序线索化二叉树，进行遍历
    //分析：因为线索化之后，各个节点的指向有变化，因此原来的遍历方式不能使用了（可能会出现死循环），这时需要使用新的遍历方式
        //遍历线索化二叉树，各个节点可以通过线性方式遍历，因此无需使用递归方式，这样也提高了遍历效率
        //遍历的次序应当和中序遍历保持一致















