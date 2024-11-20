package alicante.pkg2d.fitnesstracker;

import java.util.Scanner;

public class ViewRecord {

    public void displayRecords() {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        int action = 0;
        String response = null;

        do  {
            System.out.println("");
            System.out.println("||===========================||");
            System.out.println("||       VIEW RECORDS        ||");
            System.out.println("||===========================||");
            System.out.println("");
            System.out.println("----------------------------------------");
            System.out.println("|1.  VIEW CUSTOMER'S MEMBERSHIP STATUS |");
            System.out.println("----------------------------------------");
            System.out.println("---------------------------------");
            System.out.println("|2.  VIEW CUSTOMER'S COACHES   |");
            System.out.println("---------------------------------");
            System.out.println("-------------------------------------------------------------");
            System.out.println("|3.  VIEW CUSTOMER'S WORKOUTS AND THEIR ASSOCIATED COACH    |");
            System.out.println("-------------------------------------------------------------");
            System.out.println("---------------------------");
            System.out.println("|4.  VIEW OVERALL RECORDS |");
            System.out.println("---------------------------");
            System.out.println("-----------------------");
            System.out.println("|5.  EXIT VIEW RECORDS|");
            System.out.println("-----------------------");

            System.out.print("Enter Action: ");
           
            if (sc.hasNextInt()) {
                action = sc.nextInt(); 
                sc.nextLine();  

                ViewRecord vr = new ViewRecord();
                config conf = new config(); 

                switch (action) {
                    case 1:
                        System.out.println("====================================================================================================");
                        System.out.println("||                               VIEW CUSTOMER'S MEMBERSHIP STATUS                                ||");
                        System.out.println("====================================================================================================");
                        String customerQuery = "SELECT tbl_customers.c_id, c_fname, c_lname, c_email, m_status FROM tbl_membership "
                                + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_membership.c_id";
                        String[] customerHeaders = {"Customers ID", "Customer FirstName", "Customer LastName", "Email", "Membership Status"};
                        String[] customerColumns = {"c_id", "c_fname", "c_lname", "c_email", "m_status"};
                        conf.viewRecords(customerQuery, customerHeaders, customerColumns);
                        break;
                    case 2:
                        System.out.println("====================================================================================================");
                        System.out.println("||                                    VIEW CUSTOMER'S COACHES                                     ||");
                        System.out.println("====================================================================================================");
                        String coachQuery = "SELECT tbl_customers.c_id, tbl_customers.c_fname, coach_id, tbl_coach.coach_fname, tbl_coach.coach_lname " 
                                + "FROM tbl_coach "
                                + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_coach.coach_id";
                        String[] coachHeaders = {"Customer ID","Customer", "Coach ID", "Coach First Name", "Coach Last Name"};
                        String[] coachColumns = {"c_id","c_fname", "coach_id", "coach_fname", "coach_lname"};
                        conf.viewRecords(coachQuery, coachHeaders, coachColumns);
                        break;
                   case 3:                  
                        System.out.println("====================================================================================================");
                        System.out.println("||                      VIEW CUSTOMER'S WORKOUTS AND THEIR ASSOCIATED COACH                        ||");
                        System.out.println("====================================================================================================");
                        String workoutQuery = "SELECT tbl_customers.c_id, tbl_customers.c_fname, tbl_coach.coach_id, tbl_coach.coach_fname, tbl_workouts.w_type, tbl_workouts.w_duration "
                                + "FROM tbl_workouts "
                                + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_workouts.w_id "
                                + "LEFT JOIN tbl_coach ON tbl_coach.coach_id = tbl_workouts.w_id";
                        String[] workoutHeaders = {"Customer ID", "Customer Name", "Coach ID", "Coach Name", "Workout Type", "Workout Duration"};
                        String[] workoutColumns = {"c_id", "c_fname", "coach_id", "coach_fname", "w_type", "w_duration"};
                        conf.viewRecords(workoutQuery, workoutHeaders, workoutColumns);
                        break;
                    case 4:
                        System.out.println("====================================================================================================");
                        System.out.println("||                                      VIEW OVERALL RECORDS                                      ||");
                        System.out.println("====================================================================================================");
                        String membershipQuery = "SELECT m_id, c_fname, coach_fname, w_type, w_date, w_duration, caloriesburned, m_status, m_expirationdate "
                                + "FROM tbl_membership "
                                + "LEFT JOIN tbl_customers ON tbl_customers.c_id = tbl_membership.c_id "
                                + "LEFT JOIN tbl_coach ON tbl_coach.coach_id = tbl_membership.coach_id "
                                + "LEFT JOIN tbl_workouts ON tbl_workouts.w_id = tbl_membership.w_id";
                        String[] membershipHeaders = {"M_id", "Customer", "Coach", "Workout Type", "Date", "Duration", "Calories Burned", "Membership Status", "Membership Expiration"};
                        String[] membershipColumns = {"m_id", "c_fname", "coach_fname", "w_type", "w_date", "w_duration", "caloriesburned", "m_status", "m_expirationdate"};
                        conf.viewRecords(membershipQuery, membershipHeaders, membershipColumns);
                        break;
                   case 5:
                        System.out.println("Exiting view records... Sure naka? (yes/no): ");
                        String resp = sc.nextLine();
                        if (resp.equalsIgnoreCase("yes")) {
                            exit = false; 
                        }
                        break;
                        
                    default:
                        System.out.println("Invalid input. Please enter a valid number between 1 and 5.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number between 1 and 5.");
            }
            System.out.println("Do you want to view more records? (yes/no)");
            response = sc.nextLine();
        } while (response.equalsIgnoreCase("yes"));
        
        System.out.println("Thank you for viewing records. See you again!");
        
        }
         
}
