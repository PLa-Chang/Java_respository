11. 盛最多水的容器

给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。

找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。


返回容器可以储存的最大水量。


说明：你不能倾斜容器。

 
示例 1：
![image](https://github.com/PLa-Chang/Java_respository/assets/86483506/50c21acc-000f-46bc-b03f-b52c85055d7b)


输入：[1,8,6,2,5,4,8,3,7]

输出：49 

解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例 2：

输入：height = [1,1]

输出：1

```java
class Solution {
    public int maxArea(int[] height) {
        // 2. 双指针
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while(left < right){
            int area = Math.min(height[left],height[right]) * (right - left);
            result = Math.max(result,area);
            if (height[left] <= height[right]){
                left++;
            }else {
                right--;
            }
        }
        return result;
    }
}
```

12. 整数转罗马数字

罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给你一个整数，将其转为罗马数字。

 

示例 1:

输入: num = 3
输出: "III"

示例 2:

输入: num = 4
输出: "IV"

示例 3:

输入: num = 9
输出: "IX"

示例 4:

输入: num = 58
输出: "LVIII"
解释: L = 50, V = 5, III = 3.

示例 5:

输入: num = 1994
输出: "MCMXCIV"
解释: M = 1000, CM = 900, XC = 90, IV = 4.

```java
class Solution {
    public String intToRoman(int num) {
        /**
        模拟：将表示罗马数字的13个特殊字符以及对应的整数分别放在数组中，然后遍历数组，对于给出的num，寻找出不超过num的最大字符，并将每一个罗马字符通过StringBuffer拼接在一起即可。
         */
        StringBuffer res = new StringBuffer();
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        for(int i = 0; i < values.length; i++){
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value){
                res.append(symbol);
                num -= value;
            }
            if (num == 0){
                break;
            }
        }
        return res.toString();
    }
}
```
