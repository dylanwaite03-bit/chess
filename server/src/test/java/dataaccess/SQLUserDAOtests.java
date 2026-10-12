package dataaccess;

import dataaccess.sql.DbInitializer;
import dataaccess.sql.SQLUserDAO;
import model.UserData;
import org.junit.jupiter.api.Test;

public class SQLUserDAOtests {
    private SQLUserDAO userDAO;

    @Test
    public void createusertest()throws DataAccessException{
        DbInitializer.initialize();
        userDAO=new SQLUserDAO();
        UserData user=new UserData("dyl","pass","email");
        userDAO.createUser(user);
        UserData stored=UserDAO.getuser()
    }
}
