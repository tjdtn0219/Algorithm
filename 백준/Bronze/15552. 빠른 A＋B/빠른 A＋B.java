import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        
        List<Integer> resultList = new ArrayList<>();
        
        for(int i=0; i<N; i++){
            String s = bf.readLine();
            String array[] = s.split(" ");
            resultList.add(Integer.parseInt(array[0]) + Integer.parseInt(array[1]));            
        }
        for(int result:resultList) {
            bw.write(result+"\n");
        }
        bw.flush();
        bw.close();
    }
}