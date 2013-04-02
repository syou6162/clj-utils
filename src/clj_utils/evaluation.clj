(ns clj-utils.evaluation)

(defn get-accuracy [gold prediction]
  (assert (= (count gold) (count prediction)))
  (let [sum (count (get (group-by (fn [[n1 n2]] (= n1 n2))
                                  (map vector gold prediction))
                        true))]
    (* 1.0 (/ sum (count gold)))))

(defn get-f-value [gold prediction]
  (assert (= (count gold) (count prediction)))
  (let [freq (frequencies (map vector gold prediction))
        tp (get freq [1 1] 0)
        tn (get freq [-1 -1] 0)
        fp (get freq [-1 1] 0)
        fn (get freq [1 -1] 0)]
    (if (or (zero? (+ tp fn))
            (zero? (+ tp fp)))
      Double/NaN
      (let [recall (/ tp (+ tp fn))
            precision (/ tp (+ tp fp))]
        (/ (* 2.0 recall precision)
           (+ recall precision))))))
