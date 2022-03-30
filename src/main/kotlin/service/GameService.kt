package service

import entity.*
import tools.aqua.bgw.visual.ColorVisual
import java.io.File

/**
 *  Class for providing basic game functionalities
 */
class GameService
{
    val statService = StatService(this)
    /** The currently active game. Can be `null`, if no game has started yet */
    var currentGame : Wordle? = null
    /** counts the amount of guesses of the player */
    private var solutionLetters = listOf<String>()

    /**
     *  initialises a new game
     *  @param language: possible choices are "english" and "german"
     */
    fun startNewGame(player: String, language: String)
    {
        val wordList = getWords(language = language).shuffled()
        val wordle = Wordle(player = player, language = language, solution = wordList[0], words = wordList, tryNum = 0,
            stats = Stats (gamesPlayed = 0, solvedWords =  0, successRate = 0.0, streak = 0, averageTries = 0.0))
        solutionLetters = wordle.solution.split("").slice(1..5)
        currentGame = wordle
    }

    /**
     *  This function compares the guessed word to the current game's solution and returns a string list of colours
     *  which indicate the match level (green: right position, yellow: wrong position, gray: not included).
     */
    fun evaluateWord(guess: String): MutableList<ColorVisual>
    {
        val game = currentGame!!
        print(game.solution)
        if(!game.words.contains(guess)) throw IllegalArgumentException("word is not in list")

        val currentGuess = guess.split("").slice(1..5)
        val result = generateSequence { ColorVisual.GRAY }.take(5).toMutableList()

        val erg = currentGuess.filter { solutionLetters.contains(it) }
        for (i in 0 until 5)
        {
            if (erg.contains(currentGuess[i]))
            {
                result[i] = ColorVisual.YELLOW
                if (currentGuess[i] == solutionLetters[i])
                    result[i] = ColorVisual.GREEN
            }
        }
        return result
    }

    /** updates stats for the specific player */
    fun endGame(solved: Boolean)
    {
        val game = currentGame!!
        val statFile = File("src/main/resources/${game.player} stats")
        if (statFile.exists())
        {
            val oldStats = statService.loadStats()
            game.stats.gamesPlayed = oldStats[0].toInt() + 1
            game.stats.solvedWords = oldStats[1].toInt() + solved.toInt()
            game.stats.successRate = game.stats.solvedWords / game.stats.gamesPlayed.toDouble()
            if (solved)  game.stats.streak = oldStats[3].toInt() + 1
            else game.stats.streak = 0
            game.stats.averageTries = (oldStats[4].toDouble() * (game.stats.gamesPlayed - 1) + game.tryNum)  /
                    game.stats.gamesPlayed.toDouble()
        }
        else
        {
            game.stats = Stats(gamesPlayed = 1, solvedWords =  solved.toInt(),
                successRate = solved.toInt().toDouble(), streak = solved.toInt(),
                averageTries = game.tryNum.toDouble())
        }
        statService.saveStats()
    }

    /** HELP FUNCTIONS */

    /** reads the required document with words */
    private fun getWords(language: String):  List<String>
    {
        return File("src/main/resources/$language five letters words.txt").readLines()
    }

    /** converts boolean values to binary integers */
    private fun Boolean.toInt() = if (this) 1 else 0
}