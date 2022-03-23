package service

import kotlin.test.*

/**
 *  test class for functions of the GameService
 */
class GameServiceTest {

    private val gameService = GameService()

    /** test if games start correctly */
    @Test
    fun startNewGameTest()
    {
        // no game started yet
        assertNull(gameService.currentGame)

        // start a game
        gameService.startNewGame("Neele", "english")
        assertNotNull(gameService.currentGame)
        val game = gameService.currentGame!!
        assertEquals("Neele", game.player)
        assertEquals("english", game.language)
        assertTrue { game.solution in game.words }
    }
}