(ns lucee.core
  (:require [clojure.string :as str]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:import (lucee.cli CLI)))

(defn run-lucee
  "Given a script name, a query string, and cookies, run it via Lucee.
  Returns the output as a string.
  Since the Lucee CLI writes its output to System.out, we create
  our own output stream, change System.out, run Lucee, then restore
  System.out, and finally return the string in the output stream."
  [script-name query-string cookie]
  (let [baos (java.io.ByteArrayOutputStream.)
        out  java.lang.System/out]
    (java.lang.System/setOut (java.io.PrintStream. baos))
    (CLI/main (into-array String [(str "-uri=" script-name "?" query-string)
                                  (str "-cookie=" (str/replace cookie #"; " "&"))
                                  "-webroot=."]))
    (java.lang.System/setOut out)
    (.toString baos)))

(defn is-cfml?
  "Given a request map, return true if it is a .cfm file."
  [req]
  (let [script-name (:uri req)
        n (count script-name)]
    (and (> n 4)
         (= ".cfm" (subs script-name (- n 4))))))

(defn query-string-with-session
  "Given a request, extract the query string and
  fixate the session, using the Ring session ID."
  [req]
  (let [ring-session (get-in req [:cookies "ring-session" :value])]
    (str (:query-string req)
         "&cfid=" ring-session
         "&cftoken=0"
         "&jsessionid=ClojureLucee_" ring-session "_0")))

(defn handler
  "Our simple little application handler.
  Always return an HTML page.
  If you ask for a CFML page, it runs it."
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (if (is-cfml? req)
           (run-lucee (:uri req)
                      (query-string-with-session req)
                      (get-in req [:headers "cookie"] ""))
           (str "<h1>I'm a Clojure Ring app!</h1>"
                "<p>Try <a href=\"hello.cfm?with=some&url=arguments\">hello.cfm</a></p>"
                "<p>" (pr-str req) "</p>"))})

(def app (wrap-defaults handler site-defaults))
