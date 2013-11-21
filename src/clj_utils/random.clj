(ns clj-utils.random)

(def ^:private random-with-fixed-seed
  (let [seed 12345]
    (new java.util.Random seed)))

(defn shuffle-with-random
  ([random ^java.util.Collection coll]
     (let [arrayList (java.util.ArrayList. coll)]
       (java.util.Collections/shuffle arrayList random)
       (clojure.lang.RT/vector (.toArray arrayList))))
  ([coll]
     (shuffle-with-random random-with-fixed-seed coll)))
