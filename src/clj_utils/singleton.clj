(ns clj-utils.singleton)
; ref https://github.com/clojure/clojure-contrib/blob/master/modules/singleton/src/main/clojure/clojure/contrib/singleton.clj

(defn global-singleton
  "Returns a global singleton function.  f is a function of no
  arguments that creates and returns some object.  The singleton
  function will call f just once, the first time it is needed, and
  cache the value for all subsequent calls.

  Warning: global singletons are often unsafe in multi-threaded code.
  Consider per-thread-singleton instead."
  [f]
  (let [instance (atom nil)
        make-instance (fn [_] (f))]
    (fn [] (or @instance (swap! instance make-instance)))))

(defn per-thread-singleton
  "Returns a per-thread singleton function.  f is a function of no
  arguments that creates and returns some object.  The singleton
  function will call f only once for each thread, and cache its value
  for subsequent calls from the same thread.  This allows you to
  safely and lazily initialize shared objects on a per-thread basis.

  Warning: due to a bug in JDK 5, it may not be safe to use a
  per-thread-singleton in the initialization function for another
  per-thread-singleton.  See
  http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=5025230"
  [f]
  (let [thread-local (proxy [ThreadLocal] [] (initialValue [] (f)))]
    (fn [] (.get thread-local))))
