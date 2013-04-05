(ns clj-utils.collection-test
  (:use clojure.test
        clj-utils.collection))

(deftest find-first-test
  (is (= (find-first #(> % 3) [1 2 3 4 5])
         4)))
