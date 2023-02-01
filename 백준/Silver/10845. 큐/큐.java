import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());

        LinkedList<Integer> list = new LinkedList<>();

        for(int i=0; i<N; i++){
            String[] strings = bf.readLine().strip().split(" ");
            Integer num = queue(strings, list);
            if(num != null){
                sb.append(num).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static Integer queue(String[] strings, LinkedList<Integer> list){
        Integer num = null;
        switch(strings[0]){
            case "push":
                list.addLast(Integer.parseInt(strings[1]));
                break;
            case "pop":
                if(list.size() == 0) num = -1;
                else {
                    num = list.getFirst();
                    list.removeFirst();
                }
                break;
            case "size":
                num = list.size();
                break;
            case "empty":
                if(list.size() == 0) num = 1;
                else num = 0;
                break;
            case "front":
                if(list.size() == 0) num = -1;
                else num = list.getFirst();
                break;
            case "back":
                if(list.size() == 0) num = -1;
                else num = list.getLast();
                break;
        }
        return num;
    }
}
