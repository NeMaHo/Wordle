package view

import service.GameService
import tools.aqua.bgw.core.BoardGameApplication
import tools.aqua.bgw.core.BoardGameScene

/**
 *  Scene calls for the game Wordle
 */
class WordleApplication(private val gameService: GameService): BoardGameApplication("Wordle")
{
    /** StartMenuScene */
    private val startMenu: BoardGameScene = StartMenuScene().apply {
        startGameButton.onMouseClicked = { gameService.startNewGame(playerNameField.text,
            languageSelection.selectedItem.toString())
            showGameScene(gameScene) }
        quitButton.onMouseClicked = { exit() }
    }

    private val gameScene: BoardGameScene = GameScene(gameService).apply {
        quitButton.onMouseClicked = { exit() }
    }

    init {
        showGameScene(startMenu)
    }
}