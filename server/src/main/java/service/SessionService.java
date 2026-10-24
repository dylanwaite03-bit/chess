package service;
import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

public class SessionService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public SessionService(UserDAO userDAO, AuthDAO authDAO){
        this.userDAO=userDAO;
        this.authDAO=authDAO;
    }

    public LoginResult login(LoginRequest request) throws Exception{
        if (request.username()==null || request.password()==null){
            throw new Exception("bad request");
        }

        UserData user=userDAO.getUser(request.username());

        if (user==null){
            throw new Exception("unauthorized");
        }

        if (!BCrypt.checkpw(request.password(),user.password())){
            throw new Exception(("unauthorized"));
        }

        String authToken= UUID.randomUUID().toString();

        AuthData authData=new AuthData(authToken, request.username());
        authDAO.createAuth(authData);
        return new LoginResult(request.username(), authToken);
    }

    public void logout(String authtoken) throws Exception{
        AuthData authData= authDAO.getAuth(authtoken);

        if (authData==null){
            throw new Exception("unauthorized");
        }

        authDAO.deleteAuth(authtoken);
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

