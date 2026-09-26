package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO){
        this.userDAO=userDAO;
        this.authDAO=authDAO;
    }

    public RegisterResult register(RegisterRequest request) throws Exception{
        UserData existingUser=userDAO.getUser(request.username());

        if (existingUser!=null){
            throw new Exception("already taken");

        }

        UserData user=new UserData(request.username(),request.password(),request.email());
        userDAO.createUser(user);
        String authToken=UUID.randomUUID().toString();
        AuthData authData= new AuthData(authToken, request.username());
        authDAO.createAuth(authData);
        return new RegisterResult(request.username(), authToken);
    }

}
