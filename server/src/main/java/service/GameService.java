package service;

import chess.ChessGame;
import chess.ChessMove;
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

        GameData game=new GameData(0,null,null, request.gameName(),new ChessGame());
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

        if ("WHITE".equals(color)){
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

    public GameData getGame(String authToken, int gameId)throws Exception{
        AuthData authData=authDAO.getAuth(authToken);

        if (authData==null){
            throw new Exception("unauthorized");
        }
        GameData game=gameDAO.getGame(gameId);
        if (game==null){
            throw new Exception("no game");
        }
        return game;
    }

    public GameData makeMove(String authToken, int gameId, ChessMove move)throws Exception{
        GameData game=getGame(authToken,gameId);
        AuthData auth=authDAO.getAuth(authToken);
        ChessGame chessGame=game.game();
        String username=getUsername(authToken);
        if (!auth.username().equals(game.whiteUsername()) && !auth.username().equals(game.blackUsername())){
            throw new Exception("Error");
        }
        if(game.game().isGameOver()){
            throw new Exception("game already over");
        }
        if (game.game().getTeamTurn() != ChessGame.TeamColor.WHITE && username.equals(game.whiteUsername())){
            throw new Exception("not your turn");
        }
        if (game.game().getTeamTurn() != ChessGame.TeamColor.BLACK && username.equals(game.blackUsername())){
            throw new Exception("not your turn");
        }

        chessGame.makeMove(move);
        gameDAO.updateGame(game);
        return game;
    }

    public String getUsername(String authToken) throws Exception{
        AuthData authData=authDAO.getAuth(authToken);

        if (authData==null){
            throw new Exception("unauthorized");
        }
        return authData.username();
    }

    public void resign(String authToken, int gameId) throws Exception{
        GameData game=getGame(authToken,gameId);
        String username=getUsername(authToken);
        if(!username.equals(game.whiteUsername()) && !username.equals(game.blackUsername())){
            throw new Exception("unauthorized");
        }
        if(game.game().isGameOver()){
            throw new Exception("game already over");
        }
        game.game().setGameOver(true);
        gameDAO.updateGame(game);

    }

}
