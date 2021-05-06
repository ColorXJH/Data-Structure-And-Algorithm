package com.master.chapter004;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ColorXJH
 * @version 1.0
 * @description 链表
 * @date 2021/3/31 10:59
 */
public class LinkedList {
    public static void main(String[] args) {
        MemoryCell cell=new MemoryCell();
        cell.write(new Integer(73));
        Integer value=(Integer)cell.read();
        int val=value.intValue();
        System.out.println(val);
        java.util.LinkedList linkedList=null;
        ArrayList arrayList=null;
        Iterable iterable=null;
        Iterator iterator=null;
    }
}

class MemoryCell{
    private Object storeValue;
    public Object read(){
        return storeValue;
    }
    public void write(Object x){
        storeValue=x;
    }
}


//小结：
    //1:链表是以节点的方式来存储
    //2:每个节点包含data域，next域：指向下一个节点
    //3：链表的下一个节点不一定是连续存放
    //链表分带头节点的链表和不带头节点的链表，根据实际的需求来确定


//案例：使用带head头的单向链表实现-水浒传英雄排行榜管理
    //1:完成对英雄人物的增删改查
        //第一种方法在添加英雄时，直接添加到链表的尾部，
        //第二种方法在添加英雄时，根据排名将英雄插入到指定的位置，（如果有这个排名，则添加失败，并给出提示）


//实现见SingleLinkedList
