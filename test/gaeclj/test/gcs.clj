(ns gaeclj.test.gcs
    (:require [gaeclj.env :as env]
              [gaeclj.gcs :as gcs]            
              [gaeclj.test.helpers :as helper]
              [gaeclj.test.fixtures :as fixtures]
              [gaeclj.util :refer [try-with-default]]
              [clojure.data.json :as json]
              [clojure.java.io :as io]
              [clj-time.core :as t])
    (:use clojure.test))

(use-fixtures :once fixtures/setup-local-service-test-helper)

(defn- has-content? [response query]
  (>= (.indexOf (:body response) query) 0))

(deftest test-app
  (testing "store and read file in google cloud storage"
    (let [expected-filename "test/gaeclj/test/file_example.jpg"
          temp-file (helper/create-temp-file expected-filename)
          file-data (helper/get-file-contents (.getAbsolutePath temp-file))

          _ (gcs/with-gcs-output-stream gcs-writer env/gcs-bucket-name expected-filename
            (.write gcs-writer file-data))

          input-channel (gcs/open-input-channel env/gcs-bucket-name expected-filename)
          actual-filecontents (try-with-default "Not Found!" (slurp (gcs/to-input-stream input-channel)))]
      (is (= (String. file-data) actual-filecontents)))))
