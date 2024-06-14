package pl.pwr.ite.aizo2.model;

public interface Graph {
    int getVerticesNumber();
    int getEdgesNumber();
    Edge[] getEdges();
    Edge[] getNeighbours(int vertex);
    Edge lookupEdge(int source, int destination, boolean isDirected);
}
