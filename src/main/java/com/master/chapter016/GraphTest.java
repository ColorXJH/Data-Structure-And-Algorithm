package com.master.chapter016;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 数据结构-图
 * @date 2021/7/23 9:18
 */
public class GraphTest {
    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numberOfEdges;//表示边的数目

    public static void main(String[] args) {
        System.out.println("测试一把图是否创建ok");
        int n=5;
        String[] vertex={"a","b","c","d","e"};
        //创建图
        GraphTest test=new GraphTest(n);
        //添加顶点
        for(String value:vertex){
            test.insertVertex(value);
        }
        //添加边
        // A-B  A-C B-C B-D B-E
        test.insertEdge(0,1,1);//a-b
        test.insertEdge(0,2,1);
        test.insertEdge(1,2,1);
        test.insertEdge(1,3,1);
        test.insertEdge(1,4,1);
        //显示一把邻接矩阵
        test.showGraph();

    }

    //构造器
    public GraphTest(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        numberOfEdges=0;
    }

    //插入节点
    public void  insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    /**
     * Description: 添加边
     * @Author: ColorXJH
     * @Date: 2021/7/24 20:46
     * @param v1 表示顶点的下标，即第几个顶点
     * @param v2
     * @param weight 表示权值：是否直连
     * @Return: void
     **/
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numberOfEdges++;
    }

    //图中常用的方法：
    //返回节点的个数
    public int getNumberOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumberOfEdges(){
        return numberOfEdges;
    }
    //返回节点i对应的下标（数据） 0-》a  1->b 2->c ...
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1 v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }


}
//为什么要有图
    //1：线性表局限于一个直接前驱和直接后继的关系
    //2：树也只能有一个直接前驱也就是父节点
    //3：当我们需要表示多对对关系时，就需要用到图

//图的基本介绍
    //图是一种数据结构，其中节点可以具有0个或多个相邻元素，两个节点之间的连接称作边，节点也可以称为顶点。如图：
        //
        //                        C
        //      A
        //                    E
        //             D

        //顶点 ：A B C D E
        //边：顶点之间的连线称为边
        //路径：比如从D->C的路径有1：D->B->C  2:D->A->B->C
        //无向图：顶点之间的连接没有方向，比如A-B,既可以是A->B 也可以是B->A
        //有向图：顶点之间的连接有方向，比如A->B,只能是A->B，不能是B->A
        //带权图：边带权值（数字）的图称为带权图，也叫网
        //图的表示方式有两种：二维数组（邻接矩阵）   链表表示（邻接表）
            //1：邻接矩阵：表示图形中顶点之间相邻关系的矩阵，对于n个顶点的图而言，矩阵是row和col表示的是1.。。n个点
                    //矩阵中一般用0表示不连通，1 表示连通，0和0之间一般用0表示，表示不互联

            //2：邻接表：邻接矩阵需要为每个顶点都分配n个边的空间，其实有很多边都是不存在的，会造成空间的一定损失
                    //邻接表的实现只关心存在的边，不关心不存在的边，因此没有空间浪费，邻接表由数组+链表组成

        //案例：                                                            a  b   c   d   e
            //                  b                                       a  0  1   1   0   0
            //          c                                               b  1  0   1   1   1
            //                          e                               c  1  1   0   0   0
            //                      d                                   d  0  1   0   0   0
            //              a                                           e  0  1   0   0   0
        //思路分析   1表示能够直连的顶点，0表示不能直连的顶点
            //1:存储顶点string,使用arrayList
            //2:保存矩阵，使用二维数组int[][] edges
        //代码实现:见 main

//图的深度优先遍历介绍
    //图的遍历介绍：
