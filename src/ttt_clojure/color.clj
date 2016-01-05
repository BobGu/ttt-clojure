(ns ttt-clojure.color)
(require '[colorize.ansi :refer [colorize]])

(defn red [string]
  (colorize string {:fg :red}))

(defn yellow [string]
  (colorize string {:fg :yellow}))

(defn blue [string]
  (colorize string {:fg :blue}))

(defn green [string]
  (colorize string {:fg :green}))

