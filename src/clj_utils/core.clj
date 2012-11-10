(ns clj-utils.core
  (:use [clojure.core.memoize]))

(defn flip [f x y] (f y x))

;; https://gist.github.com/3378201
(defmacro hash-map-by-names [names]
  (zipmap (map keyword names) names))

(defn split-with-ratio [ratio coll]
  (let [n (count coll)]
    (split-at (* n ratio) coll)))

(defmacro memoize-fn [fn-name fn-args fn-body]
  `(with-local-vars
       [~fn-name (memoize (fn ~fn-args ~fn-body))]
     (.bindRoot ~fn-name @~fn-name)
     @~fn-name))
