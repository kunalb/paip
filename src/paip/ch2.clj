(ns paip.ch2
  (:require [clojure.test :refer [deftest is]]))

;;; Exercise 2.1

(def ^:private simple-grammar
  "Trivial grammar"
  (array-map
   :sentence '((:noun-phrase :verb-phrase))
   :noun-phrase '((:article :noun))
   :verb-phrase '((:verb :noun-phrase))
   :article '(the a)
   :noun '(man ball woman table pokemon)
   :verb '(hit took saw liked caught ())))

(def ^:private ^:dynamic *grammar*
  simple-grammar)

(defn- rewrites
  "Possible rewrites for the given category"
  [category]
  (get *grammar* category))

(defn- generate
  "Generate a random sentence, applying rewrites only once"
  [phrase]
  (if (list? phrase) (apply concat (map generate phrase))
      (let [options (rewrites phrase)]
        (if (not options) (list phrase)
            (generate (rand-nth options))))))

;;; Exercise 2.2

(defn- terminal?
  "Determines if the given word is a terminal in the grammar"
  [word]
  (not (rewrites word)))

(defn- generate-v2
  "Generates a random sentence, explicitly considering non-terminals separately"
  [phrase]
  (cond (list? phrase) (apply concat (map generate-v2 phrase))
        (terminal? phrase) (list phrase)
        :default (generate (rand-nth (rewrites phrase)))))


;;; Exercise 2.3

(def ^:private sexp-grammar
  "Simplistic sexp grammar with an attempted kleene *"
  (array-map
   :sexp '((\( :fn :atoms+ \)) (\( :fn :sexp \)))
   :atoms+ '((:atom :atoms*))
   :atoms* '((:atom :atoms*) ())
   :fn '(map mappend concat cond)
   :atom '(1 2 3 a b c)))

;;; Exercise 2.4

(defn- cross-product
  "Calculate the cross product of 2 lists with a custom function"
  [f as bs]
  (apply concat (map (fn [a] (map (fn [b] (f a b)) bs)) as)))

(defn- combine-all
  [xlist ylist]
  (cross-product concat xlist ylist))

(deftest ex2.4
  (is (= '(3 4 6 8) (cross-product * '(1 2) '(3 4))))
  (is (= '((a 1) (a 2) (b 1) (b 2)) (combine-all '((a) (b)) '((1) (2))))))
