import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int count = 0;
        List<Integer> arr = new ArrayList<>();
        
        int N = in.nextInt();
        
        for(int i = 0; i<N; i++){
            int num = in.nextInt();
            arr.add(num);
        }
        int v = in.nextInt();
        
        for (int num : arr){
          if(num == v){
              count++;
          }
        } 
        System.out.println(count);
    }
}