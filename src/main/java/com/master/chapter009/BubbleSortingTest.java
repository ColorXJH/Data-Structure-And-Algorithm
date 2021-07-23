package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: BubbleSortingTest
 * @Package: com.master.chapter009
 * @Description: 冒泡排序
 * @Datetime: 2021/6/8 22:20
 * @author: ColorXJH
 */
public class BubbleSortingTest {
    public static void main(String[] args) {
        //int [] array={3,9,-1,10,20};
        int [] array={1,2,3,4,5};
        int temp =0;//临时变量，交换时使用
        //大循环次数
        for(int i=0;i<array.length-1;i++){
            //每一趟内部循环的次数
            for(int j=0;j<array.length-1-i;j++){
                //如果前面的数比后面的数大则交换
                if(array[j]>array[j+1]){
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(array));
        }
        System.out.println("冒泡排序完成");
        System.out.println(Arrays.toString(array));
        System.out.println("----------------good-start-------------");
        goodSort(new int[]{1,2,3,4,5,6,-3});



        System.out.println("------测试一下冒泡排序的速度-------------");//O(N^2),给80000个数据测试
        int[] arrays=new int[80000];
        for(int i=0;i<80000;i++){
            arrays[i]=(int)(Math.random()*8000000);//生成一个[0,8000000）数
        }
        //System.out.println(Arrays.toString(arrays));
        long time1=System.currentTimeMillis();
        goodSort(arrays);
        long time2=System.currentTimeMillis();
        System.out.println("排序花费的时间为------：");
        System.out.println((time2-time1)/1000);

    }

    //冒泡排序的优化
    public static void goodSort(int[] array){
        int temp =0;//临时变量，交换时使用
        boolean flag=false;//标识变量，表示是否进行过交换
        //大循环次数
        for(int i=0;i<array.length-1;i++){
            //每一趟内部循环的次数
            for(int j=0;j<array.length-1-i;j++){
                //如果前面的数比后面的数大则交换
                if(array[j]>array[j+1]){
                    flag=true;
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            //System.out.println("第"+(i+1)+"趟排序后的数组");
            //System.out.println(Arrays.toString(array));
            if(!flag){//在一趟排序中，一次交换都没有发生
                break;
            }else{
                flag=false;//重置flag,进行下次交换
            }
        }
        System.out.println("冒泡排序完成");
        //System.out.println(Arrays.toString(array));

    }
}

//冒泡排序基本介绍
    //1:冒泡排序的基本思路是：通过对 待排序序列从前往后（从下表较小的元素开始），依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐
        //从前移向后部，就像水低的气泡一样逐渐向上冒
    //2:因为排序的过程中，各元素不断的接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此要在排序过程中设置一个标志flag
        //判断元素是否进行过交换，从而减少不必要的比较（优化）

//图解冒泡排序算法过程：3 9 -1  10 20  （如果相邻的元素逆序则交换，从小到大排序）
    //第一趟：（1）3，9比较 3 9 -1 10 20  （2）9 -1 比较 3 -1 9 10 20  (索引同时向后移动) 比较 9 10=》 3 -1 9 10 20 。。。 同时移动 比较10 20 3 -1 9 10 20
        //第一趟排序去定了最大的数（20）
   //第二趟：（1） 3 -1 比较 =》 -1 3 9 10 20 ；3，9比较=》-1 2 9 10 20 ；9 ，10比较=》-1 3 9 10 20
        //第二趟排序就是在接下来的四个数里找到最大的 10
   //第三趟排序（1） -1 ，3比较-1 3 9 10 20 ；3，9 比较-1 3 9 10 20
        //第三趟排序就是在接下来的四个数里找到最大的 9
   //第四趟排序（1） -1，3 比较 -1 3 9 10 20
        //第四趟排序就是在接下来的四个数里找到最大的 3
   //第五趟排序（5个数有四个已经排序了最大排序，最后一个不需要排序了） -1 =》 -1 3 9 10 20
        //第五趟排序就是在接下来的四个数里找到最大的 -1


//小姐冒泡排序规则：
    //1：一共进行了数组大小-1次的大循环
    //2:每一趟排序的次数在逐渐减少
    //3:如果发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序（这个就是优化）
    //4：代码实现见main

