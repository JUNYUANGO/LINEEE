import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class MySQLDatabase implements Database{
    private static final String user = "root";
    private static final String pw = "yyYY281002sql!";
    private static final String tenantDB = "tenants";
    private static final String storeDB = "store";

    private Connection connection;

    public MySQLDatabase(){
        try {
            String connectionString = "jdbc:mysql://localhost/root" + "?user=" + user + "&password=" + pw + "&useUnicode=true&characterEncoding=UTF-8";
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MySQLDatabase test = new MySQLDatabase();
        Tenant t = new Tenant("testname", "T000111ID", "tester", "1A1001", 1000.0, 100, "098-765-4321");
        test.insert(t);
    }

    @Override
    public void insert(Tenant t) {
        try {
            //String to Varchar
            String tName = "'"+t.getName()+"'";
            String tID = "'"+t.getId()+"'";
            String tNN = "'"+t.getNickname()+"'";
            String tApt = "'"+t.getApt()+"'";
            double tAmount = t.getAmount();
            int tCredit = t.getCredit();
            String tPhone = "'"+t.getPhone()+"'";

            //MySQL query
            String useQuery = "use "+tenantDB;
            String insert = "insert into "+tenantDB+" values (%s, %s, %s, %s, %f, %d, %s)";
            String insertQuery = String.format(insert, tName, tID, tNN, tApt, tAmount, tCredit, tPhone);
            Statement statement = connection.createStatement();
            statement.execute(useQuery);
            int row = statement.executeUpdate(insertQuery);
            System.out.println("Successful Insertion to Row: "+row);
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
            String useQuery = "use "+tenantDB;
            String deleteQuery = ("delete from "+tenantDB+" where id = "+tID);
            Statement statement = connection.createStatement();
            statement.execute(useQuery);
            int row = statement.executeUpdate(deleteQuery);
            System.out.println("Successfully Deleted for Row: "+row);
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
    public void updateNickName(Tenant t, String nickname) {

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
