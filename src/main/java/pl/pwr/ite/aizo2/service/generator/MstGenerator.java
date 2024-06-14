package pl.pwr.ite.aizo2.service.generator;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pwr.ite.aizo2.model.Edge;

import java.awt.*;
import java.util.Random;

public class MstGenerator {
    private static final Random random = new Random();

    public MstResult generate(int numberOfVertices, int maxWeight, boolean isDirected) {
        Edge[] mst = new Edge[numberOfVertices - 1];
        int[][] adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

        for(int v = 1; v < numberOfVertices; v++) {
            int source = v - 1;
            int destination = v;

            int weight = randomInteger(0, maxWeight) + 1;
            mst[source] = new Edge(source, destination, weight);
            adjacencyMatrix[source][destination] = weight;

            if (!isDirected) {
                adjacencyMatrix[destination][source] = weight;
            }
        }

        return new MstResult(mst, adjacencyMatrix);
    }

    private int randomInteger(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    @Data
    @AllArgsConstructor
    public static class MstResult {

        private Edge[] mst;

        private int[][] adjacencyMatrix;
    }
}
