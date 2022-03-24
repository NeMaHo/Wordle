package entity

/**
 *  This class includes stats for played games.
 *  @param gamesPlayed: amount of games played
 *  @param solvedWords: amount of successfully solved words
 *  @param successRate: (amount of successfully solved words) / (amount of games played)
 *  @param streak: amount of successfully solved words in a row
 *  @param averageTries: average needed words to solve a word
 */
class Stats (
    var gamesPlayed: Int,
    var solvedWords: Int,
    var successRate: Double,
    var streak: Int,
    var averageTries: Double)