package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class Task5 {
    private static SqlSessionFactory factory = null;

    @Test
    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session = null;

        reader = Resources.getResourceAsReader(resource);

        factory = new SqlSessionFactoryBuilder().build(reader);

        reader.close();

        try {
            session = factory.openSession();
            String area = session.selectOne("workingArea");
            System.out.println(area);

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
}
