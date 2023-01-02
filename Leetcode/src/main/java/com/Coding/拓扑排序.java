package com.Coding;
 
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 拓扑排序 {
 
	/**
	 * 自定义拓扑排序节点类
	 */
	private static class Node {
		public Object val;
		public int pathIn = 0; // 入度数
		public Node(Object val) {
			this.val = val;
		}
	}
 
	/**
	 * 自定义拓扑图类
	 */
	private static class Graph {
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
			if (adjaNode.containsKey(start)
					&& adjaNode.get(start).contains(end)) {
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
	
	private static class Topo {
		private LinkedList<Node> result; // 拓扑排序结果
		private Queue<Node> setOfZeroIndegree; // 用来存储入度为0的顶点
		private Graph graph;
 
		//构造函数，初始化
		public Topo(Graph di) {
			this.graph = di;
			this.result = new LinkedList<>();
			this.setOfZeroIndegree = new LinkedList<Node>();
			// 对入度为0的集合进行初始化
			for(Node iterator : this.graph.vertexSet){
				if(iterator.pathIn == 0){
					this.setOfZeroIndegree.add(iterator);
				}
			}
		}
 
		//拓扑排序处理过程
		private void sort() {
			while (!setOfZeroIndegree.isEmpty()) {
				Node v = setOfZeroIndegree.poll();
				
				// 将当前节点添加到结果集中
				result.addFirst(v);
				
				if(this.graph.adjaNode.keySet().isEmpty()){
					return;
				}
				
				// 遍历由v引出的所有边
				for (Node w : this.graph.adjaNode.get(v) ) {
					// 将该边从图中移除，通过减少边的数量来表示
					w.pathIn--;
					if (0 == w.pathIn) // 如果入度为0，那么加入入度为0的集合
					{
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
 
		//结果集
		public Iterable<Node> getResult() {
			return result;
		}
	}
	
	//测试
	public static void main(String[] args) {

		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");

		Graph graph = new Graph();
		graph.addNode(A, C);
		graph.addNode(B, D);
		graph.addNode(C, E);
		graph.addNode(B, C);
		graph.addNode(D, E);
		graph.addNode(F, A);
		graph.addNode(F, B);
		
		Topo topo = new Topo(graph);
		
		topo.sort();
		for(Node temp : topo.getResult()){
			System.out.print(temp.val.toString() + "-->");
		}
	}
	
}
 
 
 