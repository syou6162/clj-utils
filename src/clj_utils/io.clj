(ns clj-utils.io
  (:require [clojure.java.io :as io]))

(import '(java.io File FileReader))

(defn serialize [o filename]
  (with-open [outp (-> (File. filename) java.io.FileOutputStream. java.io.ObjectOutputStream.)]
    (.writeObject outp o)))

(defn deserialize [filename]
  (with-open [inp (-> (File. filename) java.io.FileInputStream. java.io.ObjectInputStream.)]
    (.readObject inp)))

(defmacro with-temp-file
  [[varname & [content]] & body]
  `(let [~varname (java.io.File/createTempFile "yasuhisay", ".tmp")]
     (when-let [content# ~content]
       (io/copy content# ~varname))
     (let [rv# (do ~@body)]
       (.delete ~varname)
       rv#)))