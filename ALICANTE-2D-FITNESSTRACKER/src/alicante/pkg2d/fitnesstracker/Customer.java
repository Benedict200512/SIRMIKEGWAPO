package alicante.pkg2d.fitnesstracker;

import java.util.Scanner;


public class Customer {
    
    public void cTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response = null;
        do{
            
        System.out.println("");    
        System.out.println("||===========================||");
        System.out.println("||  SULOD KAMO MGA CUSTOMER  ||");  
        System.out.println("||===========================||");
        System.out.println("");
        System.out.println("--------------------");
        System.out.println("|1. ADD CUSTOMER   |");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|2. VIEW CUSTOMER  |");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|3. UPDATE CUSTOMER|");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|4. DELETE CUSTOMER|");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("|5. EXIT CUSTOMER  | ");
        System.out.println("--------------------");
        
        System.out.print("Enter Action: ");
        if (!sc.hasNextInt()) {
                System.out.print("Invalid input! Please enter a number between 1 to 5: ");
                sc.nextLine();
               
            }
        int action = sc.nextInt();
        Customer cs = new Customer ();

        switch(action){
            case 1:
                cs.addCustomers();           
                break;
            case 2:       
                cs.viewCustomers();
                break;
            case 3:
                cs.viewCustomers();
                cs.updateCustomers();
                cs.viewCustomers();
                break;
            case 4:
                cs.viewCustomers();
                cs.deleteCustomers();
                cs.viewCustomers();    
                break;
        }
        System.out.println("Do you want to exit customer? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("no"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
        public void addCustomers(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Status: ");
        String stat = sc.next();
        System.out.print("Email: ");
        String em = sc.next();

        String sql = "INSERT INTO tbl_customers (c_fname, c_lname, c_status, c_email) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, stat, em);


    }

    public void viewCustomers() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_customers";
        
        String[] Headers = {"Customers_ID","FirstName", "LastName", "Status", "Email"};
        String[] Columns = {"c_id", "c_fname", "c_lname", "c_status", "c_email"};

        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Enter the ID to update: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Customer ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
       
        
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.print("New First Name: ");
        String nfname = sc.nextLine();
        System.out.print("New Last Name: ");
        String nlname = sc.next();
        System.out.print("New Status: ");
        String nstat = sc.next();
        System.out.print("New Email: ");
        String nem = sc.next();
        
        String qry = "UPDATE tbl_customers SET c_fname = ?, c_lname = ?, c_status = ?, c_email = ? WHERE c_id = ?";
        
        
        conf.updateRecord(qry, nfname, nlname, nstat, nem, id);            
    
        
    }
    private void deleteCustomers() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.print("Enter the ID  to delete: ");
        while (!sc.hasNextInt()) {
        System.out.print("Invalid input! Please enter a valid Customer ID: ");
        sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customers WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        String qry = "DELETE FROM tbl_customers WHERE c_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}

    