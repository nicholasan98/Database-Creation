package lab5;
import java.util.Scanner;
import java.sql.*;

/**
 * Nicholas An
 * Student #: 500750378
 */

public class Lab5 {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        String sql = "";
        
        do{
        try {
      
            Class.forName("org.sqlite.JDBC");

            c = DriverManager.getConnection("jdbc:sqlite:\\Users\\Nick's PC\\Desktop\\School\\848\\data\\Data.db");

            System.out.println("Database application, enter the number required for the required action:\n");
            System.out.println("To create a table; '1':");
            System.out.println("To insert into a table; '2':");
            System.out.println("To update an attribute; '3':");
            System.out.println("To delete an attribute; '4':");
            System.out.println("To select an attribute; '5':");
            int tmp = input.nextInt();
            
            if (tmp == 1) {
                
            	System.out.println("Enter the name:");
                String name = input2.nextLine();
                
                System.out.println("A variable: " + name + " was created and placed into the table");

                stmt = c.createStatement();
                sql = "CREATE TABLE " + name + "(" + name + "ID);"; 
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
                
            } 
            
            else if(tmp == 2) {
                
            	System.out.println("Which table would you like to insert into?:");
                String tName = input2.nextLine();
                System.out.println("What is the variable name?:");
                String vName = input2.nextLine();
                System.out.println("Enter the number, is the variable a; String(1) or a Integer(2)?:");
                tmp = input.nextInt();

                if (tmp == 1) {
                    
                    System.out.println("What is the String's value?:");
                    String vValue = input2.nextLine();
                    sql = "INSERT INTO  " + tName + "("+ vName + ") VALUES ('" + vValue + "');";
                    
                }
                
                else if(tmp == 2) {             
                    
                    System.out.println("What is the Integer value?:");
                    int vValue = input.nextInt();
                    sql = "INSERT INTO  " + tName + "("+ vName + ") VALUES (" + vValue + ");";
                    
                }
                
                c.setAutoCommit(false);
                stmt = c.createStatement();
                 
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
             
            }

            else if(tmp == 3) {
                
                System.out.println("Which table would you like to update?:");
                String tName = input2.nextLine();
                System.out.println("What is the variable name?:");
                String vName = input2.nextLine();
                System.out.println("Enter the number, is the variable a; String(1) or a Integer(2)?:");
                tmp = input.nextInt();
                
                if(tmp == 1) {
                    
                    System.out.println("New string value?:");
                    String vValue = input2.nextLine();
                    System.out.println("Old string value?:");
                    String nValue = input2.nextLine();
                    
                    sql = "UPDATE " + tName + " SET " + vName + " = '" + vValue + "' WHERE " + vName + " = '" + nValue + "';";
                    
                }
                
                else if(tmp == 2) {          
                    
                    System.out.println("New integer value?:");
                    int vValue = input.nextInt();
                    System.out.println("Old integer value?:");
                    int nValue = input.nextInt();
                    
                    sql = "UPDATE " + tName + " SET " + vName + " = " + vValue + " WHERE " + vName + " = " + nValue + ";";
                }

                //update test set testID = 'bye' where testID = 'hello';
                c.setAutoCommit(false);
                stmt = c.createStatement();
                
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
                
            }
            
            else if(tmp == 4) {
                
                System.out.println("Which table would you like to delete from?:");
                String tName = input2.nextLine();
                System.out.println("What is the variable name?:");
                String vName = input2.nextLine();
                System.out.println("Enter the number, is the variable a; String(1) or a Integer(2)?:");
                tmp = input.nextInt();
               
                if(tmp == 1) {
                    
                    System.out.println("Original string value?:");
                    String vValue = input2.nextLine();
                    
                    sql = "DELETE FROM " + tName + " WHERE " + vName + " = '" + vValue + "';";
                    
                }
                
                else if(tmp == 2) {
                   
                    System.out.println("Original integer value?:");
                    int vValue = input.nextInt();
                    
                    sql = "DELETE FROM " + tName + " WHERE " + vName + " = " + vValue + ";";
                    
                }

                //update test set testID = 'bye' where testID = 'hello';
                c.setAutoCommit(false);
                stmt = c.createStatement();
                
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
                
            }
            
            else if (tmp == 5) {
                
                System.out.println("Which table would you like to select from?:");
                String tName = input2.nextLine();
                System.out.println("Which attribute would you like to select?:");
                String vName = input2.nextLine();
                System.out.println("Select a specific attribute(1) or list out all(2)?:");
                tmp = input.nextInt();
                if(tmp == 1) {
                    
                    System.out.println("Which specific attribute would you like to select?:");
                    String v2Name = input2.nextLine();
                    System.out.println("Integer(1) or String(2)?:");
                    tmp = input.nextInt();
                    if(tmp == 1) {
                        
                        System.out.println("Value of selected specific attribute:");
                        int vValue = input.nextInt();
                        sql = "SELECT " + vName + " FROM " + tName + " WHERE " + v2Name + " = " + vValue + ";";
                        System.out.println("\nYour " + vName + "s are: \n");
                        
                    }
                    
                    else if (tmp == 2) {
                        
                        System.out.println("Value of selected specific attribute:");
                        String vValue = input2.nextLine();
                        sql = "SELECT " + vName + " FROM " + tName + " WHERE " + v2Name + " = '" + vValue + "';";
                        System.out.println("\nYour " + vName + "s are: \n");
                        
                    }
                    
                    
                }
                
                else if(tmp == 2) {
                    
                    sql = "SELECT " + vName + " FROM " + tName + ";";
                    System.out.println("\nYour " + vName + "s are: \n");
                    
                }
                
                c.setAutoCommit(false);
                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                while ( rs.next() ) {

                    String  tmp2 = rs.getString(vName);
                    System.out.println(tmp2);

                }
                System.out.println("\n");


                stmt.close();
                c.commit();                     
                c.close();
                
            }
            
            else{
                
                break;
                
            }

        } 
        
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        
        } while(true);
    
    }
    
}
