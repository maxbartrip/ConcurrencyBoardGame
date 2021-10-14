
/**
 * @author Max
 * 
 * This is the enum that signifies which type of game the user is playing. This is important as each game will have different rules and potentially a different board.
 * This is used to create the correct size board for each game.
 *
 */
public enum GameType {
  GAME_CHECKERS,
  GAME_CHESS,
  GAME_DRAUGHTS,
  GAME_GOMOKU,
  GAME_DAMA;
}
