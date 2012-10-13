(ns clj-utils.io)

(import '(java.io File FileReader))

(defn serialize [o filename]
  (with-open [outp (-> (File. filename) java.io.FileOutputStream. java.io.ObjectOutputStream.)]
    (.writeObject outp o)))

(defn deserialize [filename]
  (with-open [inp (-> (File. filename) java.io.FileInputStream. java.io.ObjectInputStream.)]
    (.readObject inp)))
