package com.master.chapter017;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 迪杰斯特拉算法
 * @date 2021/10/21 12:32
 */
public class DijkstraTest {
    public static void main(String[] args) {
        //顶点数组
        char[] vertex={'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix=new int[vertex.length][vertex.length];
        final int N=65535;//表示不可链接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建graph对象
        Graph graph=new Graph(vertex,matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showdjs();
    }
}

//迪杰斯特拉算法：应用场景-最短路径问题
    //1：战争时期，胜利乡有7个村庄（六边形和中间一个顶点）A B C D E F G,现在有6个邮差，从G点出发，需要分别把
    //邮件送往ABCDEF六个村庄
    //2：各个村庄的距离用边线表示（权），比如A-B距离5公里
    //3：问如何计算出G村庄到其他村庄的最短距离
    //4：如果从其他点出发到各个点的最短距离又是多少？
//算法介绍：迪杰斯特拉算法是经典的最短路径算法，用于计算一个节点到其他节点的最短路径，他的主要特点是以起始点为中心
//向外层层扩展（广度优先搜索思想），直到扩展到终点未知
//算法过程如下：
    //设置出发点为v,顶点集合V{v1,v2,v3...}v到V中各个顶点的距离构成距离集合Dis,Dis{d1,d2,d3...},Dis集合记录着
    //v到图中各个顶点的距离（到自身可以看作0，v到v1距离为d1）
    //1：从Dis中选择最小的di,并移除Dis集合，同时移除V集合对应的顶点vi,此时的v到vi即为最短路径
    //2：更新集合Dis,更新规则为：比较v到V集合中顶点的距离值，与v通过vi到V集合中顶点的距离值，保留值较小的一个
        //（也是也更新顶点的前驱节点为vi,表明是通过vi到达的）
    //3：重复执行上面两步骤，直到最短路径顶点为目标顶点即可结束 178
class Graph{
    private char[] vertex;   //顶点数组
    private int[][]matrix;   //邻接矩阵
    private VisitedVertex vv;//已经访问的顶点的集合
    //构造器
    public Graph(char[] vertex,int[][]matrix){
        this.vertex=vertex;
        this.matrix=matrix;
    }
    //显示图的方法
    public void showGraph(){
        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
    //显示结果
    public void showdjs(){
         vv.show();//狄杰斯特拉算法
    }/**
     * 功能描述:
     * @param: index 表示出发顶点对应的下标
     * @Return: void
     * @Author: colorxjh
     * @Date: 2021/10/23 10:27 下午
     */
    public void dsj(int index){
        //走完第一个顶点
       vv=  new VisitedVertex(vertex.length,index);
       update(index);//更新index顶点到周围顶点的距离和前驱顶点
        //走完剩下的顶点
        for (int j = 1; j <vertex.length ; j++) {
            index=vv.updateArray();//选择并返回新的访问顶点
            update(index);
        }
    }


    /**
     * 功能描述:更新index下标顶点到周围顶点的距离和周围顶点的前驱节点
     * @param: index
     * @Return: void
     * @Author: colorxjh
     * @Date: 2021/10/23 10:31 下午
     */
    private void update(int index){
        int len=0;
        //根据遍历我们的邻接矩阵的matrix[index]这一行
        for (int j = 0; j < matrix[index].length; j++) {
            //len是指出发顶点到index顶点的距离+从index顶点到j这个顶点的距离的和（广度优先算法一层层统计出来的）
            len=vv.getDis(index)+matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if(!vv.in(j)&&len<vv.getDis(j)){
                vv.updatePre(j,index);//更新j顶点的前驱为index顶点
                vv.updateDis(j,len);//更新出发顶点到j顶点的距离
            }
        }
    }
}

//以访问的顶点集合
class VisitedVertex{
    public int[] already_arr;//记录各个顶点是否访问过，1表示访问过，0表示未访问，会动态更新
    public int[] pre_visited;//每个下标对应的值为前一个顶点下标，会动态更新
    public int[] dis;//记录触发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短距离 就会放入到dis
    //构造器
    /**
     * 功能描述:
     * @param: length 表示顶点的个数
     * @param: index 表示出发顶点对应的下标
     * @Return: 
     * @Author: colorxjh
     * @Date: 2021/10/23 10:13 下午
     */
    public VisitedVertex(int length,int index){
        this.already_arr=new int[length];
        this.pre_visited=new int[length];
        this.dis=new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        this.already_arr[index]=1;//设置出发顶点被访问过
        this.dis[index]=0;//设置出发顶点的访问距离为0
    }
    /**
     * 功能描述:判断index顶点是否被访问过
     * @param: index
     * @Return: boolean 如果访问过返回true,否则返回false
     * @Author: colorxjh
     * @Date: 2021/10/23 10:16 下午
     */
    public boolean in(int index){
        return already_arr[index]==1;
    }

    /**
     * 功能描述:跟新出发顶点到index顶点的距离
     * @param: index
     * @param: len
     * @Return: void
     * @Author: colorxjh
     * @Date: 2021/10/23 10:18 下午
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }
    
    /**
     * 功能描述:更新pre这个顶点的前驱顶点为index顶点
     * @param: pre
     * @param: index
     * @Return: void
     * @Author: colorxjh
     * @Date: 2021/10/23 10:22 下午
     */
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     * 功能描述:返回出发顶点到index顶点的距离
     * @param: index
     * @Return: int
     * @Author: colorxjh
     * @Date: 2021/10/23 10:25 下午
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 功能描述:继续选择并返回新的访问节点，比如这里的G完成后，就是A做为新的访问节点（注意不是出发顶点）
     * @param:
     * @Return: int
     * @Author: colorxjh
     * @Date: 2021/10/23 10:50 下午
     */
    public int updateArray(){
           int min=65535,index=0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i]==0&&dis[i]<min){
                min=dis[i];
                index=i;
            }
        }
        //更新index顶点被访问过
        already_arr[index]=1;
        return index;

    }

    /**
     * 功能描述:显示最后的结果，说白了就是三个数组
     * @param:
     * @Return: void
     * @Author: colorxjh
     * @Date: 2021/10/23 10:57 下午
     */
    public void show(){
        System.out.println("输出already_arr,pre_visited,dis");
        for (int i = 0; i < already_arr.length; i++) {
            System.out.print(already_arr[i]+" ");
        }
        System.out.println();
        for (int j = 0; j < pre_visited.length; j++) {
            System.out.print(pre_visited[j]+" ");
        }
        System.out.println();
        for (int k = 0; k < dis.length; k++) {
            System.out.print(dis[k]+" ");
        }
        System.out.println("----------");
        //为了好看最后的最短距离，我们处理
        char[] vts={'A','B','C','D','E','F','G'};
        int count=0;
        for (int i:dis) {
             if(i!=65535){
                 System.out.print(vts[count]+"("+i+")");
             }else{
                 System.out.print("N");
             }
             count++;
        }
    }
}
//184