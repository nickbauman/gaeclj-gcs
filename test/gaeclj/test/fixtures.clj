(ns gaeclj.test.fixtures
(:import [com.google.appengine.tools.development.testing 
          LocalServiceTestConfig 
          LocalServiceTestHelper 
          LocalBlobstoreServiceTestConfig])
(:require [clojure.java.io :as io])
(:use clojure.test
      gaeclj.gcs))

(defn delete-recursively [fname]
  (let [func (fn [func f]
               (when (.isDirectory f)
                 (doseq [f2 (.listFiles f)]
                   (func func f2)))
               (io/delete-file f))]
    (func func (io/file fname))))

(defn- blobstore-config []
  (doto 
      (LocalBlobstoreServiceTestConfig.) 
    (.setNoStorage true)))

(defn- create-local-test-helper []
  (LocalServiceTestHelper. (into-array LocalServiceTestConfig [(blobstore-config)])))

(defn setup-local-service-test-helper [f] 
  (let [helper (create-local-test-helper)]
    (try 
      (.setUp helper)
      (f)
      (finally 
        (.tearDown helper)))))
