(ns ttt-clojure.spec-helper)

(defn contains-all? [results expected-results]
  (every? (fn [x] (some #(= x %) results)) expected-results))

(defn clock-speed [f]
  (clojure.string/trim (with-out-str (time (f)))))

(defn find-milliseconds [time-string]
  (read-string (clojure.string/replace time-string #"[^0-9.]+" "")))

(defn default-input []
  #(fn [] (read-line)))

(defn default-output []
  #(fn [string] (println string)))
