package com.master.chapter016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    //定义数组boolean[],记录谋而节点是否被访问过
    private boolean[] isVisited;

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
        //测试深度dfs
        System.out.println("ss--------------------- -ss" );
        //test.dfs();
        //广度优先算法
        System.out.println("gg-----------------------gg");
        test.bfs();

    }

    //构造器
    public GraphTest(int n){
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        numberOfEdges=0;
        isVisited=new boolean[n];
    }

    //得到第一个邻接节点的下标w
    /**
     * Description:
     * @Author: ColorXJH
     * @Date: 2021/7/26 10:53
     * @param index
     * @Return: int 如果存在就返回对应的下标，否则返回-1
     **/
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            if(edges[index][j]>0){

                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }


    //深度优先遍历算法
    public void dfs(boolean[] isVisited,int i){
        //首先访问该节点：输出
        System.out.print(getValueByIndex(i)+"->");
        //将该节点设置为已经访问过
        isVisited[i]=true;
        //查找节点i的第一个邻接节点w
        int w=getFirstNeighbor(i);
        while(w!=-1){//说明有邻接节点
               if(!isVisited[w]){//没有被访问过
                    dfs(isVisited,w);
               }
               //如果w节点已经被访问过,查找邻接节点的下一个节点
               w=getNextNeighbor(i,w);
        }

        //如果w不存在，则回到第一步，将从v的下一个节点继续:(见方法重载)，类似循环遍历所有的节点，然后进行dfs

    }


    //对dfs进行一个重载，遍历所有的节点，并进行dfs
    public void dfs(){
        //遍历所有的节点，进行dfs[回溯]
        for(int i=0;i<getNumberOfVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    //对一个节点进行广度优先遍历的方法
    public void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头结点对应的下标
        int w;//表示邻接节点w
        //队列，记录节点访问的顺序
        LinkedList queue=new LinkedList<>();
        //访问节点
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i]=true;
        //将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
           u=(int) queue.removeFirst();//取出队列的头部
             //得到第一个邻接节点的下标w
            w=getFirstNeighbor(u);

            while(w!=-1){//找到
                //是否访问过
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    //标记已经访问
                    isVisited[w]=true;
                    //入队列
                    queue.addLast(w);
                }
                //以u这一行的，w后面的一个邻接节点（可以画出二维数组塦帮助理解）
                w=getNextNeighbor(u,w);
            }
        }

    }

    //遍历所有的节点，都进行广度优先搜素算法
    public void bfs(){
        for(int i=0;i<getNumberOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
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
    //图的遍历介绍：所谓图的遍历，即是对节点的访问，一个图有那么多个节点，如何遍历这些节点，需要特定的策略，一般有两种访问策略：
        //深度优先遍历   广度优先遍历
    //1:深度优先遍历基本思想 depth first search  dfs
        //1:深度优先遍历，从初始访问节点出发，初始访问节点可能有多个邻接节点，深度优先遍历的策略就是，首先访问第一个邻接节点，然后再以
            //这个被访问的邻接节点作为初始节点，访问他的邻接节点，可以这样理解，每次都在访问万当前节点后首先访问当前节点的第一个邻接节点
        //2:这样的访问策略是优先纵向挖掘深入，而不是对一个节点的所有邻接节点的横向遍历
        //3:深度优先遍历是一个递归的过程
            //算法步骤：
                //1:访问初始节点v,并标记v节点已访问
                //2:查找v节点的第一个邻接节点w
                //3:若w存在，则继续执行4，如果w不存在，则回到第一步，将从v的下一个节点继续
                //4:若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v,然后进行步骤123）
                //5:查找节点v的w节点的下一个邻接节点，转到步骤3

//图的广度优先遍历基本思想 bfs
    //图的广度优先搜索（Broad First Search）,类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保持访问过的节点的顺序，以便按这个
        //顺序来访问这些节点的邻接节点
    //算法步骤：
        //1：访问初始节点v并标记节点v为已访问
        //2: 节点v如队列
        //3: 当队列非空时，继续执行，否则算法结束(对v节点而言，但是在整个节点而言，需要使用for循环介入)
        //4: 出队列，取得队头节点u
        //5: 查找节点u的第一个邻接节点w
        //6: 若节点u的邻接节点w不存在，则转移到步骤3，否则循环执行以下三个步骤：
            //1:若节点w尚未被访问，则访问节点w,并标记为已访问
            //2:节点w入队列
            //3:查找节点u的继w邻接节点后的下一个邻接节点w,转到步骤6


//深度优先和广度优先的区别：顺序并不一样
