import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {
        int MAX = 500001;

        int[][] arr = new int[MAX][2];

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());

        for(int i=0; i<N; i++){
            int num = Integer.parseInt(bf.readLine());
            if(num >= 0){
                arr[num][1]++;
            } else {
                arr[-num][0]++;
            }
        }

        List<Integer> list = new ArrayList<>();
        int sum = 0;

        for(int i=MAX-1; i>=0; i--){
            if(arr[i][0] > 0){
                for(int j=0; j<arr[i][0]; j++){
                    sum += (-i);
                    list.add(-i);
                }
            }
        }
        for(int i=0; i<MAX; i++){
            if(arr[i][1] > 0){
                for(int j=0; j<arr[i][1]; j++){
                    sum += i;
                    list.add(i);
                }
            }
        }

        int[] max_freq_arr = find_most_freq_num(arr, MAX);
        int max_freq = max_freq_arr[0];
        int max_freq_num = max_freq_arr[1];
        if(max_freq_num >= 0){
            arr[max_freq_num][1] = 0;
        } else {
            arr[-max_freq_num][0] = 0;
        }
        max_freq_arr = find_most_freq_num(arr,MAX);
        if(max_freq == max_freq_arr[0]){
            max_freq_num = max_freq_arr[1];
        }

        float avg = (float)sum/N;
        String str_avg = String.format("%.0f", avg);
        if(str_avg.equals("-0")){
            str_avg = "0";
        }

        sb.append(str_avg).append("\n")
                .append(list.get(list.size()/2)).append("\n")
                .append(max_freq_num).append("\n")
                .append(list.get(list.size()-1) - list.get(0));

        System.out.println(sb);
//        for(int num : list){
//            System.out.print(num + " ");
//        }
    }

    public static int[] find_most_freq_num(int[][] arr, int MAX){
        int max_freq = 0;
        int max_freq_num = 0;

        for(int s=0; s<2; s++){
            if(s==0){
                for(int i=MAX-1; i>=0; i--){
                    if(arr[i][s] > max_freq){
                        max_freq = arr[i][s];
                        max_freq_num = -i;
                    }
                }
            } else {
                for(int i=0; i<MAX; i++){
                    if(arr[i][s] > max_freq){
                        max_freq = arr[i][s];
                        max_freq_num = i;
                    }
                }
            }

        }
        int[] freq = new int[]{max_freq, max_freq_num};
        return freq;
    }

}