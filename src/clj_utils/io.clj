(ns clj-utils.io
  (:require [clojure.java.io :as io]))

(import '(java.io File FileReader))

(defmulti serialize (fn [o file-lile] (type file-lile)))

(defmethod serialize File [o ^File file]
  (with-open [outp (-> file java.io.FileOutputStream.
                       java.io.ObjectOutputStream.)]
    (.writeObject outp o)))

(defmethod serialize String [o ^String filename]
  (serialize o (File. filename)))

(defmulti deserialize (fn [file-lile] (type file-lile)))

(defmethod deserialize File [^File file]
  (with-open [inp (-> file java.io.FileInputStream.
                      java.io.ObjectInputStream.)]
    (.readObject inp)))

(defmethod deserialize String [^String filename]
  (deserialize (File. filename)))

(defmacro with-temp-file
  [[varname & [content]] & body]
  `(let [~varname (java.io.File/createTempFile "yasuhisay", ".tmp")]
     (when-let [content# ~content]
       (io/copy content# ~varname))
     (let [rv# (do ~@body)]
       (.delete ~varname)
       rv#)))