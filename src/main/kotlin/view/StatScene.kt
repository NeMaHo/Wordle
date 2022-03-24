package view

import service.GameService
import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.ColorVisual

/**
 *  This class visualizes the stats of the current player.
 */
class StatScene (private val gameService: GameService): MenuScene(900, 1080)
{
    /** board with stats for the current player */
    private val title = Label(width = 600, height = 150, posX = 150, posY = 160,
        text = "Statistics", alignment = Alignment.CENTER, font = Font(size = 70))
    /** private val gamesPlayedLabel = Label(width = 700, height = 60, posX = 100, posY = 350,
        text = "Games played: ${gameService.currentGame!!.stats.gamesPlayed}", font = Font(40),
        alignment = Alignment.CENTER_LEFT, visual = ColorVisual.WHITE)
    private val solvedWordsLabel = Label(width = 700, height = 60, posX = 100, posY = 430,
        text = "Solved words: ${gameService.currentGame!!.stats.solvedWords}", font = Font(40),
        alignment = Alignment.CENTER_LEFT, visual = ColorVisual.WHITE)
    private val successRateLabel = Label(width = 700, height = 60, posX = 100, posY = 510,
        text = "Success rate: ${gameService.currentGame!!.stats.successRate}", font = Font(40),
        alignment = Alignment.CENTER_LEFT, visual = ColorVisual.WHITE)
    private val streakLabel = Label(width = 700, height = 60, posX = 100, posY = 590,
        text = "Current streak: ${gameService.currentGame!!.stats.streak}", font = Font(40),
        alignment = Alignment.CENTER_LEFT, visual = ColorVisual.WHITE)
    private val averageTriesLabel = Label(width = 700, height = 60, posX = 100, posY = 670,
        text = "Average tries: ${gameService.currentGame!!.stats.averageTries}", font = Font(40),
        alignment = Alignment.CENTER_LEFT, visual = ColorVisual.WHITE) */

    /** pressing this button quits the application */
    val quitButton = Button(width = 300, height = 100, posX = 50, posY = 900,
        text = "Quit", font = Font(45), visual = ColorVisual.WHITE)

    /** pressing this button shows the game scene */
    val backToGameButton = Button(width = 400, height = 100, posX = 450, posY = 900, text = "Back to game",
        font = Font(45), visual = ColorVisual.WHITE)

    init
    {
        opacity = 0.8
        background = ColorVisual(105, 177, 225)
        addComponents(title, //gamesPlayedLabel, solvedWordsLabel, successRateLabel, streakLabel, averageTriesLabel,
        quitButton, backToGameButton)
    }
}
