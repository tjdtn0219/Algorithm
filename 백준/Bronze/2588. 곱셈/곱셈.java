import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        
        int sum = 0;
        int result;
        int mul;
        int mul2=1;
        
        while(true){
            mul = num2 % 10;
            result = num1*mul;
            System.out.println(result);
            sum += result*mul2;
            
            num2 = num2/10;
            mul2 *= 10;
            if(num2 == 0){
                break;
            }
        }
        System.out.println(sum);

    }
}