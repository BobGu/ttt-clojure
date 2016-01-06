(ns ttt-clojure.validate-input)
(use 'clojure.string)


(defn valid-name? [name]
  (= true (> (count name) 0) (< (count name) 26)))

(defn valid-piece? [piece]
  (let [piece (trim (upper-case piece))]
  (or (= piece "X") (= piece "O"))))

(defn valid-turn-order? [input]
  (or (= input "1") (= input "2")))

(defn valid-move? [space board]
  (some #(= space %) board))
