package service

import view.Refreshable

/**
 *  Class for working with refreshables.
 */
abstract class AbstractRefreshingService
{
    private val refreshables = mutableListOf<Refreshable>()

    /**
     *  adds a Refreshable to the list that gets called
     *  whenever onAllRefreshables is used.
     */
    fun addRefreshable(newRefreshable : Refreshable) {
        refreshables += newRefreshable
    }

    /**
     *  Executes the passed method (usually a lambda) on all Refreshables registered with the service class that
     *  extends this AbstractRefreshingService
     */
    fun onAllRefreshables(method: Refreshable.() -> Unit) =
        refreshables.forEach { it.method() }
}