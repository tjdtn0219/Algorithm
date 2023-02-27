import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(bf.readLine());
        List<Integer> stack = new ArrayList<>();

        int last_push = 0;

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(bf.readLine());
            if(num > last_push) {
                for(int j=last_push+1; j<=num; j++) {
                    stack.add(j);
//                    System.out.println("+");
                    sb.append("+").append("\n");
                }
                last_push = num;
            }
//            System.out.println("last_push : " + last_push + ", num : " + num + ", last_pop : " + last_pop);
            if(num <= last_push) {
                try {
                    while(true) {
                        int pop = stack.remove(stack.size() - 1);
//                        System.out.println("-" + " : " + pop);
                        sb.append("-").append("\n");
                        if(pop == num) {
                            break;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("NO");
                    return ;
                }

            }
        }

        System.out.println(sb);
    }
}