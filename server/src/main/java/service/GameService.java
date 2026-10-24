package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;

import java.util.List;

public class GameService {
    private final GameDAO gameDAO;
    private final AuthDAO authDAO;

    public GameService(GameDAO gameDAO, AuthDAO authDAO){
        this.gameDAO=gameDAO;
        this.authDAO=authDAO;
    }

    public CreateGameResult createGame(CreateGameRequest request, String authToken) throws Exception{
        AuthData authdata=authDAO.getAuth(authToken);
        if (request==null || request.gameName()==null){
            throw new Exception("bad request");
        }
        if (authdata==null){
            throw new Exception("unauthorized");
        }

        GameData game=new GameData(0,null,null, request.gameName(),null);
        int gameId=gameDAO.createGame(game);

        return new CreateGameResult(gameId);

    }

    public GameData joinGame(JoinGameRequest request, String authToken)throws Exception{
        AuthData authData=authDAO.getAuth(authToken);
        if (authData==null){
            throw new Exception("unauthorized");
        }
        GameData game = gameDAO.getGame(request.gameID());
        if (game==null){
            throw new Exception("bad request");
        }

        String color= request.playerColor();

            if (!"WHITE".equals(color) &&
                    !"BLACK".equals(color)) {
                throw new Exception("bad request");
            }

            if ("WHITE".equals(color)) {
                if (game.whiteUsername() != null) {
                    throw new Exception("already taken");
                }
                GameData updatedGame = new GameData(game.gameID(), authData.username(),
                        game.blackUsername(), game.gameName(), game.game());
                gameDAO.updateGame(updatedGame);
            }
            if ("BLACK".equals(color)) {
                if (game.blackUsername() != null) {
                    throw new Exception("already taken");
                }
                GameData updatedGame = new GameData(game.gameID(), game.whiteUsername(),
                        authData.username(), game.gameName(), game.game());
                gameDAO.updateGame(updatedGame);
            }

        return gameDAO.getGame(request.gameID());
    }

    public List<GameData> listGames(String authToken) throws Exception{
        AuthData authData=authDAO.getAuth(authToken);

        if (authData==null){
            throw new Exception("unauthorized");
        }

        return gameDAO.listGames();
    }



}
