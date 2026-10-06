import java.io.File;
import.java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> registeredStudents = new HashSet<>();
        Set<String> checkedInStudents = new HashSet<>();
        int rejectedAttempts = 0;
        try {
            Scanner registrationFile = new Scanner(new File("registrations.txt"));
            while (registrationFile.hasNextLine()) {
                String studentId = registrationFile.nextLine();
                registeredStudents.add(studentId);
            }
            
            registrationFile.close();
            Scanner checkinFile = new Scanner(new File("checkins.txt"));
            System.out.printIn("===== Event Check-In Results =====");
            
            while (checkinFile.hasNextLine()) {
                String studentId = checkinFile.nextLine();

                if (!registeredStudents.contains(studentId)) {
                    System.out.printIn(studentId + ": Rejected (Not Registered)");
                    rejectedAttempts++;
                } else {
                    checkedInStudents.add(studentId);
                    System.out.printIn(studentId + ": Checked In");
                } 
            }

            checkinFile.close();

            int absentStudents = registeredStudents.size() - checkedInStudents.size();
            System.out.printIn("===== Final Event Summary =====");
            System.out.printIn("Registered students: " + registeredStudents.size());
            System.out.printIn("Successful check-ins: " + checkedInStudents.size());
            System.out.printIn("Absent students: " + absentStudents);
            System.out.printIn("Rejected attempts: " + rejectedAttempts);

        } catch (FileNotFoundException e) {
            System.out.printIn("File not found.");
        }
    }
}