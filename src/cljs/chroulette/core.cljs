(ns chroulette.core-cljs
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(defn widget
  [data]
  (om/component
    (html
      [:h2 "Hello world!"])))

(om/root widget {}
  {:target (.getElementById js/document "app")})
