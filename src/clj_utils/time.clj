(ns clj-utils.time)

(defn conv-time-to-easily-understandable-time-format
  "(-> (+ 83.0 (* 5 60 60)) conv-time-to-easily-understandable-time-format)
  => 5 hours 1 minutes 23.000 seconds"
  [t]
  (let [[hour r0] ((juxt quot rem) t (* 60 60))
        [minute r1] ((juxt quot rem) r0 (* 60))]
    (->> [(int hour) "hours"
          (int minute) "minutes"
          (format "%3.3f" r1) "seconds"]
         (interpose \space)
         (apply str))))

(defmacro easily-understandable-time
  "(easily-understandable-time (Thread/sleep 1000))
  \"Elapsed time: 0 hours 0 minutes 1.001 seconds\"
  nil"
  [expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr
         result# (/ (double (- (. System (nanoTime)) start#)) 1000000.0)]
     (->> (/ result# 1000)
          (conv-time-to-easily-understandable-time-format)
          (str "Elapsed time: ")
          (prn))
     ret#))
