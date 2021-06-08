package com.master.chapter008;

/**
 * @ClassName: RecursionTest
 * @Package: com.master.chapter008
 * @Description: 递归
 * @Datetime: 2021/6/1 18:46
 * @author: ColorXJH
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(7);
        System.out.println(factorial(5));
    }

    //递归:打印问题
    public static void test(int n){
        if(n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }

    //递归：阶乘问题
    public static int factorial(int n){
        if(n==1){
            return 1;
        }else{
            return factorial(n-1)*n;
        }
    }
}

//迷宫回溯问题，递归
    //简单的说，递归就是方法自己调用自己，每次调用时传入不同的变量，递归有助于编程者解决复杂的问题，同时可以让代码变得简洁
//递归调用机制
    //1：打印问题
    //2:阶乘问题

//递归能解决哪些问题？
    //各种数学问题:8皇后，汉诺塔，阶乘问题，迷宫，球和篮子的问题，各种算法中也会使用到递归，比如快排，归并排序，二分查找，分治算法
    //将用栈解决的问题-》使用递归代码更加简洁

//递归需要遵守的重要规则
    //1:执行一个方法时，就创建一个新的受保护的独立空间（栈空间）
    //2:方法的局部变量是独立的，不会相互影响（如果方法中使用的是引用类型的变量，就会共享该引用类型的数据）
    //3:递归必须向推出递归的条件逼近，否则就是无限递归了
    //4:当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，该方法也执行完毕


