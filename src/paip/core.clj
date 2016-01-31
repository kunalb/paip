(ns paip.core
  (:require [clojure.test :refer [run-tests]]
            [paip.ch1])
  (:gen-class))

;;; TODO: Make this automatically run all chapters' tests
;;;       without explicitly importing each namespace.

(defn -main
  "Run ALL the tests"
  [& args]
  (run-tests 'paip.ch1))
