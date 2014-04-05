(ns chroulette.core-cljs
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn widget
  [data]
  (om/component
    (dom/h1 nil "Hello world!")))

(om/root widget {}
  {:target (.-body js/document)})
