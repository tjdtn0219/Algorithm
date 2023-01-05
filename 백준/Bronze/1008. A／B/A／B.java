import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        double a = in.nextDouble();
        double b = in.nextDouble();
        
        if(b == 0) {
            return ;
        }
        else {
            System.out.println(a/b);
        }
    }
}