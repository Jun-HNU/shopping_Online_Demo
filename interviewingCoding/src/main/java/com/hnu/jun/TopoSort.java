package com.hnu.jun;

import java.util.*;

/*
拓扑排序
 */
public class TopoSort {
    /**
     * 自定义拓扑排序节点类
     */
    public static class Node {
        public Object val;
        public int pathIn = 0; // 入度数
        public Node(Object val) {
            this.val = val;
        }
        public Object getVal() {
            return val;
        }
        public int getPathIn() {
            return pathIn;
        }
    }

    /**
     * 自定义拓扑图类
     */
    public static class Graph {
        // 图节点的集合
        public Set<Node> vertexSet = new HashSet<Node>();
        // map 记录当前节点，以及其入度
        public Map<Node, Set<Node>> adjaNode = new HashMap<Node, Set<Node>>();
        // 建立两个节点之间联系
        public boolean addNode(Node start, Node end) {
            if (!vertexSet.contains(start)) {
                vertexSet.add(start);
            }
            if (!vertexSet.contains(end)) {
                vertexSet.add(end);
            }
            if (adjaNode.containsKey(start) && adjaNode.get(start).contains(end)) {
                return false;
            }
            if (adjaNode.containsKey(start)) {
                adjaNode.get(start).add(end);
            } else {
                Set<Node> temp = new HashSet<Node>();
                temp.add(end);
                adjaNode.put(start, temp);
            }
            end.pathIn++;
            return true;
        }
    }
    public static class Topo {
        // 拓扑排序结果
        private LinkedList<Node> result;
        // 用来存储入度为0的顶点
        private Queue<Node> setOfZeroIndegree;
        private Graph graph;
        //构造函数，初始化
        public Topo(Graph di) {
            this.graph = di;
            this.result = new LinkedList<>();
            this.setOfZeroIndegree = new LinkedList<Node>();
            // 对入度为0的集合进行初始化
            for (Node iterator : this.graph.vertexSet) {
                if (iterator.pathIn == 0) {
                    this.setOfZeroIndegree.add(iterator);
                }
            }
        }
        //拓扑排序处理过程
        public void sort() {
            while (!setOfZeroIndegree.isEmpty()) {
                Node v = setOfZeroIndegree.poll();
                // 将当前节点添加到结果集中
                result.addFirst(v);
                if (this.graph.adjaNode.keySet().isEmpty()) {
                    return;
                }
                // 遍历由v引出的所有边
                for (Node w : this.graph.adjaNode.get(v)) {
                    // 将该边从图中移除，通过减少边的数量来表示
                    w.pathIn--;
                    // 如果入度为0，那么加入入度为0的集合
                    if (0 == w.pathIn) {
                        setOfZeroIndegree.add(w);
                    }
                }
                this.graph.vertexSet.remove(v);
                this.graph.adjaNode.remove(v);
            }
            // 如果此时图中还存在边，那么说明图中含有环路
            if (!this.graph.vertexSet.isEmpty()) {
                throw new IllegalArgumentException("Has Cycle !");
            }
        }
        //获取结果集
        public List<Node> getResult() {
            return result;
        }
    }
}
 
 
 