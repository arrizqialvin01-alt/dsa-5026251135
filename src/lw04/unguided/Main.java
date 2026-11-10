package lw04.unguided;

public class Main {

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }

        return copy;
    }

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                System.out.println(target + ": FOUND at index "
                        + mid + " (steps: " + steps + ")");
                return steps;
            } else if (arr[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(target + ": NOT FOUND (steps: "
                + steps + ")");
        return steps;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);

            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(new File("parcels.txt"));

            int n = input.nextInt();
            int[] parcels = new int[n];

            for (int i = 0; i < n; i++) {
                parcels[i] = input.nextInt();
            }

            int m = input.nextInt();
            int[] targets = new int[m];

            for (int i = 0; i < m; i++) {
                targets[i] = input.nextInt();
            }

            input.close();

            // Sorting after 2 passes
            int[] afterTwoPasses = new int[n];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (parcels[j] < parcels[j + 1]) {
                        int temp = parcels[j];
                        parcels[j] = parcels[j + 1];
                        parcels[j + 1] = temp;
                    }
                }
            }

            afterTwoPasses = copyArray(parcels);

            // Continue sorting until fully sorted
            for (int i = 2; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (parcels[j] < parcels[j + 1]) {
                        int temp = parcels[j];
                        parcels[j] = parcels[j + 1];
                        parcels[j + 1] = temp;
                    }
                }
            }

            System.out.println("==== After 2 Passes ====");
            printArray(afterTwoPasses);

            System.out.println();
            System.out.println("==== Fully Sorted ====");
            printArray(parcels);

            System.out.println();
            System.out.println("==== Search Results ====");

            for (int i = 0; i < m; i++) {
                binarySearch(parcels, targets[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File parcels.txt tidak ditemukan.");
        }
    }
}

}
