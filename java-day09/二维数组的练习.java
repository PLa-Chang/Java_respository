package com.Array;

import java.util.Arrays;

public class day809ArrayHomework01 {
    public static void main(String[] args) {

        //一共三大类题：二维数组的排序、杨辉三角、诗句旋转90°的输出

        /*一、数组排序
            设存在以下三个数组:
        * */
        // 设 names 数组中存放学生姓名
        String[] names = { "安琪拉" , "王昭君" , "蔡文姬" , "妲己" , "张良" };
        // 设 courses 数组中依次存放三门课程的名称
        String[] courses = { "C++" , "Java" , "Python" };
        // 设 scores 数组中依次存储的是 names 数组中各个学生的 C++ 、Java 、Python 课程的成绩
        int[][] scores = {
                { 90 , 89 , 75 } ,
                { 59 , 40 , 100 } ,
                { 100 , 99 , 80 } ,
                { 80 , 61 , 61 } ,
                { 60 , 100 , 99 } ,
        };
        //  采用以下程序可以输出每个学生的姓名以及该学生各门课程的成绩:
        for( int i = 0 ; i < scores.length ; i++ ){
            System.out.print( names[ i ] + " => " ); // 输出学生姓名
            for( int j = 0 ; j < scores[ i ] .length ; j++ ){
                System.out.print( courses[ j ] + ":");// 输出课程名称
                System.out.print( scores[ i ][ j ] ); // 输出课程成绩
                if( j < scores[ i ] .length - 1 ) {
                    System.out.print( " , " );
                }
            }
            System.out.println();
        }
        

        /*  1. 设计程序按照各个学生的 Java 成绩进行排序 ( 降序 )
            2. 设计程序，根据学生总成绩进行排序(降序排列)，并输出学生姓名、每门课程的名称和该学生的成绩、该学生的总成绩
        * */
        // 1.按照Java成绩排序
        /*for (int i = 0; i < scores.length - 1; i++) {
            for (int j = 0; j < scores.length - i - 1; j++) {
                if (scores[j][1] < scores[j+1][1]) {
                    int[] temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                }
            }
        }
        //  采用以下程序可以输出每个学生的姓名以及该学生各门课程的成绩:
        for( int i = 0 ; i < scores.length ; i++ ){
            System.out.print( names[ i ] + " => " ); // 输出学生姓名
            for( int j = 0 ; j < scores[ i ] .length ; j++ ){
                System.out.print( courses[ j ] + ":");// 输出课程名称
                System.out.print( scores[ i ][ j ] ); // 输出课程成绩
                if( j < scores[ i ] .length - 1 ) {
                    System.out.print( " , " );
                }
            }
            System.out.println();
        }*/
        // 2. 设计程序，根据学生总成绩进行排序(降序排列)，并输出学生姓名、每门课程的名称和该学生的成绩、该学生的总成绩
        int[] tempArr = new int[scores.length];     // 用于存储总成绩的
        // 计算总成绩
        for (int i = 0; i < scores.length; i++) {
            int count = 0;
            for (int j = 0; j < scores[i].length; j++) {
                count += scores[i][j];
                tempArr[i] = count;
            }
        }
        System.out.println(Arrays.toString(tempArr));

        // 根据总成绩进行排序
        for (int i = 0; i < tempArr.length; i++) {
            for (int j = 0; j < tempArr.length - i - 1; j++) {
                if (tempArr[j] < tempArr[j + 1]) {
                    // 按总分排序
                    int temp = tempArr[j];
                    tempArr[j] = tempArr[j + 1];
                    tempArr[j + 1] = temp;

                    // 对整个成绩进行排序
                    int[] t = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = t;

                    // 对姓名进行排序
                    String  n = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = n;

                }
            }
        }
        System.out.println(Arrays.toString(tempArr));
        //  采用以下程序可以输出每个学生的姓名以及该学生各门课程的成绩:
        for( int i = 0 ; i < scores.length ; i++ ){
            System.out.print( names[ i ] + " => " ); // 输出学生姓名
            for( int j = 0 ; j < scores[ i ] .length ; j++ ){
                System.out.print( courses[ j ] + ":");// 输出课程名称
                System.out.print( scores[ i ][ j ] ); // 输出课程成绩
                if( j < scores[ i ] .length - 1 ) {
                    System.out.print( " , " );
                }
            }
            System.out.println();
        }

        /*二、杨辉三角
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

        // 用户输入要打印的行数
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

        //三. 尝试设计一组循环语句，将以上形式的输出结果翻转为以下形式(旋转 90 度):
        /*   花 夜 处 春
             落 来 处 眠
             知 风 闻 不
             多 雨 啼 觉
             少 声 鸟 晓
        * */
        char[][] cX = {
                {'春', '眠', '不', '觉', '晓'},
                {'处', '处', '闻', '啼', '鸟'},
                {'夜', '来', '风', '雨', '声'},
                {'花', '落', '知', '多', '少'}
        };

        for (int j = 0; j < cX[0].length; j++) {
            for (int i = cX.length - 1; i >= 0; i--) {
                System.out.print(cX[i][j] + " ");
            }
            System.out.println();
        }
        
        
        // 四、将以下数组中的诗旋转90°输出：

        char[][] poem = {
                {'十', '年', '生', '死', '两', '茫', '茫', '，', '不', '思', '量', '，', '自', '难', '忘', '。'},
                {'千', '里', '孤', '坟', '，', '无', '处', '话', '凄', '凉', '。'},
                {'纵', '使', '相', '逢', '应', '不', '识', '，', '尘', '满', '面', '，', '鬓', '如', '霜', '。'},
                {'夜', '来', '幽', '梦', '忽', '还', '乡', '，', '小', '轩', '窗', '，', '正', '梳', '妆', '。'},
                {'相', '顾', '无', '言', '，', '惟', '有', '泪', '千', '行', '。'},
                {'料', '得', '年', '年', '肠', '断', '处', '，', '明', '月', '夜', '，', '短', '松', '冈', '。'}
        };
        int max = poem[0].length;
        for (int i = 1; i < poem.length; i++) {
            if (max < poem[i].length) {
                max = poem[i].length;
            }
        }

        for (int i = 0 ; i < max; i ++){
            for (int j = poem.length - 1; j >= 0; j--) {
                if (i >= poem[j].length) {
                    System.out.print("\t");
                }else {
                    System.out.print(poem[j][i] + "\t");
                }
            }
            System.out.println();
        }




        /*复制数组【拓展】
            有如下数组 nums ，请通过删除掉其中的 33 ，得到一个新的数组 newNums
                        int[] nums = {2, 11, 33, 44, 55, 66, 277}
                        newNums = {2, 11, 44,55, 66, 277}
        * */
        int[] nums = {2, 11, 33, 44, 55, 66, 277};
        int[] newNums = new int[nums.length - 1];

        System.arraycopy(nums,0,newNums,0,2);
        System.arraycopy(nums,3,newNums,2,4);
        System.out.println(Arrays.toString(newNums));
    }
}
