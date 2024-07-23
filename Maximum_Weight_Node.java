import java.util.*;

public class Maximum_Weight_Node {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] edge = new int[n];

        // Read the edge array
        for (int i = 0; i < n; i++) {
            edge[i] = scanner.nextInt();
        }

        // Initialize weights array
        int[] weights = new int[n];

        // Calculate the weights of each node
        for (int i = 0; i < n; i++) {
            if (edge[i] != -1) {
                weights[edge[i]] += i;
            }
        }

        // Find the node with the maximum weight
        int maxWeightNode = -1;
        int maxWeight = -1;

        for (int i = 0; i < n; i++) {
            if (weights[i] > maxWeight) {
                maxWeight = weights[i];
                maxWeightNode = i;
            }
        }

        // Print the result
        System.out.println(maxWeightNode);

        scanner.close();
    }
}

