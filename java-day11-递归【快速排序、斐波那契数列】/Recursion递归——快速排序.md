```java
package com.method;

import java.util.Arrays;

/*
* 递归的使用:
*   快速排序：快速排序的基本思想是选择一个基准元素，然后将列表分成两部分，
*            一部分包含小于基准的元素，另一部分包含大于基准的元素，最后再对这两部分进行递归排序。
*   以下是快速排序的步骤：
*                1.选择基准： 从列表中选择一个基准元素。
*                2.划分： 将列表分成两部分，一部分包含小于基准的元素，另一部分包含大于基准的元素。这个过程被称为划分（Partitioning）。
*                   遍历列表，将小于基准的元素移动到基准的左边，将大于基准的元素移动到基准的右边。
*                   这可以通过维护两个指针来完成，一个从列表的左侧移动，一个从右侧移动，直到两个指针相遇。
*                3.递归排序： 递归地对划分后的两部分进行排序，即分别对小于基准的部分和大于基准的部分应用快速排序算法。
*                4.合并： 将经过排序的两部分合并起来，以获得最终的排序结果。
* */
public class Recursion03 {
    public static void main(String[] args) {
        int[] arr = {1,2,6,0,12,15,26,12};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // 递归生成斐波那契数列的第 n 个数
    public static void quickSort(int[] arr, int low,int high) {
        // 从左边开始查找
        int left = low;
        // 从右边开始
        int right = high;
        //首先进行每次递归前的判断
        if (low > high){
            return;
        }
        // 参考值【假设是第一个】
        int temp = arr[low];
        // 左小右大
        while (left < right){
            // 开始从右边一个一个的跟参考值做比较，如果右边的数比参考值大，则继续向左移动
            while (temp <= arr[right] && left < right){
                right --;
            }
            // 右指针遇到比参考值小的数时，暂停，进行左指针的判断，如果左边的数比参考值小，则继续向右移动，直到遇到比参考值大的数
            while (temp >= arr[left] && left < right){
                left ++;
            }
            // 走到这里就说明左指针找到了比参考值大的数，右指针找到了比参考值小的数。
            // 如果满足条件则进行以下交换
            if (left < right) {
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
            }

        }
        // 循环结束就代表左指针和右指针相遇了【相等】
        // 所以就可以进行参考值与相等的那个值进行交换了。
        arr[low] = arr[left];
        arr[left] = temp;
        // 递归：负责左边的比较
        quickSort(arr, low,left - 1);
        // 负责右边的比较
        quickSort(arr,left + 1, high);
    }
}
```
![输入图片说明](../image/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F.png)
