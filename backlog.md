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
* Is the result 7 or 11, the player has a Natural.
* Is the result 2, 3 or 12, the player has a Crap.
* Is the result 4, 5, 6, 8, 9 or 10, the player has a Point.

## Craps-005
As a user i want to play one round to get the Shooter win or fail, if he has a Natural or a Crap.

### Acceptance Criteria
* Three players will play the game.
* The player who rolls the dices is named as Shooter.
* If the Shooter has a Natural, he wins.
* If the Shooter has a Crap, he lose.
* If the Shooter has a Point, he nether wins nor lose. 

## Craps-006
As a user i want to play many rounds to get the Shooter win or fail after all.

### Acceptance Criteria
* If the Shooter rolls a Point in the first round, a new round starts.
* After the first round the rules of play differs as described below.
* If the Shooter rolls the same roll as he does in the first round, he wins.
* If the Shooter rolls a 7, he lose.
* If the Shooter rolls any other roll, he rolls again.   