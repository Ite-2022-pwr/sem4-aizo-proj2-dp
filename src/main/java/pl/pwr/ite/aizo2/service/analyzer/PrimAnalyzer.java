package pl.pwr.ite.aizo2.service.analyzer;

import pl.pwr.ite.aizo2.algorithm.Kruskal;
import pl.pwr.ite.aizo2.algorithm.Prim;
import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.service.utils.TimeUtils;

import java.time.Instant;

public class PrimAnalyzer extends GraphAnalyzer {
    public PrimAnalyzer(int numberOfVertices, Edge[] edges) {
        super(numberOfVertices, edges);
    }

    public AnalyzerResult start(boolean printMstEdges) {
        String prompt = "Znajdowanie minimalnego drzewa rozpinającego algorytmem Prima dla listy sąsiedztwa";
        Instant startTime = Instant.now();

        Prim prim = new Prim();
        Prim.PrimResult primResult = prim.execute(this.adjacencyList);

        Instant finishTime = Instant.now();

        float listTime = TimeUtils.printTimeElapsed(startTime, finishTime, prompt);

        System.out.printf("Waga minimalnego drzewa rozpinającego: %d\n", primResult.getMstWeight());

        if(printMstEdges) {
            System.out.println("Krawędzie minimalnego drzewa rozpinającego");
            for(Edge edge : primResult.getMstEdges()) {
                System.out.printf("%s -> %s: %s\n", edge.getSource(), edge.getDestination(), edge.getWeight());
            }
        }

        prompt = "Znajdowanie minimalnego drzewa rozpinającego algorytmem Prima dla macierzy incydencji";
        startTime = Instant.now();

        prim = new Prim();
        primResult = prim.execute(this.incidenceMatrix);

        finishTime = Instant.now();

        float matrixTime = TimeUtils.printTimeElapsed(startTime, finishTime, prompt);

        System.out.printf("Waga minimalnego drzewa rozpinającego: %d\n", primResult.getMstWeight());

        if(printMstEdges) {
            System.out.println("Krawędzie minimalnego drzewa rozpinającego");
            for(Edge edge : primResult.getMstEdges()) {
                System.out.printf("%s -> %s: %s\n", edge.getSource(), edge.getDestination(), edge.getWeight());
            }
        }

        return new AnalyzerResult(listTime, matrixTime);
    }
}
