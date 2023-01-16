import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[5];

        int sum = 0;
        for(int i=0; i<5; i++){
            int num = Integer.parseInt(bf.readLine());
            sum += num;
            arr[i] = num;
        }

        bubble_sort(arr);

        System.out.println(sum/5);
        System.out.println(arr[2]);
    }

    public static void bubble_sort(int arr[]){
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-1-i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}