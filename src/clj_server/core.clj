(ns clj_server.core
  (:require [ring.adapter.jetty :as jetty]))
(defn ptag [content] (str "<p>" content "</p>"))
(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (ptag "String one")
                 (ptag "String two"))})
(defn -main [] (jetty/run-jetty handler {:port 3000}))