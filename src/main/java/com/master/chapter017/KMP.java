package com.master.chapter017;


import java.util.Arrays;

/**
 * @ClassName: KMP算法
 * @Package: com.master.chapter017
 * @Description:
 * @Datetime: 2021/10/7 19:49
 * @author: ColorXJH
 */
public class KMP {

}

//应用场景：字符串匹配问题
    //1：有一个字符串str1="尚硅谷 硅谷你娃硅谷姑姑 尚硅谷你好尚硅谷 尚硅谷尚硅谷你好"，和一个字符串str2="硅谷你娃硅谷姑姑"
    //2:现在要判断str1是否含有str2,如果存在，就返回第一次出现的位置，如果没有则返回-1
//1:暴力匹配算法：假设现在str1匹配到i位置，子串str2匹配到j位置，则有：
    //1:如果当前字符串匹配成功，即str1[i]=str2[j],则i++,j++继续匹配下一个字符串
    //2:如果失败，令i=1-(j-1),j=0.相当于每次匹配失败时，i回溯，j被置为0
    //3：用暴力匹配时，会得到大量的回溯，每次只移动一位，如是不匹配，移动到下一位接着判断，浪费了大量的时间
    //4：代码实现如下：

    class KMPtest{
        public static void main(String[] args) {
            String str1="硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
            String str2="尚硅谷你尚硅你";
            int index=BLMatch(str1,str2);
            System.out.println("index="+index);
        }

        public static int  BLMatch(String str1,String str2){
            char[]s1=str1.toCharArray();
            char[]s2=str2.toCharArray();
            int s1len=s1.length;
            int s2len=s2.length;
             int i=0;//i索引指向s1
             int j=0;//j索引指向s2
            while(i<s1len&&j<s2len){//保证匹配时不越界

                if(s1[i]==s2[j]){
                    i++;
                    j++;
                }else{
                    i=i-(j-1);
                    j=0;
                }
            }
            //判断是否匹配成功
            if(j==s2len){
                return i-j;
            }else{
                return -1;
            }
        }
    }


    //KMP算法介绍
        //1:KMP是一种解决模式串在文字串中是否出现过，如果出现过，最早出现位置的经典算法
        //2:Knuth-Morris-Pratt字符串查找算法，简称为kmp算法,常用于在一个文本串s内查找一个模式串p的出现位置，这个算法由以上三人于1977年联合发表
        //3:kmp方法算法就是利用之前判断过的信息，通过一个next数组，保存模式串中前后最长公共子序列的长度，每次回溯时，通过next数组找到，前面匹配过的位置
            //省去了大量的计算时间：https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
    //最佳应用：字符串匹配问题：有一个字符串str1="BBC ABCDAB ABCDABCDABDE"和一个子串str2="ABCDABD",现在要判断str1中是否包含str2,如果存在就返回第一次出现的位置
        //如果不存在就返回-1，要求使用kmp算法完成，不能使用暴力匹配算法
            //1:先得到子串的部分匹配表
            //2:使用部分匹配表完成kmp匹配，移动的位数=已匹配的字符数-对应的部分匹配值（部分匹配表中位置）

    class KmpAlgorithm{
        public static void main(String[] args) {
            String str1="BBC ABCDAB ABCDABCDABDE";
            String str2="ABCDABD";
            int[]next=kmpNext("ABCDABD");//[0, 0, 0, 0, 1, 2, 0]
            System.out.println("next="+ Arrays.toString(next));
            int index=KmpSearch(str1,str2,next);
            System.out.println("index="+index);
        }

        //写出我们的kmp搜索算法
        /**
         * 功能描述:
         * @param: str1 源字符串
         * @param: str2 子串
         * @param: next 部分匹配表，是子串对应的部分匹配表
         * @Return: int  -1 没有匹配到 否则返回第一个匹配的位置
         * @Author: ColorXJH
         * @Date: 2021/10/9 15:43
         */
        public static int KmpSearch(String str1,String str2,int[]next){
            //遍历
            for(int i=0,j=0;i<str1.length();i++){
                //需要处理str1.charAt(i)!=str2.charAt(j)
                //Kmp算法的核心点
                while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                    j=next[j-1];
                }
                if(str1.charAt(i)==str2.charAt(j)){
                    j++;
                }
                if(j==str2.length()){//找到了
                    return i-j+1;
                }
            }
            return -1;
        }

        //获取到一个字符串（子串）的部分匹配值表
        public static int[] kmpNext(String dest){
            //创建一个next数组保存部分匹配值
            int[] next=new int[dest.length()];
            next[0]=0;//如果字符串长度为1，部分匹配值就是0
            for(int i=1,j=0; i<dest.length();i++){
                //当dest.charAt(i)!=dest.charAt(j),我们需要从next[j-1]获取新的j,直到我们发现有条件满足est.charAt(i)==dest.charAt(j)
                //这是kmp算法的核心点
                while(j>0&&dest.charAt(i)!=dest.charAt(j)){
                    j=next[j-1];
                }
                //当dest.charAt(i)==dest.charAt(j)条件满足时，部分匹配值就+1
                if(dest.charAt(i)==dest.charAt(j)){j++;}
                next[i]=j;
            }
            return next;
        }
    }