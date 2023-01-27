import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().strip().split(" ");
        int N = Integer.parseInt(strings[0]);
        int idx = Integer.parseInt(strings[1]);

        strings = bf.readLine().strip().split(" ");
        int[] A = new int[N];
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(strings[i]);
        }

        merge_sort(A, 0, A.length - 1, list);

        if(list.size() < idx){
            System.out.println("-1");
            return;
        }

        System.out.println(list.get(idx-1));


    }

    public static void merge_sort(int[] A, int p, int r, List<Integer> list){
        if(p<r) {
            int q = (p+r)/2;
            merge_sort(A, p, q, list);
            merge_sort(A, q+1, r, list);
            merge(A, p, q, r, list);
        }
    }

    public static void merge(int[] A, int p, int q, int r, List<Integer> list){
        int i = p;
        int j = q+1;
        int t = 0;
        int[] tmp = new int[r-p+1];

        while(i<=q && j<=r){
            if(A[i] < A[j]){
                tmp[t++] =A[i++];
            } else{
                tmp[t++] = A[j++];
            }
        }
        while(i <= q){
            tmp[t++] = A[i++];
        }
        while(j <= r){
            tmp[t++] = A[j++];
        }
        i = p; t = 0;
        while(i <= r){
//            System.out.println("저장 : " + tmp[t]);
            list.add(tmp[t]);
            A[i++] = tmp[t++];
        }
    }

}
