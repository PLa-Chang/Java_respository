# 7. 整数反转

给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。
 

示例 1：

输入：x = 123
输出：321
示例 2：

输入：x = -123
输出：-321
示例 3：

输入：x = 120
输出：21
示例 4：

输入：x = 0
输出：0
 ```java
class Solution {
    public int reverse(int x) {
        long res = 0;
        if(x == 0){
            return 0;
        }else{
            while(x != 0){
                int temp = x % 10;
                res = res * 10 +temp;
                x /= 10;
            }
        }
        int high = (int) (Math.pow(2, 31) - 1);
        int low = (int) Math.pow(-2, 31);
        if(res > high || res < low){
            return 0;
        }else{
            return (int)res;
        }
    }
}
```

# 9. 回文数

给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。

回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

例如，121 是回文，而 123 不是。
 

示例 1：

输入：x = 121
输出：true
示例 2：

输入：x = -121
输出：false
解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3：

输入：x = 10
输出：false
解释：从右向左读, 为 01 。因此它不是一个回文数。

```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 ==0)){
            return false;
        }
        int y = x;
        int result = 0;
        while(x != 0){
            int last = x % 10;
            x = x / 10;
            result = result * 10 + last;
        }
        if (y == result){
            return true;
        }else {
            return false;
        }
    }
}
```
