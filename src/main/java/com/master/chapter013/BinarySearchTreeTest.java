package com.master.chapter013;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 二叉查找树
 * @date 2021/7/16 8:52
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        int[]array={7,3,10,12,5,1,9,2};
        BinarySortTree tree=new BinarySortTree();
        //循环添加节点到二叉排序树
        for(int i=0;i<array.length;i++){
            tree.add(new Node(array[i]));
        }
        //中序遍历二叉排序树(如果对一个二叉排序树进行中序遍历，它刚好是一个有序（一般为升序）的数组)
        tree.infixOrder();
        //测试删除叶子节点
        //tree.deleteNode(2);
        //System.out.println("删除叶子节点2后的遍历：");
        //tree.infixOrder();
        //测试删除只有一个子节点的节点
        //tree.deleteNode(1);
        //System.out.println("删除子节点1后的遍历：");
        //tree.infixOrder();
        //删除有两颗子树的节点
        tree.deleteNode(2);
        tree.deleteNode(5);
        tree.deleteNode(9);
        tree.deleteNode(12);
        tree.deleteNode(7);
        tree.deleteNode(3);
        tree.deleteNode(10);
        tree.deleteNode(1);

        System.out.println("删除子节点后的遍历：");
        System.out.println("root="+tree.getRoot());
        tree.infixOrder();
    }
}


//需求：给你一个数列{7，3，10，12，5，1，9}，要求能够高效的完成对数据的查询和添加
//解决方案分析：
    //1：使用数组
        //1：数组未排序，优点：直接在数组末尾添加，速度快，缺点；查找速度慢
        //2：数组排序，优点：可以使用二分查找，速度快，缺点：为了保证数组有序，在添加新数据时，找到插入位置后
            //后面的数据需整体移动，速度慢
    //2：使用链式存储-链表
        //不管链表是否有序，查找速度都慢，添加数据速度比数组块，不需要将数据整体移动
    //3：使用二叉排序树/二叉查找树BST

//二叉排序树介绍
    //BST，对于二叉排序树的任何一个非叶子节点，要去左子节点的值比当前节点的值小，右子节点的值比当前节点的值大
    //如果有相同的值，可以将该节点放在左子节点或右子节点
    //【7，3，10，12，5，1，9】，对应的二叉排序树为；
    //              7
    //          3       10
    //       1     5  9     12  接着插入2，得到下一行
    //         2

//二叉排序树的创建和遍历
    //一个数组创建成对应的二叉排序树，并使用中序遍历二叉排序树


