package com.master.chapter010;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 斐波那契（黄金分割法）查找算法 (前提是有序数组)
 * @date 2021/7/1 12:35
 */
public class FbnqQueryTest {
    public static int maxSize=20;
    public static void main(String[] args) {
        int[]array={1,8,10,89,1000,1234};
        int index=fibonaciSearch(array,1234);
        System.out.println(index);
    }
    //因为后面mid=low+fib(k-1)-1,需要使用到斐波那契数列，因此我们需要先获得一个斐波那契数列
    /**
     * description: 创建斐波那契数列
     * version 0.1.0
     * @return int[]
     * @param
     * @author ColorXJH
     * @date 2021/7/1 13:56
     */
    public static int[] fib(){
        int[]f=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for(int i=2;i<maxSize;i++){
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }
    /**
     * description: 斐波那契查找算法：非递归方式
     * version 0.1.0
     * @return int 返回索引
     * @param array 原数组
     * @param key  我们需要查找的关键码（数值）
     * @author ColorXJH
     * @date 2021/7/1 13:57
     */
    public static int fibonaciSearch(int[]array,int key){
        int low=0;
        int high=array.length-1;
        int k=0;//表示斐波那契分割数值的下标
        int mid=0;
        int[]f=fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while(high>f[k]-1){
            k++;
        }
        //因为f[k]的值可能大于数组的长度，因此我们需要使用Arrays类，构造一个新的数组，指向temp【】
        int[]temp= Arrays.copyOf(array,f[k]);//如果array的长度小，f(k)值大，拷贝的后部分会用0填充
        //对新的数组用最后的数来填充（array[high]）;
        //[1,8,10,89,1000,1234,0,0,0]=>[1,8,10,89,1000,1234,1234,1234,1234]
        for(int i=high+1;i<temp.length;i++){
            temp[i]=array[high];
        }
        //使用while循环处理，找到k
        while(low<=high){//只要这个条件满足就可以一直找
               mid=low+f[k-1]-1;
               if(key<temp[mid]){//像数组的左边查找
                   high=mid-1;
                   //为什么是k--,1:全部元素=全面元素+后面元素，2：f(k)=f(k-1)+f(k-2)
                   //3：因为前面有f[k-1]个元素，所以可以继续拆分，f[k-1]=f[k-2]+f[k-3]
                   //即在f[k-1]的前面继续查找，即为k--,即下次循环是mid=f[k-1-1]-1
                   k--;
               }else if(key>temp[mid]){//右边查找
                   low=mid+1;
                   //为什么是k-=2;
                   //1:全部元素=全面元素+后面元素，2:f(k)=f(k-1)+f(k-2)
                   //3:因为后面我们有f[k-2]个元素，，所以也可以继续拆分 f[k-1]=f[k-3]+f[k-4]
                   //4:即在f[k-2]的前面进行查找，k-=2;即f[k-1-2]-1;
                   k-=2;
               }else{//找到
                   //需要确定返回的是哪个下标，是mid还是high.（返回小一点的）
                   if(mid<=high){
                       return mid;
                   }else{
                       return high;
                   }
               }
        }
        return -1;//没找到；
    }
}

//斐波那契查找算法基本介绍
    //1：黄金分割点是指把一条线段分割为两个部分，使其一部分与全长之比等于另一部分与这部分之比，取其前三位数字的近似值为0.618
        //该比例称为黄金分割比，也称为中外比
    //2:斐波那契数列：{1，1，2，3，5，8，13，21，34，55}，相邻两数之和等于下一个数，其比例也无限接近0.618

//原理：
    //斐波那契查找原理与前面来两种类似，仅仅是改变了中间点mid的位置，mid不在是中间值或插值得到
    //而是位于黄金分割点附近，即mid=low+F(k-1)-1,F代表斐波那契数列
        //low         mid     high
    //       F(K-1)-1    F(k-2)-1
        //          F(K)-1
//对F(K-1)-1的理解:F代表斐波那契数列，k代表第几个元素
    //1：由斐波那契数列F(K)=F(K-1)+F(K-2)的性质（即n=n-1+n-2）,可以得到F(K)-1=F(K-1)-1+F(K-2)-1+1；
    //该式说明：只要顺序表的长度为F(K)-1,则可以将它分为F(K-1)-1和F(K-2)-1的两段，从而中间的值为mid=LOW+F(K-1)-1
    //2:类似的，每一个子段也可以用相同的方法分割
    //3：但是顺序表n的长度不一定等于F(K)-1,所以需要将原来顺序表的长度增加到F(K)-1,这里的k值只要能使得F(K)-1大于
        //或等于n即可，由下面的代码得到顺序表长度增加后，新的位置（从n+1到F(K)-1的位置，都赋值n位置的值即可）
        //while(n>F(K)-1){K++;}
