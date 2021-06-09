package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: SelectSortingTest
 * @Package: com.master.chapter009
 * @Description: 选择排序
 * @Datetime: 2021/6/9 19:11
 * @author: ColorXJH
 */
public class SelectSortingTest {
    public static void main(String[] args) {
            int[] array=new int[]{101,34,119,1,123,33,66,2};
            selectSort(array);
            int [] arrays2=new int[80000];
            for(int i=0;i<80000;i++){
                arrays2[i]=(int)(Math.random()*8000000);
            }
            System.out.println("=======开始排序==========");
            long time1=System.currentTimeMillis();
            selectSort(arrays2);
            long time2=System.currentTimeMillis();
            System.out.println("耗时："+(time2-time1)/1000+"秒");

    }

    //选择排序
    public static void selectSort(int[] array){
        /*//使用逐步推到方式来了解选择排序

        //第一轮
        //原始数组： 101  34 119 1
        //第一轮排序：1 34 119 101
        //算法： 先简单-》后复杂 ，就是可以把一个复杂的算法，查分成简单的问题，然后再逐步解决，完成高级抽象
        //假定最小位置的索引与值
        int minIndex=0;
        int min =array[0];
        //让最小值和后面的length-1个书开始比较
        for(int j=0+1;j< array.length;j++){
            if(min>array[j]){//说明假定的最小值不是最小值
                //重置最小值
                minIndex=j;
                min=array[j];
            }
        }
        //将最小值和array[0]交换
        if(minIndex!=0){
            array[minIndex]=array[0];
            array[0]=min;
        }
        System.out.println("-------第一轮后-------");
        System.out.println(Arrays.toString(array));

        //第二轮
        minIndex=1;
        min =array[1];
        //让最小值和后面的length-1个书开始比较
        for(int j=1+1;j< array.length;j++){
            if(min>array[j]){//说明假定的最小值不是最小值
                //重置最小值
                minIndex=j;
                min=array[j];
            }
        }
        //将最小值和array[1]交换
        if(minIndex!=1){
            array[minIndex]=array[1];
            array[1]=min;
        }

        System.out.println("-------第二轮后-------");
        System.out.println(Arrays.toString(array));

        //第三轮
        minIndex=2;
        min =array[2];
        //让最小值和后面的length-1个书开始比较
        for(int j=2+1;j< array.length;j++){
            if(min>array[j]){//说明假定的最小值不是最小值
                //重置最小值
                minIndex=j;
                min=array[j];
            }
        }
        //将最小值和array[1]交换
        if(minIndex!=2){
            array[minIndex]=array[2];
            array[2]=min;
        }

        System.out.println("-------第三轮后-------");
        System.out.println(Arrays.toString(array));*/


        //在推到的过程中我们发现了规律，因此可以使用for循环来解决

        for(int i=0;i< array.length-1;i++){
            int minIndex=i;
            int min =array[i];
            //让最小值和后面的length-1个书开始比较
            for(int j=i+1;j< array.length;j++){
                if(min>array[j]){//说明假定的最小值不是最小值
                    //重置最小值
                    minIndex=j;
                    min=array[j];
                }
            }
            //将最小值和array[i]交换
            if(minIndex!=i){
                array[minIndex]=array[i];
                array[i]=min;
            }


        }
        System.out.println(Arrays.toString(array));
    }
}

//选择排序基本介绍
    //1:选择排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出某一元素，再依照规定交换位置后达到排序的目的
    //2：排序思想：是一种简单的排序方法，基本思路是：第一次从array[0]-array[n-1]中选取最小值，与array[0]交换，第二次从array[1]
        //-array[n-1]中选取最小值，与array[1]交换，第三次从array[2]-array[n-1]中选出最小值与array[2]交换...
        //第i次从array[i-1]-array[n-1]中选出最小值与array[i-1]交换，第n-1次从array[n-1-1]-array[n-1]中取出最小值与array[n-2]交换
        //总共通过n-1次，得到一个按排序妈从小到大的有序序列

    //101  34  119  1
        //第一轮： 1 34 119 101
        //第二轮： 1 34 119 101
        //第三轮： 1 34 101 119
    //说明：选择排序一共有数组大小-1轮排序
    //每一轮排序，又是一个循环，循环的规则大致是：先假定当前这个数是最小的数，然后和后面的每个数进行比较，如果发现发现有比当前数更小的数，
            //就重新确定最小数并确定下标，当这轮循环结束时就得到本轮最小数和下标，然后交换位置即可

    //选择排序要快于冒泡排序
