(ns chroulette.server
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server)
  (:require [hiccup.core :as hiccup])
  (:gen-class :main true))

(defn html-template []
  (hiccup/html
    [:head
      [:title "Chroullete — play russian chekers with random person"]]
    [:body
      [:h1 "Chroullete"]]))

(defroutes app-routes
  (GET "/" [] (html-template))
  (not-found "Not Found"))

(defn -main []
  (run-server (site #'app-routes) {:port 8080}))
