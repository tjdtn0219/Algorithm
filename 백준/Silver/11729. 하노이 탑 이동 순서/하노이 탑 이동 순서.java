import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        List<String> list = new ArrayList<>();

        Hanoi(N, 1, 2, 3, list);

        sb.append(list.size()).append("\n");
        for(String s : list){
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }

    public static void Hanoi(int N, int start, int mid, int to, List<String> list){
        StringBuilder sb = new StringBuilder();

        if(N == 1){
            sb.append(start).append(" ").append(to);
            list.add(sb.toString());
            return ;
        }

        Hanoi(N-1, start, to, mid, list);

        sb.append(start).append(" ").append(to);
        list.add(sb.toString());

        Hanoi(N-1, mid, start, to, list);

    }
}
