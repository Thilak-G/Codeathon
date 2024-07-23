import java.util.*;

public class Shortest_time {
    private static class Node implements Comparable<Node> {
        int vertex;
        int time;

        Node(int vertex, int time) {
            this.vertex = vertex;
            this.time = time;
        }

        public int compareTo(Node other) {
            return this.time - other.time;
        }
    }

    private List<List<Node>> adjList;

    public Shortest_time(int n) {
        adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int time) {
        adjList.get(u).add(new Node(v, time));
        adjList.get(v).add(new Node(u, time)); // Assuming the graph is undirected
    }

    public int shortestTime(int src, int dest) {
        int[] times = new int[adjList.size()];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.vertex == dest) {
                return current.time;
            }

            for (Node neighbor : adjList.get(current.vertex)) {
                int newTime = current.time + neighbor.time;

                if (newTime < times[neighbor.vertex]) {
                    times[neighbor.vertex] = newTime;
                    pq.add(new Node(neighbor.vertex, newTime));
                }
            }
        }

        return times[dest];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();  // number of nodes
        int e = scanner.nextInt();  // number of edges

        Shortest_time graph = new Shortest_time(n);

        for (int i = 0; i < e; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int time = scanner.nextInt();
            graph.addEdge(u, v, time);
        }

        int src = scanner.nextInt();  // source node
        int dest = scanner.nextInt(); // destination node

        int result = graph.shortestTime(src, dest);
        System.out.println(result);

        scanner.close();
    }
}
