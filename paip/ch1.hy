;;; 1.11 Exercises

(import hyrule)

;; 1.1[m]
(defn last-name [name]
  (let [suffixes #{"MD" "Jr." "Sr." "IIIrd"}]
    (next (filter (fn [x] (not (in (str x) suffixes))) (reversed name)))))

(assert (= (last-name '(John Doe Sr.)) 'Doe))
(assert (= (last-name '(John Doe MD)) 'Doe))
(assert (= (last-name '(John Doe)) 'Doe))

;; 1.2[m]
(defn power [num exp]
  (cond
    (= 0 exp) 1
    (& exp 1) (* num (power num (- exp 1)))
    True (let [val (power num (// exp 2))] (* val val))))

(assert (= (power 2 0) 1))
(assert (= (power 2 3) 8))
(assert (= (power 2 4) 16))
(assert (= (power 7 3) 343))
