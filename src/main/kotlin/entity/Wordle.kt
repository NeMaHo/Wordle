package entity

/**
 *  This class is the entity of the current game.
 *  @param player: name of the current player
 *  @param language: language selected by the player
 *  @param solution: word that needs to be solved
 *  @param words: list of words in the specific language
 *  @param stats: stats of the current player
 *  @property tryNum: number of tries needed to solve the game
 */
class Wordle (
    val player: String,
    val language: String,
    val solution: String,
    val words: List<String>,
    var stats: Stats)
{
    var tryNum = 0
}