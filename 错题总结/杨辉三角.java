package com.Array;
import java.util.Arrays;

public class day809ArrayHomework03 {
    public void home4() {
        /*杨辉三角
            编写一个程序，生成一个给定行数的杨辉三角
                 1
                1 1
               1 2 1
              1 3 3 1
             1 4 6 4 1
            1 5 10 10 5 1
        */
        /*这个代码中，每一行的作用如下：
                第1行：方法声明，传入一个参数n，表示要打印的杨辉三角的行数。
                第2行：定义一个二维数组nums，用于存储杨辉三角的每一个数字。
                第3-8行：for循环，遍历每一行，初始化当前行的数组。
                        其中，nums[i] = new int[i + 1]表示定义当前行的长度为i+1，即第1行长度为1，第2行长度为2，以此类推；
                        nums[i][0] = 1和nums[i][i] = 1表示每一行的两个端点都为1；
                        nums[i][j] = nums[i - 1][j - 1] + nums[i - 1][j]表示中间的数字是上一行相邻两个数字之和。
                第9-15行：for循环，遍历每一行，输出杨辉三角。
                        其中，for (int j = 0; j < n - i - 1; j++)表示打印数字之间的空格；
                        System.out.print(nums[i][j] + " ")表示输出当前数字；System.out.println()表示换行，开始输出下一行。
        * */
        int n = 6;

        int[][] nums = new int[n][];  // 定义一个二维数组存储杨辉三角的每一个数字

        for (int i = 0; i < n; i++) {  // 遍历每一行
            nums[i] = new int[i + 1];  // 初始化当前行的数组
            nums[i][0] = 1;  // 每一行的第一个数字为1
            for (int j = 1; j < i; j++) {  // 遍历当前行中间的数字
                nums[i][j] = nums[i - 1][j - 1] + nums[i - 1][j];  // 根据上一行的数字计算出当前数字
            }
            nums[i][i] = 1;  // 每一行的最后一个数字为1
        }
        // 输出
        for (int i = 0; i < n; i++) {  // 遍历每一行
            for (int j = 0; j < n - i - 1; j++) {  // 打印数字之间的空格，使三角形更美观
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {  // 遍历当前行中的数字
                System.out.print(nums[i][j] + " ");  // 输出当前数字
            }
            System.out.println();  // 换行，开始输出下一行
        }
    }
}
