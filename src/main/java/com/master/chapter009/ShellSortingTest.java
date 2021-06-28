package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: ShellSortingTest
 * @Package: com.master.chapter009
 * @Description: 希尔排序算法
 * @Datetime: 2021/6/23 19:52
 * @author: ColorXJH
 */
public class ShellSortingTest {
    public static void main(String[] args) {
        int[] array={8,9,1,7,2,3,5,4,6,0};
        //shellSort2(array);
        //shellSort2(array);
        //测试交换法速度
        int [] arrays2=new int[80000];
        for(int i=0;i<80000;i++){
            arrays2[i]=(int)(Math.random()*9000000);
        }
        System.out.println("=======开始排序==========");
        long time1=System.currentTimeMillis();
        shellSort2(arrays2);
        long time2=System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1)+"毫秒");


    }
    //使用逐步推导的方式来编写希尔排序(交换/移动==》目前为交换)(效率很慢)
    public static void shellSort1(int[] array){
        /*int temp=0;
        //希尔排序的第一轮排序
        //因为第一轮排序是将10个数据分成了5组
        for(int i=5;i<array.length;i++){
            //遍历各组中所有的元素（共5组，每组有2个元素，步长为5）
            for(int j=i-5;j>=0; j-=5){
                //如果当前元素大于它加上步长后的那个元素，说明需要交换位置
                if(array[j]>array[j+5]){
                    temp=array[j];
                    array[j]=array[j+5];
                    array[j+5]=temp;
                }
            }
        }

        System.out.println("希尔排序第一轮之后："+ Arrays.toString(array));


        //希尔排序的第2轮排序
        //因为第2轮排序是将10个数据分成了5/2=2组
        for(int i=2;i<array.length;i++){
            //遍历各组中所有的元素（共5组，每组有2个元素，步长为5）
            for(int j=i-2;j>=0; j-=2){
                //如果当前元素大于它加上步长后的那个元素，说明需要交换位置
                if(array[j]>array[j+2]){
                    temp=array[j];
                    array[j]=array[j+2];
                    array[j+2]=temp;
                }
            }
        }
        System.out.println("希尔排序第2轮之后："+ Arrays.toString(array));

        //希尔排序的第3轮排序
        //因为第3轮排序是将10个数据分成了2/2=1组
        for(int i=1;i<array.length;i++){
            //遍历各组中所有的元素（共5组，每组有2个元素，步长为5）
            for(int j=i-1;j>=0; j-=1){
                //如果当前元素大于它加上步长后的那个元素，说明需要交换位置
                if(array[j]>array[j+1]){
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        System.out.println("希尔排序第3轮之后："+ Arrays.toString(array));*/

        //根据前面的逐步分析得到循环归纳
        int temp=0;
        int count=0;
        //分割步长
        for(int gap=array.length/2;gap>0;gap/=2){
            //遍历各组中的所有元素（共gap组，每组有length/gap组，步长gap）
            for(int i=gap; i<array.length;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    //如果当前元素大于它加上步长后的那个元素，说明需要交换位置
                    if(array[j]>array[j+gap]){
                        temp=array[j];
                        array[j]=array[j+gap];
                        array[j+gap]=temp;
                    }
                }
            }
            //System.out.println("希尔排序第"+(++count)+"轮之后："+ Arrays.toString(array));
        }
        //System.out.println("希尔排序第后："+ Arrays.toString(array));

    }

    //希尔排序移动法
    public static void shellSort2(int[]array){
        //分割步长
        for(int gap=array.length/2;gap>0;gap/=2){
            //从第gap个元素开始逐个对其所在的组进行直接插入排序
            for(int i=gap; i<array.length;i++){
               int j=i;
               int temp=array[j];
               if(array[j]<array[j-gap]){
                   while(j-gap>=0&&temp<array[j-gap]){
                        //移动
                       array[j]=array[j-gap];
                       j-=gap;
                   }
                   //当退出while循环的时候，就给temp找到了插入的位置
                   array[j]=temp;
               }
            }
            //System.out.println("希尔排序第"+(++count)+"轮之后："+ Arrays.toString(array));
        }
        System.out.println("希尔排序后："+ Arrays.toString(array));
    }
}

//希尔排序算法介绍
    //希尔排序是希尔于1959年提出的一种排序算法，希尔排序也是一种插入排序，他是简单插入排序改良之后的一个更加高效的版本，也成为缩小增量排序
//希尔排序算法基本思想
    //希尔排序是把记录按照下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时
    //整个文件恰好被分成一组，算法便终止

//例子如下： 8 9 1 7 2 3 5 4 6 0
    //初始增量：length/2=5,意味着整个数组被分成5组，[8,3] [9,5] [1,4] [7,6] [2,0]
    //对这5组分别进行直接插入排序，结果如下，=》3 5 1 6 0 8 9 4 7 2
    //然后缩小增量 ：5/2=2,将得到的数分为两组  [3 1 0 9 7] [5 6 8 4 2]
    //对上面两组再次进行直接插入排序，结果如下：[0 2 1 4 3 5 7 6 9 8]
    //再次缩小增量 2/2=1,此时整个数组为[0 2 1 4 3 5 7 6 9 8],再对这个数组进行直接插入排序得到最后的有序数组
        //[0 1 2 3 4 5 6 7 8 9]
            //希尔排序时，对有序序列在插入时采用交换法，并测试排序速度（速度相对较慢）
            //希尔排序时，对有序序列在插入时采用移动法，并测试排序速度（速度相对较快）