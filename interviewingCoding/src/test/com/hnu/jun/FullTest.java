package com.hnu.jun;

import com.hnu.jun.TopoSort;
import org.junit.Test;

import java.util.List;
import java.util.TreeMap;

import static com.hnu.jun.GetLCS.getLCS;
import static com.hnu.jun.GetPds.getPds;

public class FullTest {

    @Test
    public void testGetLCS()
    {
        TreeMap map = getLCS("abcdefabcd", "abefabghi");
        StringBuilder LCSvalues = (StringBuilder)map.lastEntry().getValue();
        System.out.println(LCSvalues.toString());
    }


    @Test
    public void testGetPds() {
        List pdsValues = getPds("Hello woworlrow");
        pdsValues.stream().forEach(System.out::println);
    }
    @Test
    public void testTopoSort() {
        TopoSort.Node A = new TopoSort.Node("A");
        TopoSort.Node B = new TopoSort.Node("B");
        TopoSort.Node C = new TopoSort.Node("C");
        TopoSort.Node D = new TopoSort.Node("D");
        TopoSort.Node E = new TopoSort.Node("E");
        TopoSort.Node F = new TopoSort.Node("F");
        TopoSort.Graph graph = new TopoSort.Graph();
        graph.addNode(A, C);
        graph.addNode(B, D);
        graph.addNode(C, E);
        graph.addNode(B, C);
        graph.addNode(D, E);
        graph.addNode(F, A);
        graph.addNode(F, B);
        TopoSort.Topo topo = new TopoSort.Topo(graph);
        topo.sort();
        List<TopoSort.Node> result = topo.getResult();
        result.stream().map(TopoSort.Node::getVal).map(s->s+ "-->").forEach(System.out::print);
    }
}
