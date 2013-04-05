(ns clj-utils.collection)

(defn find-first [pred coll]
  (first (filter pred coll)))
