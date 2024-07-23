import java.util.*;

public class Nearest_Meeting_Cell {
    private static Map<Integer, Integer> computeDistances(int start, int[] edge) {
        Map<Integer, Integer> distances = new HashMap<>();
        int steps = 0;
        int current = start;

        while (current != -1 && !distances.containsKey(current)) {
            distances.put(current, steps);
            current = edge[current];
            steps++;
        }

        return distances;
    }

    public static int findNearestMeetingCell(int n, int[] edge, int c1, int c2) {

        Map<Integer, Integer> distancesFromC1 = computeDistances(c1, edge);
        Map<Integer, Integer> distancesFromC2 = computeDistances(c2, edge);

        int minDistance = Integer.MAX_VALUE;
        int meetingCell = -1;

        for (int cell : distancesFromC1.keySet()) {
            if (distancesFromC2.containsKey(cell)) {
                int combinedDistance = Math.max(distancesFromC1.get(cell), distancesFromC2.get(cell));
                if (combinedDistance < minDistance) {
                    minDistance = combinedDistance;
                    meetingCell = cell;
                }
            }
        }

        return meetingCell;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] edge = new int[n];

        for (int i = 0; i < n; i++) {
            edge[i] = scanner.nextInt();
        }

        int c1 = scanner.nextInt();
        int c2 = scanner.nextInt();

        int result = findNearestMeetingCell(n, edge, c1, c2);
        System.out.println(result);

        scanner.close();
    }
}