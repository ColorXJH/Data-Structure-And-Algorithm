package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: QuickSortingTest
 * @Package: com.master.chapter009
 * @Description: 快速排序
 * @Datetime: 2021/6/28 21:39
 * @author: ColorXJH
 */
public class QuickSortingTest {
    public static void main(String[] args) {
        /*int[] array={-9, 78, 0, 23, -567, 70};
        quickSort(array,0, array.length-1);
        System.out.println(Arrays.toString(array));*/

        //测试夸苏排序的速度
        int [] arrays2=new int[80000];
        for(int i=0;i<80000;i++){
            arrays2[i]=(int)(Math.random()*9000000);
        }
        System.out.println("=======开始排序==========");
        long time1=System.currentTimeMillis();
        quickSort(arrays2,0, arrays2.length-1);
        long time2=System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1)+"毫秒");
        System.out.println(Arrays.toString(arrays2));
    }

    //快速排序
    public static void quickSort(int[] array,int left ,int right){
        //左右索引（下表）
        int l=left;
        int r=right;
        //pivot中轴z值
        int pivot=array[(left+right)/2];
        int temp=0;//临时变量，作为交换时使用
        //目的：让比pivot小的值放到其左边，比pivot大的放到其右边
        while(l<r){
            //在pivot的左边一直找，找到一个大于，等于pivot的值，才退出
            while(array[l]<pivot){
                l+=1;
            }
            //在pivot的右边一直找，找到一个小于，等于pivot的值，才退出
            while(array[r]>pivot){
                r-=1;
            }
            //说明pivot左右两边的值，已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值
            if(l>=r){
                break;
            }
            //上面条件不满足的话，说明需要交换位置，找第二组，知道全部找完为止
            temp=array[l];
            array[l]=array[r];
            array[r]=temp;

            //如果交换完后发现:array[l]==pivot，则r-- ==>前移一步
            if(array[l]==pivot){
                r-=1;
            }
            //如果交换完后发现:array[r]==pivot，则l++ ==>后移一步
            if(array[r]==pivot){
                l+=1;
            }
        }

        //如果l==r,必须l++,r--,否则会出现栈溢出
        if(l==r){
            l+=1;
            r-=1;
        }

        //向左递归
        if(left<r){
            quickSort(array,left,r);
        }
        //向右递归
        if(right>l){
            quickSort(array,l,right);
        }
    }

}

//快速排序算法介绍：
    //快速排序是对冒泡排序的一种改进，基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另一部分的所有数据小
    //然后再按照此方法对这两部分数据进行快速排序，整个排序过程可以递归进行，一次达到整个数据变成有序序列
        // 2 10 8 22 34 5 12 28 21 11-》以11为基准=》   2 10 8 5   11   22 34 12 28 21
                            //以5为基准   2   5 10 8   以21为基准    12 21 22 34 28 ==》递归进行
                                // 2 5 8 10 11 12 21 22 28 34
    //或者如下思路：【-9 78 0 23 -567 70】
                //数组取中间的数0，将数组划分为两部分，设置两个指针，一个指向左边部分的第一个数，一个指向右边部分的第一个数
                //找出左边比0大的第一个数，右边比0小的第一个数，让他们交换位置=》 【-9 -567 0 78 70】
                //递归处理该逻辑=》【-567 -9 0 23 70 78】