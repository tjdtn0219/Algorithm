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
            String[] strings = bf.readLine().strip().split(" ");
            StringBuilder sb = new StringBuilder();
            for(String str : strings){
                sb.append(reverseWord(str)).append(" ");
            }
            System.out.println(sb);
        }
    }

    public static String reverseWord(String word){
        if(word.length() == 0){
            return word;
        }
        String reversed = "";
        for(int i=word.length()-1; i>=0; i--){
            reversed += word.charAt(i);
        }
        return reversed;
    }
}
