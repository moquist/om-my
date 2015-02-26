(ns om-my.dev)

(defmacro websocket-url-port []
  (let [port (System/getenv (str "LEIN_FIGWHEEL_PORT"))]
    (if (< 0 (count port))
      (Long/parseLong port)
      (throw (Exception. (str "Missing LEIN_FIGWHEEL_PORT environment variable. Set it."))))))

(defmacro websocket-url []
  (let [port (websocket-url-port)]
    (str "ws://localhost:" port  "/figwheel-ws")))
