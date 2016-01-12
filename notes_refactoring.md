* Look for ways to improve step definitions (DRY them up, encourage
step-reuse)
* Eliminate need for separate get-player-move funcion.  Make it work with
get-player-input
* Decouple from command line UI
* Dont like the nested if statements in my moves function
* We have a lot of get-player-input in the game class.  Which the game needs
but may not be a responsbility of the game.  It can't really go in the
input class since it prints off output too.  Really it is these are the
interface with how the client interacts with out game.  Going to hold off on
pulling any of these things out and asks Toms opinion.
* Pull out the printing responsibility to a different function
