
1. 两数之和
  - 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

  - 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

  - 你可以按任意顺序返回答案。

> 解法一：使用HashMap
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>(len - 1);
        hashMap.put(nums[0],0);
        for(int i = 1;i < len;i++){
            int another = target-nums[i];
            if(hashMap.containsKey(another)){
                return new int[]{hashMap.get(another),i};
            }
            hashMap.put(nums[i],i);
        }
        
        return new int[0];
    }
}
```
> 解法二：暴力枚举解法

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i<nums.length;i++){
            for(int j = i+1; j<nums.length;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
            
        }
        return new int[0];
    }
}
```

2. 两数相加

  - 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

  - 请你将两个数相加，并以相同形式返回一个表示和的链表。

  - 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义一个进位数carry，carry为几就说明要进位多少
        int carry = 0;
        //result是结果链表
        ListNode result = new ListNode();
        //head为结果链表的头节点，返回的结果就是头节点
        ListNode head = result;
        //prev是该节点的上一个节点
        ListNode prev = null;
        while (l1 != null || l2 != null) {
            //获取两个链表同一位的value值，若其中一个为空就取0
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            //存入的结果为a+b+进位数，再对10取余
            result.val = (a + b + carry) % 10;
            //计算进位值carry
            carry = (a + b + carry) / 10;
            //若两个节点不为null，就取该节点的下一个节点，否则就取null
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            //新添一个节点到结果链表，并将其赋值给result
            ListNode temp = new ListNode();
            result.next = temp;
            prev = result;
            result = result.next;
        }

        //判断是否进位，若要进位就把carry存入result节点，若不需要就把最后一个空节点移除
        if (carry != 0) {
            result.val = carry;
        }else {
            prev.next = null;
        }
        return head;
    }
}
```
