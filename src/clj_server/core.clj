(ns clj_server.core
  (:require [ring.adapter.jetty :as jetty]))
(def input [
            [:hr]
            [:b "test phrase"]
            [:p [:a "test link"]]
            ])
(defn open-tag [tag-content]
  (if (not= (second tag-content) nil)
    (let [first (first tag-content)]
      (str "<" first ">")
      (open-tag (second tag-content))
      (str "</" first ">")
      )
    tag-content
    )
  )
(defn gen-html [content]
  (let [pointless "variable"]
    (str (map open-tag content))
    ))
(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   ;   :body    (str (:uri request) '? (:query-string request))})
   :body    (gen-html input)})
(defn -main [] (jetty/run-jetty handler {:port 3000}))