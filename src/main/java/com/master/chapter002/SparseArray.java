package com.master.chapter002;

import java.io.*;
import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description 稀疏数组
 * @date 2021/3/24 16:53
 */
public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建一个原始的二维数组 11*11 ，0，表示没有棋子，1表示黑子，2表示蓝子
        int chessArr[][]=new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        //输出原始二维数组
        for(int[] row:chessArr){
            for(int  data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }
        //将二维数组转为稀疏数组:先遍历，得到非0个数
        int sum=0;
        for(int[] row:chessArr){
            for(int  data:row){
               if(data!=0){
                   sum++;
               }
            }
        }

        //创建稀疏数组
        int[][] sparseArr=new int[sum+1][3];
        //给稀疏数组赋值(第一行)
        sparseArr[0][0]=chessArr.length;
        sparseArr[0][1]=chessArr[0].length;
        sparseArr[0][2]=sum;

        //遍历二维数组，将非零值存放到稀疏数组中即可
        int sumNext=1;
        for(int i=0;i<chessArr.length;i++){
            for(int j=0;j<chessArr[i].length;j++){
                if(chessArr[i][j]!=0){
                    sparseArr[sumNext][0]=i;
                    sparseArr[sumNext][1]=j;
                    sparseArr[sumNext][2]=chessArr[i][j];
                    sumNext++;
                }
            }
        }
        //输出稀疏数组
        System.out.println(Arrays.deepToString(sparseArr));

        //将稀疏数组存入文件
        File file=new File("F:\\data-structure-and-algorithm\\sparese.txt");
        FileWriter writer=new FileWriter(file);
        for(int row[]:sparseArr){
            for(int data: row){
                writer.write(data+"\t");
            }
            writer.write("\r\n");
        }
        writer.close();
        System.out.println("----------稀疏数组写入文件完成-------------");
        //重置稀疏数组
        for(int row[] :sparseArr){
            Arrays.fill(row,0);
        }
        System.out.println("重置后的稀疏数组为："+Arrays.deepToString(sparseArr));
        //从文件读取稀疏数组并转换为原始二维数组
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line=null;//读取行内容
        int row=0;//行下标
        while((line=reader.readLine())!=null){
            String[] temp=line.split("\t");
            for(int i=0;i<temp.length;i++){
                sparseArr[row][i]=Integer.parseInt(temp[i]);
            }
            row++;
        }
        reader.close();
        System.out.println("------------从文件读取稀疏数组完成------------,打印如下");
        System.out.println(Arrays.deepToString(sparseArr));
        //创建原始二维数组
        int[][] originalArr=new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=1;i<sparseArr.length;i++){
            for(int j=0;j<sparseArr[i].length;j++){
                originalArr[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
            }
        }
        System.out.println("-----稀疏数组转原始数组完成-----如下：");
        System.out.println(Arrays.deepToString(originalArr));

    }
}

//稀疏数组（sparse-array）
    //先看一个实际需求：编写的五子棋程序中，有存盘退出和续上盘的功能
    //问题分析：因为该二维数组的很多默认值为0，因此记录了很多没有意义的数据--》稀疏数组

//基本介绍：当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
    //稀疏数组的处理方法是：
        //1：记录数组一共有几行几列
        //2：把具有不同值得元素的行列及值记录在一个小规模的数组中（稀疏数组），从而缩小程序的规模
    //举例说明
    /*
       0 0 0 22 0 0 15
    *  0 11 0 0 0 17 0
    *  0 0 0 -6 0 0 0
    *  0 0 0 0 0 39 0
    *  91 0 0 0 0 0 0
    *  0 0 28 0 0 0 0
    * */
    //上面的二维数组用稀疏数组表示为
    /*    行  列  值
    * [0] 6   7   8  //第一行记录有多少行，多少列，共有多少个不同值，接下来的坐标记录是哪几个有效数据
    * [1] 0   3   22 //第一个有效数据
    * [2] 0   6   15 //第而个有效数据
    * [3] 1   1   11 //。。。
    * [4] 1   5   17
    * [5] 2   3   -6
    * [6] 3   5   39
    * [7] 4   0   91
    * [8] 5   2   28
    * */


    //应用实例
        //1：使用稀疏数组，来保留类似前面的二维数组（棋盘，地图）
        //2：把稀疏数组存盘，并且可以重新恢复原来的二维数组，
        //3：整体思路分析如下：
            //1：二维数组转稀疏数组：
                //1：遍历原始二维数组，得到有效数据个数sum,
                //2: 根据sum就可以创建稀疏数组 sparseArr int[sum+1][3](总是固定三列，行的多少取决于有多少个有效数字，外加第一行确定原始数组大小和有效数字个数)
                //3：将二维数组的有效数据存入到稀疏数组
            //2：稀疏数组转二维数组
                //1：读取稀疏数组的第一行，根据第一行的行列数据创建原始二维数组chease int[11][11]
                //2：再读取稀疏数组的后面几行的数据,并赋给原始数组即可




