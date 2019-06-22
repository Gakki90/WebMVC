package service.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import exception.UserExistException;
import service.IUserService;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao=new UserDaoImpl();

    @Override
    public void registerUser(User user) throws UserExistException {
        if(userDao.find(user.getUserName())!=null){
            throw new UserExistException("the user registered has exist");
        }
        userDao.add(user);
    }

    @Override
    public User loginUser(String userName, String userPwd) {
       return userDao.find(userName,userPwd);
    }
}
