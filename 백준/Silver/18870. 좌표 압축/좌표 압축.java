import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());
        String[] str = bf.readLine().strip().split(" ");

        int[] origin = new int[N];
        int[] sorted = new int[N];
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for(int i=0; i<N; i++){
            int num = Integer.parseInt(str[i]);
            origin[i] = num;
            sorted[i] = num;
        }

        Arrays.sort(sorted);

        int val = 0;
        for(int i=0; i<N; i++){
            if(!hashMap.keySet().contains(sorted[i])) {
                hashMap.put(sorted[i], val++);
            }
        }

//        for(Integer i : hashMap.keySet()){
//            System.out.println("Key " + i + " : " + hashMap.get(i));
//        }

        for(int key : origin){
            sb.append(hashMap.get(key)).append(" ");
        }
        System.out.print(sb);

    }
}
