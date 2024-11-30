package alicante.pkg2d.fitnesstracker;

import java.util.Scanner;

public class ViewRecord {

    public void displayRecords() {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        int action = 0;
        String response = null;

        do {
            System.out.println("");
            System.out.println("||===========================||");
            System.out.println("||       VIEW RECORDS        ||");
            System.out.println("||===========================||");
            System.out.println("");
            System.out.println("--------------------------------------------------");
            System.out.println("|1.  VIEW CUSTOMER'S MEMBERSHIP STATUS AND COACH |");
            System.out.println("--------------------------------------------------");
            System.out.println("---------------------------");
            System.out.println("|2.  VIEW OVERALL RECORDS |");
            System.out.println("---------------------------");
            System.out.println("-----------------------");
            System.out.println("|3.  EXIT VIEW RECORDS|");
            System.out.println("-----------------------");
            System.out.print("Enter Action: ");
           
            if (sc.hasNextInt()) {
                action = sc.nextInt(); 
                sc.nextLine();  

                ViewRecord vr = new ViewRecord();
                config conf = new config(); 

                switch (action) {
                    case 1:                      
                        System.out.println("================================================================================================================");
                        System.out.println("||                               VIEW CUSTOMER'S MEMBERSHIP STATUS AND COACHES                                ||");
                        System.out.println("================================================================================================================");

                        int customerId = getValidCustomerId(sc);

                        if (customerId != -1) {
                            String customerQuery = "SELECT tbl_customers.c_id, c_fname, m_status, tbl_coach.coach_id, coach_fname, tbl_workouts.w_id, w_type FROM tbl_membership "
                                    + "LEFT JOIN tbl_coach ON tbl_membership.coach_id = tbl_coach.coach_id " 
                                    + "LEFT JOIN tbl_workouts ON tbl_membership.w_id = tbl_workouts.w_id "
                                    + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_membership.c_id WHERE tbl_customers.c_id = " + customerId;
                            String[] customerHeaders = {"Customer ID", "Customer ", "Membership Status", "Coach ID", "Coach", "Workout ID", "Workout Type"};
                            String[] customerColumns = {"c_id", "c_fname", "m_status", "coach_id", "coach_fname", "w_id", "w_type"};
                            conf.viewRecords(customerQuery, customerHeaders, customerColumns);
                        } else {
                            System.out.println("Invalid Customer ID. Please try again.");
                        }
                        break;
                        
                    case 2:
                       
                        System.out.println("====================================================================================================");
                        System.out.println("||                                      VIEW OVERALL RECORDS                                      ||");
                        System.out.println("====================================================================================================");
                            String overallQuery = "SELECT tbl_customers.c_id, tbl_customers.c_fname, tbl_coach.coach_id, tbl_coach.coach_fname, tbl_workouts.w_type, "
                                    + "tbl_workouts.w_date, tbl_workouts.w_duration, tbl_workouts.caloriesburned, tbl_membership.m_status, "
                                    + "tbl_membership.m_expirationdate "
                                    + "FROM tbl_customers "
                                    + "LEFT JOIN tbl_membership ON tbl_customers.c_id = tbl_membership.c_id "  
                                    + "LEFT JOIN tbl_coach ON tbl_membership.coach_id = tbl_coach.coach_id "    
                                    + "LEFT JOIN tbl_workouts ON tbl_membership.w_id = tbl_workouts.w_id";      

                            String[] overallHeaders = {"Customer_id",  "Customer", "Coach_ID", "Coach", "Workout Type", "Date", "Duration", "Calories Burned", "Membership Status", "Membership Expiration"};
                            String[] overallColumns = {"c_id", "c_fname", "coach_id",  "coach_fname","w_type", "w_date", "w_duration", "caloriesburned", "m_status", "m_expirationdate"};
                            conf.viewRecords(overallQuery, overallHeaders, overallColumns);
                        break;

                    case 3:
                       
                        System.out.println("Exiting view records... Sure naka? (yes/no): ");
                        String resp = sc.nextLine();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false;
                        }
                        break;

                    default:
                        System.out.println("Invalid input. Please enter a valid number between 1 and 3.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number between 1 and 3.");
                sc.nextLine();  
            }
            System.out.println("Do you want to view more records? (yes/no)");
            response = sc.nextLine();
        } while (response.equalsIgnoreCase("yes"));
        
        System.out.println("Thank you for viewing records. See you again!");
    }

    
    private int getValidCustomerId(Scanner sc) {
        int customerId = -1;
        boolean validId = false;

        while (!validId) {
            System.out.print("Enter Customer ID: ");
            if (sc.hasNextInt()) {
                customerId = sc.nextInt();
                sc.nextLine(); 
                
                if (isCustomerExists(customerId)) {
                    validId = true;
                } else {
                    System.out.println("Customer ID does not exist. Please enter a valid ID.");
                }
            } else {
                System.out.println("Invalid input. Please enter a numeric ID.");
                sc.nextLine();  
            }
        }
        return customerId;
    }

    
    private boolean isCustomerExists(int customerId) {
        
        return customerId >= 1 && customerId <= 100;
    }
}
