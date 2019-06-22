package service;

import domain.User;
import exception.UserExistException;
public interface IUserService {
    void registerUser(User user) throws UserExistException;
    User loginUser(String userName,String userPwd);
}
