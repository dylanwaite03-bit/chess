package service;
import dataaccess.AuthDAO;
import dataaccess.UserDAO;
import org.eclipse.jetty.server.Authentication;

public class SessionService {
    private final UserDAO userDAO;
    private final AuthDAO authDAO;

    public SessionService(UserDAO userDAO, AuthDAO authDAO){
        this.userDAO=userDAO;
        this.authDAO=authDAO;
    }
}

