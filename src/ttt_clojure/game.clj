(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

(defn get-player-input [message validator]
  (let [input (prompt message) valid-input validator]
    (if (valid-input input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message validator)))))

(defn start-game []
  (print instructions)
  (let [first-player-name (get-player-input ask-player-for-name valid-name?)
        turn-order-message (ask-player-for-turn-order first-player-name)]
  (get-player-input ask-player-for-piece valid-piece?)
  (get-player-input ask-player-for-name valid-name?)
  (get-player-input turn-order-message valid-turn-order?)
  (print (ask-player-for-move first-player-name))))
