package dao;

import domain.User;

public interface IUserDao {
    /**
     * seek user according username and pwd
     */
    User find(String userName,String userPwd);
    void add(User user);
    User find(String userName);
}
