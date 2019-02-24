(ns clojure-basics.core
  (:require [clojure.set :as set]))

;; Basic shortcut that we will throughout the session:
;; command + shift + L ;; Load file in repl
;; command + shift + P ;; send selected form in repl

;; - "Atomic" literals:
"hello"  ;; strings
:hello   ;; keywords
nil      ;; yes, nil is a value

;; - Collection literals:
[1 2 3 4 5]   ;; a vector
{:a 1 :b 2}   ;; a hash-map (key-value pairs)
#{1 2 3 4 5}  ;; a hash-set
'(1 2 3 4 5)  ;; a list

;; - Most commonly used collection's "Built-in" functions:
map        ;; map over a collection
filter     ;; filter from a collection
reduce     ;; transform a collection

;; - first place is fixed for function, others are params for function:
(+ 1 2)    ;; - + is a function, hence comes first

;; - How to define a function:
(defn add [first-value second-value]
  (+ first-value second-value))

;; - How to call a function:
(add 1 2)

;; other commonly used function:
(def customer {:customer {:name    "first name"
                          :age     18
                          :address {:h-no   123
                                    :street "street"}}})

(get-in customer [:customer])
(get-in customer [:customer :name])
(get-in customer [:customer :address :street])
(str "Hello" " " "World")
(first [1 2])
(second [1 2])
(rest [1 2 3 4 5])
(empty? [])
(empty? [1])
(not (empty? []))
(not (empty? [1]))
(keyword "a")
(:a {:a 1 :b 2})

;; How to use map, filter abd reduce
(filter even? [1 2 3 4])
(map inc [1 2 3 4])
(reduce + [1 2 3 4])

;; some more common use of map, filter
(defn increment [value]
  (inc value))

(map #(increment %) [1 2 3 4 5 6 7 8 9 10])

(map :a [{:a 1 :b 2} {:a 3 :b 4}])

(map (fn [value]
       (inc value)) [1 2 3 4 5 6 7 8 9 10])

(filter #(= 1 (:a %)) [{:a 1 :b 2} {:a 3 :b 4}])

(filter (fn [value]
          (= 1 (:a value))) [{:a 1 :b 2} {:a 3 :b 4}])

;; fn - anonymous functions
;; def ;; define variable which executes only once and returns the same cached value afterwards
(def def-url "https:abc.com")
(println def-url)

;; let blocks are used to create local variables in clojure
(let [url (str "new " def-url)]
  (println url))

;; some more commonly used functions
(assoc {1 :a} :b 2)
(dissoc {:a 1 :b 2} :a)
(select-keys {:a 1 :b 2 :c 3} [:a :c])
(set/rename-keys {:a 1 :b 2} {:a :new-a :b :new-b})

;; if condition syntax
(defn if-demo []
  (let [data [{:a 1} {:a 2}]]
    (map #(if (= 1 (:a %)) true false) data)))

(if-demo)

;; when condtion syntax
(defn when-demo []
  (when (= 1 1)
    (println "it is equal")))

(defn when-demo-2 []
  (when (= 1 2)
    (println "it is not equal")))

(when-demo)
(when-demo-2)

;; cond
(defn pos-neg-or-zero [value]
  (cond
    (even? value) "even"
    (odd? value) "odd"
    :else "don't know"))
(pos-neg-or-zero 2)
(pos-neg-or-zero 1)

;; call multiple functions with same input
((juxt :a :b) {:a 1 :b 2 :c 3 :d 4})
((juxt inc dec) 1)

;; macros

;; The thread-first macro (->)
(-> {:a 1}
  (assoc :b 2 :c 3)
  (dissoc :b)
  (:c))

(-> {:a 1} :b inc)  ;;throws null pointer exception

;;thread-last (->>)
(->> [1 2 3 4 5]
  (map #(inc %))
  (filter #(even? %)))

(->> {:a 1} :b inc)  ;;throws null pointer exception

;;some->
(some-> {:a 1} :b inc)

;;some->>
(some->> {:a 1} :b inc)