package com.gobue.blink.algo.cache;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥已经存在，则变更其数据值；如果密钥不存在，则插入该组「密钥/数据值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 *  
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LRUCache {


    //HashMap o(1)的查询
    private HashMap<Integer, DLinkedListNode> cacheMap;
    //双向链表 o(1)的存储
    private DLinkedListNode head;
    private DLinkedListNode tail;
    private int capacity;
    private int size;

    public LRUCache() {
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity, 1);
        head = new DLinkedListNode();
        tail = new DLinkedListNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedListNode dLinkedListNode = cacheMap.get(key);
        if (dLinkedListNode == null) {
            return -1;
        }

        //查询过后，放到链表顶部
        moveToHead(dLinkedListNode);
        return dLinkedListNode.value;
    }

    public void put(int key, int value) {
        DLinkedListNode dLinkedListNode = cacheMap.get(key);
        if (dLinkedListNode == null) {
            DLinkedListNode node = new DLinkedListNode(key, value);
            cacheMap.put(key, node);
            addNode(node);
            if (++size > capacity) {
                cacheMap.remove(popTail().key);
                --size;
            }
        } else {
            dLinkedListNode.value = value;
            moveToHead(dLinkedListNode);
        }
    }

    /**
     * 添加：head - > node -> tail的方式，新增的节点永远放在（除去哑节点head）第一位
     *
     * @param node
     */
    public void addNode(DLinkedListNode node) {
        DLinkedListNode next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }

    private void removeNode(DLinkedListNode node) {
        //断链
        DLinkedListNode pre = node.pre;
        DLinkedListNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    public void moveToHead(DLinkedListNode node) {
        //删除、再添加就到顶部了
        removeNode(node);
        addNode(node);
    }

    /**
     * 淘汰最不常用的
     */
    public DLinkedListNode popTail() {
        DLinkedListNode pre = tail.pre;
        removeNode(pre);
        return pre;
    }


    class DLinkedListNode {
        private int key;
        private int value;
        private DLinkedListNode pre;
        private DLinkedListNode next;

        public DLinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public DLinkedListNode() {
        }
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));// 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

}
