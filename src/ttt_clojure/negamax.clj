(ns ttt-clojure.negamax
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.game :refer :all]))


(defn score [board]
  (if (game-won? board) 10 0))

(defn negamax
  ([piece board index](negamax piece board index 1))
  ([piece board index color]
  (let [board (update-board board piece index)
        spaces (spaces-available board)]
    (if (game-over? board)
      (score board)
      ( - (negamax (opposite-piece piece) board (first spaces) (- color)))))))

(defn score-map [piece board]
  (let [score-map (sorted-map)
        spaces (spaces-available board)
        scored-spaces (map #(list % (negamax piece board %)) spaces)]
    scored-spaces))

(defn get-move [piece board]
  (let [scores (score-map piece board)]
    (take 1 (last (vals scores)))))
