3. 无重复字符的最长子串

给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> temp = new HashSet<Character>();
        int length = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1;
        int max = 0;
        for(int i=0; i<length;i++){
            if(i != 0){
                // 左指针向右移动一格，移除一个字符
                temp.remove(s.charAt(i - 1));
            }
            while(right + 1< length && !temp.contains(s.charAt(right + 1))){
                // 不断地移动右指针
                temp.add(s.charAt(right + 1));
                ++right;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            max = Math.max(max,right - i + 1);
        }
        return max;
    }
}
```


4. 寻找两个正序数组的中位数

给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 用于计算最终计算得到的中位数
        double res = 0;

        int n1 = nums1.length,n2 = nums2.length;
       // 创建一个新的数组，用于存储两个输入数组的元素，长度为两个数组长度之和
        int[] sort_num = new int[n1 + n2];
        // 将第一个输入数组 nums1 的元素复制到新数组 sort_num 中
        for(int i = 0;i != n1;i++){
            sort_num[i] = nums1[i];
        }
        // 将第二个输入数组 nums1 的元素复制到新数组 sort_num 中
        for(int i = 0;i != n2;i++){
            sort_num[n1 + i] = nums2[i];
        }
        Arrays.sort(sort_num);
        // 判断排序后的数组长度是否为偶数
        if(sort_num.length % 2 ==0){
            // 如果是偶数，中位数为中间两个数的平均值
            res = (sort_num[sort_num.length/2] + sort_num[sort_num.length/2 - 1]) / 2.00000;
        }else {
            // 如果是奇数，中位数为中间的那个数
            res = sort_num[sort_num.length/2];
        }
        return res;
    }
}
```
