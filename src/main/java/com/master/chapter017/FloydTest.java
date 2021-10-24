package com.master.chapter017;

import java.util.Arrays;

/**
 * @ClassName: FloydTest   弗洛伊德算法
 * @Package: com.master.chapter017
 * @Description:
 * @Datetime: 2021/10/24 12:22
 * @author: ColorXJH
 */
public class FloydTest {
    public static void main(String[] args) {
        //顶点数组
        char[] vertex={'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix=new int[vertex.length][vertex.length];
        final int N=65535;
        //初始化关系准备
        matrix[0]=new int[]{0,5,7,N,N,N,2};
        matrix[1]=new int[]{5,0,N,9,N,N,3};
        matrix[2]=new int[]{7,N,0,N,8,N,N};
        matrix[3]=new int[]{N,9,N,0,N,4,N};
        matrix[4]=new int[]{N,N,8,N,0,5,4};
        matrix[5]=new int[]{N,N,N,4,5,0,6};
        matrix[6]=new int[]{2,3,N,N,4,6,0};
        Graphs graphs=new Graphs(vertex.length,matrix,vertex);
        graphs.floyed();
        graphs.show();


    }
}
//弗洛伊德算法介绍
    //1：和迪杰斯特拉算法一样，弗洛伊德算法也是一种用于给定的加权图中顶点间最短路径的算法，该算法名称以创始人之一，1978年图灵奖获得者，斯坦福大学计算机
    //系教授罗伯特。弗洛伊德命名
    //2:弗洛伊德算法计算图中各个顶点之间的最短路径
    //3:迪杰斯特拉算法用于九三图中某一个顶点到达其他顶点的最短路径
    //4:弗洛伊德算法vs迪杰斯特拉算法：迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问呢顶点到其他顶点的最短路径；弗洛伊德算法中每一个顶点都是出发访问点
    //所以需要将每一个顶点看作被访问顶点，求出从每一个顶点到其他顶点的最短路径

//弗洛伊德算法分析
    //1:设置顶点vi到顶点vk的最短路径已知为lik,顶点vk到vj的最短路径为Lkj,顶点vi到顶点vj的路径为Lij,则vi到vj的最短路径为min((Lik+Lkj),Lij),vk的取值为图中所有顶点
    //则可获得vi到vj的最短路径
    //2:至于vi到vk的最短路径Lik或者vk到vj的最短路径Lkj,是以同样的方式获得的

//创建图
class Graphs{
   private char[]vertex;//存放顶点的数组
    private int[][]dis;//保存，从各个顶点出发到其他顶点的距离，最后的结果，也是保留在该数组中的
    private int[][]pre;//保存到达目标顶点的前驱顶点
    //构造器
    /**
     * 功能描述:
     * @param: length 大小
     * @param: matrix 邻接矩阵
     * @param: vertex 顶点数组
     * @Return: 
     * @Author: ColorXJH
     * @Date: 2021/10/24 13:39
     */
    public Graphs(int length,int[][] matrix,char[] vertex){
            this.vertex=vertex;
            this.dis=matrix;
            this.pre=new int[length][length];
            //对pre数组初始化，注意，存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }
    //显示pre数组和dis数组
    public void show(){
        char[] vertex={'A','B','C','D','E','F','G'};
        for (int k = 0; k < dis.length; k++) {
            //先输出pre数组的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]]+" ");
            }
            System.out.println();
            //再输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("（"+vertex[k]+"到"+vertex[i]+"的最短路径是"+dis[k][i]+"） ");
            }
            System.out.println();
        }
    }
    //弗洛伊德算法
    public void floyed(){
        int len=0;//变量保存距离
        //对中间顶点的遍历，k就是中间顶点的下标【A,B,C,D,E,F,G】
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发【A,B,C,D,E,F,G】
            for (int i = 0; i < dis.length; i++) {
                //到达j顶点，从i顶点开始出发【A,B,C,D,E,F,G】
                for (int j = 0; j < dis.length; j++) {
                    len=dis[i][k]+dis[k][j];//求出从i顶点出发，经过k中间顶点，到达j顶点的距离
                    if(len<dis[i][j]){//如果len小于dis[i][j]直连的距离，就更新该值
                        dis[i][j]=len;//更新距离
                        pre[i][j]=pre[k][j];//更新前驱节点
                    }
                }
            }
        }
    }

}