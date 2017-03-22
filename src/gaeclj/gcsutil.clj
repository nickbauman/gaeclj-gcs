(ns gaeclj.gcsutil
  (:require [clojure.tools.logging :as log]))

(defmacro try-with-default [default & forms]
  "Tries to do 'forms'. If forms throws an exception, does default."
  `(try 
     (do  ~@forms)
     (catch Exception ~'ex
       (do 
         (log/warn (str "Failed with " (type ~'ex) ": " (.getMessage ~'ex) ". Defaulting to " ~default))
         ~default))))
