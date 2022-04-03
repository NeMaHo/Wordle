package entity

/**
 *  This class is the entity of the current game.
 *  @param player: name of the current player
 *  @param language: language selected by the player
 *  @param type: occurrence of same letters selected by the player
 *  @param solution: word that needs to be solved
 *  @param words: list of words in the specific language
 *  @param tryNum: number of tries needed to solve the game
 *  @param stats: stats of the current player
 */
class Wordle (
    val player: String,
    val language: String,
    val type: String,
    val solution: String,
    val words: List<String>,
    var tryNum: Int,
    var stats: Stats)