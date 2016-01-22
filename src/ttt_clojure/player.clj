(ns ttt-clojure.player)

(defprotocol Player
  (get-piece [this])
  (get-name [this])
  (get-move [this name board]))

