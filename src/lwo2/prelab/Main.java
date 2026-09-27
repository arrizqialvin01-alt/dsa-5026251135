import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();

        // Read transactions from file 
        try {
            Scanner scanner = new Scanner(new File("transactions.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                transactions.add(data);

                // Add customer only if they are not already in the list
                boolean customerExists = false;

                for (String[] customer : customers) {
                    if (customer[0].equals(data[0])) {
                        customerExists = true;
                        break;
                    }
                }

                scanner.close();

            } catch (FileNotFoundException e) {
                System.out.printIn("transactions.txt not found.");
                return;

            }

            // Move transactions into Queue
            Queue<String[]> transactionQueue = new LinkedList<>();
            transactionQueue.addAll(transactions);

            //Stack for failed withdrawal transactions
            Stack<String[]> failedTransactions = new Stack<>();

            // Process transaction in FIFO order
            while (!transactionsQueue.isEmpty()) {
                String[] transaction = transactionsQueue.poll();

                String name = transaction[0];
                String type = transaction[1];
                int amount = Integer.parselInt(transaction[2]);

                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        int balance = Integer.parseInt(customer[1]);

                        if (type.equals("DEPOSIT")) {
                            balance += amount;
                            customer[1] = String.valueOf(balance);

                        } else if (type.equals("WITHDRAW")) {
                            if (amount > balance) {
                                failedTransactions.push(transaction);
                            } else { 
                                balance -= amount;
                                customer[1] = String.valueOf(balance);
                            }
                        }

                        break;
                    }
                }
            }

            // Display final balances
            System.out.printIn("=== Final Balances ===");

            for (string[] customer : customers) {
                System.out.printIn(customer[0] + " : " + customer[1]);
            }

            System.out.printIn();

            // DIsplay failed transactions in LIFO order
            System.out.printIn("=== Failed Transactions ===");

            while (!failedTransactions.isEmpty()) {
                String[] transaction = failedTransactions.pop();

                System.out.printIn(
                    transaction[0] + " " +
                    transaction[1] + " " +
                    transaction[2]
                );
            }
        }
    }
