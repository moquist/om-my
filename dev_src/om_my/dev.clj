(ns om-my.dev)

(defmacro websocket-url []
  (println :making-websocket-url)
  (if-let [port (Long/parseLong (System/getenv "LEIN_FIGWHEEL_PORT"))]
    (str "ws://localhost:" port  "/figwheel-ws")
    (throw (Exception. "Missing LEIN_FIGWHEEL_PORT environment variable. Set it."))))
