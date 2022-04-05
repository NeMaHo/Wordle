package view

import service.GameService
import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.*

/**
 *  This class visualizes the stats of the current player.
 */
class StatScene(private val gameService: GameService) : MenuScene(700, 1080)
{
    /** stats for the current player */
    private var stats =  listOf("", "", "", "", "")

    /** board with stats for the current player which get revealed after pressing the fields */
    private val title = Label(width = 600, height = 150, posX = 50, posY = 160,
        text = "Statistics", alignment = Alignment.CENTER, font = Font(size = 70))
    private val gamesPlayedLabel = Label(width = 500, height = 60, posX = 100, posY = 350,
        text = "Games played: ", font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE).apply { onMouseClicked = { refreshShowingStats() } }
    private val solvedWordsLabel = Label(width = 500, height = 60, posX = 100, posY = 430,
        text = "Solved words: ", font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE).apply { onMouseClicked = { refreshShowingStats() } }
    private val successRateLabel = Label(width = 500, height = 60, posX = 100, posY = 510,
        text = "Success rate: ", font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE).apply { onMouseClicked = { refreshShowingStats() } }
    private val streakLabel = Label(width = 500, height = 60, posX = 100, posY = 590,
        text = "Current streak: ", font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE).apply { onMouseClicked = { refreshShowingStats() } }
    private val averageTriesLabel = Label(width = 500, height = 60, posX = 100, posY = 670,
        text = "Average tries: ", font = Font(40), alignment = Alignment.CENTER_LEFT,
        visual = ColorVisual.WHITE).apply { onMouseClicked = { refreshShowingStats() } }

    /** list of stat labels */
    private val statLabels = listOf(gamesPlayedLabel, solvedWordsLabel, successRateLabel, streakLabel,
        averageTriesLabel)

    /** pressing this button quits the application */
    val quitButton = Button(width = 150, height = 100, posX = 50, posY = 900,
        text = "Quit", font = Font(45), visual = ColorVisual.RED)

    /** pressing this button shows the game scene */
    val backToGameButton = Button(width = 350, height = 100, posX = 300, posY = 900,
        text = "Back to game", font = Font(45), visual = ColorVisual(164, 225, 220))

    init
    {
        opacity = 0.8
        background = ColorVisual(105, 177, 225)
        addComponents(title, gamesPlayedLabel, solvedWordsLabel, successRateLabel, streakLabel, averageTriesLabel,
            quitButton, backToGameButton)
    }

    /** reveals the stats after clicking on a stat label */
    private fun refreshShowingStats()
    {
        stats = gameService.statService.loadStats()
        for (i in 0 until 5)
        {
            statLabels[i].text += stats[i]
        }
        statLabels.forEach { it.isDisabled = true }
    }

    /** covers the stats after going back to the game scene */
    fun coverStats()
    {
        gamesPlayedLabel.text = "Games played: "
        solvedWordsLabel.text = "Solved words: "
        successRateLabel.text = "Success rate: "
        streakLabel.text = "Current streak: "
        averageTriesLabel.text = "Average tries: "
        statLabels.forEach { it.isDisabled = false }
    }
}
