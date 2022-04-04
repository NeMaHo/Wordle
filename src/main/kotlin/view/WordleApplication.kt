package view

import service.GameService
import tools.aqua.bgw.core.*

/**
 *  This class includes calls for the game Wordle.
 */
class WordleApplication(private val gameService: GameService): BoardGameApplication("Wordle")
{
    /** StartMenuScene */
    private val startMenu: BoardGameScene = StartMenuScene().apply {
        startGameButton.onMouseClicked = { gameService.startNewGame(playerNameField.text,
            languageSelection.selectedItem.toString(), typeSelection.selectedItem.toString())
            showGameScene(gameScene) }
        quitButton.onMouseClicked = { exit() }
    }

    /** GameScene */
    private val gameScene: BoardGameScene = GameScene(gameService).apply {
        quitButton.onMouseClicked = { exit() }
        statsButton.onMouseClicked = { showMenuScene(statScene) }
    }

    /** StatScene */
    private var statScene: MenuScene = StatScene(gameService).apply {
        quitButton.onMouseClicked = { exit() }
        backToGameButton.onMouseClicked = { hideMenuScene(0.5)
            showGameScene(gameScene) }
    }

    init
    {
        showGameScene(startMenu)
    }
}