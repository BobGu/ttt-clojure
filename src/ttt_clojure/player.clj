(ns ttt-clojure.player)

(defprotocol Player
  (fetch-player-name [this])
  (fetch-player-piece [this]))

