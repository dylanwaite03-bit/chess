package service;
import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;

import java.util.UUID;

public class SessionService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public SessionService(UserDAO userDAO, AuthDAO authDAO){
        this.userDAO=userDAO;
        this.authDAO=authDAO;
    }

    public LoginResult login(LoginRequest request) throws Exception{
        UserData user=userDAO.getUser(request.username());

        if (user==null){
            throw new Exception("unauthorized");
        }

        if (!user.password().equals(request.password())){
            throw new Exception(("unauthorized"));
        }

        String authToken= UUID.randomUUID().toString();

        AuthData authData=new AuthData(authToken, request.username());
        authDAO.createAuth(authData);
        return new LoginResult(request.username(), authToken);
    }


}

