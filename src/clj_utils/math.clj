(ns clj-utils.math)

(defmacro sum [bindings expr]
  `(reduce + (for ~bindings ~expr)))

(defn count-vector
  "frequencies => count vector"
  ([v max]
     (let [inc-n (fn [i n] (+ i n))]
       (reduce
        (fn [result [k v]] (update-in result [k] inc-n v))
        (vec (repeat max 0))
        (frequencies v))))
  ([v] (count-vector v (inc (apply max v)))))
