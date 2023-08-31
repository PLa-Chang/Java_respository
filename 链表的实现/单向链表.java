package com.interfaceDemo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedList implements List {
    private Node head = null;
    private int size;

    @Override
    public boolean add(Object value) {
        // 待添加的元素
        Node current = new Node(value,null);
        // 判断头节点是否为空
        if (head == null){
            // 如果为空则把当前要添加的元素赋给head
            head = current;
        }else {
            // 说明头节点有元素，则遍历链表，直到下一个节点为null，表示到了最后一个节点
            Node v = head;  // 当前遍历的 节点
            while (v.next != null){     // 不是null，则表示不是最后一个节点
                v = v.next;     // 指向下一个节点
            }
            // 说明找到了最后一个节点，将当前要添加的元素，添加到最后一个节点的下一个节点。
            v.next = current;
        }
        size++;     // 计数，记录链表的长度
        return true;
    }

    /**
     * 遍历链表1：
     */
    public void loop() {
        // 定义一个指针，它的初始值应该是指向head节点
        Node p = head;
        while (p != null){
            System.out.println(p.value);
            p = p.next;
        }
    }

    /**
     * 遍历链表2：重写toString，输出元素
     * @return  返回字符串
     */
    @Override
    public String toString(){
        // 判断头节点是否为空
        if (head == null){
            return "Linked[]";
        }else {
            StringBuilder sb = new StringBuilder("Linked[");
            // 将头节点赋给v的目的是为了防止程序运行完之后改变头节点的值
            Node v = head;
            //
            while (v != null){
                sb.append(v.value);     // 当前节点的值
                if (v.next != null){
                    sb.append(", ");
                }
                v = v.next;     // 指向下一个节点元素
            }
            sb.append("]");
            return sb.toString();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] obj = new Object[size];
        int index = 0;
        Node h = head;
        while (h != null){
            obj[index++] = h.value;
            h = h.next;
        }
        return obj;
    }

    /**
     * 删除链表中的某个元素
     * @param o element to be removed from this list, if present
     * @return  true表示删除成功
     */
    @Override
    public boolean remove(Object o) {
        // 判断链表上是否有元素
        if (size < 0){
            return false;
        }
        // 将要删除的元素
        Node currentRemove = new Node(o,null);
        // 判断删除的元素是不是头节点
        if (head.equals(currentRemove)){
            // 将头节点的value赋给临时的一个Node节点
            Node h = head;
            // 头节点指向临时节点的下一个节点，即将第二个节点赋值给头节点
            head = h.next;
            // 将头节点的next值为null
            h.next = null;
            size--;
            return true;
        }else {
            // 指向被删除元素的前一个节点
            Node preNode = head;
            //  假设要删除的节点是remove
            Node remove = head.next;

            // 如果remove不是要删除的节点
            while (remove != null && !remove.equals(currentRemove)){
                // 查找下一个节点
                preNode = remove;
                remove = remove.next;
            }
            if (remove != null){    //删除节点
                // 要删除节点的前一个节点指向要删除节点的下一个节点
                preNode.next = remove.next;
                remove.next = null;     // 将删除节点的next指针赋为null
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    // 清空链表
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * 根据索引获取节点元素
     * @param index index of the element to return
     * @return  返回一个节点值
     */
    @Override
    public Object get(int index) {
        // 先判断index是否合法
        /*if (index < 0 || index > size - 1){
            return null;
        }*/
        checkIndex(index);
        // 当前节点
        Node currentNode = head;
        for (int i = 0; i < index; i ++){
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    // 判断Index是否合法的方法
    private void checkIndex(int index){
        if (index < 0 || index > size - 1){
            throw new LinkedIndexOutOfBoundsException("下标有问题！");
        }
    }

    /**
     * 修改指定位置的元素
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return  返回修改的值
     */
    @Override
    public Object set(int index, Object element) {
        //判断index是否合法
        checkIndex(index);
        // 将头节点赋给一个临时Node，为了防止改变头节点的值
        Node h = head;
        // 一个一个往后查找，直到找到index才结束
        for (int i = 0; i < index; i++) {
            h =h.next;
        }
        // 说明找到了，就把当前节点的值赋给临时变量
        Object old = h.value;
        // 再把要修改的值赋给当前节点
        h.value = element;
        return old;
    }

    /**
     * 指定位置添加元素
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, Object element) {
        // 判读那index是否合法，size + 1是为了最后一位添加元素
        if (index < 0 || index > size + 1){
            throw new LinkedIndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        // 先判断是否为头节点
        if (index == 0){
            head = new Node(element,head);
        }else {
            // 如果不是头节点，则继续向后查找
            Node pre = head;
            Node n = head.next;
            // 因为头节点已经判断过了，所以从1 开始查找，循环直到index位才结束
            for (int i = 1; i < index; i++){
                pre = n;
                n = n.next;
            }
            // 找到index位了，
            pre.next = new Node(element,n);
        }
        size++;
    }

    /**
     * 根据index删除节点
     * @param index the index of the element to be removed
     * @return 返回被删除的节点
     */
    @Override
    public Object remove(int index) {
        checkIndex(index);
        if (index == 0){
            Node h = head;
            head = h.next;
            // 将头节点的next值为null
            h.next = null;
            size--;
            return h.value;
        }else {
            Node pre = head;
            Node n = head.next;
            for (int i = 1; i < index; i ++){
                pre = n;
                n = n.next;
            }
            Node t = pre;
            pre.next = n.next;
            n.next = null;
            size--;
            return n.value;
        }
    }

    /**
     * 查找元素的位置
     * @param o element to search for
     * @return  -1 表示没有找到
     */
    @Override
    public int indexOf(Object o) {
        // 获取头节点
        Node h = head;
        for (int i = 0; i < size; i++) {
            // 判断元素是否相同
            if (h.value == null){   // 避免空指针异常
                if (h.value == o){  // 表示如果查找的元素为null则返回
                    return i;
                }
            }else if (h.value.equals(o)){
                return i;
            }
            h = h.next;     // 指向下一个节点
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }
    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Node节点类
     */
    private class Node{
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }

        /**
         * 判断node节点是否一样
         * 节点的值是否 一样
         * @param obj   传入一个对象
         * @return  true 表示一样
         */
        @Override
        public boolean equals(Object obj){
            if (obj == null){
                return false;
            }
            if (this == obj){
                return true;
            }
            if (obj instanceof Node node){
                if (this.value == null){
                    return node.value == null;
                }else {
                    return this.value.equals(node.value);
                }
            }
            return false;
        }

    }

    /**
     * 自定义异常类
     */
    private class LinkedIndexOutOfBoundsException extends IndexOutOfBoundsException{
        public LinkedIndexOutOfBoundsException(String message){
            super(message);
        }
    }
    // ==================================================
    @Override
    public ListIterator listIterator() {
        return null;
    }
    @Override
    public Iterator iterator() {
        return null;
    }
    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
