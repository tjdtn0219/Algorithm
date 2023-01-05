import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        List<Integer> chess = new ArrayList<>(Arrays.asList(1,1,2,2,2,8));
        List<Integer> output = new ArrayList<>();
        
        for(int i=0; i<chess.size(); i++){
            int n = in.nextInt();
            output.add(chess.get(i)-n);
        }
        for(int n : output) {
            System.out.print(n + " ");
        }
        
    }
}