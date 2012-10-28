(ns clj-utils.core)

(defn flip [f x y] (f y x))

;; https://gist.github.com/3378201
(defmacro hash-map-by-names [names]
  (zipmap (map keyword names) names))

(defn split-with-ratio [ratio coll]
  (let [n (count coll)]
    (split-at (* n ratio) coll)))
