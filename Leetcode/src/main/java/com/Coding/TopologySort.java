package com.Coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class TopologySort {

  private static class Node {

    public Object val;
    public int in = 0; // 入链路数量
    public Node(Object val) {
      this.val = val;
    }
  }

  /*public static List<Node> topologySort(Graph graph) {
    HashMap<Node, Integer> inMap = new HashMap<Node, Integer>();
    Queue<Node> zeroInQueue = new LinkedList<Node>();
    for (Node node : graph.nodes.values()) {
      inMap.put(node, node.in);// 遍历所有的点记录几点及其入度
      if (node.in == 0) {// 登记点及其入度
        zeroInQueue.add(node);// 如果入度是0就添加到zeroInQueue
      }
    }
    List<Node> list = new ArrayList<Node>();
    while (!zeroInQueue.isEmpty()) {
      Node cur = zeroInQueue.poll();
      list.add(cur);
      for (Node next : cur.nexts) {
        inMap.put(next, inMap.get(next) - 1);// node的下一节点入度减一
        if (inMap.get(next) == 0) {//next.in == 0是错误的，应该写更新的了节点
          zeroInQueue.add(next);
        }
      }
    }
    return list;
  }*/
}
