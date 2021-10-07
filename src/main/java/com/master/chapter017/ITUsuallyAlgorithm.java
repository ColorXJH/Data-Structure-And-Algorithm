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

//分治算法的最佳实践--汉诺塔
    //汉诺塔问题源于一个古老的印度传说益智游戏，大梵天创造世界时制造了3根柱子，在一根柱子从下往上按照大小顺序摆放着64片黄金圆盘
    //大梵天命令婆罗门把圆盘从下面开始按照大小顺序重新摆放在另一个柱子上，并且规定，在小圆盘上不能放大圆盘，在三根柱子之间只能一次
    //移动一个圆盘，加入每秒钟一次，共需要多长时间呢，移动完这些圆盘需要5845.4亿年，地球上的一切生命都将泯灭
    //mbp-token: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

    //汉诺塔游戏的思路分析：
        //1:如果只有一个盘，a->c
        //2:如果n>2,我们总是可以看成两个盘
            //1:最上面 部分的盘子 2：最下面的一个盘子
                //先把最上面的盘子从a->b,把最下面的盘子从a->c ,把b 塔下面的所有盘子从b->c
    class HanoiTower{
        public static void main(String[] args) {
            hanniTower(3,'a','b','c');
        }
        //汉诺塔的移动方法，使用分治算法
        public static void hanniTower(int num,char a,char b,char c){
            //如果只有一个盘
            if(num==1){
                System.out.println("第一个盘从"+a+"->"+c);
            }else{
                //总是看成两个盘子：1：最上面部分的盘子，2：最下面的一个盘
                //1:先把上面的所有盘子（num-1）从a->b,移动过程会使用到c
                hanniTower(num-1,a,c,b);
                //2:把最下面的盘子从a->c
                System.out.println("第"+num+"个盘子从"+a+"->"+c);
                //3:把B塔的所有盘子从b->c,移动过程使用到a塔
                hanniTower(num-1,b,a,c);

            }
        }

    }


//动态规划算法
    //应用场景：背包问题：有一个背包：容量为4磅，现有如下物品
        //吉他 1   1500
        //音响 4   3000
        //电脑 3   2000
    //要求达到的目标为装入的背包的总价值最大，并且重量不超出，要求装入的物品不能重复
    //算法介绍： 将大问题转化为小问题进行解决，从而一步步获取最优解的处理算法，动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干个
        //子问题，先求解子问题，然后从这些子问题的解得到原问题的解
        //与分治算法不同的是，适合用于动态规划求解的问题，经分解得到的子问题往往不是互相独立的（即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解）
        //动态规划算法可以通过填表的方式来逐步推进，得到最优解

    //分析思路和图解：背包问题主要是指一个给定容量的背包，若干具有一定价值和重量的物品，如何选择物品放入背包中，使其价值最大化，其中又分为01背包和
        //完全背包（每种物品都有无限件可用）
        //这里的问题属于01背包，即每个物品最多放一个，而无限背包可以转化为01背包
    //主要思想：利用动态规划来解决，每次遍历到的第i个物品，根据w[i]和v[i]来确定是否需要将该物品放入到背包中，即对于给定的n个物品，假设v[i]和w[i]
        //分别为第i个物品的价值和重量，c为背包的容量，再令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值，则我们有如下结果：
            //v[i][0]=v[0][j]=0  表示表的第0行第0列
            //当w[i]>j时，v[i][j]=v[i-1][j]  当准备加入的新增的商品容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
            //当j>=w[i]时，v[i][j]=max{v[i-1][j],v[i-1][j-w[i]]+v[i]}  当准备键入的新增的商品的容量小于等于当前背包的容量，
                //装入的方式求最大值：v[i-1][j]：上一个单元格的装入的最大值
                //v[i]:表示当前商品的价值
                //v[i-1][j-w[i]]:装入i-1商品，到剩余空间j-w[i]的最大值

   class KnapsackProblem{
       public static void main(String[] args) {
           int[] w={1,4,3};//物品的重量
           int[] val={1500,3000,2000};//物品的价值
           int m=4;//背包的容量
           int n=val.length;//物品的个数
           //为了记录放入商品的情况，我们定义一个二维数组
           int[][]path=new  int[n+1][m+1];

           //创建二维数组，表
           int[][]v=new int[n+1][m+1];//v[i][j]表示在前i个物品中装入容量为j的背包中的最大价值
           //初始化第一行和第一列，这里再本程序中可以不用处理，因为默认为0
           for(int i=0;i<v.length;i++){
               v[i][0]=0;//将第一列设置为0;
           }
           for(int i=0;i<v[0].length;i++){
               v[0][i]=0;//将第一行设置为0
           }
           //根据前面得到的公式来动态规划处理
           for(int i=1;i<v.length;i++){//不处理第一行
               for(int j=1;j<v[0].length;j++){//不处理第一列
                    //公式
                   if(w[i-1]>j){//因为我们的程序i是从1开始的，因此原来的公式中的w[i]修改成w[i-1]
                        v[i][j]=v[i-1][j];
                   }else{//因为我们的i是从1开始的，因此下面的公式稍做了调整
                       //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                       if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
                           v[i][j]=val[i-1]+v[i-1][j-w[i-1]];
                           //把当前的情况记录到path
                           path[i][j]=1;
                       }else{
                           v[i][j]=v[i-1][j];
                       }
                   }
               }

           }

           //输出一下v
           for(int i=0;i<v.length;i++){
               for(int j=0;j<v[i].length;j++){
                   System.out.print(v[i][j]+"");
               }
               System.out.println();
           }

           //输出最后我们放入了哪些商品，遍历path(正向遍历)
           /*for(int i=0;i<path.length;i++){
               for(int j=0;j<path[i].length;j++){
                   if(path[i][j]==1){
                       System.out.printf("第%d个商品放入到背包\n",i);
                   }
               }
           }*/

           //只求出最后的放入情况
           int i=path.length-1;//行的最大下标
           int j=path[0].length-1;//列的最大下标
           while(i>0&&j>0){
                if(path[i][j]==1){
                    System.out.printf("第%d个商品放入到背包\n",i);
                    j-=w[i-1];
                }
                i--;
           }


       }
   }


