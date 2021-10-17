package com.master.chapter017;

import javax.swing.text.TableView;
import java.util.Arrays;

/**
 * @ClassName: Prim  普利姆算法
 * @Package: com.master.chapter017
 * @Description:
 * @Datetime: 2021/10/11 23:17
 * @author: ColorXJH
 */
public class Prim {
    public static void main(String[] args) {
        //测试图是否创建成果
        char[]data=new char[]{'A','B','C','D','E','F','G'};
        int verxs=data.length;
        //邻接矩阵的关系使用二维数组描述,10000表示2个点不连通，其他表示距离
        int[][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建MGraph对象
        MGraph mGraph=new MGraph(verxs);
        //创建一个MinTree对象
        MinTree tree=new MinTree();
        tree.createGraph(mGraph,verxs,data,weight);
        //输出
        tree.showGraph(mGraph);
        //测试普利姆算法
        tree.prim(mGraph,0);

    }

}

//创建最小生成树-》村庄的图
class MinTree{
    //创建图的邻接矩阵
    /**
     * Description: 
     * @Author: ColorXJH
     * @Date: 2021/10/17 12:37
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     * @Return: void
     **/
    public void createGraph(MGraph graph,int verxs, char[]data,int[][]weight){
        for (int i = 0; i <verxs ; i++) {//顶点
            graph.data[i]=data[i];
            for (int j= 0;  j<verxs ; j++) {
                graph.weight[i][j]=weight[i][j];
            }
            
        }
    }
    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link: graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }

    //编写一个prim普利姆算法，得到最小生成树
    /**
     * Description: 
     * @Author: ColorXJH
     * @Date: 2021/10/17 12:57
     * @param graph 图
     * @param v 从图的第几个顶点开始生成，‘A’-->0，'B'-->1....
     * @Return: void
     **/
    public  void prim(MGraph graph,int v){
        //标记结点（顶点）是否被访问过
        int[]visited= new int[graph.verxs];
        //visited[]默认的元素的值都为0，表示没有访问过
        for (int i = 0; i < graph.verxs ; i++) {
            visited[i]=0;
        }
        //把当前这个结点标记为已访问
        visited[v]=1;
        //h1,h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int totalWieght=0;
        int minWeight=10000;//将minWeight初始化为一个较大的值，，后面在遍历的过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs个顶点，普利姆算法结束后，有graph.verxs-1条边
            //确定每一次生成的子图和哪个节点和这次遍历的这个节点的距离最近
            for (int i = 0; i < graph.verxs ; i++) {//遍历已经访问过的节点，i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs ; j++) {//遍历所有没有访问过的节点，j节点表示还没有访问过的节点
                    //visited[i]==1表示被访问过，visited[j]==0表示没有被访问过，graph.weight[i][j]表示距离
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        //替换minweight，寻找已经访问过的额节点和未访问过的节点间的全脂最小的边
                        minWeight=graph.weight[i][j];
                        //同时记录下节点的位置
                        h1=i;
                        h2=j;

                    }
                }
            }
            //找到一条边是最小的边
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值是："+minWeight);
            //将当前找到的节点标记为已经访问
            visited[h2]=1;
            totalWieght+=minWeight;
            //minweight 重置
            minWeight=10000;
        }
        System.out.println("总权值为："+totalWieght);

    }
}


class MGraph{
    int verxs;//表示图的节点个数
    char[] data;//存放节点的数据
    int[][]weight;//存放边，这就是我们的邻接矩阵

    public MGraph(int verxs){
        this.verxs=verxs;
        data=new char[verxs];
        weight=new int[verxs][verxs];
    }
}

//应用场景--修路问题
    //有一个六边形的七个村庄（边角和中心各一个），现在需要修路把7个村庄都联通，各个村庄的距离分别不同，问如何修路能保证各个村庄都能联通
    //并且总的修路里程最少？

//思路：1：将10条边全部都链接起来，但是总的里程肯定是最长的
//正确思路：尽可能的选择少的路线，并且每条路线最小，保证总里程数最少

//最小生成树
    //修路问题的本质就是最小生成树问题，先介绍一下最小生成树（Minimum Cost Spanning Tree），简称MST
        //1:给定一个带权的无向连接图，如何选择一颗生成树，使树上所有边上权的总和最小，这叫最小生成树
        //2:N个顶点，一定有N-1条边
        //3：包含全部顶点
        //4:N-1条边都在图中
        //5:举例说明如图：
        //6:求最小生成树的算法主要是普利姆算法和克鲁斯卡尔算法
                //1---2                        2
                //|   |===>  1--2--3--4    1   3
                //3===4                        4
//普利姆算法
    //1:普利姆算法求最小生成树，也就是在包含N个顶点的连通图中，找出只有n-1条边包含所有n个顶点的联通子图，也就是所谓的极小联通子图
        //2:算法如下：
            //1设G=(V,E)是联通网，T=(U,D)是最小生成树，V,U是顶点集合，E,D是边的集合
            //2若从顶点u开始构造最小生成树，则从集合V中取出顶点u放入到集合U中，标记顶点v的visited[u]=1
            //3若集合U中顶点ui与集合V-U中的顶点vj之间存在边，则寻找这些边中权值最小的边，但不能构成回路，将顶点vj加入集合U中，将边（ui,vj）加入集合D中，
                //标记visited[vj]=1
            //4重复步骤2，知道U与V相等，即所有顶点都被标记为访问过，此时D中有n-1条边
            //5提示：单独看步骤很难理解：，通过代码比较好理解-169
//next 172