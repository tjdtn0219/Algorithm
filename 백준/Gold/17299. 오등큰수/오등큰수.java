
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{
    public static void main(String[] args) throws IOException {

       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        int MAX = 1000001;
        int N = Integer.parseInt(bf.readLine());
        String[] strings = bf.readLine().strip().split(" ");
        int[] num_arr = new int[N];
        int[] cnt_arr = new int[N];
        int[] count = new int[MAX];

        for(int i=0; i<N; i++) {
            num_arr[i] = Integer.parseInt(strings[i]);
            count[num_arr[i]]++;
        }

        for(int i=0; i<N; i++) {
            cnt_arr[i] = count[num_arr[i]];
        }

        for(int i=0; i<N; i++) {
            while(!stack.empty()) {
                if(cnt_arr[stack.peek()] < cnt_arr[i]) {
                    int pop = stack.pop();
                    cnt_arr[pop] = num_arr[i];
//                    num_arr[pop] = num_arr[i];
                } else {
                    stack.push(i);
                    break;
                }
            }
            if(stack.empty()) {
                stack.push(i);
            }
        }
        while(!stack.empty()) {
            int pop = stack.pop();
            cnt_arr[pop] = -1;
//            num_arr[pop] = -1;
        }

        for(int n : cnt_arr) {
            sb.append(n).append(" ");
        }

        System.out.println(sb);
    }
}