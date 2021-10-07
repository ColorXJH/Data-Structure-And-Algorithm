package com.master.chapter017;

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
        //暴力匹配算法实现
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