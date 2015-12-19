(ns ttt-clojure.validate-input)

(defn valid-name? [name]
  (= true (> (count name) 0) (< (count name) 26)))
