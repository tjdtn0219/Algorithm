import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] a = new int[n];
        for(int i=0; i<n; i++) {
            a[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(a);

        //a[i] + a[j] + a[l] = a[k] <=> a[i] + a[j] = a[k] - a[l]
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                list.add(a[i]+a[j]);
            }
        }
        Collections.sort(list);
        Integer[] a2 = list.toArray(new Integer[list.size()]);

//        int ans = 0;
        for(int i=n-1; i>0; i--) {
            for(int j=i-1; j>=0; j--) {
                if(Arrays.binarySearch(a2, a[i] - a[j]) >= 0) {
                    System.out.println(a[i]);
                    return ;
                }
            }
        }

    }
}
