/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author andre
 */
public class MyBatisUtil {

    public static final String RESURCE = "config/mybatis-config.xml";
    public static final String ENVINROMENT = "development";

    public static SqlSession getSesion() {
        SqlSession session = null;
        try {
            Reader reader = Resources.getResourceAsReader(RESURCE);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader, ENVINROMENT);
            session = sqlMapper.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }
}
