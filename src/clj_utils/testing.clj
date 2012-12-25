(ns clj-utils.testing)

(defn bootstrap-test [x y n]
  (letfn [(sum [xs] (reduce + 0.0 xs))
          (mean [xs] (/ (sum xs) (count xs)))
          (sample [xs n] (mapv (fn [_] (rand-nth xs)) (range n)))]
    (let [z (concat x y)
          x-s (map (fn [_] (sample z(count x))) (range n))
          y-s (map (fn [_] (sample z (count y))) (range n))
          theta-s (map (fn [t c] (- (mean t) (mean c))) x-s y-s)]
      (/ (sum (map (fn [s] (if (> s (- (mean x) (mean y))) 1 0)) theta-s))
         n))))
