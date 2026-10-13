package dataaccess;

import chess.ChessGame;
import dataaccess.sql.DbInitializer;
import dataaccess.sql.SQLGameDAO;
import model.GameData;
import org.junit.jupiter.api.*;

import javax.xml.crypto.Data;
import java.util.List;

public class SQLGameDAOtests {
    private SQLGameDAO gameDAO;

    @BeforeAll
    public static void createTables() throws DataAccessException{
        DbInitializer.initialize();;
    }

    @BeforeEach
    public void setup() throws DataAccessException{
        gameDAO=new SQLGameDAO();
        gameDAO.clear();
    }

    @Test
    public void listgamestest()throws DataAccessException{
        int firstId=gameDAO.createGame(new GameData(0,null,null,"first",new ChessGame()));
        int secondId=gameDAO.createGame(new GameData(1,null,null,"second",new ChessGame()));

        List<GameData> games=gameDAO.listGames();

        Assertions.assertEquals(2, games.size());

        GameData stored = games.get(0);
        Assertions.assertEquals(firstId,stored.gameID());
        Assertions.assertEquals("first",stored.gameName());
        Assertions.assertNull(stored.whiteUsername());
        Assertions.assertNull(stored.blackUsername());
    }

}
