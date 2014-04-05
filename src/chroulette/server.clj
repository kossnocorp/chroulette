(ns chroulette.server
  (:use [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        org.httpkit.server)
  (:require [compojure.route :as route]
            [hiccup.core :as hiccup]
            [hiccup.page :as page])
  (:gen-class :main true))

(defn html-template []
  (page/html5
    [:head
      [:title "Chroullete — play russian chekers with random person"]
      (page/include-js "/js/app.js")]
    [:body
      [:h1 "Chroullete"]]))

(defroutes app-routes
  (GET "/" [] (html-template))
  (route/resources "/")
  (route/not-found "Not Found"))

(defn -main []
  (run-server (site #'app-routes) {:port 8080}))
