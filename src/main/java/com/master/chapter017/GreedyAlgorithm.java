package com.master.chapter017;

import java.util.*;

/**
 * @ClassName: GreedyAlgorithm 贪心算法
 * @Package: com.master.chapter017
 * @Description:
 * @Datetime: 2021/10/9 16:04
 * @author: ColorXJH
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到map
        Map<String,HashSet<String>> broadcasts=new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String>hs1=new HashSet<>();
        hs1.add("北京");
        hs1.add("上海");
        hs1.add("天津");
        HashSet<String>hs2=new HashSet<>();
        hs2.add("广州");
        hs2.add("北京");
        hs2.add("深圳");
        HashSet<String>hs3=new HashSet<>();
        hs3.add("成都");
        hs3.add("上海");
        hs3.add("杭州");
        HashSet<String>hs4=new HashSet<>();
        hs4.add("天津");
        hs4.add("上海");
        HashSet<String>hs5=new HashSet<>();
        hs5.add("杭州");
        hs5.add("大连");
        broadcasts.put("K1",hs1);
        broadcasts.put("K2",hs2);
        broadcasts.put("K3",hs3);
        broadcasts.put("K4",hs4);
        broadcasts.put("K5",hs5);
        //存放所有地区
        HashSet<String>allAreas=new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        allAreas.add("深圳");
        //创建一个arraylist,存放选择的电台集合
        List<String> selects=new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台的覆盖的地区和当前还没有覆盖的地区的交集
        Set<String>temp=new HashSet<>();
        //定义一个maxkey,保存在一次遍历过程中，能够覆盖的最多未覆盖地区对应的电台的key,如果maxkey不为空，则会加入到selects
        String maxkey=null;
        while(allAreas.size()!=0){//表示还没有覆盖到所有的地区
            //每while一次，需要将maxkey置空
            maxkey=null;
            //遍历broadcasts
            for(String key:broadcasts.keySet()){
                //每进行一次循环，都需要将temp清空
                temp.clear();
                HashSet<String> areas=broadcasts.get(key);
                temp.addAll(areas);
                //求出temp和allareas集合的交集，交集会重新赋值给temp
                temp.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量比maxkey指向的集合未覆盖的地区还要多
                //就需要重置maxKey
                //temp.size()>broadcasts.get(maxkey).size()体现出贪心算法，每次都选择最优的
                if(temp.size()>0&&(maxkey==null||temp.size()>broadcasts.get(maxkey).size())){
                    maxkey=key;
                }
            }
            //maxKey不等于空的情况下，就应该将maxkey加入到selects中
            if(maxkey!=null){
                selects.add(maxkey);
                //将maxkey指向的广播电台覆盖的地区从allareas中去除掉
                allAreas.removeAll(broadcasts.get(maxkey));
            }
        }
        System.out.println("得到的选择结果："+selects);

    }
}
//应用场景：集合覆盖问题
    //假设存在下面需要付费的广播台，以及广播台的信号可以覆盖的区域，如何选择最少的广播台，让所有的区域都可以接受到信号
        //广播台               覆盖区域
        //K1                    北京上海天津
        //K2                    广州北京深圳
        //K3                    成都上海杭州
        //K4                    上海天津
        //K5                    杭州大连
//贪心算法介绍
    //贪婪算法是指在对问题进行求解时，在每一步选择中都选择最好或者最优的选择，从而希望能够导致结果是最好或者最优的算法
    //贪婪算法所得到的结果不一定是最优的结果（有时候会是最优解），但是都是相对近似最优解的结果
//思路分析：如何找出覆盖所有地区的广播台的集合呢，使用穷举法实现，列出每个可能的广播台的集合，这被称为幂集，假设总的有n个广播台，则广播台
    //的组合共有2^n -1个，假设每秒可以计算10个子集，也会随着广播台数量变多而时间花费指数上升
    //2:使用贪心算法：效率高，目前并没有算法可以快速的计算得到准备的值，使用贪心算法，则可以得到非常接近的解，而且效率高，选择策略上
        //因为需要覆盖全部地区的最小集合
            //1:遍历所有的广播电台，找到一个覆盖了最多未覆盖地区的电台（此电台可能包含一些已经覆盖的地区，但是没有关系）
            //2：将这个电台加入到一个集合中（比如arraylist），想办法把该电台覆盖的地区在下次对比时去掉
            //3:重复第一步直到覆盖了全部的地区
    //以下为代码实现

