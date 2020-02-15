import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class ConnectionTestTask {

    String user = "testUser";
    String password = "test12345";
    SoftAssertions softly = new SoftAssertions();
    String query2 = "SELECT * FROM lecture_1.agents where AGENT_NAME = 'Lucida'";
    String query1 = "SELECT * FROM lecture_1.agents limit 5";


    @Test
    public void firstTest() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = getConnection("jdbc:mysql://127.0.0.1:3306/lecture_1", user, password);
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query1);
        con.close();
    }

    @Test
    public void assertData() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = getConnection("jdbc:mysql://127.0.0.1:3306/lecture_1", user, password);
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query2);
        while (resultSet.next()) {
            softly.assertThat("A012").isEqualTo(resultSet.getString(1));
            softly.assertThat("San Jose").isEqualTo(resultSet.getString(3));
            softly.assertThat("0.12").isEqualTo(resultSet.getString(4));
            softly.assertThat("044-52981425").isEqualTo(resultSet.getString(5));
            softly.assertAll();
        }
        con.close();
    }

    @Test
    public void addRemoveData() throws SQLException {
        String query3 = "INSERT INTO AGENTS VALUES ('A013','Test','Ŗiga','1.12','778-32556178','')";
        String query4 = "UPDATE AGENTS SET WORKING_AREA='Banggladesh', PHONE_NO = '777-11111111' WHERE AGENT_NAME ='Mukesh'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = getConnection("jdbc:mysql://127.0.0.1:3306/lecture_1", user, password);
        Statement stmt = con.createStatement();
        int resultInsert = stmt.executeUpdate(query3);
        int resultUpdate = stmt.executeUpdate(query4);
        con.close();
    }

}



