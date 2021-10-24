package com.master.chapter017;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ClassName: KnightDrive 骑士周游算法
 * @Package: com.master.chapter017
 * @Description:
 * @Datetime: 2021/10/24 14:22
 * @author: ColorXJH
 */
public class KnightDrive {
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //创建一个数组来标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性标记，是否棋盘的所有位置是否都被访问过了
    private static boolean finished;
    /**
     * 功能描述: 骑士周游问题算法
     * @param: chessboard 棋盘
     * @param: row 马儿当前的位置行，从0开始
     * @param: column 马儿当前位置的列，从0开始
     * @param: step 是第几步，初始位置就是第一步
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/10/24 15:03
     */
    public static void traversalChessboard(int[][]chessboard,int row,int column,int step){
        chessboard[row][column]=step;
        //马儿在一维数组的位置，标记该位置已经访问
        visited[row*X+column]=true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps=next(new Point(column,row));
        //对ps进行排序，排序的规则是对ps的所有的Point对象的下一步位置的数目进行非递减排序（优化）
        sort(ps);
        //遍历ps
        while(!ps.isEmpty()){
            Point p=ps.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过 ,参照： visited[row*X+column]
            if(!visited[p.y*X+p.x]){//没有访问过
                traversalChessboard(chessboard,p.y,p.x,step+1);
            }
        }
        //判断马儿是否完成了任务，step和应该走的步数（行列相乘-1）比较，如果没有达到相应数量，表示没有完成任务，将整个期盼置0
        if(step<X*Y&& !finished){//step<X*Y成立情况有两种：1：期盼到目前为止任然没有走完，2：棋盘处于回溯过程
            chessboard[row][column]=0;
            visited[row*X+column]=false;
        }else{
            finished=true;
        }
    }
    public static void main(String[] args) {
        X=8;Y=8;
        int row=1;//马儿初始位置的行，从1开始编号
        int column=1;//马儿初始位置的列，从1开始编号
        int[][] chessboard=new int[X][Y];
        visited=new boolean[X*Y];//初始值都是false
        //测试一下耗时
        System.out.println("开始周游===");
        long start=System.currentTimeMillis();
        traversalChessboard(chessboard,row-1,column-1,1);
        long end=System.currentTimeMillis();
        System.out.println("一共耗时："+(end-start)+"毫秒");
        //输出棋盘的最后情况
        for(int[] rows:chessboard){
            for(int step:rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }

    }
    /**
     * 功能描述: 根据当前位置（Point），计算马儿还能走哪些位置(Point)，并放入到一个集合中（ArrayList）,最多有8个位置
     * @param: currentPoint
     * @Return: java.util.ArrayList<java.awt.Point>
     * @Author: ColorXJH
     * @Date: 2021/10/24 14:45
     */
    public static ArrayList<Point> next(Point currentPoint){
        ArrayList<Point>ps=new ArrayList<>();
        Point p1=new Point();
        //表示马儿可以走5的位置
        if((p1.x=currentPoint.x-2)>=0&&(p1.y=currentPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走6的位置
        if((p1.x=currentPoint.x-1)>=0&&(p1.y=currentPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走7的位置
        if((p1.x=currentPoint.x+1)<X&&(p1.y=currentPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走0的位置
        if((p1.x=currentPoint.x+2)<X&&(p1.y=currentPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        //表示马儿可以走1的位置
        if((p1.x=currentPoint.x+2)<X&&(p1.y=currentPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        //表示马儿可以走2的位置
        if((p1.x=currentPoint.x+1)<X&&(p1.y=currentPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //表示马儿可以走3的位置
        if((p1.x=currentPoint.x-1)>=0&&(p1.y=currentPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        //表示马儿可以走4的位置
        if((p1.x=currentPoint.x-2)>=0&&(p1.y=currentPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这一步的所有的下一步的选择位置，进行非递减排序
    public static void sort(ArrayList<Point>ps){
         ps.sort(new Comparator<Point>() {
             @Override
             public int compare(Point o1, Point o2) {
                 //获取到o1的下一步的所有位置个数
                 int count1=next(o1).size();
                 //获取到o2的下一步的所有位置个数
                 int count2=next(o2).size();
                 if(count1<count2){
                     return -1;
                 }else if(count1==count2){
                     return 0;
                 }else{
                     return 1;
                 }
             }
         });
    }
}

//马踏棋盘算法也称为骑士周游算法
//将马随机放在国际象棋的8*8棋盘中【0-7】【0-7】，马按照走棋规则，走日进行移动，要求每个方格只进入一次，走遍棋盘上全部64个方格


//代码实现
    //1:马踏棋盘问题实际上是图的深度优先搜素（DFS）的应用
    //2:如果使用回溯（就是深度优先搜素）来解决，加入马儿踏了53个点，如图，走到了第53个，坐标位置（1，0），发现已经走到尽头，没办法，那只能回退了
    //查看其他的路径，在棋盘上不同的回溯，思路分析+代码实现
    //3:分析第一种方式的问题，并使用贪心算法进行优化，解决马踏棋盘问题
    //4:验证是否正确
        //1:创建棋盘chessBoard，是一个二维数组
        //2:将当前位置设置为以访问，然后根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中（ArrayList）,最多有8个位置，每走一步，就是用step+1
        //3:遍历ArrayList中存放的所有位置，看看哪个可以走通， 走的同就继续走不通就回溯
        //4:判断马儿是否完成了任务，step和应该走的步数（行列相乘-1）比较，如果没有达到相应数量，表示没有完成任务，将整个期盼置0
    //注意：马儿不同的走法，会得到不同的结果，效率也会有影响（优化）

        //优化思路：使用贪心算法对原来算法优化
            //1:我们获取到当前位置，可以走的下一个位置的集合:ArrayList<Point> ps=next(new Point(column,row));
            //2:我们需要对ps中所有的Point的下一步的所有集合的数目进行非递减排序,就ok了（减少回溯的可能性）
            //递减9，7，6，5，4
            //非递减：1，2，2，3，5，6，7，7，9，9（在递增过程中允许有重复的值）
            //非递增： 9，7，7，6，4，4，2，2，1（在递减过程中允许有重复的值）


