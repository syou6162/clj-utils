(ns clj-utils.core)

(defn flip [f x y] (f y x))

;; https://gist.github.com/3378201
(defmacro hash-map-by-names [names]
  (zipmap (map keyword names) names))
