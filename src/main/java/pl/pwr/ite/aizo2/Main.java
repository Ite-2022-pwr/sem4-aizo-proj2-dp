package pl.pwr.ite.aizo2;

import pl.pwr.ite.aizo2.service.analyzer.BellmanFordAnalyzer;
import pl.pwr.ite.aizo2.service.benchmark.BellmanFordBenchmark;

public class Main {

    private static final BellmanFordBenchmark bellmanFordBenchmark = new BellmanFordBenchmark();

    public static void main(String[] args) {
        bellmanFordBenchmark.start();
    }
}
