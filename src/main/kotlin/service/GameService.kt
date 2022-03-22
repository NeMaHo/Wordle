package service

import entity.*
import tools.aqua.bgw.visual.ColorVisual
import java.io.File

/**
 *  Class for providing basic game functionalities
 */
class GameService (private val rootService: RootService): AbstractRefreshingService()
{
    /** counts the amount of guesses of the player */
    private var solutionLetters = listOf<String>()

    /**
     *  initialises a new game
     *  @param language: possible choices are "english" and "german"
     */
    fun startNewGame(player: String, language: String)
    {
        val wordList = getWords(language = language).shuffled()
        val wordle = Wordle(player = player, solution = wordList[0], words = wordList,
            stats = Stats (gamesPlayed = 0, solvedWords =  0, successRate = 0.0, streak = 0, averageTries = 0.0,
                favouriteStartWord = ""))
        solutionLetters = wordle.solution.split("").slice(1..5)
        rootService.currentGame = wordle
    }

    /**
     *  This function compares the guessed word to the current game's solution and returns a string list of colours
     *  which indicate the match level (green: right position, yellow: wrong position, gray: not included).
     */
    fun evaluateWord(guess: String): MutableList<ColorVisual>
    {
        val game = rootService.currentGame!!
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

    /** HELP FUNCTIONS */

    /** reads the required document with words */
    private fun getWords(language: String):  List<String>
    {
        return File("src/main/resources/$language five letters words.txt").readLines()
    }
}