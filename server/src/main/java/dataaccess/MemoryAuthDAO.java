package dataaccess;

import model.AuthData;

import java.util.HashMap;
import java.util.Map;


public class MemoryAuthDAO implements AuthDAO{
    private final Map<String, AuthData> authTokens=new HashMap<>();

    @Override
    public void createAuth(AuthData authdata) throws DataAccessException{
        authTokens.put(authdata.authtoken(), authdata);
    }

    @Override
    public AuthData getAuth(String authtoken) throws DataAccessException{
        return authTokens.get(authtoken);
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {
        authTokens.remove(authToken);
    }

    @Override
    public void clear() throws DataAccessException{
        authTokens.clear();
    }


}
