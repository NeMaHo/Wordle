package view

import service.GameService
import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.*
import java.awt.Color

/**
 *  This class visualizes the game scene.
 */
class GameScene (private val gameService: GameService): BoardGameScene(1320, 1080)
{
    /** pressing this button quits the application */
    val quitButton = Button(width = 150, height = 80, posX = 1160, posY = 10,
        text = "Quit", alignment = Alignment.CENTER, font = Font(size = 35), visual = ColorVisual.RED)

    /** pressing this button initializes a new game */
    private val restartButton = Button(width = 220, height = 80, posX = 1090, posY = 120,
        text = "Restart", alignment = Alignment.CENTER, font = Font(size = 35),
        visual = ColorVisual(164, 225, 220)).apply { onMouseClicked = { refreshAfterRestart() } }

    /** pressing this shows the stats screen */
    val statsButton = Button(width = 180, height = 80, posX = 1130, posY = 230,
        text = "Stats", alignment = Alignment.CENTER, font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply {
            this.isDisabled = true }

    /** shows an error message if the word list doesn't contain the guessed word */
    private var errorLabel = Label(width = 400, height = 80, posX = 700, posY = 400,
        text = "", alignment = Alignment.CENTER, font = Font(size = 45, color = Color.RED))

    /** counter for orientation within the button fields */
    private var counter = 1
    /** list of up to five letters that make up the new guessed word */
    private var pressedKeyStore = mutableListOf<Button>()

    /** fields for the guesses */
    private var oneColOne: Button = Button(width = 90, height = 90, posX = 220, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var twoColOne: Button = Button(width = 90, height = 90, posX = 320, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var threeColOne: Button = Button(width = 90, height = 90, posX = 420, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var fourColOne: Button = Button(width = 90, height = 90, posX = 520, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var fiveColOne: Button = Button(width = 90, height = 90, posX = 620, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    // second line guess
    private var oneColTwo: Button = Button(width = 90, height = 90, posX = 220, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColTwo: Button = Button(width = 90, height = 90, posX = 320, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColTwo: Button = Button(width = 90, height = 90, posX = 420, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColTwo: Button = Button(width = 90, height = 90, posX = 520, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColTwo: Button = Button(width = 90, height = 90, posX = 620, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // third line guess
    private var oneColThree: Button = Button(width = 90, height = 90, posX = 220, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColThree: Button = Button(width = 90, height = 90, posX = 320, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColThree: Button = Button(width = 90, height = 90, posX = 420, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColThree: Button = Button(width = 90, height = 90, posX = 520, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColThree: Button = Button(width = 90, height = 90, posX = 620, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // forth line guess
    private var oneColFour: Button = Button(width = 90, height = 90, posX = 220, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColFour: Button = Button(width = 90, height = 90, posX = 320, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColFour: Button = Button(width = 90, height = 90, posX = 420, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColFour: Button = Button(width = 90, height = 90, posX = 520, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColFour: Button = Button(width = 90, height = 90, posX = 620, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // fifth line guess
    private var oneColFive: Button = Button(width = 90, height = 90, posX = 220, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColFive: Button = Button(width = 90, height = 90, posX = 320, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColFive: Button = Button(width = 90, height = 90, posX = 420, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColFive: Button = Button(width = 90, height = 90, posX = 520, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColFive: Button = Button(width = 90, height = 90, posX = 620, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // sixth line guess
    private var oneColSix: Button = Button(width = 90, height = 90, posX = 220, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColSix: Button = Button(width = 90, height = 90, posX = 320, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColSix: Button = Button(width = 90, height = 90, posX = 420, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColSix: Button = Button(width = 90, height = 90, posX = 520, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColSix: Button = Button(width = 90, height = 90, posX = 620, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }

    /** list of the buttons for the fields */
    private val wordLetterButtons = listOf(oneColOne, twoColOne, threeColOne, fourColOne, fiveColOne,
        oneColTwo, twoColTwo, threeColTwo, fourColTwo, fiveColTwo,
        oneColThree, twoColThree, threeColThree, fourColThree, fiveColThree,
        oneColFour, twoColFour, threeColFour, fourColFour, fiveColFour,
        oneColFive, twoColFive, threeColFive, fourColFive, fiveColFive,
        oneColSix, twoColSix, threeColSix, fourColSix, fiveColSix)

    /** keyboard buttons */
    // first line letters
    private var qButton: Button = Button(width = 70, height = 70, posX = 100, posY = 760,
        text = "Q", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var wButton: Button = Button(width = 70, height = 70, posX = 180, posY = 760,
        text = "W", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var eButton: Button = Button(width = 70, height = 70, posX = 260, posY = 760,
        text = "E", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var rButton: Button = Button(width = 70, height = 70, posX = 340, posY = 760,
        text = "R", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var tButton: Button = Button(width = 70, height = 70, posX = 420, posY = 760,
        text = "T", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var yButton: Button = Button(width = 70, height = 70, posX = 500, posY = 760,
        text = "Y", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var uButton: Button = Button(width = 70, height = 70, posX = 580, posY = 760,
        text = "U", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var iButton: Button = Button(width = 70, height = 70, posX = 660, posY = 760,
        text = "I", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var oButton: Button = Button(width = 70, height = 70, posX = 740, posY = 760,
        text = "O", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var pButton: Button = Button(width = 70, height = 70, posX = 820, posY = 760,
        text = "P", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    // second line letters
    private var aButton: Button = Button(width = 70, height = 70, posX = 140, posY = 840,
        text = "A", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var sButton: Button = Button(width = 70, height = 70, posX = 220, posY = 840,
        text = "S", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var dButton: Button = Button(width = 70, height = 70, posX = 300, posY = 840,
        text = "D", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var fButton: Button = Button(width = 70, height = 70, posX = 380, posY = 840,
        text = "F", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var gButton: Button = Button(width = 70, height = 70, posX = 460, posY = 840,
        text = "G", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var hButton: Button = Button(width = 70, height = 70, posX = 540, posY = 840,
        text = "H", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var jButton: Button = Button(width = 70, height = 70, posX = 620, posY = 840,
        text = "J", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var kButton: Button = Button(width = 70, height = 70, posX = 700, posY = 840,
        text = "K", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var lButton: Button = Button(width = 70, height = 70, posX = 780, posY = 840,
        text = "L", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    // third line letters
    private var zButton: Button = Button(width = 70, height = 70, posX = 180, posY = 920,
        text = "Z", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var xButton: Button = Button(width = 70, height = 70, posX = 260, posY = 920,
        text = "X", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var cButton: Button = Button(width = 70, height = 70, posX = 340, posY = 920,
        text = "C", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var vButton: Button = Button(width = 70, height = 70, posX = 420, posY = 920,
        text = "V", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var bButton: Button = Button(width = 70, height = 70, posX = 500, posY = 920,
        text = "B", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var nButton: Button = Button(width = 70, height = 70, posX = 580, posY = 920,
        text = "N", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    private var mButton: Button = Button(width = 70, height = 70, posX = 660, posY = 920,
        text = "M", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this) } }
    // erase and enter
    private var eraseButton: Button = Button(width = 150, height = 70, posX = 860, posY = 840,
        text = "Erase", alignment = Alignment.CENTER, font = Font(size = 30),
        visual = ColorVisual(255, 215, 250)).apply { this.isDisabled = true
        onMouseClicked = { refreshAfterErase() } }
    private var enterButton: Button = Button(width = 150, height = 70, posX = 740, posY = 920,
        text = "Enter", alignment = Alignment.CENTER, font = Font(size = 30),
        visual = ColorVisual(0, 100, 160)).apply { isDisabled = true
            onMouseClicked = { try{ refreshAfterEnter() }
                catch(error: Exception) { errorLabel.text = error.message.toString() } } }

    /** map of letters (needed for right coloring) */
    private var keyStore = mutableMapOf("Q" to 0, "W" to 0, "E" to 0, "R" to 0, "T" to 0, "Y" to 0, "U" to 0, "I" to 0,
        "O" to 0, "P" to 0, "A" to 0, "S" to 0, "D" to 0, "F" to 0, "G" to 0, "H" to 0, "J" to 0, "K" to 0, "L" to 0,
        "Z" to 0, "X" to 0, "C" to 0, "V" to 0, "B" to 0, "N" to 0, "M" to 0)

    /** list of keyboard letter buttons */
    private val keyButtons = listOf(qButton, wButton, eButton, rButton, tButton, yButton, uButton, iButton, oButton,
        pButton, aButton, sButton, dButton, fButton, gButton, hButton, jButton, kButton, lButton, zButton, xButton,
        cButton, vButton, bButton, nButton, mButton)

    init
    {
        background = ColorVisual(0, 180, 120)
        addComponents(quitButton, restartButton, statsButton, errorLabel,
            oneColOne, twoColOne, threeColOne, fourColOne, fiveColOne,
            oneColTwo, twoColTwo, threeColTwo, fourColTwo, fiveColTwo,
            oneColThree, twoColThree, threeColThree, fourColThree, fiveColThree,
            oneColFour, twoColFour, threeColFour, fourColFour, fiveColFour,
            oneColFive, twoColFive, threeColFive, fourColFive, fiveColFive,
            oneColSix, twoColSix, threeColSix, fourColSix, fiveColSix,
            qButton, wButton, eButton, rButton, tButton, yButton, uButton, iButton, oButton, pButton,
            aButton, sButton, dButton, fButton, gButton, hButton, jButton, kButton, lButton,
            zButton, xButton, cButton, vButton, bButton, nButton, mButton, eraseButton, enterButton)
    }

    /** refreshes the scene after pressing a letter button */
    private fun refreshAfterPressLetter(button: Button)
    {
        // update store of pressed keys
        pressedKeyStore.add(button)
        if (pressedKeyStore.size > 5)
        {
            pressedKeyStore[4] = button
            pressedKeyStore.removeLast()
        }
        // add the letter to the current field
        wordLetterButtons[counter - 1].text = button.text
        // disable buttons or delete error message
        eraseButton.isDisabled = false
        errorLabel.text = ""
        // enable the enter button if the last filled field is rightmost otherwise increase the counter
        if (counter % 5 == 0) enterButton.isDisabled = false
        else counter++
    }

    /** refreshes the scene after pressing erase */
    private fun refreshAfterErase()
    {
        // disable buttons or delete error message
        enterButton.isDisabled = true
        errorLabel.text = ""
        // decrease the counter if the current field is empty and not leftmost
        if ((wordLetterButtons[counter - 1].text == "") && (counter !in listOf(1, 6, 11, 16, 21, 26))) counter--
        // delete field content
        wordLetterButtons[counter - 1].text = ""
        pressedKeyStore.removeLast()
        // disable the button if the current field is leftmost
        if (counter in listOf(1, 6, 11, 16, 21, 26))
        {
            eraseButton.isDisabled = true
            pressedKeyStore.clear()
        }
    }

    /** refreshes the scene after pressing enter */
    private fun refreshAfterEnter()
    {
        // convert the single entered characters to a word
        val guess = (wordLetterButtons[counter - 5].text + wordLetterButtons[counter - 4].text +
            wordLetterButtons[counter - 3].text + wordLetterButtons[counter - 2].text +
            wordLetterButtons[counter - 1].text).lowercase()
        // result of word's evaluation
        try { gameService.evaluateWord(guess) }
        catch (error: Exception) { errorLabel.text = error.message.toString() }

        val evaluateWordResult = gameService.evaluateWord(guess)

        // color guess fields by the result
        for (i in 5 downTo 1) wordLetterButtons[counter - i].visual = evaluateWordResult[5 - i]

        // color keyboard buttons by the result
        for (i in 0 until 5)
        {
            if (keyStore[pressedKeyStore[i].text]!! < colorVisualToInt(evaluateWordResult[i]))
            {
                pressedKeyStore[i].visual = evaluateWordResult[i]
                keyStore[pressedKeyStore[i].text] = colorVisualToInt(evaluateWordResult[i])
            }
        }
        // reset keystore, erase and enter buttons
        pressedKeyStore = mutableListOf()
        enterButton.isDisabled = true
        eraseButton.isDisabled = true

        // check if the game ends
        if (evaluateWordResult.all { it == ColorVisual.GREEN } || counter > 29)
        {
            // disable keyboard buttons
            keyButtons.forEach { it.isDisabled = true }
            // update stats
            gameService.currentGame!!.tryNum = counter / 5
            // when catching an exception the solution will be printed
            try { gameService.endGame(evaluateWordResult.all { it == ColorVisual.GREEN }) }
            catch (error: Exception) { errorLabel.text = error.message.toString() }
            statsButton.isDisabled = false
            statsButton.visual = ColorVisual.PINK
        }
        else
        {
            // highlight the next guess fields
            for (i in 0 until 5) wordLetterButtons[counter + i].visual = ColorVisual.WHITE
            counter++
        }
    }

    /** refreshes all buttons and starts a new game */
    private fun refreshAfterRestart()
    {
        // start a new game and reset all buttons and counters
        val game = gameService.currentGame!!
        gameService.startNewGame(game.player, game.language, game.type)
        counter = 1
        pressedKeyStore = mutableListOf()
        keyButtons.forEach { it.isDisabled = false }
        keyButtons.forEach { it.visual = ColorVisual.WHITE }
        for (key in keyStore.keys) keyStore[key] = 0
        wordLetterButtons.forEach { it.text = "" }
        wordLetterButtons.forEach { it.visual = ColorVisual.WHITE }
        enterButton.isDisabled = true
        eraseButton.isDisabled = true
        statsButton.isDisabled = true
        statsButton.visual = ColorVisual.LIGHT_GRAY
        errorLabel.text = ""
    }

    /** help function to get the value of a ColorVisual */
    private fun colorVisualToInt(color: ColorVisual): Int
    {
        if(color == ColorVisual.WHITE) return 0
        if(color == ColorVisual.GRAY) return 1
        return if(color == ColorVisual.YELLOW) 2
        else 3
    }
}