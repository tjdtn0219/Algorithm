import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = bf.readLine();

        int line_cnt = 0;
        int sum = 0;

        for(int i=0; i<input.length(); i++) {
            if(i < input.length()-1) {
                if(input.charAt(i) == '(' && input.charAt(i+1) == ')') {
                    sum += line_cnt;
                    i++;
                    continue;
                }
            }
            if(input.charAt(i) == '(') {
                line_cnt++;
            } else if(input.charAt(i) == ')') {
                sum += 1;
                line_cnt--;
            }
        }

        System.out.println(sum);

    }
}