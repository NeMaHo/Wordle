package entity

/**
 *  This class includes stats for played games.
 *  @param gamesPlayed: amount of games played
 *  @param solvedWords: amount of successfully solved words
 *  @param successRate: (amount of successfully solved words) / (amount of games played)
 *  @param streak: amount of successfully solved words in a row
 *  @param averageTries: average needed words to solve a word
 *  @param favouriteStartWord: most used starting word
 */
class Stats (
    val gamesPlayed: Int,
    val solvedWords: Int,
    val successRate: Double,
    val streak: Int,
    val averageTries: Double,
    val favouriteStartWord: String)