package com.master.chapter001;

/**
 * @author ColorXJH
 * @version 1.0
 * @description 数据结构与算法--介绍
 * @date 2021/3/24 9:49
 */
public class Introduce {
    public static void main(String[] args) {

    }
}


//几个经典的算法面试题
    //1：字符串匹配问题：
        //1：有一个字符串str=" 123 1234 12345 321";和另一个子字符串str2="1234",现在要你判断str1是否包含str2
        //,如果存在，就返回第一次出现的位置，如果没有则返回-1，要求用最快的速度完成匹配

    //思路：1：暴力匹配 2：KMP算法 （部分匹配表）

    //2：汉诺塔游戏：将a塔的所有圆盘移动到c塔，并且规定：小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘
    //思路：使用到分治算法

    //3：8皇后问题：是一个古老而著名的问题，是回溯算法的典型案例，在8*8格的象棋中摆放8个皇后，使其不能互相攻击，
        //即：任何两个皇后都不能出意同一行同一列或者同一斜线上，问有多少种摆法
    //思路：使用到回溯算法（92）

    //4：马踏棋盘算法：将马随机放在8*8国际象棋棋盘中，按照马走日规则进行移动，要求每个方格只进行一次，走遍棋盘上的64个方格
    //思路：会使用到图的深度优化遍历算法(DFS)+贪心优化算法



//数据结构和算法内容介绍：
    //1：重要性：
        //1：算法是程序得灵魂，优秀得程序可以在海量得程序计算时，依然保持高速计算
        //2：一般来讲，程序会使用了内存计算框架（比如spark）和缓存技术（比如redis等）来优化程序，再深入得思考一下，这些计算框架和缓存技术，它的核心功能是哪个部分呢

//数据结构和算法得关系：
    //1：数据结构是一门研究组织数据方式的学科，有了编程语言也就有了数据结构，学好数据结构可以编写出更漂亮，更加有效率的代码
    //2：要学习数据结构就要多多考虑如何将生活中的问题，用程序去解决
    //3：程序=数据结构+算法
    //4：数据结构是算法的基础


//看如下问题
    //1：
    /*public static void main(String[] agrs ){
        String str="java,java,hello,world";
        String newStr=str.replaceAll("java","Color");
        System.out.println("the newStr is "+newStr);
        //（单链表）
    }*/
    //问：试写出用单链表表示的字符串类及字符串终点类的定义，并依次实现它的构造函数，以及计算串长度，串赋值，判断两串是否相等
        //求子串，两串相连，求子串在串中位置等7个成员函数

    //2：一个五子棋程序，要求判断游戏输赢，并可以完成存盘，退出，悔棋，和继续上局功能
        //棋盘映射为一个二位数组（稀疏数组：可压缩/解压），--》写入文件（存档退出）--》恢复上局（读取文件，恢复成数组）--》棋盘显示

    //3：约瑟夫问题（丢手帕问题）：假设1，2，3，4,...n的n个人围坐一圈，约定编号为为k(1<=k<=n)的人从1开始报数
        //数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，知道所有人出列，由此产生一个出队编号的序列
        //提示：用一个不带头节点的循环链表来处理josephu问题：先构成一个有n个节点的单循环链表，然后由k节点开始从1开始计数，技数到m时，
            //对应节点从链表中剔除，然后再从被删除的节点的下一个节点又从1开始计数，直到最后一个节点从链表中删除，算法结束
                    //（单项环形链表）

    //4：其他算法问题：磁盘问题，公交车问题，画图，矩阵中查找单词路径数，球和篮子，扔石头，扑克牌组三组以上，
        //修路问题（最小生成树（加权值）+普利姆算法），最短路径问题（图+弗洛伊德算法），汉诺塔（分支算法），八皇后问题（回溯算法）

//数据结构包括：线性结构和非线性结构
    //1：线性结构：
        //1：线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系
        //2：线性结构有两种不同的存储结构，即顺序存储结构和链式存储结构，顺序存储的线性表称为顺序表，顺序表中存储元素是连续的（地址连续）
        //3：链式存储的线性表称为链表（利用碎片内存），链表中的存储元素不一定是连续的，元素节点中存放数据元素以及相邻元素的地址信息
        //4：线性结构常见有：数组，队列，链表和栈
    //2：非线性结构
        //非线性结构包含：二位数组，多维数组，广义表，树结构，图结构








