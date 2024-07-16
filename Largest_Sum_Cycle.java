import java.util.*;

public class Largest_Sum_Cycle {


    public static int dfs(int n, ArrayList<ArrayList<Integer>> adj)
    {
         int ans = -1;
         int count = -1;
         boolean[] vis = new boolean[n];
         Queue<Integer> q = new LinkedList<>();
         for(int i = 0;i<n;i++)
         {
             ArrayList<Integer> add = new ArrayList<>();
             for(int j = 0;j<adj.get(i).size();j++)
             {
                 if(adj.get(i).get(j) != -1)
                 {
                     q.add(adj.get(i).get(j));
                     vis[adj.get(i).get(j)] = true;
                     count = -1;
                     while (!q.isEmpty())
                     {
                         int node = q.poll();
                         add.add(node);
                         for (int it : adj.get(node))
                         {
                             if(it != -1) {
                                 if (!vis[it]) {
                                     q.add(it);
                                     vis[it] = true;
                                 }
                                 if(vis[it])
                                 {
                                     int sum = 0;
                                     if(add.contains(it) && add.size() != 1) {
                                         int start = add.indexOf(it);
                                         int end = add.indexOf(node);
                                         for(int k = start;k<=end;k++) {
                                             sum += add.get(k);
                                         }
                                             count = sum;
                                     }
                                     if(add.contains(it) && add.size() == 1)
                                         count = -1;
                                 }
                             }
                         }
                     }
                 }
                 ans = Math.max(ans , count);
             }
         }
         return ans;
    }

    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            int n = scan.nextInt();
            int[] arr = new int[n];
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for(int i = 0 ; i < n ; i++) {
                adj.add(new ArrayList<>());
                arr[i] = scan.nextInt();
            }
            for(int i = 0 ; i < n ; i++)
                adj.get(i).add(arr[i]);
            int ans = dfs(n,adj);
            System.out.println(ans);
        }
}
