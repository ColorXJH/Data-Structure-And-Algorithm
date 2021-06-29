package com.master.chapter009;

import java.util.Arrays;

/**
 * @ClassName: MergeSortingTest
 * @Package: com.master.chapter009
 * @Description: 归并排序
 * @Datetime: 2021/6/28 22:44
 * @author: ColorXJH
 */
public class MergeSortingTest {
    public static void main(String[] args) {
        /*int[] array={8,4,5,7,1,3,6,2};
        int[] temp=new int[array.length];//归并排序需要一个额外的空间开销
        mergeSort(array,0, array.length-1, temp);
        System.out.println("归并排序后="+ Arrays.toString(array));*/
        //速度测试
        int [] arrays2=new int[80000];
        int[] temp=new int[arrays2.length];//归并排序需要一个额外的空间开销
        for(int i=0;i<80000;i++){
            arrays2[i]=(int)(Math.random()*9000000);
        }
        System.out.println("=======开始排序==========");
        long time1=System.currentTimeMillis();
        mergeSort(arrays2,0,arrays2.length-1,temp);
        long time2=System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1)+"毫秒");
    }

    /**
     * 功能描述: 分+合并方法
     * @param: array
     * @param: left
     * @param: right
     * @param: temp
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/6/29 19:20
     */
    public static void mergeSort(int[] array,int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2;//中间索引
            //先向左递归分解
            mergeSort(array,left,mid,temp);
            //再向右递归分解
            mergeSort(array,mid+1,right,temp);
            //上述两步只能分解，如果没有下面这一步，则分解后原数组排序一样
            //到合并时，根据调用机制，最先合并栈顶元素，也就是最后分解的元素
            merge(array,left,mid,right,temp);
        }
    }

    /**
     * 功能描述: 合并的方法
     * @param: array :原始数组
     * @param: left:左边有序序列索引
     * @param: mid：中间索引
     * @param: right：右边索引
     * @param: temp：临时数组
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/6/29 18:45
     */
    public static void merge(int[]array,int left,int mid,int right,int[]temp){
        int i=left;//初始化i,左边有序序列的初始索引
        int j=mid+1;//初始化j,右边有序序列的初始索引
        int t=0;//指向tem数组的当前索引

        //1:先把左右两边（有序）的数据按照规则填充到temp数组。直到左右两边的有序序列有一边处理完毕为止
        while(i<=mid&&j<=right){//继续
            //如果左边的有序序列的当前元素小于右边的有序序列的当前元素，将左边的当前元素拷贝到temp,然后将各自下标后移一位
            if(array[i]<=array[j]){
                temp[t]=array[i];
                t+=1;
                i+=1;
            }else{//反之，将右边有序序列的当前元素拷贝到temp
                temp[t]=array[j];
                t+=1;
                j+=1;
            }
        }
        //2:把有剩余数据的一边的数据依次全部填充到temp
        while(i<=mid){//左边有序序列还有剩余元素，就全部填充到temp
            temp[t]=array[i];
            t+=1;
            i+=1;
        }
        while(j<=right){//右边有序序列还有剩余元素，就全部填充到temp
            temp[t]=array[j];
            t+=1;
            j+=1;
        }
        //3：将temp数组的元素拷贝到array,注意：并不是每次都拷贝所有的
        t=0;
        int tempLeft=left;
        //System.out.println("templeft="+tempLeft+"  right="+right);
        while(tempLeft<=right){//第一次合并时，tempLeft=0.right=1;第二次：tempLeft=2，right=3;第三次 tempLeft=0,right=3,...最后一次才是0，7
            array[tempLeft]=temp[t];
            t+=1;
            tempLeft+=1;
        }


    }
}

//归并排序：归并排序是利用归并的思路实现排序的算法，该算法采用经典的分治策略（分治法将问题分解成一些小的问题然后递归求解，而治的阶段
//则将分的阶段得到的答案修补在一起，即：分而治之）。
    //示意图如下
        //8 4 5 7 1 3 6 2
    //分  8 4 5 7     1 3 6 2
    //  8 4  5 7    1 3   6 2
    //8  4  5  7   1  3  6  2
    //治  48 57      13  26
    //    4578       1236
    //        12345678
    //可以看到这种结构很像一个完全二叉树，归并排序我们采用递归去实现，（也可以采用迭代的方式去实现）
    //分的阶段就可以看成递归拆分子序列的过程，治可以看成为合并相邻有序子序列，我们需要将两个已经有序的子序列合并成一个有序序列
    //n个数字需要合并n-1次，线性增长，效率较高，我们就最后一次合并来做分析
        //4578  1236
    //有i j 两个下表参数，分别指向左右两个序列的初始位置，比较i, j位置的数据大小，把较小的数据放在第一位，然后让较小位置的序列索引
    //向后移动一位（因为已经较小的数据已经被比较出来了），然后让新的索引值再次和较大的索引值比较，然后将较小的值再次放入新数组的下一个位置
    //重复执行上面的步骤，依次将较小的数放入新的数组中
