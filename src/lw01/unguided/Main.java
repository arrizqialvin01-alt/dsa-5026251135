package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner =
                new Scanner(new File("src/lw01/unguided/washes.txt"));

        int total = scanner.nextInt();

        WashService[] services = new WashService[total];
        int[] units = new int[total];

        for (int i = 0; i < total; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int unit = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                services[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                services[i] = new CarWash(id, days);
            }

            units[i] = unit;
        }

        scanner.close();

        for (int i = 0; i < services.length; i++) {
            System.out.println(
                    services[i].getId()
                    + " | "
                    + services[i].label()
                    + " | "
                    + services[i].calculateCharge(units[i])
            );
        }
    }
}
    
}
