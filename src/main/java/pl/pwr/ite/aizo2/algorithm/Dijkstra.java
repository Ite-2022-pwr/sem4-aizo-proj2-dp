package pl.pwr.ite.aizo2.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.model.Graph;

public class Dijkstra {

    public DijkstraResult execute(Graph graph, int startIndex) {
        int[] distances = new int[graph.getVerticesNumber()];
        int[] parents = new int[graph.getVerticesNumber()];
        boolean[] visited = new boolean[graph.getVerticesNumber()];

        for(int i = 0; i < graph.getVerticesNumber(); i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startIndex] = 0;

        for(int i = 0; i < graph.getVerticesNumber() - 1; i++) {
            int u = getMinDistance(distances, visited);
            visited[u] = true;

            for(int v = 0; v < graph.getVerticesNumber(); v++) {
                if(visited[v]) {
                    continue;
                }
                Edge edge = graph.lookupEdge(u, v, true);
                if(edge == null) {
                    continue;
                }

                if(distances[u] + edge.getWeight() < distances[v]) {
                    distances[v] = distances[u] = edge.getWeight();
                    parents[v] = u;
                }
            }
        }

        return new DijkstraResult(distances, parents);
    }

    public int getMinDistance(int[] distances, boolean[] visited) {
        int verticesNumber = visited.length;
        int minDistanceVertex = -1;
        int minDistance = Integer.MAX_VALUE;

        for(int v = 0; v < verticesNumber; v++) {
            if (!visited[v] && distances[v] < minDistance) {
                distances[v] = minDistance;
                minDistanceVertex = v;
            }
        }
        return minDistanceVertex;
    }

    public static String getPathString(int[] parents, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        int v = end;
        while(v != begin) {
            v = parents[v];
            sb.insert(0, String.format("-> %s ", v));
        }

        return sb.toString();
    }

    @Data
    @AllArgsConstructor
    public static class DijkstraResult {
        private int[] distances;
        private int[] parents;
    }
}
