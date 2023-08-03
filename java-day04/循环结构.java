package com.base;

public class day803LoopDemo02 {
    public static void main(String[] args) {

        System.out.println("--------------------------------打印九九乘法表-----------------------------------------------------");
        // 九九乘法表【倒置】
        for (int i = 9; i >= 1; i--) {                  // 外层循环控制“行数”
            for (int j = i; j >= 1; j--) {              // 内层循环控制“列数”
                System.out.print("|"+i + "*" + j + "=" + i*j + "|\t");
            }
            System.out.println();
        }

        System.out.println("--------------------------------九九乘法表-----------------------------------------------------");
        // 九九乘法表【正的】
        for (int i = 1; i <=9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + "*" + j + "=" + i*j + "\t");
            }
            System.out.println();
        }


        System.out.println("-------------------------直角三角形-------------------------------------");
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("---------------------------等腰三角形----------------------------------");
        for (int i = 1; i <= 6; i++) {
            for (int j = 6; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("----------------------------菱形-------------------------------------");

        for (int i = 1; i <= 10; i++) {
            for (int j = 10; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        for (int i = 9; i >= 1; i--) {
            for (int j = i; j <= 10; j++) {
                System.out.print(" ");
            }
            for (int j = i; j >= 1; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("----------------------------圣诞树-------------------------------------");
        for (int i = 1; i <= 5; i++) {
            for (int j = 8; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // System.out.println("----------------------------圣诞树中层-------------------------------------");
        for (int i = 1; i <= 3; i++) {
            for (int j = 5; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i+ 3; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // System.out.println("----------------------------圣诞树下层-------------------------------------");
        for (int i = 1; i <= 3; i++) {
            for (int j = 4; j >= i; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i+ 4; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // System.out.println("----------------------------圣诞树根-------------------------------------");
        for (int i = 1; i <= 4; i++) {
            for (int j = 6; j >= 1; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 3; j++) {
                System.out.print("# ");
            }
            System.out.println();
        }

    }
}
