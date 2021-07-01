package com.master.chapter010;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 查找算法
 * @date 2021/7/1 8:46
 */
public class QueryAlgorithmTest {
    public static void main(String[] args) {
        int[]array={1,8,10,89,1000,1234};
        int index=orderSearch(array,89);
        System.out.println(index);
        int index2=binarySearch(array,0,array.length-1,9);
        System.out.println(index2);
        int[]array2={1,8,10,89,1000,1000,1000,1234};
        int[] array3=binarySearch2(array2,0,array2.length-1,1000);
        System.out.println(Arrays.toString(array3));
    }
    //线性查找:逐一比对，发现有相同的值时就返回，否则返回-1
    /**
     * description:
     * version 0.1.0
     * @return int 返回索引
     * @param array 原数组
     * @param value 要查找的值
     * @author ColorXJH
     * @date 2021/7/1 10:14
     */
    public static int orderSearch(int[] array,int value){
        for(int i=0;i<array.length;i++){
            if(array[i]==value){
                return i;
            }
        }
        return -1;
    }

    //二分查找(前提是数组有序)
    /**
     * description:
     * version 0.1.0
     * @return int  返回下表
     * @param array 查找数组
     * @param left  左边下表
     * @param right 右边下标
     * @param value 要查找的值
     * @author ColorXJH
     * @date 2021/7/1 10:13
     */
    public static int binarySearch(int[]array,int left,int right,int value){
        //当left>right时，说明递归了整个数组，但是还没有找到
        if(left>right){
            return -1;
        }
        int mid=(left+right)/2;
        int midValue=array[mid];
        if(value>midValue){//向右递归
            return binarySearch(array,mid+1,right,value);
        }else if(value<midValue){
            return binarySearch(array,left,mid-1,value);
        }else{
            return mid;
        }

    }


    //如果存在多个相同的数，如何全部返回
    //思路分析：找到mid时先不要返回，，向mid索引的左右两边扫描，将所有满足的值都加入数组
    public static int[] binarySearch2(int[]array,int left,int right,int value){
        int[] array2=new int[array.length];//也可以用List代替数组
        //当left>right时，说明递归了整个数组，但是还没有找到
        if(left>right){
            return array2;
        }
        int mid=(left+right)/2;
        int midValue=array[mid];
        if(value>midValue){//向右递归
            return binarySearch2(array,mid+1,right,value);
        }else if(value<midValue){
            return binarySearch2(array,left,mid-1,value);
        }else{

            //向mid索引的左边扫描，将满足的值加入到数组
            int temp=mid-1;
            int index2=0;
            while(true){
                if(temp<0||array[temp]!=value){//扫描完左边  退出
                   break;
                }
                //否则将temp放入数组
                array2[index2++]=temp;
                //temp左移
                temp-=1;
            }
            //将中间的数加入
            array2[index2++]=mid;
            //向mid索引右边扫描，将所有满足的数加入数组
            temp=mid+1;
            while(true){
                if(temp>array.length-1||array[temp]!=value){//扫描完右边，退出
                    break;
                }
                //否则就将temp放入数组
                array2[index2++]=temp;
                temp+=1;
            }
            return array2;
        }

    }


}

//查找算法介绍：
    //java中常见的查找算法有四种：1：顺序（线性）查找，2：二分查找/折半查找 3：插值查找 4：斐波那契查找（黄金分割点查找）

//1：线性查找算法
    //：有一个数列【1，8，10，89，1000，1234】，判断数列中是否包含此名称，如果找到了就提示找到了，并给出下标
    //：代码见orderSearch
//2：二分查找算法：（前提是有序）（递归-非递归）
    //请对有许数组进行二分查找【1，8，10，89，1000，1234】，输入一个数看看该数组是否存在此数，并求出下标，没有就返回-1
    //思路：1：首先确定该数组中间的下标mid=(left+right)/2  2：让需要查找的数和mid对应的数比较，如果value>mid,在mid的右边
            //因此需要递归的向右查找，如果mid>value,在mid的左边，递归查找，如果mid=value，就是该下标
            //2:何时结束递归：1：找到，结束递归 2：递归完数组仍然未找到，也要结束递归：left>right时就需要结束递归
    //代码实现见：binarySearch