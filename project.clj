(def appengine-version "2.0.20")

(defproject gaeclj-gcs "0.1.3"
  :description "Clojure library for working with Google Cloud Storage API in App Engine"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :min-lein-version "2.6.0"
  :url "https://github.com/nickbauman/gaeclj-gcs"
  :javac-options ["-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-time "0.13.0"]
                 [ch.qos.logback/logback-classic "1.1.8"]
                 [com.google.guava/guava "20.0"]
                 [com.google.appengine/appengine-api-1.0-sdk ~appengine-version]
                 [com.google.appengine.tools/appengine-gcs-client "0.6" 
                  :exclusions [com.google.api-client/google-api-client 
                               com.google.appengine/appengine-api-1.0-sdk 
                               com.google.guava/guava 
                               com.google.http-client/google-http-client-jackson2 
                               com.google.api-client/google-api-client-appengine 
                               com.google.http-client/google-http-client]]
                 [org.apache.httpcomponents/httpclient "4.5.2"]
                 [com.google.api-client/google-api-client-appengine "1.22.0"
                  :exclusions [com.google.guava/guava-jdk5]]
                 [com.google.oauth-client/google-oauth-client-appengine "1.22.0"
                  :exclusions [com.google.guava/guava-jdk5]]       
                 [com.google.http-client/google-http-client-appengine "1.22.0"
                  :exclusions [com.google.guava/guava-jdk5]]]
  :java-source-paths ["src-java"]
  :aot :all
  :profiles
  {:dev
   {:dependencies [[com.google.appengine/appengine-testing ~appengine-version]
                   ;[com.google.appengine/appengine-api-labs ~appengine-version]
                   [com.google.appengine/appengine-api-stubs ~appengine-version]
                   [com.google.appengine/appengine-tools-sdk ~appengine-version]]}})
