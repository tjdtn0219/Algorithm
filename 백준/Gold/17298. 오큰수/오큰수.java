import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{
    public static void main(String[] args) throws IOException {

       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(bf.readLine());
        String[] strings = bf.readLine().strip().split(" ");
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        for(int i=0; i<N; i++) {
            while(!stack.empty()) {
                if(arr[stack.peek()] < arr[i]) {
                    arr[stack.pop()] = arr[i];
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
            arr[stack.pop()] = -1;
        }

        for(int n : arr) {
            sb.append(n).append(" ");
        }

        System.out.println(sb);
    }
}