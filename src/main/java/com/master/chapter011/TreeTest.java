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


