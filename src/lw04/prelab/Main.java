package lw04.prelab;

public class Main {

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void insertionSortDescending(int[] arr) {
        for (int i = 1; i <= 3 && i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("branches.txt"));

        int n = input.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = input.nextInt();
        }

        int m = input.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = input.nextInt();
        }

        input.close();

        insertionSortDescending(branches);

        for (int i = 0; i < branches.length; i++) {
            System.out.print(branches[i]);

            if (i < branches.length - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        for (int i = 0; i < targets.length; i++) {
            int result = linearSearch(branches, targets[i]);

            if (result != -1) {
                System.out.println(targets[i] + ": FOUND at index " + result);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}

}
