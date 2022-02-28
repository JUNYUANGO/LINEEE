import java.sql.*;
import java.util.Scanner;

public class MySQLDatabase implements Database{
    private static final String user = "root";
    private static final String tenantDB = "tenants";
    private static final String storeDB = "store";
    private Connection connection;

    public MySQLDatabase(){
        try {
            try {
                System.out.println("Enter the password for the database: ");
                Scanner sc = new Scanner(System.in);
                String pw = sc.nextLine();
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionString = "jdbc:mysql://localhost/LINEEE" + "?user=" + user + "&password=" + pw + "&useUnicode=true&characterEncoding=UTF-8";
                connection = DriverManager.getConnection(connectionString);
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong Password!");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception, wrong password!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySQLDatabase test = new MySQLDatabase();
        Tenant t = new Tenant("testname", "T000111ID", "1A1001", 1000.0, 100, "098-765-4321");
        System.out.println("Insertion test.");
        test.insert(t);
        //Store st = new Store("testStore", "this is my address", "Monday to Friday: 8am to 8pm", "Bakery");
    }

    @Override
    public void insert(Tenant t) {
        try {
            //String to Varchar
            String tName = "'"+t.getName()+"'";
            String tID = "'"+t.getId()+"'";
            String tApt = "'"+t.getApt()+"'";
            double tAmount = t.getAmount();
            int tCredit = t.getCredit();
            String tPhone = "'"+t.getPhone()+"'";

            //MySQL query
            String insert = "insert into "+tenantDB+" values (%s, %s, %s, %f, %d, %s)";
            String insertQuery = String.format(insert, tName, tID, tApt, tAmount, tCredit, tPhone);
            Statement statement = connection.createStatement();
            statement.execute(insertQuery);
            System.out.println("Successful Insertion!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Tenant t) {
        try {
            //delete by ID
            String tID = "'"+t.getId()+"'";

            //MySQL query
            String deleteQuery = ("delete from "+tenantDB+" where id = "+tID);
            Statement statement = connection.createStatement();
            statement.execute(deleteQuery);
            System.out.println("Successfully Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findByID(String id) {

    }

    @Override
    public void findByName(String name) {

    }

    @Override
    public void updateID(Tenant t, String id) {

    }

    @Override
    public void updateName(Tenant t, String name) {

    }

    @Override
    public void updateApt(Tenant t, String apt) {

    }

    @Override
    public void updateAmount(Tenant t, double amount) {

    }

    @Override
    public void updateCredit(Tenant t, int credit) {

    }

    @Override
    public void updatePhone(Tenant t, String phoneNum) {

    }
}
