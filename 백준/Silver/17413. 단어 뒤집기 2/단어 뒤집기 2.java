import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = bf.readLine();

        boolean flag = true;
        int start = 0;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '<' && flag) {
                flag = false;
                sb.append(reverseString(input.substring(start, i)));
                start = i;
            } else if(input.charAt(i) == '>') {
                sb.append(input.substring(start, i+1));
                start = i+1;
                flag = true;
            }
        }
        if(flag){
            sb.append(reverseString(input.substring(start, input.length())));
        }

        System.out.print(sb);
    }

    public static String reverseWord(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--){
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static String reverseString(String str) {
        String[] words = str.split(" ");
        StringBuilder sb = new StringBuilder();
        if(words.length > 1) {
            for(int i=0; i<words.length; i++) {
                sb.append(reverseWord(words[i]));
                if(i != words.length-1) sb.append(" ");
            }
        } else {
            sb.append(reverseWord(str));
        }

        return sb.toString();
    }
}