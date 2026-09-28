package service;

import dataaccess.AuthDAO;
import dataaccess.UserDAO;

public class ClearService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public ClearService(UserDAO userDAO,AuthDAO authDAO){
        this.userDAO=userDAO;
        this.authDAO=authDAO;
    }

    public void clear() throws Exception{
        userDAO.clear();
        authDAO.clear();
    }
}
