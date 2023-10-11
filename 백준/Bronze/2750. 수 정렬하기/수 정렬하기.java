
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        sort(arr);

        for(int i=0; i<n; i++) System.out.println(arr[i]);
        System.out.println();

    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, n-1);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low>=high) return ;

        int pivot = (low + high) / 2;
        int pivotValue = arr[pivot];

        int left = low;
        int right = high;
        while(left<=right) {
            while(arr[left] < pivotValue) left++;
            while(arr[right] > pivotValue) right--;
            if(left <= right) {
                int tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                left++;
                right--;
            }
        }

        quickSort(arr, low, right);
        quickSort(arr, left, high);
    }
}
