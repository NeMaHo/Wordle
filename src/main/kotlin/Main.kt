import service.RootService
import view.WordleApplication

/**
 *  This class builds and runs the application.
 */
fun main() {
    WordleApplication(rootService = RootService()).show()
    println("Application ended. Goodbye")
}
