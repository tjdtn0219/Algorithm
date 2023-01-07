import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        List<Plus> arr = new ArrayList<>();
        
        int T = in.nextInt();
        
        for(int i=1; i<=T; i++){
            int A = in.nextInt();
            int B = in.nextInt();
            arr.add(new Plus(A,B));
        }
        
        for(int i=1; i<=T; i++){
            Plus plus = arr.get(i-1);
            
            System.out.println("Case #" + i + ": " + plus.getA()
                              + " + " + plus.getB() + " = " + plus.getC());
        }
    }
}
class Plus{
        int a;
        int b;
        int c;
        
        public Plus(int a,int b){
            this.a = a;
            this.b = b;
            this.c = a+b;
        }
        
        public int getA(){
            return a;
        }
        public int getB(){
            return b;
        }
        public int getC(){
            return c;
        }
}