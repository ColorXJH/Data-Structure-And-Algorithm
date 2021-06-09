package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: InsertSortingTest
 * @Package: com.master.chapter009
 * @Description: 插入排序
 * @Datetime: 2021/6/9 20:06
 * @author: ColorXJH
 */
public class InsertSortingTest {
    public static void main(String[] args) {
        int[] array=new int[]{101,34,119,1,-1,89};
        insertSort(array);
        int [] arrays2=new int[80000];
        for(int i=0;i<80000;i++){
            arrays2[i]=(int)(Math.random()*9000000);
        }
        System.out.println("=======开始排序==========");
        long time1=System.currentTimeMillis();
        insertSort(arrays2);
        long time2=System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1)+"毫秒");

    }

    //插入排序
    public static void insertSort(int[] array){
        /*//使用逐步推导方式来理解
        //第一轮{101,34,119,1}-》{34 101 119 1}（将34插入到第一个有序列表中）

        //定义待插入的数
        int insertValue=array[1];
        //定义待插入数与有序表的数比较索引 ：即array[1]前面这个数的下标
        int insertIndex=1-1;
        //给insertValue找到插入的位置
        while(insertIndex>=0&&insertValue<array[insertIndex]){
            //insertIndex>=0 ==>保证在给insertValue找到插入位置时不越界
            //insertValue<array[insertIndex]==>待插入的数还没有找到插入的位置，就需要将array[insertIndex]后移
            array[insertIndex+1]=array[insertIndex];//101,34,119,1}-》{101 101 119 1},34已经被临时变量保存起来了
            insertIndex--;
        }
        //当推出while循环时，说明插入的位置找到了，insertIndex+1
        array[insertIndex+1]=insertValue;
        System.out.println("--第一轮插入后--");
        System.out.println(Arrays.toString(array));


        //第二轮
        insertValue=array[2];
        insertIndex=2-1;
        while(insertIndex>=0&&insertValue<array[insertIndex]){//34, 101, 119, 1
            //insertIndex>=0 ==>保证在给insertValue找到插入位置时不越界
            //insertValue<array[insertIndex]==>待插入的数还没有找到插入的位置，就需要将array[insertIndex]后移
            array[insertIndex+1]=array[insertIndex];
            insertIndex--;
        }
        array[insertIndex+1]=insertValue;
        System.out.println("--第二轮插入后--");
        System.out.println(Arrays.toString(array));

        //第三轮
        insertValue=array[3];
        insertIndex=3-1;
        while(insertIndex>=0&&insertValue<array[insertIndex]){//[(34, 101, 119), 1]
            //insertIndex>=0 ==>保证在给insertValue找到插入位置时不越界
            //insertValue<array[insertIndex]==>待插入的数还没有找到插入的位置，
            //就需要将array[insertIndex]后移 -->[34, 101, 119,119]->[34, 101, 101,119]->[34, 34, 101,119]->[1, 34, 101,119]
            array[insertIndex+1]=array[insertIndex];
            insertIndex--;
        }
        array[insertIndex+1]=insertValue;
        System.out.println("--第三轮插入后--");
        System.out.println(Arrays.toString(array));*/


        //经过前面推导发现可用for循环抽象
        //定义待插入的数
        int insertValue=0;
        //定义待插入数与有序表的数比较索引 ：即array[1]前面这个数的下标
        int insertIndex=0;
        for(int i=1;i< array.length;i++){
            //定义待插入的数
            insertValue=array[i];
            //定义待插入数与有序表的数比较索引 ：即array[1]前面这个数的下标
            insertIndex=i-1;
            //给insertValue找到插入的位置
            while(insertIndex>=0&&insertValue<array[insertIndex]){
                //insertIndex>=0 ==>保证在给insertValue找到插入位置时不越界
                //insertValue<array[insertIndex]==>待插入的数还没有找到插入的位置，就需要将array[insertIndex]后移
                array[insertIndex+1]=array[insertIndex];//101,34,119,1}-》{101 101 119 1},34已经被临时变量保存起来了
                insertIndex--;
            }
            //当推出while循环时，说明插入的位置找到了，insertIndex+1
                //判断是否需要赋值
            if(insertIndex+1!=i){//只有当不是最后一个位置时才需要赋值，如果在最后一个位置，默认不需要改变位置
                array[insertIndex+1]=insertValue;
            }
            //System.out.println("--第"+i+"轮插入后--");
            //System.out.println(Arrays.toString(array));

        }
        System.out.println(Arrays.toString(array));
    }
}

//插入排序基本介绍
    //插入排序属于内部排序法，，是对与排序的元素，以插入的方式找寻该元素的适当位置，以达到排序的目的
    //差人排序算法的基本思想是：把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表只包含一个元素，无序表中包含n-1个元素
        //排序过程中每次从无序表中取出第一个元素，把他的排序码依次与有序元素表的排序码进行比较，将他插入到有序表中的适当位置，使之成为新的有序表
            //(17)    3   25  14  20  9
            //第一次插入 （3 17）  25  14  20  9
            //第二次插入 （3 17 25）  14  20  9
            //第三次插入 （3 14 17 25） 20  9
            //第四次插入 （3 14 17 20 25 ）9
            //第五次插入 （3 9 14 17 20 25）
    //总结：一共需要插入元素length-1次

