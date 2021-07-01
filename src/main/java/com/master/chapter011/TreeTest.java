package com.master.chapter011;

/**
 * @ClassName: TreeTest
 * @Package: com.master.chapter011
 * @Description: 树结构
 * @Datetime: 2021/7/1 20:20
 * @author: ColorXJH
 */
public class TreeTest {
    public static void main(String[] args) {

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