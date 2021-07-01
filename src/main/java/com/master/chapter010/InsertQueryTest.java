package com.master.chapter010;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 插值查找算法
 * @date 2021/7/1 10:51
 */
public class InsertQueryTest {
    public static void main(String[] args) {
        int[]array=new int[100];
        for(int i=0;i<100;i++){
            array[i]=i+1;
        }
        System.out.println(Arrays.toString(array));
        int index=insertValueSearch(array,0,array.length-1,3);
        System.out.println(index);
    }
    /**
     * description: 插值查找算法，前提数组有序
     * version 0.1.0
     * @return int 返回索引
     * @param array 原数组
     * @param left 左边索引
     * @param right 右边索引
     * @param value 要查找的值
     * @author ColorXJH
     * @date 2021/7/1 12:18
     */
    public static int insertValueSearch(int[]array,int left,int right,int value){
        System.out.println("调用次数");
        //value<array[0]||value>array[array.length-1]必须需要，，否则得到的mid可能越界，因为其参与mid计算
        if(left>right||value<array[0]||value>array[array.length-1]){
            return -1;
        }
        //求出mid （插值查找算法的公式：核心）
        int mid=left+(right-left)*(value-array[left])/(array[right]-array[left]);
        int midValue=array[mid];
        if(value>midValue){//向右递归
            return insertValueSearch(array,mid+1,right,value);
        }else if(value<midValue){//向左递归
            return insertValueSearch(array,left,mid-1,value);
        }else{
            return mid;
        }
    }
}
//插值查找原理介绍：
    //1：插值查找算法类似于二分查找算法，不同的是插值查找每次从自适应mid处开始查找
    //2：将折半查找中的求mid索引的公式，low表示左边，high表示右边：mid=(low+high)/2=low+1/2(high-low)改成
        //mid=low+[(key+a[low])/(a[high]-a[low])](high-low)
    //3:int midindex=low+(high-low)*(key-array[low])/(array[high]-array[low]);//插值索引，key代表要查找的值
    //4:举例查找1-100的数组
        //array[1,2,3...100];假设需要查找的值伟1，二分查找需要多次递归，如果使用插值查找，第一次mid:
            //int mid=0+(99-0)*(1-1)/(100-1)=0+99*0/99=0;一次就查找出来了
        //如果查找的值为100： int mid=0+(99-0)*(100-1)=0+99*99/99=99,就已经很接近目标了
    //代码实现如上

//说明：
    //1：对于数据量较大，关键字分布比较均匀的查找来说，采用插值查找，速度较快
    //2：关键字分布不均匀的情况下，该方法不一定比二分法好


