import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());

        LinkedList<Integer> list = new LinkedList<>();

        for(int i=0; i<N; i++){
            String[] strings = bf.readLine().strip().split(" ");
            Integer num = executeCommands(strings, list);
            if(num != null){
                sb.append(num).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static Integer executeCommands(String[] strings, LinkedList<Integer> list){
        if(strings.length == 2){
            //push
            if(strings[0].split("_")[1].equals("back")){
                //push_back
                list.addLast(Integer.parseInt(strings[1]));
            } else {
                //push_front
                list.addFirst(Integer.parseInt(strings[1]));
            }
            return null;
        }
        else{
            if(strings[0].split("_")[0].equals("pop")) {
                //pop
                int num = 0;
                if(list.size() == 0) return -1;
                if(strings[0].split("_")[1].equals("back")){
                    //pop_back
                    num = list.getLast();
                    list.removeLast();
                } else {
                    //pop_front
                    num = list.getFirst();
                    list.removeFirst();
                }
                return num;
            }//pop
            else if(strings[0].equals("size")) {
                return list.size();
            } else if(strings[0].equals("empty")) {
                if(list.size() == 0) return 1;
                else return 0;
            } else if(strings[0].equals("front")) {
                if(list.size() == 0) return -1;
                return list.getFirst();
            } else if(strings[0].equals("back")) {
                if(list.size() == 0) return -1;
                return list.getLast();
            }
        }
        return null;
    }
}