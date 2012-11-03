(ns clj-utils.math-test
  (:use clojure.test
        clj-utils.math))

(deftest sum-test
  (is (= (sum [x [1 2 3]] (* x x)) 14)))

(deftest count-vector-test
  (are [x y] (= x y)
       (count-vector [1 2 3 4 5 1 1 2] 9) [0 3 2 1 1 1 0 0 0]
       (count-vector [1 2 3 4 5 1 1 2]) [0 3 2 1 1 1]))
