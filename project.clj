(defproject paip "0.1.0-SNAPSHOT"
  :description "Principles of Artificial Intelligence Exercises in Clojure"
  :url "https://github.com/kunalb/paip"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot paip.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
