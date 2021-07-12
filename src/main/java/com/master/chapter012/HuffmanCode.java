package com.master.chapter012;

import java.util.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 赫夫曼编码（数据压缩）
 * @date 2021/7/12 12:05
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str="i like like like java do you like a java";
        byte[] contextBytes=str.getBytes();
        System.out.println(contextBytes.length);//40
        List<Nodes>nodes=getNodes(contextBytes);
        System.out.println(nodes);
        Nodes root=createHuffmanTree(nodes);
        preOrder(root);
        //getCodes(root,"",builder);
        Map<Byte,String> huffmanCodess=getCodes(root);
        System.out.println("生成的赫夫曼编码表为 "+huffmanCodess);
        byte[] huffmanBytes=zip(contextBytes,huffmanCodess);
        System.out.println("赫夫曼编码后生成的字节数组--------------------：");
        System.out.println(Arrays.toString(huffmanBytes));
    }
    /**
     * description:
     * version 0.1.0
     * @return java.util.List<com.master.chapter012.Nodes>
     * @param bytes 接收字节数组
     * @author ColorXJH
     * @date 2021/7/12 16:02
     */
    private static List<Nodes>getNodes(byte[] bytes){
        //创建一个List
        List<Nodes>nodes=new ArrayList<>();
        //遍历bytes,统计每一个byte出现的次数-》map
        Map<Byte,Integer> counts=new HashMap<>();//k:数据，v:次数
        for(byte b:bytes){
            Integer count=counts.get(b);
            if(count==null){//map还没有存放整个字符数据
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }
        }
        //把每个键值对转换成Node对象并加入到List
        for(Map.Entry<Byte,Integer>entry:counts.entrySet()){
            nodes.add(new Nodes(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //可以通过List,创建对应的赫夫曼树
    private static Nodes createHuffmanTree(List<Nodes>nodes){

        while(nodes.size()>1){
            //先排序（从小到大排序）
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Nodes leftNode=nodes.get(0);
            //取出第二颗最小的二叉树
            Nodes rightNode=nodes.get(1);
            //创建一颗新的二叉树，它的根节点只有权值，没有data(所有的字符最终都是放在叶子节点的)
            Nodes parent=new Nodes(null,leftNode.weight+rightNode.weight);
            parent.left=leftNode;
            parent.right=rightNode;
            //将处理过的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树加入nodes
            nodes.add(parent);

        }
        //nodes最后的节点就是赫夫曼树的根节点
        return nodes.get(0);
    }

    //前序遍历方法
    public  static void preOrder(Nodes nodes){
        nodes.preOrder();
    }

    private static Map<Byte,String>getCodes(Nodes root){
        if(root==null){
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",builder);
        //处理右子树
        getCodes(root.right,"1",builder);
        return huffmanCodes;
    }


    //生成赫夫曼树对应的赫夫曼编码表
    //存放在map中比较合适Map<Byte,String>形式=？【32-》01，97-》100，100-》11000...】
    //在生成赫夫曼编码表时需要去拼接路径。定义一个StringBuilder,存储莫格叶子节点的路径
    static StringBuilder builder=new StringBuilder();
    static Map<Byte,String>huffmanCodes=new HashMap<>();
    /**
     * description: 将传入nodes节点的所有叶子节点的赫夫曼编码得到，并放入到集合中
     * version 0.1.0
     * @return void
     * @param node 传入节点
     * @param code 路径：左子节点为0，右子节点为1
     * @param builder   用于凭借路径
     * @author ColorXJH
     * @date 2021/7/12 16:40
     */
    private static void getCodes(Nodes node,String code,StringBuilder builder){
        StringBuilder builder1=new StringBuilder(builder);
        //将code加入到builder1,
        builder1.append(code);
        if(node!=null){//如果node==null不处理
            //判断当前node,是叶子节点还是非叶子节点
            if(node.data==null){//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left,"0",builder1);
                //向右递归
                getCodes(node.right,"1",builder1);
            }else{//说明是一个叶子节点
                //表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data,builder1.toString());
            }
        }

    }


    //编写一个方法，将字符串对应的byte【】数组，通过生成的赫夫曼编码表返回一个赫夫曼编码压缩后的字节数组
    /**
     * 功能描述:
     * @param: bytes 原始的字符串对应的字节数组
     * @param: huffmanCodes 生成的赫夫曼编码map
     * @Return: byte[] 返回赫夫曼编码处理后的byte[]数组
     * @Author: ColorXJH
     * @Date: 2021/7/12 18:08
     */
    private static  byte[] zip(byte[]bytes,Map<Byte,String>huffmanCodes){
        //1:利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder builder=new StringBuilder();
        //遍历bytes数组
        for(byte b:bytes){
            builder.append(huffmanCodes.get(b));
        }
        System.out.println("赫夫曼编码后的字节数组的二进制字符串形式如下：");
        System.out.println(builder.toString());
        //将‘11110010101011101010101...’转成byte[]
        //统计返回的赫夫曼编码长度有多大
        //一句话：（builder.length()+7）/8
        int len;
        if(builder.length()%8==0){
            len=builder.length()/8;
        }else{
            len=builder.length()/8+1;
        }
        //创建一个存储压缩后的byte[]
        byte[] huffmanCodeBytes=new byte[len];
        int index=0;//记录第几个byte
        for(int i=0;i<builder.length();i+=8){//步长为8，每8为一个字节数组
            String strByte;
            if(i+8>builder.length()){//不够8位
                strByte=builder.substring(i);
            }else{
                strByte=builder.substring(i,i+8);
            }
            //将strByte转换成byte【】，放入到by
            huffmanCodeBytes[index]=(byte)Integer.parseInt(strByte,2);
            index++;
        }
        return  huffmanCodeBytes;

    }

}

//创建Node节点，带数据和权值
class Nodes implements Comparable<Nodes>{
    Byte data;//存放数据本身，比如‘a’=97,' '=32
    int weight;//权值，表示字符出现的次数
    Nodes left;
    Nodes right;

    public Nodes(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Nodes o) {
        return this.weight-o.weight;//从小到大排序
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }



}
