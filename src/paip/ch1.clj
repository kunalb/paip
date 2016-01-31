(ns paip.ch1
  (:require [clojure.test :refer [deftest is]]))

;;; Exercise 1.1
(def ^:dynamic *suffixes*
  #{'MD 'Jr. 'Sr. 'III})

(defn last-name
  "Extract last name from a list of atoms representing a name, minus suffixes"
  [full-name]
  (->> full-name
       (reverse)
       (drop-while *suffixes*)
       first))

(deftest ex1.1
  (is (= 'C (last-name '(George C III))))
  (is (= 'Morgan (last-name '(Rex Morgan MD))))
  (is (= 'Downey (last-name '(Robert Downey Jr. III)))))


;;; Exercise 1.2
(defn power
  "Reasonable implementation of power."
  [base exp]
  (cond (= 1 exp) base
        (odd? exp) (* base (power base (- exp 1)))
        :else (power (* base base) (/ exp 2))))

(deftest ex1.2
  (is (= 9 (power 3 2)))
  (is (= 32 (power 2 5)))
  (is (= 1024 (power 2 10))))


;;; Exercise 1.3
(def ^:dynamic is-nil-atom? true)

(defn count-atoms
  "Count all the atoms in a list; treats 'nil as an atom, but not ()"
  [atoms]
  (cond (symbol? atoms) 1
        (nil? atoms) (if is-nil-atom? 1 0)
        (list? atoms) (if (empty? atoms) 0
                          (+ (count-atoms (first atoms))
                             (count-atoms (rest atoms))))))

(deftest ex1.3
  (is (= 3 (count-atoms '(a (b) c))))
  (binding [is-nil-atom? true]
    (is (= 3 (count-atoms '(a nil c))))
    (is (= 2 (count-atoms '(a () c)))))
  (binding [is-nil-atom? false]
    (is (= 2 (count-atoms '(a nil c))))
    (is (= 2 (count-atoms '(a () c))))))


;;; Exercise 1.4
(defn count-anywhere
  "Count how amny times an expression occurs within another expression"
  [needle haystack]
  (cond
    (= needle haystack) 1
    (list? haystack) (if (empty? haystack) 0
                             (+ (count-anywhere needle (first haystack))
                                (count-anywhere needle (rest haystack))))
    :else 0))

(deftest ex1.4
  (is (= 0 (count-anywhere 'aleph '())))
  (is (= 3 (count-anywhere nil '(nil nil nil))))
  (is (= 0 (count-anywhere 'a 'b)))
  (is (= 1 (count-anywhere '(a b) '(d e (a b) c))))
  (is (= 3 (count-anywhere 'a '(a ((a b) a))))))


;;; Exercise 1.5
(defn dot-product
  "Dot product of 2 sequences of lists"
  [as bs]
  (if (or (empty? as) (empty? bs)) 0
      (+ (* (first as) (first bs))
         (dot-product (rest as) (rest bs)))))

(deftest ex1.5
  (is (= 110 (dot-product '(10 20) '(3 4))))
  (is (= 0 (dot-product '(10) '()))))
