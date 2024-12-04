package alicante.pkg2d.fitnesstracker;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Workout {
    public void wTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response = "yes";
        int action = -1;
        do{
        
        System.out.println("");
        System.out.println("||===========================||");
        System.out.println("||        PASINGOT TA!       ||");  
        System.out.println("||===========================||");
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("|1.  ADD WORKOUT   |");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|2.  VIEW WORKOUT  |");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|3.  UPDATE WORKOUT|");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|4.  DELETE WORKOUT|");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|5.  EXIT WORKOUT  | ");
        System.out.println("--------------------");
        
        System.out.print("Enter Action: ");
        try {
                action = sc.nextInt(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid action, Please enter a numeric value.");
                sc.nextLine();
                continue; 
            }

            if (action < 1 || action > 5) {
                System.out.println("Invalid action, Please enter a value between 1 and 5.");
                continue; 
            }
        Workout wt = new Workout ();
        

        switch(action){
            case 1:
                wt.addWorkouts();           
                break;
            case 2:       
                wt.viewWorkouts();
                break;
            case 3:
                wt.viewWorkouts();
                wt.updateWorkouts();
                wt.viewWorkouts();
                break;
            case 4:
                wt.viewWorkouts();
                wt.deleteWorkouts();
                wt.viewWorkouts();    
                break;
            case 5:
                    System.out.println("Exiting Workout Transactions...");
                    return;
            }

            System.out.print("Do you want to continue to workout? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));

        System.out.println("Thank You, See you soonest!");
    }
    
    
    public void addWorkouts(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Date: ");
        String date = sc.next();
        System.out.print("Type: ");
        String type = sc.next();
        System.out.print("Duration: ");
        String dura = sc.next();
        System.out.print("CaloriesBurned: ");
        String cb = sc.next();

        String sql = "INSERT INTO tbl_workouts (w_date, w_type, w_duration, caloriesburned) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, date, type, dura, cb);


    }

    public void viewWorkouts() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_workouts";
        String[] Headers = {"Workouts_ID","Date", "Type", "Duration", "CaloriesBurned"};
        String[] Columns = {"w_id", "w_date", "w_type", "w_duration", "caloriesburned"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateWorkouts() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to update: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Workout ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT w_id FROM tbl_workouts WHERE w_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Workout ID Again: ");
        id = sc.nextInt();
        }

        System.out.println("New Date: ");
        String ndate = sc.next();
        
        System.out.println("New Type: ");
        String ntype = sc.next();
        
        System.out.println("New Duration: ");
        String ndura = sc.next();
        
        System.out.println("New CaloriesBurned: ");
        String ncb = sc.next();
        
        String qry = "UPDATE tbl_workouts SET w_date = ?, w_type = ?, w_duration = ?, caloriesburned = ? WHERE w_id = ?";
        
        
        conf.updateRecord(qry, ndate, ntype, ndura, ncb, id);         
        
        
    }
    
    private void deleteWorkouts() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter the ID  to delete: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Workout ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT w_id FROM tbl_workouts WHERE w_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Workout ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_workouts WHERE w_id = ?";
        
        
        conf.deleteRecord(qry, id);
    }
}
