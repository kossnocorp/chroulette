(ns chroulette.core-cljs
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn widget
  [data]
  (om/component
    (dom/h2 nil "Hello world!")))

(om/root widget {}
  {:target (.getElementById js/document "app")})
