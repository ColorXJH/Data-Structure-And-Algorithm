package com.master.chapter009;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName: RedixSortingTest
 * @Package: com.master.chapter009
 * @Description: 基数排序（桶排序）
 * @Datetime: 2021/6/29 19:57
 * @author: ColorXJH
 */
public class RedixSortingTest {
    public static void main(String[] args) {
        int []array={53, 3, 542, 748, 14, 214};
        double []array3={53, 3, 542, 748, 14, 214,122};
        //radixSort(array);//【542 53 3 14 214 748】
        //测试基数排序速度
        int [] arrays2=new int[80000];
        for(int i=0;i<80000;i++){
            arrays2[i]=(int)(Math.random()*9000000);
        }
        System.out.println("=======开始排序==========");
        long time1=System.currentTimeMillis();
        radixSort(arrays2);
        long time2=System.currentTimeMillis();
        System.out.println("耗时："+(time2-time1)+"毫秒");

        //如果是80000000个数据需要多大内存呢？开辟10个桶外加原来的数组总共11个数组，每个int 4个字节 /1024=KB /1024/MB /1024=GB
        //80000000*11*4/1024/1024/1024=3.3G
        //所以需要注意内存的大小

        //计数排序算法
        countSort(array);
        //桶排序算法
        bucketSort(array3);



    }
    //基数排序算法
    public static void radixSort(int[] array){
        /*//第一轮：（针对每个元素的个位数进行排序）

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //二维数组包含10个一维数组，为了防止数据溢出，则每个一维数组（桶）的大小为array.length,基数排序是使用空间换时间的经典算法
        int[][]bucket=new int[10][array.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义了一个一维数组来记录各个桶每次放入的数据个数
        //bucketElementCounts[0]记录的就是bucket[0]桶的放入数据个数
        int[] bucketElementCounts=new int[10];

        //第一轮（针对每个元素的各位经行排序处理）
        for(int j=0;j<array.length;j++){
            //取出每个元素的个位数值
            int digitOfElement=array[j]%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[j];
            //如果下一个数还是在这个桶中，需要自增该桶的索引
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标）依次取出数据，放回到原数组=》【542 53 3 14 214 748】
        int index=0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for(int k=0;k<bucketElementCounts.length;k++){
            //如果桶中有数据，我们才放入到原数组
            if(bucketElementCounts[k]!=0){
                //循环该桶，即第k个桶（即第k个一维数组），放入
                for(int l=0;l<bucketElementCounts[k];l++){
                    //取出元素，放入到array
                    array[index++]=bucket[k][l];
                }
            }
            //第一轮处理后，需要将每个bucketElementCounts[k]=0
            bucketElementCounts[k]=0;
        }
        System.out.println("第一轮对个位数的排序处理之后："+ Arrays.toString(array));

        //第2轮（针对每个元素的各位经行排序处理）
        for(int j=0;j<array.length;j++){
            //取出每个元素的十位数值
            int digitOfElement=array[j]/10%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[j];
            //如果下一个数还是在这个桶中，需要自增该桶的索引
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标）依次取出数据，放回到原数组=》[3, 14, 214, 542, 748, 53]
        index=0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for(int k=0;k<bucketElementCounts.length;k++){
            //如果桶中有数据，我们才放入到原数组
            if(bucketElementCounts[k]!=0){
                //循环该桶，即第k个桶（即第k个一维数组），放入
                for(int l=0;l<bucketElementCounts[k];l++){
                    //取出元素，放入到array
                    array[index++]=bucket[k][l];
                }
            }
            bucketElementCounts[k]=0;
        }
        System.out.println("第2轮对个位数的排序处理之后："+ Arrays.toString(array));

        //第3轮（针对每个元素的各位经行排序处理）
        for(int j=0;j<array.length;j++){
            //取出每个元素的百位数值
            int digitOfElement=array[j]/100%10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[j];
            //如果下一个数还是在这个桶中，需要自增该桶的索引
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标）依次取出数据，放回到原数组=》[3, 14, 53, 214, 542, 748]
        index=0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for(int k=0;k<bucketElementCounts.length;k++){
            //如果桶中有数据，我们才放入到原数组
            if(bucketElementCounts[k]!=0){
                //循环该桶，即第k个桶（即第k个一维数组），放入
                for(int l=0;l<bucketElementCounts[k];l++){
                    //取出元素，放入到array
                    array[index++]=bucket[k][l];
                }
            }
            bucketElementCounts[k]=0;
        }
        System.out.println("第3轮对个位数的排序处理之后："+ Arrays.toString(array));*/


        //根据上述发现规律归纳如下：
            //1：先要得到数组中最大的位数
            int max=array[0];//假设第一个数就是最大数
            for(int i=0;i<array.length;i++){
                if(array[i]>max){
                    max=array[i];
                }
            }
            //得到最大数是几位数
            int maxLength=(max+"").length();
            //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            //二维数组包含10个一维数组，为了防止数据溢出，则每个一维数组（桶）的大小为array.length,基数排序是使用空间换时间的经典算法
            int[][]bucket=new int[10][array.length];

            //为了记录每个桶中，实际存放了多少个数据，我们定义了一个一维数组来记录各个桶每次放入的数据个数
            //bucketElementCounts[0]记录的就是bucket[0]桶的放入数据个数
            int[] bucketElementCounts=new int[10];

            //使用循环处理代码
            for(int i=0,n=1;i<maxLength;i++,n*=10){
                //针对每个元素的对应位进行排序，第一次个位，第二次十位...
                for(int j=0;j<array.length;j++){
                    //取出每个元素对应位数值
                    int digitOfElement=array[j]/n%10;
                    //放入到对应的桶中
                    bucket[digitOfElement][bucketElementCounts[digitOfElement]]=array[j];
                    //如果下一个数还是在这个桶中，需要自增该桶的索引
                    bucketElementCounts[digitOfElement]++;
                }
                //按照这个桶的顺序（一维数组的下标）依次取出数据，放回到原数组=》【542 53 3 14 214 748】
                int index=0;
                //遍历每一个桶，并将桶中的数据放入原数组
                for(int k=0;k<bucketElementCounts.length;k++){
                    //如果桶中有数据，我们才放入到原数组
                    if(bucketElementCounts[k]!=0){
                        //循环该桶，即第k个桶（即第k个一维数组），放入
                        for(int l=0;l<bucketElementCounts[k];l++){
                            //取出元素，放入到array
                            array[index++]=bucket[k][l];
                        }
                    }
                    //第i+1轮处理后，需要将每个bucketElementCounts[k]=0
                    bucketElementCounts[k]=0;
                }
                //System.out.println("第"+(i+1)+"轮对个位数的排序处理之后："+ Arrays.toString(array));
            }




    }



