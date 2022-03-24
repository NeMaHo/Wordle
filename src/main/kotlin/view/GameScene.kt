package view

import service.GameService
import tools.aqua.bgw.components.uicomponents.*
import tools.aqua.bgw.core.*
import tools.aqua.bgw.util.*
import tools.aqua.bgw.visual.*

/**
 *  This class visualizes the game scene.
 */
class GameScene (private val gameService: GameService): BoardGameScene(1920, 1080)
{
    /** counter for orientation within the button fields */
    private var counter = 1
    /** list of up to five letters that make up the new guessed word */
    private var pressedKeyStore = mutableListOf<Button>()

    /** pressing this button quits the application */
    val quitButton = Button(width = 150, height = 80, posX = 1760, posY = 10,
        text = "Quit", alignment = Alignment.CENTER, font = Font(size = 35), visual = ColorVisual.RED)

    /** pressing this button initializes a new game */
    private val restartButton = Button(width = 220, height = 80, posX = 1690, posY = 130,
        text = "Restart", alignment = Alignment.CENTER, font = Font(size = 35),
        visual = ColorVisual(164, 225, 220)).apply { onMouseClicked = { refreshAfterRestart() } }

    /** pressing this shows the stats screen */
    val statsButton = Button(width = 180, height = 80, posX = 1730, posY = 240,
        text = "Stats", alignment = Alignment.CENTER, font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply {
        this.isDisabled = true }

    /** fields for the guesses */
    private var oneColOne: Button = Button(width = 90, height = 90, posX = 600, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var twoColOne: Button = Button(width = 90, height = 90, posX = 700, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var threeColOne: Button = Button(width = 90, height = 90, posX = 800, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var fourColOne: Button = Button(width = 90, height = 90, posX = 900, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    private var fiveColOne: Button = Button(width = 90, height = 90, posX = 1000, posY = 50,
        font = Font(size = 35)).apply { this.isFocusable = false }
    // second line guess
    private var oneColTwo: Button = Button(width = 90, height = 90, posX = 600, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColTwo: Button = Button(width = 90, height = 90, posX = 700, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColTwo: Button = Button(width = 90, height = 90, posX = 800, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColTwo: Button = Button(width = 90, height = 90, posX = 900, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColTwo: Button = Button(width = 90, height = 90, posX = 1000, posY = 160,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // third line guess
    private var oneColThree: Button = Button(width = 90, height = 90, posX = 600, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColThree: Button = Button(width = 90, height = 90, posX = 700, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColThree: Button = Button(width = 90, height = 90, posX = 800, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColThree: Button = Button(width = 90, height = 90, posX = 900, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColThree: Button = Button(width = 90, height = 90, posX = 1000, posY = 270,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // forth line guess
    private var oneColFour: Button = Button(width = 90, height = 90, posX = 600, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColFour: Button = Button(width = 90, height = 90, posX = 700, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColFour: Button = Button(width = 90, height = 90, posX = 800, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColFour: Button = Button(width = 90, height = 90, posX = 900, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColFour: Button = Button(width = 90, height = 90, posX = 1000, posY = 380,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // fifth line guess
    private var oneColFive: Button = Button(width = 90, height = 90, posX = 600, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColFive: Button = Button(width = 90, height = 90, posX = 700, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColFive: Button = Button(width = 90, height = 90, posX = 800, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColFive: Button = Button(width = 90, height = 90, posX = 900, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColFive: Button = Button(width = 90, height = 90, posX = 1000, posY = 490,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    // sixth line guess
    private var oneColSix: Button = Button(width = 90, height = 90, posX = 600, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var twoColSix: Button = Button(width = 90, height = 90, posX = 700, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var threeColSix: Button = Button(width = 90, height = 90, posX = 800, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fourColSix: Button = Button(width = 90, height = 90, posX = 900, posY = 600,
        font = Font(size = 35), visual = ColorVisual.LIGHT_GRAY).apply { this.isFocusable = false }
    private var fiveColSix: Button = Button(width = 90, height = 90, posX = 1000, posY = 600,
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
    private var qButton: Button = Button(width = 70, height = 70, posX = 480, posY = 760,
        text = "Q", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var wButton: Button = Button(width = 70, height = 70, posX = 560, posY = 760,
        text = "W", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var eButton: Button = Button(width = 70, height = 70, posX = 640, posY = 760,
        text = "E", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var rButton: Button = Button(width = 70, height = 70, posX = 720, posY = 760,
        text = "R", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var tButton: Button = Button(width = 70, height = 70, posX = 800, posY = 760,
        text = "T", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var yButton: Button = Button(width = 70, height = 70, posX = 880, posY = 760,
        text = "Y", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var uButton: Button = Button(width = 70, height = 70, posX = 960, posY = 760,
        text = "U", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var iButton: Button = Button(width = 70, height = 70, posX = 1040, posY = 760,
        text = "I", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var oButton: Button = Button(width = 70, height = 70, posX = 1120, posY = 760,
        text = "O", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var pButton: Button = Button(width = 70, height = 70, posX = 1200, posY = 760,
        text = "P", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    // second line letters
    private var aButton: Button = Button(width = 70, height = 70, posX = 520, posY = 840,
        text = "A", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var sButton: Button = Button(width = 70, height = 70, posX = 600, posY = 840,
        text = "S", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var dButton: Button = Button(width = 70, height = 70, posX = 680, posY = 840,
        text = "D", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var fButton: Button = Button(width = 70, height = 70, posX = 760, posY = 840,
        text = "F", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var gButton: Button = Button(width = 70, height = 70, posX = 840, posY = 840,
        text = "G", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var hButton: Button = Button(width = 70, height = 70, posX = 920, posY = 840,
        text = "H", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var jButton: Button = Button(width = 70, height = 70, posX = 1000, posY = 840,
        text = "J", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var kButton: Button = Button(width = 70, height = 70, posX = 1080, posY = 840,
        text = "K", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var lButton: Button = Button(width = 70, height = 70, posX = 1160, posY = 840,
        text = "L", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    // third line letters
    private var zButton: Button = Button(width = 70, height = 70, posX = 560, posY = 920,
        text = "Z", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var xButton: Button = Button(width = 70, height = 70, posX = 640, posY = 920,
        text = "X", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var cButton: Button = Button(width = 70, height = 70, posX = 720, posY = 920,
        text = "C", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var vButton: Button = Button(width = 70, height = 70, posX = 800, posY = 920,
        text = "V", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var bButton: Button = Button(width = 70, height = 70, posX = 880, posY = 920,
        text = "B", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var nButton: Button = Button(width = 70, height = 70, posX = 960, posY = 920,
        text = "N", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    private var mButton: Button = Button(width = 70, height = 70, posX = 1040, posY = 920,
        text = "M", alignment = Alignment.CENTER, font = Font(size = 30), visual = ColorVisual.WHITE).apply {
            onMouseClicked = { refreshAfterPressLetter(this.text)
            pressedKeyStore.add(this) } }
    // erase and enter
    private var eraseButton: Button = Button(width = 150, height = 70, posX = 1240, posY = 840,
        text = "Erase", alignment = Alignment.CENTER, font = Font(size = 30),
        visual = ColorVisual(255, 215, 250)).apply { this.isDisabled = true
        onMouseClicked = { refreshAfterErase() } }
    private var enterButton: Button = Button(width = 150, height = 70, posX = 1120, posY = 920,
        text = "Enter", alignment = Alignment.CENTER, font = Font(size = 30),
        visual = ColorVisual(0, 100, 160)).apply { isDisabled = true
            onMouseClicked = { refreshAfterEnter() } }

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
        addComponents(quitButton, restartButton, statsButton,
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
    private fun refreshAfterPressLetter(letter: String)
    {
        wordLetterButtons[counter - 1].text = letter
        eraseButton.isDisabled = false

        if (counter % 5 == 0)
        {
            enterButton.isDisabled = false
        }
        else
        {
            counter++
        }
    }

    /** refreshes the scene after pressing erase */
    private fun refreshAfterErase()
    {
        enterButton.isDisabled = true
        if (counter in listOf(1, 6, 11, 16, 21, 26))
        {
            eraseButton.isDisabled = true
            if (wordLetterButtons[counter - 1].text != "")
                wordLetterButtons[counter - 1].text = ""
        }
        else
        {
            wordLetterButtons[counter - 1].text = ""
            counter--
        }
        if (pressedKeyStore.isNotEmpty()) pressedKeyStore.removeLast()
    }

    /** refreshes the scene after pressing enter */
    private fun refreshAfterEnter()
    {
        // convert the single entered characters to a word
        val guess = (wordLetterButtons[counter - 5].text + wordLetterButtons[counter - 4].text +
            wordLetterButtons[counter - 3].text + wordLetterButtons[counter - 2].text +
            wordLetterButtons[counter - 1].text).lowercase()

        // result of word's evaluation
        val evaluateWordResult = gameService.evaluateWord(guess)

        // color guess fields by the result
        for (i in 5 downTo 1)
        {
            wordLetterButtons[counter - i].visual = evaluateWordResult[5 - i]
        }

        // color keyboard buttons by the result
        for (i in 0 until 5)
        {
            if(keyStore[pressedKeyStore[i].text]!! < colorVisualToInt(evaluateWordResult[i]))
            {
                pressedKeyStore[i].visual = evaluateWordResult[i]
                keyStore[pressedKeyStore[i].text] = colorVisualToInt(evaluateWordResult[i])
            }
        }

        pressedKeyStore = mutableListOf()
        enterButton.isDisabled = true
        eraseButton.isDisabled = true

        if(evaluateWordResult.all { it == ColorVisual.GREEN } || counter > 29 )
        {
            // disable keyboard buttons
            keyButtons.forEach { it.isDisabled = true }
            // update stats
            gameService.currentGame!!.tryNum = counter.div(5)
            gameService.endGame(evaluateWordResult.all { it == ColorVisual.GREEN })
            statsButton.isDisabled = false
            statsButton.visual = ColorVisual.PINK
        }
        else
        {
            // highlight the next guess fields
            for (i in 0 until 5)
            {
                wordLetterButtons[counter + i].visual = ColorVisual.WHITE
            }
            counter++
        }
    }

    /** refreshes all buttons and starts a new game */
    private fun refreshAfterRestart()
    {
        val game = gameService.currentGame!!
        gameService.startNewGame(game.player, game.language)
        counter = 1
        pressedKeyStore = mutableListOf()
        keyButtons.forEach { it.isDisabled = false }
        keyButtons.forEach { it.visual = ColorVisual.WHITE }
        for (key in keyStore.keys)
        {
            keyStore[key] = 0
        }
        wordLetterButtons.forEach { it.text = "" }
        wordLetterButtons.forEach { it.visual = ColorVisual.WHITE }
        enterButton.isDisabled = true
        eraseButton.isDisabled = true
        statsButton.isDisabled = true
        statsButton.visual = ColorVisual.LIGHT_GRAY
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