(ns clj-utils.mapping-test
  (:use clojure.test
        clj-utils.mapping))

(def-obj-and-id-mapping word)

(deftest obj-to-id-test
  (are [x y] (= x y)
       0 (word-to-id "a")
       1 (word-to-id "b")
       2 (word-to-id "c")
       0 (word-to-id "a")))
