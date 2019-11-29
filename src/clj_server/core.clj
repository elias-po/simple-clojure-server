(ns clj_server.core
  (:require [ring.adapter.jetty :as jetty]))
(def input [
            [:h1 "These are the tasks 1a and 1b"]
            [:hr]
            [:b "Here is a test phrase"]
            [:p [:big [:i [:strike "and here is a nested phrase"]]]]
            ])
(defn open-tag [tag-content]
  (if (vector? tag-content)
    (let [first (first tag-content)]
      (if (not= (second tag-content) nil)
        (str "<" (name first) ">"
             (open-tag (second tag-content))
             "</" (name first) ">")
        (str "<" (name first) "/>")
        )
      )
    tag-content
    )
  )

(defn gen-html [content]
  (map open-tag content)
  )
(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   ;   :body    (str (:uri request) '? (:query-string request))})
   :body    (gen-html input)})
(defn -main [] (jetty/run-jetty handler {:port 3000}))