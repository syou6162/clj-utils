(ns clj-utils.random-test
  (:use clojure.test
        clj-utils.random))

(deftest shuffle-with-random-test
  (let [seed 12345
        random (new java.util.Random seed)
        numbers (range 10)]
    (is (= (shuffle-with-random random numbers)
           [3 2 0 5 8 9 6 7 4 1]))
    (is (= (shuffle-with-random numbers)
           [3 2 0 5 8 9 6 7 4 1]))))
