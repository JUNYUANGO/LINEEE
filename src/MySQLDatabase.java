import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class MySQLDatabase implements Database{

    public MySQLDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_lineee", "root", "yyYY281002sql!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Tenant t) {

    }

    @Override
    public void delete(Tenant t) {

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
