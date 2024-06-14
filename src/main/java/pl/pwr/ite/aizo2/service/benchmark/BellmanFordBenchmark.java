package pl.pwr.ite.aizo2.service.benchmark;

import pl.pwr.ite.aizo2.model.Graph;
import pl.pwr.ite.aizo2.service.analyzer.AnalyzerType;
import pl.pwr.ite.aizo2.service.analyzer.BellmanFordAnalyzer;
import pl.pwr.ite.aizo2.service.analyzer.GraphAnalyzer;
import pl.pwr.ite.aizo2.service.generator.GraphSetGenerator;
import pl.pwr.ite.aizo2.service.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BellmanFordBenchmark {

    private final GraphSetGenerator graphSetGenerator = new GraphSetGenerator();

    public void start() {
        System.out.println("Rozpoczynanie testowania algorytmu Bellmana-Forda");

        for(int density : Parameters.DENSITIES) {
            List<String> listOutputs = new ArrayList<>();
            List<String> matrixOutputs = new ArrayList<>();
            for(int verticesNumber : Parameters.VERTICES_NUMBER) {
                List<GraphAnalyzer.AnalyzerResult> results = new ArrayList<>();
                GraphAnalyzer[] analyzers = graphSetGenerator.generate(AnalyzerType.BellmanFord, Parameters.NUMBER_OF_GRAPHS, verticesNumber, verticesNumber / 10, density, true);

                for(int i = 0; i < analyzers.length; i++) {
                    System.out.printf("Bellman-Ford %d wieszchołków %d%% gęstości - pomiar %d/%d\n", verticesNumber, density, i + 1, Parameters.NUMBER_OF_GRAPHS);
                    BellmanFordAnalyzer analyzer = (BellmanFordAnalyzer) analyzers[i];
                    GraphAnalyzer.AnalyzerResult result = analyzer.start(0, verticesNumber - 1);
                    results.add(result);
                }

                double listAverageTime = results.stream()
                        .mapToDouble(GraphAnalyzer.AnalyzerResult::getListTime)
                        .average().orElse(0);
                double matrixAverageTime = results.stream()
                        .mapToDouble(GraphAnalyzer.AnalyzerResult::getMatrixTime)
                        .average().orElse(0);

                listOutputs.add(String.format("%s,%.3f", verticesNumber, listAverageTime));
                matrixOutputs.add(String.format("%s,%.3f", verticesNumber, matrixAverageTime));
            }
            FileUtils.saveToCSV(String.format("bellmanford_list_density_%s.csv", density), listOutputs);
            FileUtils.saveToCSV(String.format("bellmanford_matrix_density_%s.csv", density), listOutputs);
        }
    }
}
