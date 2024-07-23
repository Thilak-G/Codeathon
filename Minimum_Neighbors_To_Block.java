import java.util.*;

public class Minimum_Neighbors_To_Block {
    private static class Edge {
        int to, capacity, flow, rev;

        Edge(int to, int capacity, int rev) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
            this.rev = rev;
        }
    }

    private List<List<Edge>> graph;
    private int[] level;
    private int[] ptr;
    private int n;

    public Minimum_Neighbors_To_Block(int n) {
        this.n = n;
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int capacity) {
        graph.get(u).add(new Edge(v, capacity, graph.get(v).size()));
        graph.get(v).add(new Edge(u, 0, graph.get(u).size() - 1)); // Reverse edge for residual graph
    }

    private boolean bfs(int src, int dest) {
        level = new int[n];
        Arrays.fill(level, -1);
        level[src] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : graph.get(u)) {
                if (level[edge.to] < 0 && edge.flow < edge.capacity) {
                    level[edge.to] = level[u] + 1;
                    queue.add(edge.to);
                }
            }
        }
        return level[dest] >= 0;
    }

    private int dfs(int u, int dest, int flow) {
        if (u == dest) {
            return flow;
        }
        for (; ptr[u] < graph.get(u).size(); ptr[u]++) {
            Edge edge = graph.get(u).get(ptr[u]);
            if (level[edge.to] == level[u] + 1 && edge.flow < edge.capacity) {
                int df = dfs(edge.to, dest, Math.min(flow, edge.capacity - edge.flow));
                if (df > 0) {
                    edge.flow += df;
                    graph.get(edge.to).get(edge.rev).flow -= df;
                    return df;
                }
            }
        }
        return 0;
    }

    private int maxFlow(int src, int dest) {
        int flow = 0;
        while (bfs(src, dest)) {
            ptr = new int[n];
            while (true) {
                int pushed = dfs(src, dest, Integer.MAX_VALUE);
                if (pushed == 0) {
                    break;
                }
                flow += pushed;
            }
        }
        return flow;
    }

    public List<Integer> minCut(int src, int dest) {
        // First compute the max flow
        int maxFlow = maxFlow(src, dest);
        System.out.println("Max Flow: " + maxFlow);

        // Then find the nodes reachable from src in the residual graph
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : graph.get(u)) {
                if (!visited[edge.to] && edge.flow < edge.capacity) {
                    visited[edge.to] = true;
                    queue.add(edge.to);
                }
            }
        }

        // Identify the minimum cut
        List<Integer> cut = new ArrayList<>();
        for (int u = 0; u < n; u++) {
            if (visited[u]) {
                for (Edge edge : graph.get(u)) {
                    if (!visited[edge.to] && edge.capacity > 0) {
                        cut.add(u);
                        break; // Each node should only be added once
                    }
                }
            }
        }
        return cut;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // number of nodes
        int e = scanner.nextInt(); // number of edges

        Minimum_Neighbors_To_Block graph = new Minimum_Neighbors_To_Block(n);

        for (int i = 0; i < e; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v, 1);
        }

        int src = scanner.nextInt(); // source node
        int dest = scanner.nextInt(); // destination node

        List<Integer> result = graph.minCut(src, dest);
        if (result.isEmpty()) {
            System.out.println("No cut found or destination is already unreachable.");
        } else {
            for (int neighbor : result) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
