package view

import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.*

/**
 *  This class visualizes the start menu.
 */
class StartMenuScene: BoardGameScene(900, 800)
{
    /** title */
    private val title = Label(width = 900, height = 90, posX = 0, posY = 50,
        text = "Wordle - menu", alignment = Alignment.CENTER, font = Font(size = 80))

    /** text for the name of the player */
    var playerNameField: TextField = TextField(width = 600, height = 70, posX = 150, posY = 200,
        prompt = "Enter name", font = Font(size = 25)).apply { onKeyTyped = { refreshStartButton() } }

    val languageSelection = ComboBox(width = 600, height = 70, posX = 150, posY = 320,
        prompt = "Select language", font = Font(40), items = listOf("english", "german")).apply {
        onMouseEntered = { refreshStartButton() } }

    val typeSelection = ComboBox(width = 600, height = 70, posX = 150, posY = 440,
        prompt = "Select occurrences of letters ", font = Font(40), items = listOf("unique", "multiple")).apply {
        onMouseEntered = { refreshStartButton() } }

    /** Pressing this button starts a new game. */
    val startGameButton = Button(width = 300, height = 130, posX = 300, posY = 650,
        text = "Start", alignment = Alignment.CENTER, font = Font(size = 75), visual = ColorVisual.ORANGE).apply {
        this.isDisabled = true }

    /** Pressing this button quits the application. */
    val quitButton = Button(width = 150, height = 80, posX = 1760, posY = 10,
        text = "Quit", alignment = Alignment.CENTER, font = Font(size = 35), visual = ColorVisual.RED)

    init
    {
        background = ColorVisual(0, 180, 120)
        addComponents(title, startGameButton, playerNameField, languageSelection, typeSelection, quitButton)
    }

    /** refreshes disability of the startGameButton */
    private fun refreshStartButton()
    {
        startGameButton.isDisabled = playerNameField.text.isBlank() || languageSelection.selectedItem.isNullOrBlank()
                || typeSelection.selectedItem.isNullOrBlank()
    }
}