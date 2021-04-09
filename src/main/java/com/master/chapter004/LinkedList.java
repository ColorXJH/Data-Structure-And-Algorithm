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