(ns clj-utils.string-test
  (:use clojure.test
        clj-utils.string))

(deftest levenshtein-distance-test
  (is (= (levenshtein-distance "avada kedavra" "abracadabra")
         7)))
