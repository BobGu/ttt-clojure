(ns ttt-clojure.player)

(defprotocol Player
  (fetch-player-name [this]))

