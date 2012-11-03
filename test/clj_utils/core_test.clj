(ns clj-utils.core-test
  (:use clojure.test
        clj-utils.core))

(deftest split-with-ratio-test
  (is (= (split-with-ratio 0.9 (range 10))
         ['(0 1 2 3 4 5 6 7 8) '(9)])))
