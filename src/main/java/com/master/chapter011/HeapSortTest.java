package com.master.chapter011;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 树结构的应用-堆排序
 * @date 2021/7/6 16:17
 */
public class HeapSortTest {
    public static void main(String[] args) {
        //要求将数组进行升序排列
        int[]array={4,6,8,5,9};

        //heapSort(array);
        //堆排序的速度很快 O(nlogn)==>线性对数阶
        int [] arrays2=new int[80000];
        for(int i=0;i<80000;i++){
            arrays2[i]=(int)(Math.random()*8000000);
        }
        System.out.println("=======开始排序==========");
        long time1=System.currentTimeMillis();
        heapSort(arrays2);
        long time2=System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1)/1000+"秒");
        System.out.println(Arrays.toString(arrays2));
    }
    /**
     * description: 编写一个堆排序的方法
     * version 0.1.0
     * @return void
     * @param array
     * @author ColorXJH
     * @date 2021/7/7 10:23
     */
    public static void heapSort(int[]array){
        //分布完成
        /*adjustHeap(array,1,array.length);
        System.out.println(Arrays.toString(array));
        adjustHeap(array,0,array.length);
        System.out.println(Arrays.toString(array));*/

        //完成最终代码
        //将无序序列构建成一个堆，根据肾虚降序需求选择大顶堆或小顶堆
        int temp;//临时变量c
        for(int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素沉到数组末尾
        //重新调整结构，是剩余数组满足大顶堆定义，继续反复执行上述步骤，知道整个序列有序
        for(int j=array.length-1;j>0;j--){
            //交换
            temp=array[j];
            array[j]=array[0];
            array[0]=temp;
            //接下来反复调整，直到整个序列有序
            adjustHeap(array,0,j);//0代表从顶上元素开始，算然分析是c
       }

    }
    /**
     * description: 将一个数组（二叉树），调整成一个大顶堆，i对应6，4
     * 完成将以i对应的非叶子节点的树调整成大顶堆 【4 6 8 5 9】 -》【4 9 8 5 6】
     * version 0.1.0
     * @return void
     * @param array 要调整得数组
     * @param i  非叶子节点在数组中得索引
     * @param length   对多少个元素进行调整
     * @author ColorXJH
     * @date 2021/7/7 10:22
     */
    public static void adjustHeap(int[]array,int i,int length){
        int temp=array[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //k代表以i为节点的左子节点
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length&&array[k]<array[k+1]){//左子节点值小于右子节点的值
                k++;//k指向右子节点
            }
            if(array[k]>temp){//如果子节点大于父节点
                array[i]=array[k];//把较大的值赋值给当前的节点
                i=k;//让i指向k,继续循环比较
            }else{
                break;//
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶上（i原来的位置）
        array[i]=temp;//将temp值放到调整后的位置

    }
}

//堆排序
    //1：堆排序是利用“堆”这种数据结构设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复杂度
        //均是O(nlogn),它也是不稳定的排序
    //2：堆是具有以下性质的完全二叉树：每个节点的值都大于或等于其左右孩子节点的值，称为大顶堆，注意：
        //没有要求节点的左孩子的值和右孩子的值的大小关系
    //3：每个节点的值都小于或等于其左右孩子节点的值，称为小顶堆
    //4：大顶堆实例如下：
            //                          60（0）
            //                  45（1）         40（2）
            //              20（3）  25（4）  35（5）  30（6）
            //            10（7） 15（8）
        //我们对堆中的节点按层进行编号，映射到数组就是如下样子
            //【50 45 40 20 25 35 30 10 15】
            //  0  1  2  3  4  5  6  7  8
        //大顶堆特点：array[i]>=array[2*i+1]&&array[i]>=array[2*i+2]
            //其中： i 对应第几个节点，i 从0开始编号

        //小顶堆实例如下
            //                             10（0）
            //                    20（1）               15（2）
            //              25（3）     50（4）   30（5）      40（6）
            //          35（7）  45（8）
            //小顶堆： array[i]<=array[2*i+1]&&array[i]<=array[2*i+2]
                //其中：i 对应第几个节点，从0开始编号
        //一般升序采用大顶堆，降序采用小顶堆

//堆排序的基本思想
    //1：将待排序的序列构成一个大顶堆（将树用数组的形式存放）
    //2：此时整个序列的最大值就是堆顶的根节点
    //3：将其与末尾元素进行交换，此时末尾值就是最大值
    //4：然后将剩余n-1个与那苏重新构成一个堆，这样会得到n个元素的次小值，如此反复执行，便能得到一个有序序列了
//可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后就得到一个有序序列了

//例子：4 6 8 5 9 要求使用堆排序，将数组升序排列
//1:
    //              4
    //          6       8
    //        5    9
    //[4,6,8,5,9]
//2: 此时我们从最后一个非叶子节点开始（叶节点自然不用调整，第一个非叶子节点 array.length/2-1=5/2-1=1,也就是下面的6节点）
    //从左到右，从上至下进行调整
        //      4                       4
        //   6     8   --》           9    8
        //  5  9                    5   6
        //6从左到右，从下到上进行比较: 6与左右子节点比较，右子节点大于本节点，则交换
//3：再找到第二个非叶子节点4，由于4，9，8中9最大，所以交换4和9，得到
        //              9
        //          4       8
        //        5   6
//4：这时交换导致了子根 4，5，6结构混乱，继续调整4，5，6交换4和6，得到如下：
        //              9
        //           6     8
        //        4     5
//5：此时我们将一个无序序列构成了一个大顶堆
//6：将堆顶元素与末尾元素进行交换，使末尾元素最大，9 6 8 5 4=》4 6 8 5 9
        // 然后继续调整堆，再将堆顶元素与末尾元素交换，得到第二大元素
        //如此反复进行交换，重建，交换




