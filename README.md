# Wordle

In this repository you can find my implementation of the game Wordle (https://en.wikipedia.org/wiki/Wordle). Kotlin is used as the programming language and the BoardGameWork framework (https://github.com/tudo-aqua/bgw) is used for visualization. The word lists are processed using R and are originally from https://github.com/dwyl/english-words/blob/master/words.txt (english) and https://gist.github.com/MarvinJWendt/2f4f4154b8ae218600eb091a5706b5f4#file-wordlist-german-txt) (german).

## Design

This class diagram shows the structure of the program.

<img src="README pictures/class diagram.png" alt="README pictures/class diagram.png" width="400" height="350"/>

## How to play

To be able to start a game, the name field must first be filled out. The player's game statistics are saved in a text file under the entered name, so that future games can contribute to the statistics even after the applications have been closed. The choices of the language and the occurrence of (multiple equal) letters determine which word list is used in the game.

<img src="README pictures/menu scene.png" alt="README pictures/menu scene.png" width="240" height="200"/>

Gameplay and coloring are the same as the original game (green: correct guess, yellow: wrong position, grey: not included) with the only difference that excess repeating letters are colored yellow if the solution word contains the specific letter.

The player has the option to restart the game at any time with a new solution word. Only completed games are included in the statistics, which cannot be viewed before ending a game.

<img src="README pictures/game scene 1.png" alt="README pictures/game scene 1.png" width="240" height="200"/> <img src="README pictures/game scene 2.png" alt="README pictures/game scene 2.png" width="240" height="200"/>

To see the numbers of the statistics a label must be clicked on. This is (hopefully) just a temporary way of displaying them.

<img src="README pictures/stats scene.png" alt="README pictures/stats scene.png" width="240" height="200"/>