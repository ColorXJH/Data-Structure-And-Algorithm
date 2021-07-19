package com.master.chapter014;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 平衡二叉树
 * @date 2021/7/19 11:53
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        //int[]array={4,3,6,5,7,8};
        //int[]array={10,12,8,9,7,6};
        int[]array={10,11,7,6,8,9};
        //创建树
        AVLTree tree=new AVLTree();
        //添加节点
        for(int i=0;i<array.length;i++){
            tree.add(new Node(array[i]));
        }
        //遍历
        System.out.println("中序遍历：");
        tree.infixOrder();
        System.out.println("平衡处理后：");
        System.out.println("树的高度："+tree.getRoot().height());
        System.out.println("左子树的高度："+tree.getRoot().leftHeight());
        System.out.println("右子树的高度："+tree.getRoot().rightHeight());
        System.out.println("当前根节点："+tree.getRoot());
        System.out.println(tree.getRoot().left);
    }
}

//先看一个案例：
//给你一个数列【1，2，3，4，5，6】，要求创建一个二叉排序树BST,并分析问题所在
//      1
//          2
//              3
//                  4
//                      5
//                          6
//1:左子树全部为空，从形式上看，更向一个单链表
//2：插入速度没有影响
//3：查询速度明显降低（因为需要依次比较），不能发挥bst的优势，因为每次还要比较左子树
//其查询速度比单链表还慢
//4：解决方案-平衡二叉树

//平衡二叉树AVL的基本介绍
//平衡二叉树也叫平衡二叉搜素树（self-balancing binary search tree）又称AVL树，可以保证查询效率较高
//具有以下特点：他是一颗空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一颗平衡二叉树
//平衡二叉树常用的实现方式有：红黑树，AVL(算法),替罪羊树，Treap,伸展树等
//以下那些是AVL树，为什么
//                  0                       0                   0
//          0              0            0       0            0      0
//      0       0                     0    0   0            0  0
//                                                               0

//平衡二叉树应用案例-单旋转（左旋转）
//左旋转：右子树的高度高，要降低高度，对其进行左旋转
//把当前节点的右子树的最小值拿到，左旋转放到左子树的最大值位置上
//要求：给定一个数列【4，3，6，5，7，8】，创建出对应的平衡二叉树
//其对应的二叉排序树如下：
//                      4
//                  3       6
//                        5    7
//                                8
//当插入8时，rightHeight-leftHeight>1,此时不再是一颗AVL树了，如何处理？-》左旋转
//1：创建一个新的节点newNode(以4这个值创建)：创建一个新的节点，值等于当前根节点的值
//2：把新节点的左子树设置为当前根节点的左子树：noewNode.left=root.left
//3：把新节点的右子树设置为当前根节点的右子树的左子树：newNode.right=root.right.left
//4：把当前根节点的值设置为右子节点的值 root.value=root.right.value
//5：把当前根节点的右子树设置为右子树的右子树：root.right=root.right.right
//6：把当前根节点的左子树设置为新的节点： root.left=newNode
//得到新的AVL如下：
//              6
//          4       7
//        3   5       8

