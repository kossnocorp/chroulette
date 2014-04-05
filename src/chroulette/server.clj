(ns chroulette.server
  (:use [compojure.core :only [defroutes GET POST DELETE ANY context]]
        [compojure.handler :only [site]]
        [org.httpkit.server :only [run-server]])
  (:require [ring.middleware.reload :as reload]
            [compojure.route :as route]
            [hiccup.core :as hiccup]
            [hiccup.page :as page])
  (:gen-class :main true))

(defn html-template []
  (page/html5
    [:head
      [:title "Chroullete — play russian chekers with random person"]
    [:body
      [:h1 "Chroullete"]
      [:div {:id "app"} "Loading..."]
      (page/include-js "http://fb.me/react-0.10.0.js")
      (page/include-js "/js/app.js")]]))

(defroutes app-routes
  (GET "/" [] (html-template))
  (route/resources "/")
  (route/not-found "Not Found"))

(defn in-dev? [] true)

(defn -main []
  (let [handler (if (in-dev?)
                  (reload/wrap-reload (site #'app-routes))
                  (site app-routes))]
    (run-server handler {:port 8080})))
