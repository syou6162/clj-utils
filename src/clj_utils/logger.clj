(ns clj-utils.logger
  (:require [taoensso.timbre :as timbre]))

(import java.text.SimpleDateFormat)

(def ^:dynamic *logging-dir* "logs")

(def ^:dynamic *filename*
  (let [s (-> (.format (SimpleDateFormat. "yyyy-MM-dd-HH-mm-ss")
                       (java.util.Date.)))]
    (str *logging-dir* "/" s ".log")))

(let [dir (java.io.File. ^String *logging-dir*)]
  (when-not (.exists dir)
    (.mkdir dir)))

(timbre/set-config!
 [:appenders :standard-out :enabled?] true)

(timbre/set-config!
 [:appenders :standard-out :fn]
 (fn [{:keys [error? throwable message]}]
   (binding [*out* (if error? *err* *out*)]
     (timbre/str-println message (timbre/stacktrace throwable)))))

(timbre/set-config!
 [:appenders :spit :enabled?] true)

(timbre/set-config!
 [:shared-appender-config :spit-filename] *filename*)