    /**
     * 计数排序
     * @param arr：要排序的数组
     * @return
     */
    public static int[] countSort(int[] arr){
        int n = arr.length;
        //先定义两个变量用来存放数组中的最大值和最小值
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < n; i++){
            if (max < arr[i]){
                max = arr[i];
            }
            if (arr[i] < min){
                min = arr[i];
            }
        }
        //定义一个长度为len的数组，这样做是为了防止数组中的最小值为1000，最大值为1010
        //这样创建一个大小为10的数组就行了，不用创建大小为1010的数组，浪费空间
        int len = max - min + 1;
        //哪个数字出现了一次，就把它的数字作为下标存起来，假如1006出现了一次，就把temp[1006-1000]加一
        int[] temp = new int[len];
        for (int i = 0; i < n; i++) {
            temp[arr[i] - min]++;
        }
        int k = 0;
        //对temp进行遍历，temp[i]的值就是i出现的次数，加入temp[5]=3，说明(5+1000)出现了3次
        for (int i = 0; i < len; i++) {
            for (int j = temp[i]; j > 0; j--){
                arr[k] = i + min;
                k++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }
    //计数排序是一种适合于最大值和最小值的差值不是不是很大的排序。
    //基本思想：就是把数组元素作为数组的下标，然后用一个临时数组统计该元素出现的次数，
    // 例如 temp[i] = m, 表示元素 i 一共出现了 m 次。最后再把临时数组统计的数据从小到大汇总起来，此时汇总起来是数据是有序的。


    /**
     * 桶排序
     * @param arr
     * @return
     */
    public static double[] bucketSort(double[] arr){
        //1.计算出最大值和最小值，求出两者的差值
        double min = arr[0];
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
            if (arr[i] < min){
                min = arr[i];
            }
        }
        double d = max - min;

        //2.初始化桶
        int bucketNum = arr.length;
        List<LinkedList<Double>> bucketList = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        //3.遍历数组中的元素，把所有元素都放入对应的桶当中
        for (int i = 0; i < arr.length; i++) {
            //计算当前元素应该放在哪个桶里面
            int num = (int)((arr[i] - min) / (d / (bucketNum - 1)));
            bucketList.get(num).add(arr[i]);
        }

        //4.对每个桶里面的元素进行排序
        for (int i = 0; i < bucketNum; i++) {
            Collections.sort(bucketList.get(i));
        }

        //5.输出全部元素
        int k = 0;
        for(LinkedList<Double> doubles : bucketList){
            for (Double aDouble : doubles) {
                arr[k] = aDouble;
                k++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }
    //桶排序可以看作是对计数排序的改进，计数排序对于数值在一定范围的整数数组可以进行排序，
    //但是对于小数的数组却没有办法计数，这时候就要用到桶排序。
    //桶排序是将数组中的数划分到不同的区间，再对每个桶中的数进行排序，
    //这时用的排序算法一般为快速排序或者归并排序，然后再把所有桶中的数返回给原数组。

}

//基数排序算法介绍
    //1：基数排序redix-sort属于分配式排序，又称桶子法bucket-sort,顾名思义，他是通过键值的各个位置的值，将要排序的元素分配至某些
        //"桶"中，达到排序的作用
    //2:基数排序算法是属于稳定的排序，基数排序法的效率是高效稳定的排序算法
    //3：基数排序算法是桶排序算法的扩展
    //4:基数排序是1887年赫尔曼-何乐礼发明的，他是这样实现的：将整数按照位数切割成不同的数字，然后按照每个位数分别比较

//基本思想：
    //将所有带比较数值统一为同样的数位长度，数位较短的数前面补零，然后从最低位开始，依次进行依次排序。这样从最低位排序一直到最高位排序完成以后
        //数列就变成一个有序序列
    //看下图理解
    //[53 3 542 748 14 214]
        //第一轮排序：将每个元素的个位数取出，然后再看整个数应该放在哪个对应的桶（一个一维数组）
        //按照这个桶的顺序（一维数组的下标）依次取出数据，放回到原数组=》【542 53 3 14 214 748】
        //第二轮排序，将每个元素的十位放在桶中排序，依次取出数据=》【3 14 214 542 748 53】
        //第三轮。。。最后得出数据=》【3 14 53 214 748】
        //多少论取决于最大数的位数

//基数排序的说明
    //1:基数排序是对传统桶排序的扩展，速度很快
    //2:基数排序是经典的空间换时间的方式，占用内存很大，当对海量数据进行排序时，容易造成OutOfMemoryError
    //3:基数排序是稳定的：假定在待排序的记录中，存在多个相同关键字的记录，若经过排序，这些记录相对次序保持不变，则称这种排序算法为稳定的，否则为不稳定的
    //4:有负数的数组，我们不用基数排序，如果要支持负数，见下方解决

class RadixSortIncludeNagetive{
    /*
     * 基数序 解决不能负数的问题
     */
    public static void negative_radix_sortin(int[] str) {
        // 桶 10个桶 每个桶的最大容量默认为数组长度
        int[][] bucket = new int[10][str.length];
        // 每个桶的当前容量
        int[] capacity = new int[10];
        // 注意：正数负数共用10个桶 不要再重新定义 节约内存 因为每次都有清理空
        int negative_number = 0;// 记录负数个数
        int positive_number = 0;// 记录正数个数
        int[] negative_arr = new int[str.length];// 存放负数
        int[] positive_arr = new int[str.length];// 存放正数
        // 记录正数最大值 和负数最小值 用于记录长度
        int max = positive_arr[0];
        int min = negative_arr[0];
        // 先把原数组分成一个负数数组 和一个正数数组 并找出正数最大值
        for (int a = 0; a < str.length; a++) {
            if (str[a] < 0) {
                negative_arr[negative_number] = str[a];
                negative_number += 1;
            } else {
                positive_arr[positive_number] = str[a];
                positive_number += 1;
            }
            // 找出正数最大值
            if (str[a] > max) {
                max = str[a];
            }
        }
        // 把负数数组变成正数数组 再找出最大值
        for (int r = 0; r < negative_number; r++) {
            negative_arr[r] = negative_arr[r] / (-1);
            // 此时的负数数组已经是正数
            if (negative_arr[r] > min) {
                min = negative_arr[r];
            }
        }
        // 求出最大长度 用于判断循环几大轮
        int max_length = (max + "").length();
        int min_length = (min + "").length();
        // 先排序正数
        for (int b = 0, u = 1; b < positive_number; b++, u *= 10) {
            for (int i = 0; i < positive_number; i++) {
                int base = positive_arr[i] / u % 10; // 比如基数为 4
                // 将基数按照规则放进桶中
                bucket[base][capacity[base]] = positive_arr[i]; // 放进第四个桶中 的第一几个当前容量位置
                capacity[base]++; // 容量增加
            }
            // 取出数据
            int d = 0;
            for (int k = 0; k < capacity.length; k++) {
                if (capacity[k] != 0) {
                    for (int p = 0; p < capacity[k]; p++) {
                        positive_arr[d] = bucket[k][p];
                        d++;
                    }
                }
                // 注意：清零
                capacity[k] = 0;
            }
        }
        // 排序负数数组 正数差不多 注意最后取出数据的时候 才大到小 不再是从小到大
        for (int b = 0, u = 1; b < negative_number; b++, u *= 10) {
            for (int i = 0; i < negative_number; i++) {
                int base = negative_arr[i] / u % 10;
                bucket[base][capacity[base]] = negative_arr[i]; // 放进第四个桶中 的第一几个当前容量位置
                capacity[base]++;
            }
            int d = 0;
            for (int k = capacity.length - 1; k >= 0; k--) {
                if (capacity[k] != 0) {
                    for (int p = 0; p < capacity[k]; p++) {
                        negative_arr[d] = bucket[k][p];
                        d++;
                    }
                }
                // 注意：清零
                capacity[k] = 0;
            }
        }
        // 把负数数组转化成负数 覆盖给原来的数组（从0开始）
        int c = 0;
        for (int e = 0; e < negative_number; e++) {
            str[c] = negative_arr[e] / (-1);
            c++;
        }
        // 正数接上原来数组
        for (int t = 0; t < positive_number; t++) {
            str[c] = positive_arr[t];
            c++;
        }
    }
}