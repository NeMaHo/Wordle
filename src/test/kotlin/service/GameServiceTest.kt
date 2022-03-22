package service

import kotlin.test.*

/**
 *  test class for functions of the GameService
 */
class GameServiceTest {

    private val root = RootService()

    /** test if games start correctly */
    @Test
    fun startNewGameTest()
    {
        // no game started yet
        assertNull(root.currentGame)

        // start a game
        root.gameService.startNewGame("Neele", "english")
        assertNotNull(root.currentGame)
        val game = root.currentGame!!
        assertEquals("Neele", game.player)
        assertTrue { game.solution in game.words }
    }
}