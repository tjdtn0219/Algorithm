import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        HashMap<Integer, String> hash_map1 = new HashMap<>();
        HashMap<String, Integer> hash_map2 = new HashMap<>();

        String[] strings = bf.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        for(int i=1; i<=n; i++) {
            String pname = bf.readLine();
            hash_map1.put(i, pname);
            hash_map2.put(pname, i);
        }

        for(int i=1; i<=m; i++) {
            String input = bf.readLine();
            if(input.charAt(0) > '0' && input.charAt(0) <= '9') {
                sb.append(hash_map1.get(Integer.parseInt(input)) + "\n");
            } else {
                sb.append(hash_map2.get(input) + "\n");
            }
        }
        System.out.println(sb);
    }
}