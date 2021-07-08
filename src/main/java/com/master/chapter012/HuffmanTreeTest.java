package com.master.chapter012;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 赫夫曼树
 * @date 2021/7/7 11:46
 */
public class HuffmanTreeTest {
    public static void main(String[] args) {
        int[] array = {13, 7, 8, 3, 29, 6, 1};
        Node root=createHuffmanTree(array);
        //测试一下
        preOrder(root);//67  29  38   15 7  8  23  10  4 1 3 6 13

    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] array) {
        //1:为了操作方便，遍历array数组，将数组的每个元素构建成一个Node,并放入到ArrayList
        List<Node> nodes = new ArrayList<Node>();
        for (int value : array) {
            nodes.add(new Node(value));
        }
        //我们处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            //2:排序
            Collections.sort(nodes);
            System.out.println(nodes);
            //开始创建赫夫曼树
            //1：取出权值最小的两个节点（二叉树），构成新二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //2:构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //3:从arraylist中删除，处理过的二叉树将，并将parent加入
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
            System.out.println("第一次排序后：" + nodes);
        }
        //返回赫夫曼树的root节点
        return nodes.get(0);

    }

    //调用前序遍历方法
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("空树无法遍历");
        }
    }
}
//基本介绍
//1：给定n个权值作为n个叶子节点，构造一颗二叉树，若该树的带权路径长度（wpl）达到最小，
//称这样的二叉树为最优二叉树，也称赫夫曼树/霍夫曼树
//2：赫夫曼树是带权路径长度最短的树，权值较大的节点离根很近
//赫夫曼树的几个重要概念和举例说明
//1：路径和路径长度：在一棵树中，从一个节点往下可以达到的孩子或者孙子节点之间的通路，称为路径
//通路中分支的数目称为路径长度，若规定根节点的层数为1，则从根节点到第L层节点的路径长度为L-1
//2:节点的权及带权路径长度：若将树中节点赋给一个有着某种含义的数值，则整个数值称为该节点的权，
//节点的带权路径长度为：从根节点到该节点之间的路径长度与该节点的权的乘积
//3：树的带权路径长度：树的带权路径长度规定为所有叶子节点的带权路径长度之和，记作wpl(weighted path length)
//权值越大的节点离根节点越近的二叉树才是最优二叉树
//4：wpl最小的数就是赫夫曼树
//例子如下：
//              a                   a                      a
//          b      c             13       b              7    b
//       13   7   8  3                 8     c              3   c
//                                         7    3              8 13
//wpl=13*2+7*2+8*2+3*2=62   wpl=13*1+8*2+7*3=3*3=59   wpl=7*1=3*2+(8+13)*3=76
//第二种树的wpl值最小，


//赫夫曼树创建思路图解
//给你一个数列【13，7，8，3，29，6，1】，要求转成一颗赫夫曼树
//赫夫曼树生成的步骤
//1：从小到大进行排序，将每一个数据，每一个数据都是一个节点，每个节点都可以看成是一个最简单的二叉树
//2：取出根节点权值最小的两颗二叉树
//3：组成一个新的二叉树，该新的二叉树根节点的权值是前面两颗二叉树根节点权值的和
//4：再将这颗新的二叉树，以根节点的全职大小再次排序，不断重复1-2-3-4的步骤，直到数列中
//所有的数据都被处理，就得到一颗赫夫曼数
//图如下：
//1：排序后： 13 6 7 8 13 29
//2：取出1 3 构建二叉树   4
//           1   3
//3：   4  6 7 8 13 29
//     1 3
//4：接下来选出4，6 再次构建二叉树
//          10
//        4    6
//       1  3
//5：接种排序：7  8   10  13  29，选出最小的两个再次构成二叉树   15
//        4  6                                  7   8
//       1  3
//6：再次排序：   10      13      15      29
//     4  6            7  8
//    1 3
//7：        15      23      29
//          7  8   10  13
//                4  6
//               1 3
//8：      29                    38
//                         15        23
//                        7  8     10   13
//                                4  6
//                               1 3
//9：   最终的二叉树：如下
//                    67
//          29                    38
//                          15            23
//                         7   8       10     13
//                                   4     6
//                                  1 3

//代码实现
//1;创建节点类
//为了让node对象支持Collections集合排序，需要实现Comparable接口
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序（从大到小反过来）
        return this.value - o.value;
    }
}


//赫夫曼编码
    //1：赫夫曼编码也称哈夫曼编码，又称霍夫曼编码，是一种编码方式，属于一种程序算法
    //2：赫夫曼编码是赫夫曼树在电讯通信中的经典应用之一
    //3：赫夫曼编码广泛的应用在数据文件压缩，其压缩率通常在20%-90%之间
    //4：赫夫曼编码是可变字长编码（VLC）的一种，是Huffman于1952年提出的一种编码方法，称之为最佳编码
//原理刨析
    //1：通信领域中信息的处理方式1——》定长编码
    //i like like like java do you like a java //共40个字符，包含空格
    //对应相应的ascii数字和对应的二进制8位，按照二进制信息来传递，总的长度计算后是359（包含空格）
    //2：通信领域中信息的处理方式2--》变长编码
    //上面的字符串：d:1 y:1 u:1 j:2 v:2 l:4 k:4 e:4 i:5 a:5 :9(空格有9个)
        //按照各个字符出现的次数进行编码，原则是出现的次数越多，则编码越小，比如空格出现了9次，编码为0，其他依次类推
        //各个字符对应的个数0= ，1=a,10=i,11=e,100=k,101=l,110=o,111=v,1000=j,1001=u,1010=y,1011=d(各个字符编码对应的二进制)
    //按照上面给各个字符规定编码，则现在我们传输上面的字符串时，编码就是： 10010110100.。。
    //10 0 101 10 100      =>i'空格'lik=》变长编码 （目前整个编码不是前缀编码，存在二义性，即无法判断从哪里拆分）
    //前缀编码：字符的编码都不能是其他字符编码的前缀，符合此要求的编码叫做前缀编码，即不能匹配到重复的编码
    //3：通信领域中信息的处理方式3--》赫夫曼编码
    //上面的字符串: d:1 y:1 u:1 j:2 v:2 o:2 i:4 k:4 e:4 i:5 a:5  :9
    //按照上面字符出现的次数构建一颗赫夫曼树，次数作为权值：如图（参照前面构建赫夫曼树步骤）
        //                                                  40
        //                                          17                  23
        //                                       8     : 9        10            13
        //                                    4    i:4         5     i:5   a:5      8
        //                                j:2  v:2          o:2    3             k:4 e:4
        //                                                      u:1  2
        //                                                        d:1 y:1
    //4:根据赫夫曼树，给各个字符规定编码，向左的路径为0，向右的路径为1，编码如下：
        //o:1000 u:10010 d:100110 y:100111 i:101 a:110 k:1110 e:1111 j:0000 v:0001 l:001 :01
        //此时编码为前缀编码（不存在二义性）
    //5：按照上面的编码。我们40个字符串的编码就为101010011.。。（长度133）
    //原来长度359，现在长度133，压缩了（359-133）/359=62.9%
        //总结：创建赫夫曼树，用路径作为编码（左0右1），无损编码（解码时可以完全恢复）
    //注意：赫夫曼树根据排序方法不同，也可能不太一样，这样对应的赫夫曼编码也不完全一样，但是wpl是一样的，
        //都是最小的，比如：我们让每次生成的新的二叉树总是排在权值相同的二叉树的最后一个（即存在相同的值他的位置不一定是稳定的）
