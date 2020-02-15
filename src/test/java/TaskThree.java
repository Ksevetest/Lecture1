import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class TaskThree {


    SoftAssertions softly = new SoftAssertions();
    String user = "testUser";
    String password = "test12345";
    String url = "jdbc:mysql://127.0.0.1:3306/lecture_1";

    private Connection con;
    private Statement stmt;

    @Before
    public void setupDataBase() throws SQLException {

        String[]  query1 = {"CREATE TABLE `AGENTS2` \n" +
                "   (\t\n" +
                "    `AGENT_CODE` CHAR(6) NOT NULL PRIMARY KEY, \n" +
                "\t`AGENT_NAME` CHAR(40), \n" +
                "\t`WORKING_AREA` CHAR(35), \n" +
                "\t`COMMISSION` DECIMAL(10,2), \n" +
                "\t`PHONE_NO` CHAR(15), \n" +
                "\t`COUNTRY` VARCHAR(25) \n" +
                "\t );",
                        "INSERT INTO AGENTS2 VALUES ('A007', 'Ramasundar', 'Bangalore', '0.15', '077-25814763', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A003', 'Alex ', 'London', '0.13', '075-12458969', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A008', 'Alford', 'New York', '0.12', '044-25874365', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A011', 'Ravi Kumar', 'Bangalore', '0.15', '077-45625874', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A010', 'Santakumar', 'Chennai', '0.14', '007-22388644', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A012', 'Lucida', 'San Jose', '0.12', '044-52981425', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A005', 'Anderson', 'Brisban', '0.13', '045-21447739', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A001', 'Subbarao', 'Bangalore', '0.14', '077-12346674', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A002', 'Mukesh', 'Mumbai', '0.11', '029-12358964', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A006', 'McDen', 'London', '0.15', '078-22255588', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A004', 'Ivan', 'Torento', '0.15', '008-22544166', '');\n",
                        "INSERT INTO AGENTS2 VALUES ('A009', 'Benjamin', 'Hampshair', '0.11', '008-22536178', '');"
        };

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
        for (String query : query1) {
            stmt.addBatch(query);
            stmt.executeBatch();
        }
    }


    @Test
    public void assertData() throws Exception {
        String query2 = "SELECT * FROM lecture_1.agents2 where AGENT_NAME ='Lucida'";
        ResultSet resultSet = stmt.executeQuery(query2);

        while (resultSet.next()) {
            softly.assertThat("A012").isEqualTo(resultSet.getString(1));
            softly.assertThat("San Jose").isEqualTo(resultSet.getString(3));
            softly.assertThat("0.12").isEqualTo(resultSet.getString(4));
            softly.assertThat("044-52981425").isEqualTo(resultSet.getString(5));
            softly.assertAll();
        }
    }

    @After
    public void closeTest() throws Exception {
        String query3 = "DROP TABLE lecture_1.agents2";
        stmt.executeUpdate(query3);
        con.close();
    }
}
