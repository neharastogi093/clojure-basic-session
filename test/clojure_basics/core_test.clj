(ns clojure-basics.core-test
  (:require [clojure.test :refer :all]
            [clojure-basics.core :refer :all]
            [mock-clj.core :as mock]))

(deftest add-test
  (testing "add 1 and 2"
    (with-redefs [a identity])
    (with-redefs [a (fn []
                      (inc a))])
    (with-redefs [a (constantly true)])
    (mock/with-mock [a 1])
    (is (= 1 1))))
