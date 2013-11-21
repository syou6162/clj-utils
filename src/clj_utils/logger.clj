(ns clj-utils.logger
  (:require [taoensso.timbre :as timbre]))

(import java.text.SimpleDateFormat)

(def ^:dynamic *logging-dir* "logs")

(def ^:dynamic *filename*
  (let [s (-> (.format (SimpleDateFormat. "yyyy-MM-dd-HH-mm-ss")
                       (java.util.Date.)))]
    (str *logging-dir* "/" s ".log")))

(defn init-logger []
  (let [dir (java.io.File. *logging-dir*)]
    (when-not (.exists dir)
      (.mkdir dir)))
  (timbre/set-config!
   [:appenders :standard-out :enabled?] false)
  (timbre/set-config!
   [:appenders :spit :enabled?] true)
  (timbre/set-config!
   [:shared-appender-config :spit-filename] *filename*))
