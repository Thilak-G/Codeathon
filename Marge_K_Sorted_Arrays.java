import java.util.*;

public class Marge_K_Sorted_Arrays {

    public static ArrayList<Integer> mergeKArrays(ArrayList<ArrayList<Integer>> arr,int K)
    {
        ArrayList<Integer> l=new ArrayList<>();
        for(int i=0;i<K;i++)
        {
            for(int j=0;j<arr.get(i).size();j++)
            {
                l.add(arr.get(i).get(j));
            }
        }
        Collections.sort(l);
        return l;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            arr.add(new ArrayList<>());
            for(int j = i + 3 ; j < i + 10 ; j = j + 2){
                arr.get(i).add(j);
            }
        }
        List<Integer> li = mergeKArrays(arr , 4);
        System.out.println(li);
    }
}
