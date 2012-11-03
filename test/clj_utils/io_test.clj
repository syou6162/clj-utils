(ns clj-utils.io-test
  (:use clojure.test
        clj-utils.io)
  (:use [clojure.string :only (split)]))

(deftest serialize-and-deserialize-test
  (let [obj {:a "a" :b [1 2 3]}]
    (with-temp-file [file]
      (do
        (serialize obj file)
        (is (= obj (deserialize file)))))))
