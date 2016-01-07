1.) Look for ways to improve step definitions (DRY them up, encourage
step-reuse)
2.) Eliminate need for separate get-player-move funcion.  Make it work with
get-player-input
3.) Decouple from command line UI
5.) Nothing specific jumps out to me about how to change the length of the
start-game.  We just have a lot of setup that we then pass along to moves.  We
could wrap what we have and move it to a different function we call game setup
that returns us this vector of maps.
6.) I think refactoring the above setup will change our moves function, which
could also use some refactoring, don't really like the nested if within our
moves function.
7.) We have a lot of get-player-input in the game class.  Which the game needs
but may not be a responsbility of the game.  It can't really go in the
input class since it prints off output too.  Really it is these are the
interface with how the client interacts with out game.  Going to hold off on
pulling any of these things out and asks Toms opinion.
8.) Valid moves needs to factor something besides numbers
9.) UI could use some cleaning up.  Confirmation that message were recieved.
New lines after error messages.
