package pl.pwr.ite.aizo2.service.generator;

import pl.pwr.ite.aizo2.model.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {

    private static final Random random = new Random();
    private final MstGenerator mstGenerator = new MstGenerator();

    public Edge[] generate(int numberOfVertices, int maxWeight, int densityPercentage, boolean isDirected) {
        int numberOfEdges = numberOfVertices * (numberOfVertices - 1);
        if(!isDirected) {
            numberOfEdges /= 2;
        }
        numberOfEdges = numberOfEdges * densityPercentage / 100;
        if(numberOfEdges < numberOfVertices - 1) {
            return null;
        }
        if(densityPercentage > 60 && isDirected) {
            List<Edge> edges = new ArrayList<>();
            for(int i = 0; i < numberOfVertices; i++) {
                for(int j = 0; j < numberOfVertices; j++) {
                    if(i == j) {
                        continue;
                    }
                    edges.add(new Edge(i, j, randomInteger(0, maxWeight) + 1));
                }
            }

            for (int i = 0; i < numberOfVertices * (numberOfVertices - 1) - numberOfEdges; i++) {
                int index = randomInteger(0, edges.size());
                edges.remove(index);
            }
            return edges.toArray(new Edge[0]);
        }

        int edgesCount = numberOfVertices - 1;
        MstGenerator.MstResult mstResult = mstGenerator.generate(numberOfVertices, maxWeight, isDirected);
        List<Edge> edges = new ArrayList<>(List.of(mstResult.getMst()));
        int[][] adjacencyMatrix = mstResult.getAdjacencyMatrix();

        while(edgesCount < numberOfEdges) {
            int source = randomInteger(0, numberOfVertices);
            int destination = randomInteger(0, numberOfVertices);
            if(source == destination || adjacencyMatrix[source][destination] != 0 || adjacencyMatrix[destination][source] != 0) {
                continue;
            }

            int weight = randomInteger(0, maxWeight) + 1;
            edges.add(new Edge(source, destination, weight));
            adjacencyMatrix[source][destination] = weight;
            if(!isDirected) {
                adjacencyMatrix[destination][source] = weight;
            }
            edgesCount++;
        }
        return edges.toArray(new Edge[0]);
    }

    private int randomInteger(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
