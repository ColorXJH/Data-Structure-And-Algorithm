package com.master.chapter009;

/**
 * @ClassName: SortCompare
 * @Package: com.master.chapter009
 * @Description: 常用排序算法总结对比
 * @Datetime: 2021/6/30 19:12
 * @author: ColorXJH
 */
public class SortCompare {
}

//  排序算法        平均时间复杂度     最好情况        最坏情况        空间复杂度       排序方式        稳定性
//  冒牌排序        O(n^2)          O(n^2)         O(n^2)        O(1)           in-place       稳定
//  选择排序        O(n^2)          O(n^2)         O(n^2)        O(1)           in-place       不稳定
//  插入排序        O(n^2)          O(n^2)         O(n^2)        O(1)           in-place       稳定
//  希尔排序        O(n log n)      O(n log2 n)    O(n log2 n)   O(1)           in-place       不稳定
//  归并排序        O(n log n)      O(n log n)     O(n log n)    O(n)           out-place      稳定
//  快速排序        O(n log n)      O(n log n)     O(n^2)        O(log n)       in-place       不稳定
//  堆排序          O(n log n)      O(n log n)     O(n log n)    O(1)           in-place       不稳定
//  计数排序        O(n+k)          O(n+k)         O(n+k)        O(k)           out-place      稳定
//   桶排序         O(n+k)          O(n+k)         O(n^2)        O(n+k)         out-place      稳定
//  基数排序        O(n*k)          O(n*k)         O(n*k)        O(n+k)         out-place      稳定

    //n:数据规模  k：桶的个数 in-place:不占用额外内存 out-place:占用额外内存