package com.master.chapter017;

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

//克鲁斯卡尔算法
    //应用场景-公交站问题
        //1：某城市新增7个站点A B C D E F G,现在需要修路把7个站点联通
        //2:各个站点的距离用边线表示（权），比如A-B的距离12公里
        //3:问如何修路保证各个站点都能够联通，并且总的修建公里总数最少
//算法介绍：
    //:克鲁斯卡尔算法是用来求加权连通图的最小生成树算法
    //基本思想：按照权值从小到大的顺序选择n-1条边，并保证这n-1条边不构成回路
    //具体做法：首先构造一个只含n个顶点的森林，然后依照权值从小到大从联通网中选择边加入到僧林中，并使森林中不产生回路，直至森林变成一棵树为止
//问题分析：根据前面介绍的克鲁斯卡尔算法的基本思路和做法，了解到，该算法需要重点解决一下两个问题：
    //1：对图的所有边按照权值大小进行排序
    //2:将边添加到最小生成树中时，怎么样判断是否形成了回路
        //问题1比较好解决：采用排序算法进行排序即可
        //问题2的处理方式为：记录顶点在“最小生成树”中的终点，顶点的终点是“在最小生成树中与它联通的最大顶点”，然后每次需要将一条边添加到最小生成树时，
        //判断该边的两个顶点的终点是否重合，重合的话则会构成回路
            //终点：将所有顶点按照从小到大的顺序排好后，某个顶点的终点就是“与它联通的最大顶点”
            //我们加入的边的两个顶点不能都指向同一个终点，否则将构成回路
//代码如下：
class Kruskal{
    private int edgeNum;//记录变得个数
    private char[] vertexs;//顶点数组
    private int[][]matrix;//邻接矩阵
    private static final int INF=Integer.MAX_VALUE;//表示两个顶点不能联通

    public Kruskal(char[] vertexs,int[][]matrix){
        //初始化顶点数和边的个数
        int vlen=vertexs.length;
        //初始化顶点
        this.vertexs=new char[vlen];
        for (int i = 0; i <vertexs.length ; i++) {
            this.vertexs[i]=vertexs[i];
        }
        //初始化边,复制拷贝
        this.matrix=new int[vlen][vlen];
        for (int i = 0; i <vlen ; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j]=matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j]!=INF){
                    edgeNum++;
                }
            }
        }

    }
    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t",matrix[i][j]);
            }
            System.out.println();//换行处理
        }
    }

    //对边进行排序处理
    /**
     * 功能描述: 对边进行排序处理，冒泡排序
     * @param: eData 边的集合
     * @Return: void
     * @Author: ColorXJH
     * @Date: 2021/10/18 21:23
     */
    private void sortEdges(EData[] eData){
        for (int i = 0; i < eData.length-1; i++) {
            for (int j = 0; j < eData.length-1-i; j++) {
                if(eData[j].weight>eData[j+1].weight){//交换
                    EData temp=eData[j];
                    eData[j]=eData[j+1];
                    eData[j+1]=temp;
                }
            }
        }
    }
    /**
     * 功能描述:
     * @param: ch 顶点的值 比如‘A’,'B'
     * @Return: int 返回ch顶点对应的下边，如果找不到，返回-1
     * @Author: ColorXJH
     * @Date: 2021/10/18 22:01
     */
    public int getPosition(char ch ){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i]==ch){//找到
                return i;
            }
        }
        //找不到
        return -1;
    }
    
    /**
     * 功能描述: 获取图中边，放到EData【】数组中，后面我们需要遍历该数组，通过Matrix邻接矩阵来获取
     *  EData[]形式大致[['A','B',12],['B','F',7]]
     * @param:
     * @Return: com.master.chapter017.EData[]
     * @Author: ColorXJH
     * @Date: 2021/10/18 22:43
     */
    private EData[] getEdges(){
        int index=0;
        EData[] eData=new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j <vertexs.length ; j++) {
                if(matrix[i][j]!=INF){
                    eData[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return eData;
    }
    /**
     * 功能描述: 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param: ends 数组就是记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成的
     * @param: i  表示传入的顶点对应的下标
     * @Return: int 返回的就是下标为i的这个顶点对应的终点的下标
     * @Author: ColorXJH
     * @Date: 2021/10/18 23:03
     */
    private int getEnd(int[] ends,int i){
        while(ends[i]!=0){
            i=ends[i];
        }
        return i;
    }

    public void kruskalAlgorithy(){
        int index =0;//表示最后结果数组的索引
        int [] ends=new int[edgeNum];//用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rest=new EData[edgeNum];
        //获取图中 所有的边的集合，一共有12条边
        EData[] edgs=getEdges();
        System.out.println("图的边的集合："+Arrays.toString(edgs)+" 共"+edgs.length);
        //按照边的权值进行排序
        sortEdges(edgs);
        //遍历edgs数组，将边添加到最小生成树中时，怎么是准备加入的边是否形成了回路，如果没有，就加入rest，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1=getPosition(edgs[i].start);
            //获取到第i条边的第二个顶点（起点）
            int p2=getPosition(edgs[i].end);
            //获取p1这个顶点在已有最小生成树中的终点
            int m=getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n=getEnd(ends,p2);
            //是否构成回路
            if(m!=n){//没有构成回路
                ends[m]=n;//设置m在“已有最小生成树”中的终点《E,F》
               rest[index++]=edgs[i];//有一条边加入到rest数组


            }
        }
        //统计并打印“最小生成树”,输出rets
        //System.out.println("最小生成树："+Arrays.toString(rest));
        for (int i = 0; i <index; i++) {
            System.out.println(rest[i]);
        }

    }


    public static void main(String[] args) {
        char[] vertexs={'A','B','C','D','E','F','G'};
        //
        int[][] matrix={
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };
        Kruskal  kruskal=new Kruskal(vertexs,matrix);
        kruskal.print();
        EData[] data=kruskal.getEdges();
        System.out.println(Arrays.toString(kruskal.getEdges()));//未排序时
        System.out.println("排序后：=================");
        kruskal.sortEdges(data);
        System.out.println("排序：="+Arrays.toString(data));
        kruskal.kruskalAlgorithy();


    }
}


//创建一个类EData，他的对象实例就表示一条边
class EData{
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值
    public EData(char start,char end,int weight){
        this.start=start;
        this.end=end;
        this.weight=weight;
    }
    //重写toString

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}