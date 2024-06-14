package pl.pwr.ite.aizo2.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.model.Graph;

import java.util.ArrayList;
import java.util.List;

public class Prim {

    public PrimResult execute(Graph graph) {
        int[] parents = new int[graph.getVerticesNumber()];
        boolean[] mstSet = new boolean[graph.getVerticesNumber()];
        int[] keys = new int[graph.getVerticesNumber()];

        for(int i = 0; i < graph.getVerticesNumber(); i++) {
            keys[i] = Integer.MAX_VALUE;
        }
        keys[0] = 0;

        for(int i = 0; i < graph.getVerticesNumber(); i++) {
            int u = findMinKey(keys, mstSet);
            mstSet[u] = true;

            for(int v = 0; v < graph.getVerticesNumber(); v++) {
                if(mstSet[v]) {
                    continue;
                }

                Edge edge = graph.lookupEdge(u, v, false);
                if(edge == null) {
                    continue;
                }
                if(edge.getWeight() < keys[v]) {
                    parents[v] = u;
                    keys[v] = edge.getWeight();
                }
            }
        }

        int mstWeight = 0;
        for(int v : keys) {
            mstWeight += v;
        }
        List<Edge> mstEdges = new ArrayList<>();
        for(int i = 0; i < parents.length; i++) {
            if (i == parents[i]) {
                continue;
            }
            mstEdges.add(new Edge(parents[i], i, keys[i]));
        }
        return new PrimResult(mstWeight, mstEdges.toArray(new Edge[0]));
    }

    private int findMinKey(int[] keys, boolean[] mstSet) {
        int V = keys.length;
        int minKey = Integer.MAX_VALUE;

        int minKeyIndex = -1;
        for(int v = 0; v < V; v++) {
            if(!mstSet[v] && keys[v] < minKey) {
                minKey = keys[v];
                minKeyIndex = v;
            }
        }

        return minKeyIndex;
    }

    @Data
    @AllArgsConstructor
    public static class PrimResult {
        private int mstWeight;
        private Edge[] mstEdges;
    }
}
