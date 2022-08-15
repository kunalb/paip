;; Tail call check: fails with an error
(defn tailcallcheck [val]
  (when (!= 0 val)
    (tailcallcheck (- val 1))))

(tailcallcheck 1001)
