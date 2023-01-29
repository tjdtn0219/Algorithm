import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{

        BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        List<Integer> stack = new ArrayList<>();

        for(int i=0; i<N; i++){
            String[] commands = bf.readLine().strip().split(" ");
            executeCommands(stack, commands);
        }
    }

    public static void executeCommands(List<Integer> stack, String[] commands){
        switch(commands[0]){
            case "push":
                stack.add(Integer.parseInt(commands[1]));
                break;
            case "pop":
                int final_idx = stack.size() - 1;
                if(final_idx == -1){
                    System.out.println(final_idx);
                } else {
                    int popped = stack.get(final_idx);
                    stack.remove(final_idx);
                    System.out.println(popped);
                }
                break;
            case "size":
                System.out.println(stack.size());
                break;
            case "empty":
                if(stack.size() == 0) System.out.println("1");
                else System.out.println("0");
                break;
            case "top":
                if(stack.size() == 0){
                    System.out.println("-1");
                } else {
                    int top = stack.get(stack.size()-1);
                    System.out.println(top);
                }
                break;
            default:
//                System.out.println("DEFAULT");
                break;
        }
    }
}
