(ns conversor.cambista
  (:require ;;	referenciando	parse-string	do	Cheshire
            [cheshire.core	:refer	[parse-string]]
            [clj-http.client :as http-client]))

;;chave para consumir api - https://free.currencyconverterapi.com/free-api-key/
(def chave-api "5211dbd818cd34da8dad")

(def api-url "https://free.currconv.com/api/v7/convert")

(defn parametrizar-moedas [de para]
  (str de "_" para))

(defn cotar [de para]
  (let [moedas (parametrizar-moedas de para)]
    (-> (:body (http-client/get api-url
                                {:query-params {"q" moedas
                                                "apiKey" chave-api}}))
        (parse-string)
        (get-in ["results" moedas "val"]))))
