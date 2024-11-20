package alicante.pkg2d.fitnesstracker;

import java.util.Scanner;


public class Membership {
    public void mTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
           
        System.out.println("");    
        System.out.println("||===========================||");
        System.out.println("||       PA MEMBRO NA!       ||");  
        System.out.println("||===========================||");
        System.out.println("");
        System.out.println("----------------------");
        System.out.println("|1. ADD MEMBERSHIP   |");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("|2. VIEW MEMBERSHIP  |");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("|3. UPDATE MEMBERSHIP|");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("|4. DELETE MEMBERSHIP|");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("|5. EXIT MEMBERSHIP  | ");
        System.out.println("----------------------");
        
        System.out.print("Enter Action: ");
        if (!sc.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number between 1 to 5: ");
                sc.nextLine();
               
            }
        int action = sc.nextInt();
        Membership mb = new Membership ();
        

        switch(action){
            case 1:
                mb.addMembership();  
                mb.viewMembership();
                break;
            case 2:       
                mb.viewMembership();
                break;
            case 3:
                mb.viewMembership();
                mb.updateMembership();
                mb.viewMembership();
                break;
            case 4:
                mb.viewMembership();
                mb.deleteMembership();
                mb.viewMembership();
                break;
        }
        System.out.println("Do you want to exit membership? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("no"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    private void addMembership(){
        Scanner sc = new Scanner (System.in);   
        config conf = new config(); 
        Customer cs = new Customer();
        cs.viewCustomers();         
        
        System.out.print("Enter the ID of the Customer: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Customer ID: ");
        sc.next();
        }
        int cid = sc.nextInt();
        sc.nextLine();
        
        String csql = "SELECT c_id FROM tbl_customers WHERE c_id = ?";
        while(conf.getSingleValue(csql, cid) == 0){
            System.out.print("Customer does not exist, Select Again: ");
            cid = sc.nextInt();
            
        }
        
        Coach ch = new Coach ();
        ch.viewCoach();  
        
        System.out.print("Enter the ID of the Coach: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Coach ID: ");
        sc.next();
        }
        int coachid = sc.nextInt();
        sc.nextLine();
        
        String coachsql = "SELECT coach_id FROM tbl_coach WHERE coach_id = ?";
        while(conf.getSingleValue(coachsql, coachid) == 0){
            System.out.print("Coach does not exist, Select Again: ");
            coachid = sc.nextInt();
        }
        Workout wt = new Workout ();
        wt.viewWorkouts();
        
        System.out.print("Enter the ID of the Workout: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Workout ID: ");
        sc.next();
        }
        int wid = sc.nextInt();
        sc.nextLine();
        String wsql = "SELECT w_id FROM tbl_workouts WHERE w_id = ?";
        while(conf.getSingleValue(wsql, coachid) == 0){
            System.out.print("Workout does not exist, Select Again: ");
            wid = sc.nextInt();
        }
        
        System.out.print("Enter Membership Status: ");
        String status= sc.next();

        System.out.print("Enter Membership Expiration Date: ");
        String med = sc.next();
   
        
        String qry = "INSERT INTO tbl_membership (c_id, coach_id, w_id, m_status, m_expirationdate)"
                + "VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(qry, cid, coachid, wid, status, med);

   } 
    public void viewMembership() {
        
        String qry = "SELECT m_id, c_fname, coach_fname, w_type, w_date, w_duration, caloriesburned, m_status, m_expirationdate FROM tbl_membership "
        + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_membership.c_id "
        + "LEFT JOIN tbl_coach ON tbl_coach.coach_id = tbl_membership.coach_id "
        + "LEFT JOIN tbl_workouts ON tbl_workouts.w_id = tbl_membership.w_id";


        String[] hrds = {"M_id", "Customer", "Coach", "WorkoutType", "Date", "Duration" ,"CaloriesBurned" , "Membership Status", "Membership Expiration"};
        String[] clms = {"m_id", "c_fname", "coach_fname", "w_type", "w_date", "w_duration" ,"caloriesburned", "m_status", "m_expirationdate"};
        config conf = new config();
        conf.viewRecords(qry, hrds, clms);
}
    
    private void updateMembership() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to update: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Membership ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT m_id FROM tbl_membership WHERE m_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Membership ID Again: ");
        id = sc.nextInt();
        } 
        
        System.out.print("Enter New Membership Status: ");
        String mstat = sc.next();
        
    
        String qry = "UPDATE tbl_membership SET m_status = ? WHERE m_id = ?";
        
        
        conf.updateRecord(qry, mstat, id);         
  }
   private void deleteMembership() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter the ID  to delete: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Membership ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT m_id FROM tbl_membership WHERE m_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Membership ID Again: ");
        id = sc.nextInt();
        }
        String qry = "DELETE FROM tbl_membership WHERE m_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
   
}
   
