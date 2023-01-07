import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        
        int T = in.nextInt();
        
        for(int i=1; i<=T; i++){
            int A = in.nextInt();
            int B = in.nextInt();
            list.add(A+B);
        }
        
        for(int i=1; i<=T; i++){
            System.out.println("Case #" + i + ": " + list.get(i-1));
        }
    }
}