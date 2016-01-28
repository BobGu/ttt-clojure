(ns ttt-clojure.player)

(defprotocol Player
  (get-piece [this])
  (get-name [this])
  (get-strategy [this])
  (get-move [this name board input output]))

