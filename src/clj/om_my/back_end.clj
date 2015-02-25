(ns om-my.back-end
  (:require [ring.util.response :refer [file-response]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure.edn :as edn]
            [org.httpkit.client :as http]
            [clojure.string :as str]
            [cheshire.core :refer [parse-string]]
            [cemerick.url :refer [url-encode]]))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(def api-base "http://api.rottentomatoes.com/api/public/v1.0/")
(defn rt-fetch! [query]
  (let [api-key (System/getenv "RT_API_KEY")
        ;; graciously eat leading & and ?
        query (str/replace query #"^/" "")
        url (str api-base query "&apikey=" api-key)]
    (-> url
        http/get
        deref
        :body
        (parse-string true)
        pr-str)))

(defn read-inputstream-edn [input]
  (edn/read
   {:eof nil}
   (java.io.PushbackReader.
    (java.io.InputStreamReader. input "UTF-8"))))

(defn parse-edn-body [handler]
  (fn [request]
    (handler (if-let [body (:body request)]
               (assoc request
                 :edn-body (read-inputstream-edn body))
               request))))

(defroutes routes
  (POST "/rt" {edn-body :edn-body} (rt-fetch! edn-body))
  (POST "/rt2" req (do (println :req req) {:status 200}))
  (route/files "/" {:root "resources/public"}))

(def handler
  (-> routes
      parse-edn-body))