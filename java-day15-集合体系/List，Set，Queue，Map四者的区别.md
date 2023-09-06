# List，Set，Queue，Map四者的区别
Java 集合， 也叫作容器，主要是由两大接口派生而来：一个是 Collection 接口，主要用于存放单一元素；另一个是 Map 接口，主要用于存放键值对。对于Collection 接口，下面又有三个主要的子接口： List 、 Set 和 Queue
![输入图片说明](../image/List%EF%BC%8CSet%EF%BC%8CQueue%EF%BC%8CMap%E5%9B%9B%E8%80%85%E7%9A%84%E5%8C%BA%E5%88%AB.png)
# 集合底层数据结构梳理
- List接口
    - ArrayList：Object[]数组
    - Vector：Object[]数组
    - LinkedList：双向链表
- Set接口
    - HashSet（无序，唯一）：底层使用HashMap存储数据
    - LinkedHashSet：LinkedHashSet 是 HashSet 的子类，并且其内部是通过
    - LinkedHashMap 来实现的。
    - TreeSet (有序，唯一): 红黑树(自平衡的排序二叉树)
- Queue接口
    - PriorityQueue : Object[] 数组
    - ArrayQueue : Object[] 数组
- Map接口
    - HashMap：数组 + 链表|红黑树。
        - 添加元素时，如果当前数组的长度小于 64，那么会选择先进行数组扩容。
        - 如果数组长度达到64时，数组不在扩容，而是转换数组 + 链表存储元素。
        - 当链表长度大于阈值（默认为 8）时，将链表转化为红黑树，以减少搜索时间。
- LinkedHashMap：（双向链表） +（数组 + 链表|红黑树），其中的双向链表实现了LinkedHashMap能顺序访问。
- Hashtable ：数组+链表组成的
- TreeMap ：红黑树（自平衡的排序二叉树）