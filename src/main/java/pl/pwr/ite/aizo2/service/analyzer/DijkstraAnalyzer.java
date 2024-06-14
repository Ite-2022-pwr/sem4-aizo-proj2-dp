package pl.pwr.ite.aizo2.service.analyzer;

import pl.pwr.ite.aizo2.algorithm.BellmanFord;
import pl.pwr.ite.aizo2.algorithm.Dijkstra;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.service.utils.TimeUtils;

import java.time.Instant;

public class DijkstraAnalyzer extends GraphAnalyzer {

    public DijkstraAnalyzer(int numberOfVertices, Edge[] edges) {
        super(numberOfVertices, edges);
    }

    public GraphAnalyzer.AnalyzerResult start(int startIndex, int finishIndex) {
        String prompt = String.format("Znajdowanie najkrótszej ścieżki algorytmem Dijkstry dla listy sąsiedztwa od wierzchołka %d do %d\n", startIndex, finishIndex);

        Instant startTime = Instant.now();

        Dijkstra dijkstra = new Dijkstra();
        Dijkstra.DijkstraResult dijkstraResult = dijkstra.execute(this.adjacencyList, startIndex);

        Instant finishTime = Instant.now();
        float listTime = TimeUtils.printTimeElapsed(startTime, finishTime, prompt);
        System.out.printf("Długość ścieżki od %d do %d: %d", startIndex, finishIndex, dijkstraResult.getDistances()[finishIndex]);
        String path = Dijkstra.getPathString(dijkstraResult.getParents(), startIndex, finishIndex);
        System.out.println("Scieżka: ".concat(path));

        prompt = String.format("Znajdowanie najkrótszej ścieżki algorytmem Dijkstry dla mecierzy incydencji od wierzchołka %d do %d\n", startIndex, finishIndex);

        startTime = Instant.now();

        dijkstra = new Dijkstra();
        dijkstraResult = dijkstra.execute(this.incidenceMatrix, startIndex);

        finishTime = Instant.now();
        float matrixTime = TimeUtils.printTimeElapsed(startTime, finishTime, prompt);

        System.out.printf("Długość ścieżki od %d do %d: %d", startIndex, finishIndex, dijkstraResult.getDistances()[finishIndex]);
        path = Dijkstra.getPathString(dijkstraResult.getParents(), startIndex, finishIndex);
        System.out.println("Scieżka: ".concat(path));

        return new AnalyzerResult(listTime, matrixTime);
    }
}
