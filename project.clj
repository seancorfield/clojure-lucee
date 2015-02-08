(defproject lucee "0.1.0-SNAPSHOT"
  :description "A Clojure Ring app that can evaluate CFML using Lucee as an embedded engine."
  :url "https://github.com/seancorfield/clojure-lucee"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"cfmlprojects" "http://cfmlprojects.org/artifacts"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [javax.servlet/jsp-api "2.0"]
                 [ring/ring-defaults "0.1.3"]
                 [org.lucee/lucee "4.5.0.042"]]
  :plugins [[lein-ring "0.9.1"]]
  :ring {:handler lucee.core/app})
