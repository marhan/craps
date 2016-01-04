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
* If the Shooter has a Crap, he loses.
* If the Shooter has a Point, he nether wins nor loses. 

## Craps-006
As a user i want to play many rounds to get the Shooter win or fail after all.

### Acceptance Criteria
* If the Shooter rolls a Point in the first round, a new round starts.
* At the second round and after, the rules for scoring are as described below.
* If the Shooter rolls the same roll as he does in the round before, he wins.
* If the Shooter rolls a 7, he loses.
* If the Shooter rolls any other sum, he rolls again.
   
## Craps-007
As a Shooter i want to have the Point as a second phase in the come-out round, 
to set the Point Number for the rounds after.    

### Acceptance Criteria
* If the Shooter has a come-out with a Natural, the 'Pass' line wins and the game is over.
* If the Shooter has a come-out with a Craps, the 'Don't Pass' line loses and the game is over.
* If the Shooter rolls a Point, he will play as many rounds as possible until he rolls a 7.
* The sum of the Point in the come-out round establishes the value the Shooter has to roll again.
* If the Shooter rolls the value of the Point again, the 'Pass' line wins and the game is over. 
* If the Shooter rolls the 7 in the a round after the Point, the 'Don't Pass' wins and the game is over.

## Craps-008
As a user i want that the three players are Shooters in rotation.  

### Acceptance Criteria
* If the first player has finished the game by winning or losing, the second player will play, and so on.
* All three players have been the Shooter, after rotation is finished.
* After a complete rotation the game is over.

## Craps-009  
As a player i want to place a bet on Pass or Don't Pass. 

### Acceptance Criteria
* Pass line bet:
    * If the come-out roll is 7 or 11, the bet wins.
    * If the come-out roll is 2, 3 or 12, the bet loses (known as "crapping out").
    * If the roll is any other value, it establishes a point.
    * If, with a point established, that point is rolled again before a 7, the bet wins.
    * If, with a point established, a 7 is rolled before the point is rolled again ("seven out"), the bet loses.
* Don't Pass
    * If the come-out roll is 2 or 3, the bet wins.
    * If the come-out roll is 7 or 11, the bet loses.
    * If the come-out roll is 12, the bet is a push (neither won nor lost).     
    * If the roll is any other value, it establishes a point.
        * If, with a point established, a 7 is rolled before the point is rolled again ("seven out"), the bet wins.
        * If, with a point established, that point is rolled again before a 7, the bet loses.
* All bets pays even account.
* Every bet will have the amount of ONE.
* A bet will done on every game iteration by every player.
* The shooter will do a Pass line bet.
* The other players will do a Don't pass line bet.

## Craps-010
As a user i want to play 100 games to get the players playing and betting many times.

### Acceptance Criteria
* The following should be configurable:
    * The max number of games.
    * The initial account of the players.  
* The configuration provides the default values below:
    * 100 number of games.
    * 30 units/euro initial account.
* If the number of games reaches maximum, the game is over.
* If the account of any player is insufficient for bet, the game is over.
