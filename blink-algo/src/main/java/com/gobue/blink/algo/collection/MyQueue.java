package com.gobue.blink.algo.collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 232. 用栈实现队列
 */
class MyQueue {

    int front = -1;
    Stack<Integer> in;
    Stack<Integer> out;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        in = new Stack<Integer>();
        out = new Stack<Integer>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (in.isEmpty()) {
            front = x;
        }
        in.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!out.isEmpty()) {
            return out.peek();
        }
        return front;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }

    public static void main(String[] args) {
        Queue<Integer> list = new LinkedList<Integer>();
    }
}