(ns ttt-clojure.human
  (:require [ttt-clojure.player :refer :all]))

(defprotocol Player
  (fetch-player-name [this]))

(deftype Human []
  Player
  (fetch-player-name [this] "Hello human"))

(defn new-human
  (Human.))

(def human (new-human))
