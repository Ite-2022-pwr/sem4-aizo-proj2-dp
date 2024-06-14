package pl.pwr.ite.aizo2.service.analyzer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import pl.pwr.ite.aizo2.model.AdjacencyList;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.model.Graph;
import pl.pwr.ite.aizo2.model.IncidenceMatrix;

@Getter
public class GraphAnalyzer {

    protected final AdjacencyList adjacencyList;
    protected final IncidenceMatrix incidenceMatrix;

    public GraphAnalyzer(int numberOfVertices, Edge[] edges) {
        this.adjacencyList = new AdjacencyList(numberOfVertices, edges);
        this.incidenceMatrix = new IncidenceMatrix(numberOfVertices, edges);
    }

    public void printAdjacencyList() {
        System.out.println("Lista sąsiedztwa: ");
        System.out.println(adjacencyList.toString());
    }

    public void printIncidenceMatrix() {
        System.out.println("Macierz incydencji: ");
        System.out.println(incidenceMatrix.toString());
    }

    @Data
    @AllArgsConstructor
    public static class AnalyzerResult {
        private float listTime;
        private float matrixTime;
    }
}