//创建二叉排序树
class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node){
        if(root==null){
            root=node;
        }else{
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    //查找要删除的节点
    public Node search(int value){
        if(root==null){
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value){
        if(root==null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }
    //编写方法
    /**
     * description: 返回以node为根节点的二叉排序树的最小节点的值.
     * version 0.1.0
     * @return int 以node为根节点的二叉排序树的最小节点的值
     * @param node 传入的节点（当作二叉排序树的根节点）
     * @author ColorXJH
     * @date 2021/7/19 10:03
     */
    public int delRightTreeMin(Node node){
        Node target=node;
        //循环的查找左节点，就会找到最小值
        while(target.left!=null){
            target=target.left;
        }
        //这时，target就指向了最小节点
        //删除最小节点
        deleteNode(target.value);
        //返回最小值
        return target.value;
    }

    //删除节点
    public void deleteNode(int value){
        if(root==null){
            return;
        }else{
            //1:需要先去找到要删除的节点
            Node targetNode=search(value);
            //如果没有找到要删除的节点，则返回
            if(targetNode==null){
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个节点
            if(root.left==null&&root.right==null){
                root=null;
                return;
            }
            //去找到targetNo的父节点
            Node parent=searchParent(value);
            //如果要删除的节点是叶子节点
            if(targetNode.left==null&&targetNode.right==null){
                 //判断targetNode是parent的左。右子节点
                if(parent.left!=null&&parent.left.value==value){
                    parent.left=null;
                }else if(parent.right!=null&&parent.right.value==value){
                    parent.right=null;
                }
            }else if(targetNode.left!=null&&targetNode.right!=null){//删除有两颗子树的节点
                int minValue=delRightTreeMin(targetNode.right);
                targetNode.value=minValue;
                //也可以不用上面的思路，在左子树中找最大的
            }else{//删除只有一颗子树的节点
                if(targetNode.left!=null){
                    //如果parent节点为空则应该直接让root节点指向该节点的子节点。这里需要先判断一下找个节点是否为空
                    if(parent!=null){
                        //如果要删除的节点有左子节点
                        //如果targetNode是parent的左/右子节点
                        if(parent.left.value==value){
                            parent.left=targetNode.left;
                        }else{
                            parent.right=targetNode.left;
                        }
                    }else{
                       root=targetNode.left;
                    }
                }else{//要删除的节点有右子节点
                   if(parent!=null){
                       if(parent.left.value==value){
                           parent.left=targetNode.right;
                       }else{
                           parent.right=targetNode.right;
                       }
                   }else{
                     root=targetNode.right;
                   }
                }
            }

        }

    }
}

//创建Node节点
class Node{
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

    //添加节点的方法
    //递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node){
        if(node==null){
            return;
        }
        //判断传入节点的值，和当前子树的根节点的关系
        if(node.value<this.value){
            //如果当前节点左子节点为空
            if(this.left==null){
                this.left=node;
            }else{
                //递归的向左子树添加
                this.left.add(node);
            }
        }else{//node.value>=this.value==添加的节点值大于等于当前节点的值
            if(this.right==null){
                this.right=node;
            }else{
                //递归的向右子树添加
                this.right.add(node);
            }
        }
    }


    //中序遍历
    public void infixOrder(){
        if(this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right!=null){
            this.right.infixOrder();
        }
    }



    //查找要删除的节点
    //value:希望删除的节点值
    //如果找到返回该节点，否则返回null
    public Node search(int value){
        if(value==this.value){
            return this;
        }else if(value<this.value){//如果查找的值小于当前节点，向左子树递归查找
            //如果左子节点为空
            if(this.left==null){
                return null;
            }
            return this.left.search(value);
        }else{//如果查找的值不小于当前节点，向右子树递归查找
            if(this.right==null){
                return null;
            }
            return this.right.search(value);
        }
    }


    //查找要删除节点的父节点
    //value:要查找的节点值
    //返回要查找节点的父节点，没有返回null
    public Node searchParent(int value){
        //当前节点就是要删除节点的父节点
        if((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)){
                return this;
        }else{
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if(value<this.value&&this.left!=null){
                //向左子树递归查找
                return this.left.searchParent(value);
            }else if(value>=this.value&&this.right!=null){
                //向右子树递归查找
                return this.right.searchParent(value);
            }else{
                return null;//没有找到父节点
            }
        }

    }
}

//              7
//          3       10
//       1     5  9     12
//         2

//二叉树的删除
    //二叉树的删除情况比较复杂，有三种情况需要考虑
    //1：删除叶子节点，比如2，5，9，12
        //思路：1：首先找到要删除的节点 targetNode
        //2：找到目标节点targetNode的父节点parentNode(考虑是否存在父节点)（对于叶子节点这个其实可以不用考虑）
        //3：确定targetNode是parentNode的左子节点还是右子节点
        //4：根据前面的情况来对应删除（parent.left/right=null）
    //2：删除只有一颗子树的节点，比如1
        //思路：1：首先找到要删除的节点 targetNode
        //2：找到目标节点targetNode的父节点parentNode(考虑是否存在父节点)
        //3：确定targetNode的子节点是左子节点还是右子节点
        //4：确定targetNode是parentNode的左子节点还是右子节点
            //1：targetNode是parent的左子节点，并且target的子节点为左子节点（四种情况）
                    //parent.left=target.left
                    //parent.left=target.right
                    //parent.right=target.left
                    //parent.right=target.right
//3：删除有两个子树的节点，比如7，3，10，以10为例子
    //思路：1：首先找到要删除的节点 targetNode
    //2：找到目标节点targetNode的父节点parentNode(考虑是否存在父节点)
    //3：从targetNode的右子树找到最小的节点 //或者左子树的最大节点
    //4：用一个临时变量将最小节点的值保存 temp （12）
    //5：删除该最小节点 //10的第一个右子节点(12)
    //6：targetNode.value=temp //将临时变量的值赋值到target节点




