import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        List<Integer> arrList = new ArrayList<>();
        
        int num = in.nextInt();
        
        for(int i=0; i<num; i++){
            int A = in.nextInt();
            int B = in.nextInt();
            arrList.add(A+B);
        }
        for(int result:arrList){
            System.out.println(result);
        }
        
    }
}