package dao.impl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.taglibs.standard.lang.jstl.ELEvaluator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import dao.IUserDao;
import domain.User;
import util.XmlUtils;
import util.JdbcUtils_C3P0;


public class UserDaoImpl implements IUserDao {
    @Override
    public User find(String userName, String userPwd) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JdbcUtils_C3P0.getConnection();
            String sql = "select * from users where name=? and password=?";

            st = conn.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2,userPwd);
            //获取数据库自动生成的主键
            rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString(1));
                user.setEmail(rs.getString(2));
                user.setUserPwd(rs.getString(3));

                String birth = rs.getString(4);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(sdf.parse(birth));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                JdbcUtils_C3P0.release(conn,st,rs);
        }

        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void add(User user) {

        try {
            //获取数据库连接
            QueryRunner qr = new QueryRunner(JdbcUtils_C3P0.getDatasource());

            String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
            Object params[] = {user.getUserName(), user.getUserPwd(), user.getEmail(), user.getBirthday()};

            qr.update(sql, params);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
    public User find(String userName) {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            //获取数据库连接
            conn = JdbcUtils_C3P0.getConnection();
            String sql = "select * from users where name=?";

            st = conn.prepareStatement(sql);
            st.setString(1, userName);

            //获取数据库自动生成的主键
            rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setUserPwd(rs.getString(4));

                String birth = rs.getString(5);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(sdf.parse(birth));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_C3P0.release(conn,st,rs);
        }

            return null;
    }
}
