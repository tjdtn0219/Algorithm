import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int N = in.nextInt();
                
        int numOfPNum = 0;
        for(int i=0; i<N; i++){
            int num = in.nextInt();
            if(checkIsPNum(num)){
                numOfPNum++;
            }
        }
        System.out.println(numOfPNum);
        
    }
    
    public static boolean checkIsPNum(int num){
        if(num==1){
            return false;
        }
        for(int i=2; i<num; i++){
            if((num%i)==0){
                return false;
            }
        }
        return true;
    }
}