(ns clj-utils.core-test
  (:use clojure.test
        clj-utils.core)
  (:use [clojure.string :only (split)]))

(deftest flip-test
  (let [s "a b c"]
    (is (= (split s #"\s")
           (flip split #"\s" s)))))

(deftest hash-map-by-names-test
  (is (= (let [x :x, y :y]
           (hash-map-by-names [x y]))
         {:y :y, :x :x})))

(deftest split-with-ratio-test
  (is (= (split-with-ratio 0.9 (range 10))
         ['(0 1 2 3 4 5 6 7 8) '(9)])))
