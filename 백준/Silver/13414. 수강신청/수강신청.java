import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = bf.readLine().split(" ");

        int m = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);

        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(int i=0; i<n; i++) {
            String sid = bf.readLine();
            if(set.contains(sid)) {
                set.remove(sid);
                set.add(sid);
            }
            else set.add(sid);
        }

        Iterator iter = set.iterator();
        int i=0;
        while(i<m) {
            if(iter.hasNext()) sb.append(iter.next() + "\n");
            i++;
        }
        System.out.println(sb.toString());

    }
}
