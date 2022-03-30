package view

import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.*

/**
 *  This class visualizes the stats of the current player.
 */
class StatScene(var stats: List<String>): BoardGameScene(900, 1080)
{
    /** board with stats for the current player */
    private val title = Label(width = 600, height = 150, posX = 150, posY = 160,
        text = "Statistics", alignment = Alignment.CENTER, font = Font(size = 70))
    private val gamesPlayedLabel = Label(width = 700, height = 60, posX = 100, posY = 350,
        text = "Games played: " + stats[0], font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE)
    private val solvedWordsLabel = Label(width = 700, height = 60, posX = 100, posY = 430,
        text = "Solved words:" + stats[1], font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE)
    private val successRateLabel = Label(width = 700, height = 60, posX = 100, posY = 510,
        text = "Success rate:" + stats[2], font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE)
    private val streakLabel = Label(width = 700, height = 60, posX = 100, posY = 590,
        text = "Current streak:" + stats[3], font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE)
    private val averageTriesLabel = Label(width = 700, height = 60, posX = 100, posY = 670,
        text = "Average tries:" + stats[4], font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE)

    /** pressing this button quits the application */
    val quitButton = Button(width = 300, height = 100, posX = 50, posY = 900,
        text = "Quit", font = Font(45), visual = ColorVisual.WHITE)

    /** pressing this button shows the game scene */
    val backToGameButton = Button(width = 400, height = 100, posX = 450, posY = 900,
        text = "Back to game", font = Font(45), visual = ColorVisual.WHITE)

    init
    {
        opacity = 0.8
        background = ColorVisual(105, 177, 225)
        addComponents(title, gamesPlayedLabel, solvedWordsLabel, successRateLabel, streakLabel, averageTriesLabel,
            quitButton, backToGameButton)
    }
}
