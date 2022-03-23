package view

import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.*

/**
 *  Class for providing basic game functionalities
 */
class StartMenuScene: BoardGameScene(1920, 1080)
{
    /** title */
    private val title = Label(width = 1920, height = 1080, posX = 0, posY = -400,
        text = "Wordle - menu", alignment = Alignment.CENTER, font = Font(size = 80))

    /** text for the name of the player */
    var playerNameField: TextField = TextField(width = 700, height = 80, posX = 600, posY = 320,
        prompt = "Enter name", font = Font(size = 25)).apply { onKeyTyped = { refreshStartButton() } }

    val languageSelection = ComboBox(width = 700, height = 70, posX = 600, posY = 450,
        prompt = "Select language", font = Font(50), items = listOf("english", "german")).apply {
        onMouseExited = { refreshStartButton() } }

    /** Pressing this button starts a new game. */
    val startGameButton = Button(width = 500, height = 200, posX = 715, posY = 800,
        text = "Start", alignment = Alignment.CENTER, font = Font(size = 70), visual = ColorVisual.ORANGE).apply {
        this.isDisabled = true }

    private fun refreshStartButton()
    {
      startGameButton.isDisabled = playerNameField.text.isBlank() || languageSelection.selectedItem.isNullOrBlank()
    }

    /** Pressing this button quits the application. */
    val quitButton = Button(width = 150, height = 80, posX = 1760, posY = 10,
        text = "Quit", alignment = Alignment.CENTER, font = Font(size = 35), visual = ColorVisual.RED)

    init
    {
        background = ColorVisual(0, 180, 120)
        addComponents(title, startGameButton, playerNameField, languageSelection, quitButton)
    }
}