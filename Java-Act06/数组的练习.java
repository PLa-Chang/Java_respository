package com.Array;

import java.util.Random;
import java.util.Scanner;

public class day807ArrayDemo02 {
    public static void main(String[] args) {
        day807ArrayDemo02 demo = new day807ArrayDemo02();
        // demo.arr1();
        // demo.arr2();
        // demo.arr3();
        // demo.arr4();
        // demo.arr5();
        // demo.arr6();
        // demo.arr7();
        // demo.arr8();
        // demo.arr9();
        demo.ary10();
    }

    /*1.定义一个含有五个元素的数组,并为每个元素赋值,求数组中所有元素的最小值
        操作步骤：
        1.定义5个元素数组
        2.可以使用初始化数组的两种方式之一为数组元素赋值
        3.遍历数组求数组中的最小值
    * */
    public void arr1() {
        int[] arr = {2, 34, 22, 6, 13};
        int min = arr[0];
        for (int item : arr) {
            if (item < min) {
                min = item;
            }
        }
        System.out.println("最小值是：" + min);
    }

    /*2. 创建一个长度为6的整数数组。请编写代码，随机生成六个0（包含）-100（不包含）之间的整数存放到数组中，然后计算出数组中所有元素的和并打印。
     * */
    public void arr2() {
        int[] arr2 = new int[6];
        Random random = new Random(100);    //随机生成0——100之间的数
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            int num = random.nextInt(100);
            System.out.println(num);
            arr2[i] = num;
            sum += arr2[i];
        }
        System.out.println("0（包含）-100（不包含）之间的整数和=" + sum);
    }

    /*3．需求：求出数组中索引与索引对应的元素都是奇数的元素
        分析：
        1、遍历数组
        2、判断索引是否是奇数（索引 % 2 != 0）
        3、判断索引对应的元素是否是奇数(arr[索引] % 2 != 0)
        4、满足条件输出结果
    * */
    public void arr3() {
        int[] arr = {2, 3, 7, 9, 34, 22, 13, 11};
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0 && arr[i] % 2 != 0) {
                System.out.println(arr[i] + "是奇数");
            }
        }
    }

    /*4.按要求在main方法中完成以下功能：
        a. 定义一个长度为5的int型数组arr，提示用户输入5个1-60之间的数字作为数组元素
        b. 生成2-10（范围包含2和10）之间的随机数num
        c. 遍历数组arr,筛选出数组中不是num倍数的元素并输出
        PS：输入的数组元素范围包括1和60，不需要代码判断
    * */
    public void arr4() {
        int[] arr = new int[5];
        int num2 = (int) (Math.random() * 10 + 2);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入5个1-60之间的数字：");
        for (int i = 0; i < arr.length; i++) {
            int num = scanner.nextInt();
            arr[i] = num;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num2 != 0) {
                System.out.println(arr[i]);
            }
        }
    }

    /*5.有一个数组int[] arr = {9,1,3,4,54,56,23,22,20,43,45,78};,要求打印数组中能被6整除的元素。
     * */
    public void arr5() {
        int[] arr = {9, 1, 3, 4, 54, 56, 23, 22, 20, 43, 45, 78};
        for (int item : arr) {
            if (item % 6 == 0) {
                System.out.println(item);
            }
        }
    }

    /*6.定义一个长度为20的数组，元素为20-40的随机数，要求判断指定元素在数组中出现的次数，指定元素为键盘录入范围为20-40之间。
     * */
    public void arr6() {
        int[] arr = new int[20];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            int num = (int) (Math.random() * 10 + 20);
            arr[i] = num;
        }
        System.out.println("请输入范围为20-40之间的数：");
        int num = scanner.nextInt();
        for (int i = 0; i < arr.length; i++) {
            if (num == arr[i]) {
                count++;
            }
        }
        System.out.println(count);
    }

    /*7. 现有一个整数数组，数组中的每个元素都是[0-9]之间的数字，从数组的最大索引位置开始到最小索引位置，依次表示整数的个位、十位、百位。。。依次类推。请编写程序计算，这个数组所表示的整数值。例如：
        1 数组：{2, 1, 3, 5, 4}
        2 表示的整数为：21354 // 注：是整数类型的两万一千三百五十四，不是字符串拼起来的。
    * */
    public void arr7() {
        int[] arr = new int[]{2, 1, 3, 5, 4};
        int len = arr.length;
        int num = 0;
        for (int i = len - 1; i >= 0; i--) {
            num += arr[i] * Math.pow(10, 4 - i);
        }
        System.out.println(num);
    }

    /*8. 定义一个数组其中包含多个数字。用自己的方式最终实现，奇数放在数组的左边，偶数放在数组的右边。（可以创建其他数组，不必须在原数组中改变）
     * */
    public void arr8() {
        int[] arr1 = {1, 4, 7, 9, 3, 5};
        int countRight = 0;     // 用来统计数组中元素为偶数的个数
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] % 2 == 0) {
                countRight++;
            }
        }
        int countLeft = 0;      // 用来统计数组中元素为奇数的个数
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] % 2 != 0) {
                countLeft++;
            }
        }
        int[] left = new int[countLeft];        //用来存储奇数
        int[] right = new int[countRight];      //用来存储偶数
        int[] arr = new int[arr1.length];       //用来汇总奇数和偶数的

        int r = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] % 2 == 0) {
                right[r] = arr1[i];
                r++;
            }
        }

        int l = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] % 2 != 0) {
                left[l] = arr1[i];
                l++;
            }
        }

        for (int i = 0; i < left.length; i++) {
            arr[i] = left[i];
        }

        for (int i = 0; i < right.length; i++) {
            arr[left.length] = right[i];
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    /*9. 在唱歌比赛中，有6名评委给选手打分，分数范围是[0 - 100]之间的整数。选手的最后得分为：去掉最高分、最低分后的4个评委的平均分，请完成上述过程并计算出选手的得分。
     * */
    public void arr9() {
        Scanner scanner = new Scanner(System.in);
        int[] score = new int[6];
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            System.out.printf("请评委%d打分[0 - 100]", i + 1);
            score[i] = scanner.nextInt();
        }
        int max = score[0];
        int min = score[0];
        for (int i = 0; i < score.length; i++) {
            if (score[i] < min) {
                min = score[i];
            } else if (score[i] > max) {
                max = score[i];
            }
        }
        for (int i = 0; i < 6; i++) {
            sum += score[i];
        }
        sum = sum - (max + min);
        System.out.println(sum / 4);
    }

    /*10. 现有一个int 数组，数组中有十个元素。将数组反转后输出。
     * */
    public void ary10() {
        int[] arr = new int[]{12, 23, 34, 56, 1, 2, 34, 8, 9, 10};
        int[] newArr = new int[arr.length];
        int j = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[j];
            j--;
        }
        for (int i = 0; i < newArr.length; i++) {
            System.out.print(newArr[i]+"\t");
        }
    }
}
