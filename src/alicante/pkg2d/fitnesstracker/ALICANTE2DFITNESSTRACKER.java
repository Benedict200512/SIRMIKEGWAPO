package alicante.pkg2d.fitnesstracker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ALICANTE2DFITNESSTRACKER {
    
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

            do {  
            System.out.println("============================================================");
            System.out.println("||            |‾‾‾\\       |‾‾‾‾‾      /‾‾‾‾‾\\               || ");
            System.out.println("||            |    \\      |          |                    ||  "); 
            System.out.println("||            |‾‾‾//      |‾‾‾‾‾      |  ‾‾‾‾               || ");
            System.out.println("||            |    \\      |          |       \\            || ");
            System.out.println("||            |___//      |           \\______/            || ");
            System.out.println("============================================================");
            System.out.println("||             WELCOME KAMO SA BEN FITNESS GYM            ||");
            System.out.println("============================================================");
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("|1. CUSTOMER    |");
            System.out.println("-----------------");
            System.out.println("-----------------");
            System.out.println("|2. COACH       |");
            System.out.println("-----------------");
            System.out.println("-----------------");
            System.out.println("|3. WORKOUT     |");
            System.out.println("-----------------");
            System.out.println("-----------------");
            System.out.println("|4. MEMBERSHIP  |");
            System.out.println("-----------------");
            System.out.println("-----------------");
            System.out.println("|5. VIEW RECORDS|");
            System.out.println("-----------------");
            System.out.println("-----------------");
            System.out.println("|6. EXIT        |");
            System.out.println("-----------------");

            System.out.print("Enter Action: ");
            
           int action = -1; 

            try {
                action = sc.nextInt(); 
            } catch (InputMismatchException e) {
               
                System.out.println("Invalid action, Please enter a numeric value.");
                sc.nextLine(); 
                continue; 
            }

            if (action < 1 || action > 6) {
                System.out.println("Invalid action, Please enter a value between 1 and 6.");
                continue; 
            }

                switch (action) {
                    case 1:
                        Customer cs = new Customer();
                        cs.cTransaction();
                        break;

                    case 2:
                        Coach ch = new Coach();
                        ch.coachTransaction();
                        break;

                    case 3:
                        Workout wt = new Workout();
                        wt.wTransaction();
                        break;

                    case 4:
                        Membership mb = new Membership();
                        mb.mTransaction();
                        break;
                        
                    case 5:
                        ViewRecord vr = new ViewRecord();
                        vr.displayRecords();
                        break;    

                    case 6:
                    System.out.print("Do you want to exit program? (yes/no): ");
                    String resp = sc.next();
                    if (resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
            }
        } while (exit);

        System.out.println("Hanggang sa muli, Paalam Amego!");
    }
}

