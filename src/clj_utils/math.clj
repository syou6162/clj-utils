(ns clj-utils.math)

(defmacro sum [bindings expr]
  `(reduce + (for ~bindings ~expr)))
