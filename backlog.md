# Story backlog
===============================. 

## Craps-001 (technical)
As a user i need an user interface to interact with the application.

## Craps-002 (technical)
As a developer i want that a build server is triggered after push to be informed about a broken build.

## Craps-003
As a user i want to start the game to roll two dices in one single round.

### Acceptance Criteria
* The game can be started without any arguments via the terminal.
* The value of the dices will be printed to the terminal.
* The printed value is the sum of both dices.

## Craps-004
As a user i want to start the game to play it with one player one round automatically. 
 
### Acceptance Criteria
* The player plays one round.
* The value of the dices is random and will be different each start of the game.
* The result will be printed to the terminal.
* Is the result 7 or 11, the player wins.
* Is the result 2,3 or 12, the player lose.
* Any other value will be printed out without win or lose.