(ns ^:figwheel-always om-my.core
    (:require [om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]
              [om-my.utils :as utils]
              [cemerick.url :refer [url-encode]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn get-movie [title]
  (let [title (url-encode title)]
    (utils/edn-xhr
     {:method :post
      :url "/rt"
      :data (str "movies.json?q=" title)
      :on-complete (fn [response] (reset! app-state {:movie response}))})))

(defn display-list [items]
  (apply dom/ul nil
         (map #(dom/li nil %) items)))

(defn display-movie-abridged-cast [movie]
  (let [cast (-> movie
                 :movies
                 first
                 :abridged_cast)]
    (display-list (map :name cast))))

(om/root
  (fn [data owner]
    (reify om/IRender
      (render [_]
        (cond
         (:text data) (dom/h1 nil (:text data))
         (:movie data) (display-movie-abridged-cast (:movie data))))))
  app-state
  {:target (. js/document (getElementById "app"))})


