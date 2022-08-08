package com.hnu.字符串;
class Node{
    boolean isEnd;
    Node[] next = new Node[26];
    Node(){};
}
public class 前缀树208 {
    
        Node node;
        public 前缀树208() {
            node = new Node();//初始化，新建一个哑节点
        }
        //插入一个字符串
        public void insert(String word) {
            //用cur标记遍历位置，从根节点开始
            Node cur = node;
            for(int i=0; i<word.length(); i++){
                //存在这个字符就继续
                if(cur.next[word.charAt(i)-'a']!=null){
                    cur = cur.next[word.charAt(i)-'a'];
                    continue;
                }
                //不存在就建立节点
                Node new_node = new Node();
                cur.next[word.charAt(i)-'a'] = new_node;
                cur = new_node;
            }
            //最后在尾部记录一下
            cur.isEnd = true;

        }
        //查询字符串是否存在
        public boolean search(String word) {
            Node cur = node;
            for(int i=0; i<word.length(); i++){
                if(cur.next[word.charAt(i)-'a']!=null){
                    cur = cur.next[word.charAt(i)-'a'];
                    continue;
                }
                return false;
            }
            //这步很重要如果还没到底部，说明他是一个前缀例如：leet相对于leetcode.
         //搜索leet时，会返回false,因为leet的最后一个字符所在的节点的isEnd为false.
            return cur.isEnd;
        }
        //查询是否是前缀
        public boolean startsWith(String prefix) {
            Node cur = node;
            for(int i=0; i<prefix.length(); i++){
                if(cur.next[prefix.charAt(i)-'a']!=null){
                    cur = cur.next[prefix.charAt(i)-'a'];
                    continue;
                }
                return false;
            }
            //这里就可以直接返回true;
            return true;
        }


/**
 * Your 前缀树208 object will be instantiated and called as such:
 * 前缀树208 obj = new 前缀树208();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

}
