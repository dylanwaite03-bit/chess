package dataaccess;
import model.UserData;
import org.eclipse.jetty.server.Authentication;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserDAO implements UserDAO {
    private final Map<String,UserData> users=new HashMap<>();

    @Override
    public void createUser(UserData user) throws DataAccessException{
        if (users.containsKey(user.username())){
            throw new DataAccessException("already taken");
        }
        users.put(user.username(),user);
    }



}
