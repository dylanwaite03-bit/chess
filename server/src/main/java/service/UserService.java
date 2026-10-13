package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO){
        this.userDAO=userDAO;
        this.authDAO=authDAO;
    }

    public RegisterResult register(RegisterRequest request) throws Exception{
        if (request.username()==null || request.password()==null ||
            request.email()==null){
            throw new Exception("bad request");
        }
        UserData existingUser=userDAO.getUser(request.username());

        if (existingUser!=null){
            throw new Exception("already taken");

        }
        String hashpassword= BCrypt.hashpw(request.password(),BCrypt.gensalt());
        UserData user=new UserData(request.username(),hashpassword,request.email());
        userDAO.createUser(user);
        String authToken=UUID.randomUUID().toString();
        AuthData authData= new AuthData(authToken, request.username());
        authDAO.createAuth(authData);
        return new RegisterResult(request.username(), authToken);
    }

}
