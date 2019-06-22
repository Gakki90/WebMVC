package dao.impl;

import domain.User;
import util.JdbcUtils_C3P0;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class DataSourceTest {

    @Test
    public void c3p0DataSourceTest() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            //获取数据库连接
            conn = JdbcUtils_C3P0.getConnection();
            conn = JdbcUtils_C3P0.getConnection();
            String sql = "select * from users where name='myname' ";

            st = conn.prepareStatement(sql);

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
                System.out.println(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            JdbcUtils_C3P0.release(conn, st, rs);
        }
    }
}
