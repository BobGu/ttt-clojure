(defproject ttt-clojure "0.1.0-SNAPSHOT"
  :description "tic-tac-toe using clojure"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-RC2"]
                 [info.cukes/cucumber-clojure "1.2.4"]
                 [bronsa/colorize "0.2.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.1"]]}}
  :plugins [[speclj "3.3.1"]
            [org.clojars.punkisdead/lein-cucumber "1.0.4"]]
  :test-paths ["spec"])
