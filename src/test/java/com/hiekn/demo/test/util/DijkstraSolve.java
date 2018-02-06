package com.hiekn.demo.test.util;

import com.google.common.collect.Sets;
import com.google.common.graph.MutableValueGraph;

import java.util.Set;

public class DijkstraSolve {
	
    public static void dijkstra(String sourceNode, String targetNode,MutableValueGraph<String, Integer> graph) {
        //initPathFromSourceNode
        graph.nodes().stream().filter(node -> !graph.adjacentNodes(sourceNode).contains(node)).forEach(node -> graph.putEdgeValue(sourceNode, node, Integer.MAX_VALUE));
        graph.putEdgeValue(sourceNode, sourceNode, 0);
        
        Set<String> nodes = graph.nodes();//不可变
        if(!nodes.contains(sourceNode)) {
            throw new IllegalArgumentException(sourceNode +  " is not in this graph!");
        }

        Set<String> notVisitedNodes = Sets.newHashSet(graph.nodes());//可变
        String currentVisitNode = sourceNode;
        while(!notVisitedNodes.isEmpty()) {
            String nextVisitNode = findNextNode(currentVisitNode, notVisitedNodes,graph,sourceNode);
            if(nextVisitNode.equals("")) {
                break;
            }
            notVisitedNodes.remove(currentVisitNode);
            currentVisitNode = nextVisitNode;
        }
        System.out.println(graph);
        System.out.println(sourceNode +"到" + targetNode + "的minPath=" + graph.edgeValue(sourceNode,targetNode).get());
    }

    private static String findNextNode(String currentVisitNode, Set<String> notVisitedNodes, MutableValueGraph<String, Integer> graph,String sourceNode) {
        int shortestPath = Integer.MAX_VALUE;
        String nextVisitNode = "";
        
        for (String node : graph.nodes()) {
            if(currentVisitNode.equals(node) || !notVisitedNodes.contains(node)) {//滤掉自身和不在图上的node
                continue;
            }

            if(graph.successors(currentVisitNode).contains(node)) {
                Integer edgeValue = graph.edgeValue(sourceNode, currentVisitNode).get() + graph.edgeValue(currentVisitNode, node).get();
                Integer currentPathValue = graph.edgeValue(sourceNode, node).get();
                if(edgeValue > 0) {
                    graph.putEdgeValue(sourceNode, node, Math.min(edgeValue, currentPathValue));
                }
            }
            
            int minPath = graph.edgeValue(sourceNode, node).get();
            if( minPath < shortestPath) {
                shortestPath = minPath;
                nextVisitNode = node;
            }
        }

        return nextVisitNode;
    }


}
