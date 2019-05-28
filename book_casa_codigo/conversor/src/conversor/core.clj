(ns conversor.core
  	;;	importando	outros	namespaces	do	projeto
		(:require	[conversor.formatador	:refer	[formatar]]
								[conversor.interpretador-de-opcoes	:refer [interpretar-opcoes]]
								[conversor.cambista	:refer	[cotar]]))

;;	funções	declaradas	como	defn-	são	visíveis	apenas
;;	para	o	namespace	onde	elas	foram	definidas
(defn- valores-em [argumento]
  (cond
    (.startsWith argumento "--de=") {:de (.substring argumento 5)}
    (.startsWith argumento "--para=") {:para (.substring argumento 7)}
    :else {}))

;; Conversor baseado na api https://free.currencyconverterapi.com/
;; - define que a função é estatica
;; & quantidade idenfinida de arqumentos vars no java
(defn	-main
  [&	args]
  (let [{de :de para :para} (interpretar-opcoes args)]
    (-> (cotar de para)
        (formatar de para)
        (prn))))


