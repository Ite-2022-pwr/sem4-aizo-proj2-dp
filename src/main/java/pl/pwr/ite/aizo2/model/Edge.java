package pl.pwr.ite.aizo2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    private int source;

    private int destination;

    private int weight;

    public static int compareEdges(Edge edge1, Edge edge2) {
        if (edge1.getWeight() > edge2.getWeight()) {
            return 1;
        } else if (edge1.getWeight() < edge2.getWeight()) {
            return -1;
        }
        return 0;
    }
}
