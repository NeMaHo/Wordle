import service.GameService
import view.WordleApplication

/**
 *  This class builds and runs the application.
 */
fun main() {
    WordleApplication(gameService = GameService()).show()
}
