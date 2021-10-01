package com.master.chapter017;

/**
 * @Description: 程序员常用的十种算法
 * @Author: ColorXJH
 * @CreateDate: 2021/7/26 13:30
 * @Version: 1.0
 */
public class ITUsuallyAlgorithm {
    public static void main(String[] args) {
        int[]array={1,3,8,10,11,67,100};
        int index=BinarySearchNoRecursion.binarySearch(array,-8);
        System.out.println(index);
    }
}

//二分查找算法（非递归）
    //前面有学习过二分查找递归算法，现在看非递归
    //二分查找只适用于从有序的数列中进行查找（比如数字、字母），将数列排序后再进行查找
    //二分查找的时间为对数时间O(log2N),即查找到需要的目标位置最多需要log2N步，假设0-99队列（100个数），寻找目标30 ，最多需要log2 100,即最多需要7次

//代码实现：【1,3，8,10，11,67，100】，编程使用二分查找，非递归方式
class BinarySearchNoRecursion{
    /**
     * Description: 二分查找非递归实现，array升序排列
     * @Author: ColorXJH
     * @Date: 2021/7/26 13:46
     * @param array 带查找数组
     * @param target  需要查找的数据
     * @Return: int 返回对应下标，-1表示没找到
     **/
    public static int binarySearch(int[] array,int target){
        int left=0;
        int right=array.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(array[mid]==target){
                return mid;
            }else if(array[mid]>target){
                right=mid-1;//向左边查找
            }else{
                left=mid+1;//向右边查找
            }
        }
        return-1;
    }
}



//分治算法
    //分治算法是一种很重要的算法，字面理解为分而治之，就是把复杂的问题分成两个或更多的相同或相似的子问题，再把子问题分成更小的子问题，知道最后的子问题
    //可以简单的求解，原问题的解即等于子问题的解的合并，这个技巧是很多高效算法的基础，如排序算法（快排，归并），傅里叶变换（快速傅里叶变换）...
    //分治算法可以求解的经典问题：
        //二分查找
        //大整数乘法
        //棋盘覆盖
        //合并排序
        //快速排序
        //线性时间选择
        //最接近点对问题
        //循环赛日程表
        //汉诺塔
    //分治算法基本步骤
        //1:分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
        //2:解决：若子问题规模较小而容易被解决则直接解决，否则递归的解决各个子问题
        //3:合并：将各个子问题的解合并为原问题的解
    //设计模式如下：
        //if |p|<=n0 then return (ADHOC(P))
        //将P问题分解为较小的子问题，P1，P2，P3...
        //for i<-1 to k
        //do yi<- Devide-and-Conquer(Pi)//递归解决Pi
        //T<-merge(y1,y2,y3...yk)//合并子问题
        //return T
        //其中|p|表示问题p的规模，n0为一阈值，表示当问题p的规模不超过n0时，问题已经容易直接解除，不必要再继续分解，ADHOC(P)是该分治法中的基本子算法
        //merge是分治法中的合并子算法 ，用于将问p的所有子问题p1,p2,p3...  pk的相应解页,y2,y3...yk合并为p的解

        //代码演示
        //test  use github token instead of username/password
        //TEST2

//分治算法的最佳实践--汉诺塔
    //汉诺塔问题源于一个古老的印度传说益智游戏，大梵天创造世界时制造了3根柱子，在一根柱子从下往上按照大小顺序摆放着64片黄金圆盘
    //大梵天命令婆罗门把圆盘从下面开始按照大小顺序重新摆放在另一个柱子上，并且规定，在小圆盘上不能放大圆盘，在三根柱子之间只能一次
    //移动一个圆盘，加入每秒钟一次，共需要多长时间呢，移动完这些圆盘需要5845.4亿年，地球上的一切生命都将泯灭
    //test