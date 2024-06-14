package pl.pwr.ite.aizo2.model;

import lombok.Getter;
import lombok.Setter;
import pl.pwr.ite.aizo2.service.utils.LengthUtils;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class AdjacencyList implements Graph {

    private int verticesNumber;
    private int edgesNumber;
    private Edge[][] list;

    public AdjacencyList(int verticesNumber, Edge[] edges) {
        this.edgesNumber = edges.length;
        this.verticesNumber = verticesNumber;

        this.list = new Edge[verticesNumber][verticesNumber];
        for (Edge edge : edges) {
            if (edge.getSource() < 0 || edge.getSource() > verticesNumber) {
                throw new IllegalArgumentException("Invalid source vertex in edge " + edge);
            }
            if (edge.getDestination() < 0 || edge.getDestination() > verticesNumber) {
                throw new IllegalArgumentException("Invalid destination vertex in edge " + edge);
            }
            //append to the end of the list
            this.list[edge.getSource()][this.list[edge.getSource()].length - 1] = edge;
        }
    }

    @Override
    public Edge[] getEdges() {
        Edge[] edges = new Edge[this.edgesNumber];
        int edgeIndex = 0;
        for(int v = 0; v < this.verticesNumber; v++) {
            int edgesCount = LengthUtils.getLen(this.list[v]);
            for(int e = 0; e < edgesCount && edgeIndex < this.edgesNumber; e++) {
                edges[edgeIndex] = this.list[v][e];
                edgeIndex++;
            }
        }
        return edges;
    }

    @Override
    public Edge[] getNeighbours(int vertex) {
        if(vertex < 0 || vertex >= this.verticesNumber) {
           return null;
        }
        return this.list[vertex].clone();
    }

    @Override
    public Edge lookupEdge(int source, int destination, boolean isDirected) {
        if(source < 0 || destination < 0 || source >= this.verticesNumber || destination >= verticesNumber) {
            return null;
        }
        for(int i = 0; i < LengthUtils.getLen(this.list[source]); i++) {
            if(this.list[source][i].getDestination() == destination) {
                return this.list[source][i];
            }
        }
        if(!isDirected) {
            for(int i = 0; i < LengthUtils.getLen(this.list[destination]); i++) {
                if(this.list[destination][i].getDestination() == source) {
                    return this.list[destination][i];
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.verticesNumber; i++) {
            sb.append(Arrays.toString(this.list[i])).append("\n");
        }
        return sb.toString();
    }
}
