package com.hnu.链表;

import java.util.HashMap;
/*
链表：定义前后节点，key和value
hashMap：key 为 链表节点的key,value 为节点本身。
LRU对外暴露的函数中，操作都是key和value.
hashmap根据key可以找到node，
而node 的操作LRUCache其他函数封装起来 的。

 */

class DlinkNode{
    DlinkNode pre;//方便在删除和添加时找到相邻的节点
    DlinkNode next;
    int value;
    int key;
    public DlinkNode(){};
    public DlinkNode(int key,int value){
        this.key=key;
        this.value=value;
    }
}

public class LRUCache {
    HashMap<Integer,DlinkNode> cache;//在hashMap中记录key和节点
    int capacity=0;//HashMap容量
    DlinkNode head;
    DlinkNode tail;
    //构造方法
    public LRUCache(int capacity) {
        this.capacity=capacity;
        //使用指定容量初始化HashMap
        cache=new HashMap<>(capacity);
        //初始化头尾结点均为空节点
        head=new DlinkNode();
        tail=new DlinkNode();
        head.next=tail;//初始化双向链表的头尾节点，方便操作
        tail.pre=head;
    }
    
    public int get(int key) {
        //若当前HashMap中存在该key
        if(cache.containsKey(key)){
            //从HashMap中获取对应的结点
            DlinkNode node=cache.get(key);
            //将该结点移动到链头
            moveToHead(node);  
            return node.value;
        }else return -1;//当前HashMap中不存在该key，返回-1
    }
    
    public void put(int key, int value) {
        //若当前HashMap中存在该key
        if(cache.containsKey(key)){
            //从HashMap中获取对应的结点
            DlinkNode node=cache.get(key);
            //更新该结点的值
            node.value=value;
            //将该结点移动到链头
            moveToHead(node);
        }else{ //若当前HashMap中不存在该key
            //首先判断HashMap是否还有多余的容量
            if(cache.size()==capacity){ //容量不足
                //从HashMap中删除链尾结点
                cache.remove(tail.pre.key);//取出双向链表的尾部哑节点，进而取出存放数据的最后一个节点
                //从链表中删除链尾结点
                deleteNode(tail.pre);            
            }
            //创建一个新的结点
            DlinkNode node=new DlinkNode(key,value);
            //添加到链头
            addNode(node);
            //添加到HashMap中
            cache.put(key,node);
        }
    }
    
    //删除结点
// 1 2
// 2 3
// 3 4
    void deleteNode(DlinkNode node){
        //将当前节点的前一个节点，作为给当前节点下一个节点的前一个节点
        //当前节点的下一个节点，作为当前节点前一个节点的下一个节点


        //删除某个节点，只需将
        node.next.pre=node.pre;
        node.pre.next=node.next;
    }
    //在链头添加结点
// head 1
// 1 2
    void addNode(DlinkNode node){
        node.next=head.next;
        head.next.pre=node;

        head.next=node;
        node.pre=head;
    }
    //将指定结点移动到链头
    void moveToHead(DlinkNode node){
        //删除该结点
        deleteNode(node);
        //在链头添加该结点
        addNode(node);
    }
}
