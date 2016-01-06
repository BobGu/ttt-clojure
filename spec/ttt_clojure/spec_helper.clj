(ns ttt-clojure.spec-helper)

(defn contains-all? [results expected-results]
  (every? (fn [x] (some #(= x %) results)) expected-results))
