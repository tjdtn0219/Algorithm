import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int N = in.nextInt();
        int sum = 0;
        
        for(int i=1; i<=N; i++){
            sum += i;
        }
        System.out.println(sum);
    }
}