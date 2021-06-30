package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: RedixSortingTest
 * @Package: com.master.chapter009
 * @Description: 基数排序（桶排序）
 * @Datetime: 2021/6/29 19:57
 * @author: ColorXJH
 */
public class RedixSortingTest {
    public static void main(String[] args) {
        int[] array={53,3,542,748,14,214};
        radixSort(array);//[542,53,3,14,214,748]
    }

    //基数排序方法
    public static void radixSort(int[] array){
       //第一轮排序（针对每个元素的个位经行排序处理）


       //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
       // 二维数组包含10个一维数组，为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定义伟array.length
       //基数排序是使用空间换时间的经典算法
       int[][]bucket=new int[10][array.length];

       //为了记录每个桶中实际存放了多少数据，我们定义一个一维数组来记录各个桶每次放入的数据个数
            //buckerElementCounts[0]记录的就是bucker[0]桶的放入数据的个数
        int[] buckerElementCounts=new int[10];

        for(int j=0;j<array.length;j++){
            //取出每个元素的个位
            int digitOfElement=array[j]%10;
            //将当前的数放入对应的桶中的对应的位置
            bucket[digitOfElement][buckerElementCounts[j]]=array[j];
            //记录桶中元素的个数+1;
            buckerElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组下标依次去除数据，放入原来数据组）
        int index=0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for(int k=0;k<buckerElementCounts.length;k++){
            //如果桶中有数据，我们才放入到原数组
            if(buckerElementCounts[k]!=0){
                //循环该桶（第k个桶，即第k个一维数组），放入
                for(int l=0;l<buckerElementCounts[k];l++){
                    //取出元素，放入到array
                    array[index]=bucket[k][l];
                }
            }
        }
        System.out.println("第一轮，对个位数的排序处理："+ Arrays.toString(array));

    }
}

//基数排序算法介绍
    //1：基数排序redix-sort属于分配式排序，又称桶子法bucket-sort,顾名思义，他是通过键值的各个位置的值，将要排序的元素分配至某些
        //"桶"中，达到排序的作用
    //2:基数排序算法是属于稳定的排序，基数排序法的效率是高效稳定的排序算法
    //3：基数排序算法是桶排序算法的扩展
    //4:基数排序是1887年赫尔曼-何乐礼发明的，他是这样实现的：将整数按照位数切割成不同的数字，然后按照每个位数分别比较

//基本思想：
    //将所有带比较数值统一为同样的数位长度，数位较短的数前面补零，然后从最低位开始，依次进行依次排序。这样从最低位排序一直到最高位排序完成以后
        //数列就变成一个有序序列
    //看下图理解
    //[53 3 542 748 14 214]
        //第一轮排序：将每个元素的个位数取出，然后再看整个数应该放在哪个对应的桶（一个一维数组）
        //按照这个桶的顺序（一维数组的下标）依次取出数据，放回到原数组=》【542 53 3 14 214 748】
        //第二轮排序，将每个元素的十位放在桶中排序，依次取出数据=》【3 14 214 542 748 53】
        //第三轮。。。最后得出数据=》【3 14 53 214 748】
        //多少论取决于最大数的位数


//代码实现见main方法
