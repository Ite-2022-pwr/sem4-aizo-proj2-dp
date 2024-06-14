package pl.pwr.ite.aizo2.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class IncidenceMatrix implements Graph {

    private int verticesNumber;
    private int edgesNumber;
    private int[] weights;
    private int[][] matrix;

    public IncidenceMatrix(int verticesNumber, Edge[] edges) {
        this.verticesNumber = verticesNumber;
        this.edgesNumber = edges.length;
        this.weights = new int[this.edgesNumber];
        this.matrix = new int[this.verticesNumber][this.edgesNumber];

        for (int i = 0; i < this.edgesNumber; i++) {
            if (edges[i].getSource() < 0 || edges[i].getSource() >= this.verticesNumber) {
                throw new IllegalArgumentException("Invalid source vertex in edge " + edges[i]);
            }
            if (edges[i].getDestination() < 0 || edges[i].getDestination() >= this.verticesNumber) {
                throw new IllegalArgumentException("Invalid destination vertex in edge " + edges[i]);
            }

            this.matrix[edges[i].getSource()][i] = 1;
            this.matrix[edges[i].getDestination()][i] = 1;
            this.weights[i] = edges[i].getWeight();
        }
    }

    @Override
    public Edge[] getEdges() {
        Edge[] edges = new Edge[this.edgesNumber];
        for(int e = 0; e < this.edgesNumber; e++) {
            int source = -1;
            int destination = -1;
            for(int v = 0; v < verticesNumber; v++) {
                if (this.matrix[v][e] == 1) {
                    source = v;
                } else if (this.matrix[v][e] == -1) {
                    destination = v;
                }
            }
            edges[e] = new Edge(source, destination, this.weights[e]);
        }
        return edges;
    }

    @Override
    public Edge[] getNeighbours(int vertex) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < this.verticesNumber; i++) {
            if (vertex == i) {
                continue;
            }
            Edge edge = lookupEdge(vertex, i, true);
            if(edge != null) {
                edges.add(edge);
            }
        }
        return edges.toArray(new Edge[0]);
    }

    @Override
    public Edge lookupEdge(int source, int destination, boolean isDirected) {
        if(source < 0 || destination < 0 || source >= this.verticesNumber || destination >= this.verticesNumber) {
            return null;
        }
        for(int i = 0; i < this.edgesNumber; i++) {
            if(this.matrix[source][i] == 1 && this.matrix[destination][i] == -1) {
                return new Edge(source, destination, this.weights[i]);
            } else if (!isDirected && this.matrix[source][i] == -1 && this.matrix[destination][i] == 1) {
                return new Edge(source, destination, this.weights[i]);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.verticesNumber; i++) {
            sb.append(Arrays.toString(this.matrix[i])).append("\n");
        }
        sb.append(Arrays.toString(this.weights)).append("\n");
        return sb.toString();
    }
}
