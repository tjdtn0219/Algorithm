import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        String bf = in.nextLine();

        String str = in.nextLine();

        int sum = 0;
        
        for(int i=0; i<str.length(); i++){
            int num = str.charAt(i) - 48;
            sum += num;
        }
        System.out.println(sum);
    }
}