(ns chroulette.server
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server)
  (:gen-class :main true))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (not-found "Not Found"))

(defn -main []
  (run-server (site #'app-routes) {:port 8080}))
