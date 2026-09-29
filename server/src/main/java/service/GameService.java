package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;

public class GameService {
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;

    public GameService(GameDAO gameDAO, AuthDAO authDAO){
        this.gameDAO=gameDAO;
        this.authDAO=authDAO;
    }

    public CreateGameResult createGame(CreateGameRequest request, String authToken) throws Exception{
        AuthData authdata=authDAO.getAuth(authToken);
        if (authdata==null){
            throw new Exception("unauthorized");
        }
        if (request.gameName()==null){
            throw new Exception("bad request");
        }

        GameData game=new GameData(0, request.gameName(),null,null,null);
        int gameId=gameDAO.createGame(game);

        return new CreateGameResult(gameId);

    }

    public void joinGame(JoinGameRequest request, String authToken)throws Exception{
        AuthData authData=authDAO.getAuth(authToken);
        if (authData==null){
            throw new Exception("unauthorized");
        }
        if(!request.playerColor().equals("WHITE") &&
                !request.playerColor().equals("BLACK")){
            throw new Exception("unauthorized");
        }

        GameData game=gameDAO.getGame(request.gameId());
        if (game==null){
            throw new Exception("bad request");
        }
        if(request.playerColor().equals("WHITE")){
            if (game.whiteUsername()==null){
                throw new Exception("already taken");
            }
        }
        GameData updatedGame=new GameData(game.gameId(),game.gameName(),authData.username(),
                game.blackUsername(),game.game());




    }

}
