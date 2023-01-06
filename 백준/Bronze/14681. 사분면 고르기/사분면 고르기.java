import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        
        int x = in.nextInt();
        int y = in.nextInt();
        
        int q = 0;
        
        if(x>0 && y>0){
            q = 1;
        } else if(x<0 && y>0){
            q = 2;
        } else if(x<0 && y<0){
            q = 3;
        } else {
            q = 4;
        }
        System.out.println(q);
    }
}