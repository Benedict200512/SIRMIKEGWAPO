package alicante.pkg2d.fitnesstracker;

import java.util.Scanner;

public class Coach {
    public void coachTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
        
        System.out.println("");
        System.out.println("||===========================||");
        System.out.println("||      KINSA NGA COACH      ||");  
        System.out.println("||===========================||");
        System.out.println("");
        System.out.println("-----------------");
        System.out.println("|1. ADD COACH   |");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("|2. VIEW COACH  |");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("|3. UPDATE COACH|");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("|4. DELETE COACH|");
        System.out.println("-----------------");
        System.out.println("-----------------");
        System.out.println("|5. EXIT COACH  |");
        System.out.println("-----------------");
        
        System.out.print("Enter Action: ");
        if (!sc.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number between 1 to 5: ");
                sc.nextLine();
               
            }
        int action = sc.nextInt();
        Coach ch = new Coach ();
        

        switch(action){
            case 1:
                ch.addCoach();           
                break;
            case 2:       
                ch.viewCoach();
                break;
            case 3:
                ch.viewCoach();
                ch.updateCoach();
                ch.viewCoach();
                break;
            case 4:
                ch.viewCoach();
                ch.deleteCoach();
                ch.viewCoach();    
                break;
        }
        System.out.println("Do you want to exit coach? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("no"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addCoach(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Age: ");
        String age = sc.next();
        System.out.print("Address: ");
        String add = sc.next();

        String sql = "INSERT INTO tbl_coach (coach_fname, coach_lname, coach_age, coach_address) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, age, add);


    }

    public void viewCoach() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_coach";
        String[] Headers = {"Coach_ID","FirstName", "LastName", "Age", "Address"};
        String[] Columns = {"coach_id", "coach_fname", "coach_lname", "coach_age", "coach_address"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateCoach() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID  to update: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Coach ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT coach_id FROM tbl_coach WHERE coach_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Coach ID Again: ");
        id = sc.nextInt();
        }
        System.out.println("New First Name: ");
        String nfname = sc.next();
        
        System.out.println("New Last Name: ");
        String nlname = sc.next();
        
        System.out.println("New Age: ");
        String nage = sc.next();
        
        System.out.println("New Address: ");
        String nadd = sc.next();
        
        String qry = "UPDATE tbl_coach SET coach_fname = ?, coach_lname = ?, coach_age = ?, coach_address = ? WHERE coach_id = ?";
        
        
        conf.updateRecord(qry, nfname, nlname, nage, nadd, id);         
        
        
        
    }
    private void deleteCoach() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter the ID  to delete: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Coach ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT coach_id FROM tbl_coach WHERE coach_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Coach ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_coach WHERE coach_id = ?";
        
      
        conf.deleteRecord(qry, id);
    }
} 
       
    

