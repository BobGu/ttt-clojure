(use 'ttt-clojure.game)

(Given #"^the game has started$" []
       (if (boolean (resolve 'game-start))
         "the game has started"))
