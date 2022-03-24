package service

import java.io.File

/**
 * This class manages the stats file saving
 */
class StatService (private val gameService: GameService)
{
    /** saves the stats of the current player in a text file */
    fun saveStats()
    {
        val game = gameService.currentGame!!
        File("src/main/resources/${game.player} stats").writeText(
            game.stats.gamesPlayed.toString() + "\n" + game.stats.solvedWords + "\n" +
                    game.stats.successRate + "\n" + game.stats.streak + "\n" + game.stats.averageTries)
    }

    /** loads the text file for the current player */
    fun loadStats(): List<String>
    {
        return File("src/main/resources/${gameService.currentGame!!.player} stats").readLines()
    }
}