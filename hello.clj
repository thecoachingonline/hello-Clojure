(ns my-app
  (:require [clojure.math.combinatorics.partitions :as p])
  (:require [clojure.math.biginteger :as bi]))

(defn generate-random-lattice-vector [n]
  (let [g (bi/rand-bigint (bi/pow-mod 2 (bi/+ n 1)) (bi/pow-mod 2 (bi/+ n 2)))
        v (for [i (range n)] (bi/rand-bigint (bi/pow-mod 2 (bi/+ n 1)) (bi/pow-mod 2 (bi/+ n 2))))]
    (vec v)))

(defn generate-random-noise [n]
  (let [g (bi/rand-bigint (bi/pow-mod 2 (bi/+ n 1)) (bi/pow-mod 2 (bi/+ n 2)))]
    (for [i (range n)] (bi/rand-bigint (bi/pow-mod 2 (bi/+ n 1)) (bi/pow-mod 2 (bi/+ n 2))))
    (vec noise)))

(defn encrypt [message n]
  (let [v (generate-random-lattice-vector n)
        noise (generate-random-noise n)
        c (bi/add (bi/dot (vec message) v) noise)]
    (vec c)))

(defn decrypt [ciphertext n secret-key]
  (let [v (generate-random-lattice-vector n)]
    (bi/dot (vec ciphertext) v)))

(defn main []
  (let [message "สวัสดีชาวโลก"
        n 4096
        secret-key (bi/rand-bigint (bi/pow-mod 2 (bi/+ n 1)) (bi/pow-mod 2 (bi/+ n 2)))]
    (println "Message: " message)
    (println "Ciphertext: " (encrypt message n))
    (println "Plaintext: " (decrypt (encrypt message n) secret-key))))

(main)
