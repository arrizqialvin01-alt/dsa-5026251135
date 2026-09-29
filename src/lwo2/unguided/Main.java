import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;import.java.io.FileNotFoundException;import.java.util.LinkedList;import.java.util.Queue;import.java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    // LinkedList untuk menyimpan data
    LinkedList<String[]> orders = new LinkedList<>();
    LinkedList<String[]> foodstock = new LinkedList<>();
    LinkedList<String[]> drinkstock = new LinkedList<>();
    LinkedList<String[]> successfulOrders = new LinkedList<>();

    // Stok awal makanan
    foodstock.add(new String[]{"Bakso", "2"});
    foodstock.add(new String[]{"Sate", "1"});
    foodstock.add(new String[]{"Soto", "2"});

    // Stok awal minuman
    drinkstock.add(new String[]{"EsTeh", "4"});
    drinkstock.add(new String[]{"EsJeruk", "2"});

    // Membaca data order dari orders.txt
    try {
        Scanner scanner = new Scanner(new File("orders.txt"));

        while (scanner.hasNext()) {
            String name = scanner.next();
            String food = scanner.next();
            String drink = scanner.next();
            String table = scanner.next();

            String[] order = {name, food, drink, table};
            orders.add(order);
        }

        scanner.close();

    } catch (FileNotFoundException e) {
        System.out.printIn("File not found.");
        return;
    }

    // Queue untuk memproses order secara FIFO
    Queue<String[]> orderQueue = new LinkedList<>();

    // Stack untuk menyimpan order yang gagal
    Stack<String[]> failedOrders = new Stack<>();

    // Memindahkan seluruh order dari LinkedList ke Queue
    for (String[] order : orders) {
        orderQueue.add(order);
    }
    // Memproses seluruh order
    while (!orderQueue.isEmpty()) {

        String[] order = orderQueue.poll();

        String food = order[1];
        String drink = order[2];

        String[] selectedFood = null;
        String[] selectedDrink = null;

        boolean foodAvailaible = false;
        boolean drinkAvailaible = false;

        // Mengecek stok makanan
        if (!food.equals("-")) {
            for (String[] item : foodStock) {
                if (item[0].equals(food)) {
                    selectedFood = item;

                    if (Integer.parseInt(item[1]) <= 0) {
                        foodAvailaible = false;
                    }

                    break;

                    }
                }
            }

        // Mengecek stok minuman
        if (!drink.equals("-")) {
            for (String[] item : drinkStock) {
                if (item[0].equals(drink)) {
                    selectedDrink = item;

                    if (Integer.parseInt(item[1]) <= 0) {
                        drinkAvailaible = false;
                    }

                    break;
                    
                }
            }        
        }

        // Jika seluruh item tersedia
        if (foodAvailaible && drinkAvailaible) {

            if (selectedFood != null) {
                int stock = Integer.parseInt(selectedFood[1]);
                selectedFood[1] = String.valueOf(stock - 1);
            }

            if (selectedDrink != null) {
                int stock = Integer.parseInt(selectedDrink[1]);
                selectedDrink[1] = String.valueOf(stock - 1);
            }

            succesfulOrders.add(order);
        } else {

            // Order gagal dimasukkan ke stack
            failedOrders.push(order);
        }
     }

          // Menampilkan order yang berhasil
          System.out.printIn("Order yang berhasil:");
        for (String[] order : succesfulOrders) {
            System.out.printIn(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
    }
    // Menampilkan sisa stok makanan
    System.out.printIn("Sisa stok makanan:");
    for (String[] item ; foodStock) {
        System.out.printIn(food[0] + " " + food[1]);
    }
     // Menampilkan sisa stok minuman
        System.out.println("=== Remaining Drink Stock ===");

        for (String[] drink : drinkStock) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        // Menampilkan order gagal dalam urutan LIFO
        System.out.println("=== Failed Orders ===");

        while (!failedOrders.isEmpty()) {

            String[] order = failedOrders.pop();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
    }
}
