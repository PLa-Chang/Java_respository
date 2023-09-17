package com.method;
/*
* 递归的使用：
*   二分查找
* */
public class Recursion01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int target = 5;
        int index = binarySearch(arr, target);
        System.out.println(index);      // 4
    }
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        int index = binarySearch(arr, target, left, right);
        return index;
    }
    public static int binarySearch(int[] arr, int target, int left, int right){
        if (left > right) {
            return -1;
        }
        int middle = (left + right) >> 1;
        if (arr[middle] == target) {
            return middle;
        } else if (arr[middle] > target) {
            right = middle - 1;
        }else {
            left = middle + 1;
        }
        return binarySearch(arr, target, left, right);
    }
}

// 斐波那契数列
/*
* 递归的使用:
*   斐波那契数列：F(n) = F(n-1) + F(n-2)
* */
public class Recursion02 {
    public static void main(String[] args) {
        int n = 10;  // 想要生成的斐波那契数列的长度
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    // 递归生成斐波那契数列的第 n 个数
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}

 
