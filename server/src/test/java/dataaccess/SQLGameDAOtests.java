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
    public void creategamestest()throws DataAccessException{
        int firstId=gameDAO.createGame(new GameData(0,null,null,"first",new ChessGame()));

        Assertions.assertTrue(firstId>0);
        Assertions.assertNotNull(gameDAO.getGame(firstId));
    }

    @Test
    public void getgamestest()throws DataAccessException{
        int firstId=gameDAO.createGame(new GameData(0,null,null,"first",new ChessGame()));
        GameData stored=gameDAO.getGame(firstId);
        Assertions.assertEquals(firstId,stored.gameID());
        Assertions.assertNull(stored.whiteUsername());
        Assertions.assertNull(stored.blackUsername());
        Assertions.assertEquals("first",stored.gameName());
        Assertions.assertNotNull(stored.game());
    }

    @Test
    public void getgamestestfail()throws DataAccessException{
        Assertions.assertNull(gameDAO.getGame(22));
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

    @Test
    public void listgamesempty()throws DataAccessException{

        List<GameData> games=gameDAO.listGames();
        Assertions.assertTrue(games.isEmpty());
    }


}
