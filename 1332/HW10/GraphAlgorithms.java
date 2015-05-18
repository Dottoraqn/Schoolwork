import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class GraphAlgorithms {
    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph.
     * You should use the adjacency list representation of the graph.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be visited. There are no negative edge weights in the
     * graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph or start vertex is null
     * @param graph the graph to search
     * @param start the starting vertex
     * @return map of the shortest distance between start and all other vertices
     */
    public static Map<Vertex, Integer> dijkstraShortestPath(Graph graph,
                                                            Vertex start) {
        if (graph == null || start == null) {
            throw new IllegalArgumentException("Go insane, go insane");
        }
        Map<Vertex, Integer> visited = new HashMap<Vertex, Integer>();
        PriorityQueue<VertexDistancePair> pq =
                new PriorityQueue<VertexDistancePair>();
        pq.add(new VertexDistancePair(start, 0));

        if (graph.getAdjacencyList().size() < 0) {
            return visited;
        } else {
            for (Vertex v : graph.getVertices()) {
                visited.put(v, Graph.INF);
            }
        }

        while (!pq.isEmpty()) {
            VertexDistancePair alpha = pq.poll();
            Map<Vertex, Integer> tmp = graph.getAdjacencies(alpha.getVertex());

            if (alpha.getDistance() < visited.get(alpha.getVertex())) {
                visited.put(alpha.getVertex(), alpha.getDistance());
                if (tmp != null && tmp.size() > 0) {
                    for (Map.Entry<Vertex, Integer> entry : tmp.entrySet()) {
                        pq.add(new VertexDistancePair(entry.getKey(),
                                (alpha.getDistance() + entry.getValue())));
                    }
                }
            }
        }
        return visited;
    }

    /**
     * Run Floyd Warshall on the given graph to find the length of all of the
     * shortest paths between vertices.
     *
     * You will also detect if there are negative cycles in the
     * given graph.
     *
     * You should use the adjacency matrix representation of the graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the graph
     * @return the distances between each vertex. For example, given {@code arr}
     * represents the 2D array, {@code arr[i][j]} represents the distance from
     * vertex i to vertex j. Return {@code null} if there is a negative cycle.
     */
    public static int[][] floydWarshall(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Throw some glitter");
        }
        int numVert = graph.getVertices().size();
        int[][] adjMat = graph.getAdjacencyMatrix();
        int[][] distMat = new int[numVert][numVert];
        for (int src = 0; src < numVert; src++) {
            for (int dest = 0; dest < numVert; dest++) {
                distMat[src][dest] = adjMat[src][dest];
            }
        }

        for (int k = 0; k < numVert; k++) {
            for (int j = 0; j < numVert; j++) {
                for (int i = 0; i < numVert; i++) {
                    if (distMat[j][k] + distMat[k][i] < distMat[j][i]) {
                        distMat[j][i] = distMat[j][k] + distMat[k][i];
                    }
                    if (distMat[j][i] < 0 && j == i) {
                        return null;
                    }
                }
            }
        }

        return distMat;
    }

    /**
     * A topological sort is a linear ordering of vertices with the restriction
     * that for every edge uv, vertex u comes before v in the ordering.
     *
     * You should use the adjacency list representation of the graph.
     * When considering which vertex to visit next while exploring the graph,
     * choose the next vertex in the adjacency list.
     *
     * You should start your topological sort with the smallest vertex
     * and if you need to select another starting vertex to continue
     * with your sort (like with a disconnected graph),
     * you should choose the next smallest applicable vertex.
     *
     * @throws IllegalArgumentException if the graph is null
     * @param graph a directed acyclic graph
     * @return a topological sort of the graph
     */
    public static List<Vertex> topologicalSort(Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Make it rain");
        }
        List<Vertex> toReturn = new LinkedList<Vertex>();
        Set<Vertex> visited = new HashSet<Vertex>();
        Set<Vertex> temp = new HashSet<Vertex>();
        Map<Vertex, Map<Vertex, Integer>> adjList = graph.getAdjacencyList();
        Queue<Vertex> small = new PriorityQueue<Vertex>();

        for (Vertex v : adjList.keySet()) {
            small.add(v);
        }

        while (adjList.size() > visited.size()) {
            Vertex vert = small.poll();
            visiting(graph, vert, toReturn, visited, temp);
        }
        return toReturn;
    }

    /**
     * A helper method for topological sort
     *
     * @param graph the graph to sort
     * @param vert a vertex in the graph
     * @param toReturn the list to return
     * @param visited the list of visited nodes
     * @param temp the list of visited nodes that can be manipulated
     */
    private static void visiting(Graph graph, Vertex vert,
               List<Vertex> toReturn, Set<Vertex> visited, Set<Vertex> temp) {
        if (vert != null) {
            if (!temp.contains(vert)) {
                if (!visited.contains(vert)) {
                    temp.add(vert);

                    Map<Vertex, Integer> adjList = graph.getAdjacencies(vert);
                    if (adjList != null) {
                        for (Vertex v : adjList.keySet()) {
                            visiting(graph, v, toReturn, visited, temp);
                        }
                    }
                    visited.add(vert);
                    temp.remove(vert);
                    toReturn.add(0, vert);
                }
            }
        }
    }

    /**
     * A class that pairs a vertex and a distance. Hint: might be helpful
     * for Dijkstra's.
     */
    private static class VertexDistancePair implements
        Comparable<VertexDistancePair> {
        private Vertex vertex;
        private int distance;

        /**
         * Creates a vertex distance pair
         * @param vertex the vertex to store in this pair
         * @param distance the distance to store in this pair
         */
        public VertexDistancePair(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * Gets the vertex stored in this pair
         * @return the vertex stored in this pair
         */
        public Vertex getVertex() {
            return vertex;
        }

        /**
         * Sets the vertex to be stored in this pair
         * @param vertex the vertex to be stored in this pair
         */
        public void setVertex(Vertex vertex) {
            this.vertex = vertex;
        }

        /**
         * Gets the distance stored in this pair
         * @return the distance stored in this pair
         */
        public int getDistance() {
            return distance;
        }

        /**
         * Sets the distance to be stored in this pair
         * @param distance the distance to be stored in this pair
         */
        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistancePair v) {
            return (distance < v.getDistance() ? -1
                                         : distance > v.getDistance() ? 1 : 0);
        }
    }
}
