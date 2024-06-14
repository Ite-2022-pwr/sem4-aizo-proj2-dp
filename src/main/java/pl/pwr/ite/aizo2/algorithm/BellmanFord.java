package pl.pwr.ite.aizo2.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.model.Graph;

public class BellmanFord {

    public BellmanFordResult execute(Graph graph, int startIndex) {
        int[] distances = new int[graph.getVerticesNumber()];
        int[] parents = new int[graph.getVerticesNumber()];

        for(int i = 0; i < graph.getVerticesNumber(); i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startIndex] = 0;

        Edge[] edges = graph.getEdges();
        for(int i = 0; i < graph.getVerticesNumber() - 1; i++) {
            boolean changed = false;
            for(int e = 0; e < graph.getEdgesNumber(); e++) {
                Edge edge = edges[e];
                if(edge == null) {
                    continue;
                }
                int u = edge.getSource();
                int v = edge.getDestination();
                if(distances[u] + edge.getWeight() < distances[v]) {
                    distances[v] = distances[u] + edge.getWeight();
                    parents[v] = u;
                    changed = true;
                }
            }
            if(!changed) {
                break;
            }
        }

        for(int e = 0; e < graph.getEdgesNumber(); e++) {
            Edge edge = edges[e];
            if(edge == null) {
                continue;
            }
            int u = edge.getSource();
            int v = edge.getDestination();
            if(distances[u] + edge.getWeight() < distances[v]) {
                return null;
            }
        }
        return new BellmanFordResult(distances, parents);
    }

    @Data
    @AllArgsConstructor
    public static class BellmanFordResult {
        private int[] distances;
        private int[] parents;
    }
}
