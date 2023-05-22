import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(bf.readLine());

        HashSet<String> hash_set = new HashSet<>();

        for(int i=0; i<n; i++) {
            String[] strings = bf.readLine().split(" ");
            if(strings[1].equals("enter")) {
                hash_set.add(strings[0]);
            } else {
                hash_set.remove(strings[0]);
            }
        }

        ArrayList<String> list = new ArrayList<>(hash_set);
        Collections.sort(list, (o1,o2) -> o2.compareTo(o1));

        for(String name : list) {
            sb.append(name + "\n");
        }

        System.out.println(sb);

    }
}