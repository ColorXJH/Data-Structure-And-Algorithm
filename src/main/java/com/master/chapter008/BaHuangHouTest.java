package com.master.chapter008;

/**
 * @ClassName: BaHuangHouTest
 * @Package: com.master.chapter008
 * @Description: 八皇后问题
 * @Datetime: 2021/6/7 20:43
 * @author: ColorXJH
 */
public class BaHuangHouTest {
    //定义一个max,表示有多少个皇后
    int max=8;
    //定义一个数组array，保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
    int [] array=new int[max];
    static int count=0;
    static int judgeCount=0;
    public static void main(String[] args) {
        //
        BaHuangHouTest test=new BaHuangHouTest();
        test.check(0);//把第一个皇后放在第一行的第一列
        System.out.println(count);
        System.out.println(judgeCount);
    }


    //编写一个方法，放置第n个皇后
        //特别注意：check是每一次递归时，进入到check中都有一套for循环，因此会有回溯
    private void check(int n){
        if(n==max){//n=8,其实8个皇后就依然放好了
            print();
            return ;
        }
        //依次放入皇后，并判断是否冲突
        for(int i=0;i<max;i++){
            //先把当前这个皇后n,放到该行的第1列，
            array[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){//不冲突
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n]=i;,即将第n个皇后，放置在本行的后移的一个位置
        }
    }

    //查看当我们放置第n个皇后后，就去检测该皇后是否和前面已经摆放的皇后是否冲突
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            //判断第n个皇后是否和前面n-1个皇后在同一列
            // 第n个皇后是否和第i皇后是否在同一条斜线上（这个和数组array的设计结构有关系）
                //n=1放置在第二行第二列 1 n=1 array[1]=1,math.abs(1-0)=1  math.abs(array[n]-array[i])=math.abs(1-0)=1
            //判断在同一行默认实现（n每次都在递增，不需要写）
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }


    //写一个方法，可以将皇后摆放的位置打印出来
    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }


}

//八皇后问题是回溯算法的经典案例，再8*8格子的象棋盘上摆放8个皇后，使其不能互相攻击，即任意两个皇后不能处于同一行同一列或者同一斜线上
    //问有多少种摆发

//8皇后问题算法思路分析（92种）
    //1:第一个皇后先放在第一行第一列
    //2:第二个皇后放在第二行第一列，然后判断是否ok,如果不ok,继续放在第二列，第三列，依次把所有的列放完，找到合适的位置
    //3:继续第三个皇后，还是第一列，第二列，第三列。。。，直到第8个皇后也能放在不冲突的位置，算式找到了一个正确解
    //4:当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到
    //5:然后回头继续第一个皇后放第二列，后面继续循环执行1，2，3，4的步骤
        //说明：理论上需要一个二维数组棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题 arr[8]={0,4,7,5,2,6,1,3}
            //0代表第一个皇后，位置为第一行第一列，4表示第二个皇后，代表第二行第5列。。。。：arr[i]=val:val表示第i+1个皇后
            //放在第i+1行的第val+1列