//代码实现
//1：前提：计算出一棵树的高度 和计算出其左右子树的高度
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    //编写方法

    /**
     * description: 返回以node为根节点的二叉排序树的最小节点的值.
     * version 0.1.0
     *
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @return int 以node为根节点的二叉排序树的最小节点的值
     * @author ColorXJH
     * @date 2021/7/19 10:03
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时，target就指向了最小节点
        //删除最小节点
        deleteNode(target.value);
        //返回最小值
        return target.value;
    }

    //删除节点
    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            //1:需要先去找到要删除的节点
            Node targetNode = search(value);
            //如果没有找到要删除的节点，则返回
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //去找到targetNo的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是parent的左。右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子树的节点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
                //也可以不用上面的思路，在左子树中找最大的
            } else {//删除只有一颗子树的节点
                if (targetNode.left != null) {
                    //如果parent节点为空则应该直接让root节点指向该节点的子节点。这里需要先判断一下找个节点是否为空
                    if (parent != null) {
                        //如果要删除的节点有左子节点
                        //如果targetNode是parent的左/右子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//要删除的节点有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }

    }

}

//创建Node节点
class Node {
    Node left;
    Node right;
    int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以该节点为根节点的树的高度
    public int height() {
        //节点的左子树/右子树的最大值(递归调用的次数)+1（本身的高度）
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate(){
        //创建新的节点，以当前根节点的值创建
        Node newNode=new Node(value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left=left;
        //把新的节点的右子树设置为当前节点的右子树的左子树
        newNode.right=right.left;
        //把当前节点的值替换成右子节点的值
        value=right.value;
        //把当前节点的右子树设置成当前节点的右子树的右子树
        right=right.right;
        //把当前节点的左子节点设置成新的节点
        left=newNode;
    }

    //右旋转方法
    public void rightRotate(){
        Node newNode=new Node(value);
        newNode.right=right;
        newNode.left=left.right;
        value=left.value;
        left=left.left;
        right=newNode;
    }

    //添加节点的方法
    //递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入节点的值，和当前子树的根节点的关系
        if (node.value < this.value) {
            //如果当前节点左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {//node.value>=this.value==添加的节点值大于等于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }

        //判断：当添加完一个节点后，如果右子树的高度比左子树的高度大于1，就发生左旋转
        if(rightHeight()-leftHeight()>1){
            //如果它的右子树的左子树的高度大于它的右子树的高度
                //先对右子节点经行右旋转
                //然后对当前节点经行左旋转
            if(right!=null&&right.leftHeight()>right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate();
            }
            return ;//必须要
        }
        //判断：当添加完一个节点后，如果左子树的高度比右子树的高度大于1，就发生右旋转
        if(leftHeight()-rightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if(left!=null&&left.rightHeight()>left.leftHeight()){
                //先对当前节点的左节点（左子树）进行左旋转
                left.leftRotate();
                //再对当前节点行右旋转
                rightRotate();
            }else{
                //直接经行右旋转即可
                rightRotate();
            }

        }
    }


    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    //查找要删除的节点
    //value:希望删除的节点值
    //如果找到返回该节点，否则返回null
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//如果查找的值小于当前节点，向左子树递归查找
            //如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前节点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }


    //查找要删除节点的父节点
    //value:要查找的节点值
    //返回要查找节点的父节点，没有返回null
    public Node searchParent(int value) {
        //当前节点就是要删除节点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                //向左子树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归查找
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点
            }
        }

    }
}


//平衡二叉树应用案例-单旋转（右旋转）
    //要求：给定一个数列【10，12，8，9，7，6】，创建出平衡二叉树
    //二叉排序树如下：
    //              10
    //          8        12
    //       7     9
    //     6
    //问题：当插入6时，此时leftHeight()-rightHeight()>1,不再是一颗AVL树了，如何处理——》右旋转
        //右旋转：左子树的高度过高，为了平衡需要将其右旋转再平衡（这里是将9这个节点，通过右旋转，平衡到右子树）
            //将当前节点的左子树的最大值拿到，通过右旋转放入到右子树的最小值位置
    //1：创建一个新的节点newNode(以10这个值创建)，创建一个新的节点，值等于当前根节点的值
    //2：把新节点的右子树设置为当前节点的右子树：newNode.right=right
    //3：把新节点的左子树设置为当前节点的左子树的右子树：newNode.left=left.right
    //4：把当前节点的值替换为左子节点的值：value=left.value
    //5：把当前节点的左子树设置为左子树的左子树：left=left.left;
    //6：把当前节点的右子树设置为新的节点：right=newNode;
    //转换完成之后：
        //          8
        //      7       10
        //    6      9     12  仍然时一颗平衡的二叉树


//平衡二叉树应用案例-双旋转
    //前面两个案例都是进行单旋转（即依次旋转），就可以将非平衡二叉树转换为AVL,但是在某些情况下，单旋转不能完成平衡二叉树的转换
    //比如数列【10，11，7，6，8，9】 【2，1，6，5，7，3】
    //以【10，11，7，6，8，9】为例子
        //          10                     7
        //        7    11               6      10
        //      6   8                        8    11
        //            9                        9
    //问题分析：1：当符合右旋转的条件时，2：如果他的左子树的右子树高度大于它的左子树的高度，
            //3；先对当前这个节点的左节点进行左旋转，目的是为了降低高节点的左节点的右子树的高度
            //4：然后在对当前节点向右旋转操作即可，画图如下：
                //          10                  10               8
                //        7    11             8     11        7     10
                //      6   8               7   9           6      9  11
                //            9            6

   //左旋转是当前节点的右节点的左节点设置为旋转后的右子节点
   //右旋转是当前节点的左节点的右节点设置为旋转后的左子节点
