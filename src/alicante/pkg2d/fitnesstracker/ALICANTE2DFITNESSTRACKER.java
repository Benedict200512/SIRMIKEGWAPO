package alicante.pkg2d.fitnesstracker;

import java.util.Scanner;

public class ALICANTE2DFITNESSTRACKER {
    
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;  
        int action = 0;
        String response = null;

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
            
            if (sc.hasNextInt()) {
                action = sc.nextInt(); 
                sc.nextLine();  

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
                        System.out.println("Exiting... Sure naka? (yes/no): ");
                        String resp = sc.nextLine();  
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = true;  
                        }
                        break;

                    default:
                        System.out.println("Invalid action. Please enter a number between 1 and 6.");
                        break;
                }
            } else {
                System.out.println("Invalid action. Please enter a number between 1 and 6.");
                sc.nextLine();  
            }
            System.out.println("Do you want to exit progam? (yes/no)");
            response = sc.nextLine();
            }while(response.equalsIgnoreCase("no"));
            
           System.out.println("Hanggang sa muli, Paalam Amego!!");
           
    }
}
