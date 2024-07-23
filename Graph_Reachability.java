import java.util.*;

public class Graph_Reachability {
    private List<List<Integer>> adjList;

    public Graph_Reachability(int n) {
        adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    public boolean isReachable(int src, int dest) {
        if (src == dest) return true;

        boolean[] visited = new boolean[adjList.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adjList.get(node)) {
                if (neighbor == dest) {
                    return true;
                }
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of nodes
        int n = scanner.nextInt();
        // Number of edges
        int e = scanner.nextInt();

        Graph_Reachability graph = new Graph_Reachability(n);

        // Reading edges
        for (int i = 0; i < e; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }

        // Source and destination
        int src = scanner.nextInt();
        int dest = scanner.nextInt();

        // Check reachability
        boolean result = graph.isReachable(src, dest);
        System.out.println(result);

        scanner.close();
    }
}
