package pl.pwr.ite.aizo2.service.generator;

import pl.pwr.ite.aizo2.model.Edge;
import pl.pwr.ite.aizo2.service.analyzer.*;

import java.util.ArrayList;
import java.util.List;

public class GraphSetGenerator {

    private final GraphGenerator graphGenerator = new GraphGenerator();

    public GraphAnalyzer[] generate(AnalyzerType type, int numberOfGraphs, int numberOfVertices, int maxWeight, int densityPercentage, boolean isDirected) {
        List<GraphAnalyzer> analyzers = new ArrayList<>();
        for (int i = 0; i < numberOfGraphs; i++) {
            Edge[] edges = graphGenerator.generate(numberOfVertices, maxWeight, densityPercentage, isDirected);
            GraphAnalyzer graphAnalyzer = switch (type) {
                case BellmanFord -> new BellmanFordAnalyzer(numberOfVertices, edges);
                case Dijkstra -> new DijkstraAnalyzer(numberOfVertices, edges);
                case Kruskal -> new KruskalAnalyzer(numberOfVertices, edges);
                case Prim -> new PrimAnalyzer(numberOfVertices, edges);
            };

            analyzers.add(graphAnalyzer);
        }

        return analyzers.toArray(new GraphAnalyzer[0]);
    }
}
