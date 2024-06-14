package pl.pwr.ite.aizo2.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.model.Graph;
import pl.pwr.ite.aizo2.service.utils.HeapSort;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {

    public KruskalResult execute(Graph graph) {
        int mstWeight = 0;
        Edge[] edges = graph.getEdges();
        UnionFind unionFind = getUnionFind(graph.getVerticesNumber());
        int edgesCount = 0;

        HeapSort.sort(edges, Edge::compareEdges);
        List<Edge> mstEdges = new ArrayList<>(List.of(edges));
        for(Edge edge : edges) {
            if(union(unionFind, edge.getSource(), edge.getDestination())) {
                mstWeight += edge.getWeight();
                edgesCount++;
                mstEdges.add(edge);
                if(edgesCount == graph.getVerticesNumber() - 1) {
                    return new KruskalResult(mstWeight, mstEdges.toArray(new Edge[0]));
                }
            }
        }
        return new KruskalResult(mstWeight, mstEdges.toArray(new Edge[0]));
    }

    public UnionFind getUnionFind(int numberOfVertices) {
        int[] parents = new int[numberOfVertices];
        int[] sizes = new int[numberOfVertices];

        for(int i = 0; i < numberOfVertices; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }

        return new UnionFind(parents, sizes);
    }

    public int find(UnionFind unionFind, int v) {
        while(v != unionFind.getParents()[v]) {
            unionFind.getParents()[v] = unionFind.getParents()[unionFind.getParents()[v]];
            v = unionFind.getParents()[v];
        }
        return v;
    }

    public boolean union(UnionFind unionFind, int u, int v) {
        int rootU = find(unionFind, u);
        int rootV = find(unionFind, v);

        if(rootU == rootV) {
            return true;
        }
        if(unionFind.getSizes()[rootU] > unionFind.getSizes()[rootV]) {
            unionFind.getParents()[rootU] = rootU;
            unionFind.getSizes()[rootU]++;
        } else {
            unionFind.getParents()[rootU] = rootV;
            unionFind.getSizes()[rootV]++;
        }

        return true;
    }

    @Data
    @AllArgsConstructor
    public static class KruskalResult {
        private int mstWeight;
        private Edge[] msgEdges;
    }

    @Data
    @AllArgsConstructor
    public static class UnionFind {
        private int[] parents;
        private int[] sizes;
    }
}
