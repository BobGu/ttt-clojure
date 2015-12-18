(ns ttt-clojure.input)

(defn prompt [message]
  (println message)
  (read-line))
