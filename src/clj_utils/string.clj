(ns clj-utils.string)

(defn levenshtein-distance
  "Calculates the edit-distance between two sequences"
  [seq1 seq2]
  (cond
   (empty? seq1) (count seq2)
   (empty? seq2) (count seq1)
   :else (min
          (+ (if (= (first seq1) (first seq2)) 0 1)
             (#'levenshtein-distance (rest seq1) (rest seq2))) 
          (inc (#'levenshtein-distance (rest seq1) seq2))      
          (inc (#'levenshtein-distance seq1 (rest seq2))))))

(def levenshtein-distance (memoize levenshtein-distance))
