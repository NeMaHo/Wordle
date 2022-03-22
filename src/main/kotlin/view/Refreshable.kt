package view

/**
 *  This interface provides a mechanism for the service layer classes to communicate
 *  (usually to the view classes) that certain changes have been made to the entity
 *  layer, so that the user interface can be updated accordingly.
 *
 *  Default (empty) implementations are provided for all methods, so that implementing
 *  UI classes only need to react to events relevant to them.
 */
interface Refreshable
{
    /** RefreshAfterStart aktualisiert die GameScene direkt bei Initialisierung */
    fun refreshAfterStart() {}
    /** RefreshAfterTurn aktualisiert die Mitte sowie den Spieler, welcher als Nächstes am Zug ist */
    fun refreshAfterTurn() {}
    /** RefreshAfterGameEnd erstellt eine Übersicht über den Spielausgang und leitet zum Scoreboard */
    fun refreshAfterGameEnd() {}
}