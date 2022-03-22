package view

import service.RootService
import tools.aqua.bgw.core.BoardGameApplication
import tools.aqua.bgw.core.BoardGameScene

/**
 *  Scene calls for the game Wordle
 */
class WordleApplication(val rootService: RootService = RootService()):
    BoardGameApplication("Wordle"), Refreshable
{
    /** StartMenuScene */
    private val startMenu: BoardGameScene = StartMenuScene().apply {
        startGameButton.onMouseClicked = { rootService.gameService.startNewGame(playerNameField.text,
            languageSelection.selectedItem.toString())
            showGameScene(gameScene) }
        quitButton.onMouseClicked = { exit() }
    }

    private val gameScene: BoardGameScene = GameScene(rootService).apply {
        quitButton.onMouseClicked = { exit() }
    }

    init {
        showGameScene(startMenu)
    }
}