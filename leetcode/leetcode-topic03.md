5. 最长回文子串


提示
给你一个字符串 s，找到 s 中最长的回文子串。

如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。

 

示例 1：

> 输入：s = "babad"
> 
> 输出："bab"
> 
> 解释："aba" 同样是符合题意的答案。

示例 2：


> 输入：s = "cbbd"
> 
> 输出："bb"

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        int strLen = s.length(); // 获取输入字符串的长度
        int left = 0; // 初始化左边界指针
        int right = 0; // 初始化右边界指针
        int len = 1; // 初始化回文子串长度
        int maxStart = 0; // 记录最长回文子串的起始位置
        int maxLen = 0; // 记录最长回文子串的长度
        for (int i = 0; i < strLen; i++){ // 遍历字符串的每个字符
            left = i - 1; // 初始化左边界指针为当前字符的前一个字符
            right = i + 1; // 初始化右边界指针为当前字符的后一个字符
            
            // 扩展回文子串，向左右两边扩展，直到不再是回文子串
            while (left >= 0 && s.charAt(left) == s.charAt(i)){
                len++;
                left--;
            }
            while(right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while(left >= 0 && right< strLen && s.charAt(right)==s.charAt(left)){
                len = len + 2; // 回文子串长度加2，因为左右两边各扩展了一个字符
                left--;
                right++;
            }
            
            // 更新最长回文子串的起始位置和长度
            if (len > maxLen){
                maxLen = len;
                maxStart = left;
            }
            len = 1; // 重置回文子串长度为1，准备查找下一个字符的回文子串
        }
        
        // 根据最长回文子串的起始位置和长度返回结果
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }
}
```

6. N 字形变换

将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
```java
P       A       H       N

A   P   L   S   I   I   G
 
Y       I       R
```
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);
 

示例 1：

> 输入：s = "PAYPALISHIRING", numRows = 3
> 输出："PAHNAPLSIIGYIR"

示例 2：
> 输入：s = "PAYPALISHIRING", numRows = 4
> 输出："PINALSIGYAHRPI"
```java
解释：

P     I    N

A   L S  I G

Y A   H R

P     I
```
示例 3：

输入：s = "A", numRows = 1

输出："A"
