(ns chroulette.rules
  (:require [chroulette.board :as board]))

(defn move-to-black?
  [position]
  (and
    (board/on-board? position)
    (board/black-square? position)))

(defn simple-move-distance
  [move]
  (let
    [from-y (get-in move [0 1])
     to-y (get-in move [1 1])]
     (Math/abs (- to-y from-y))))

(defn forward-move?
  [move colour]
   (let
    [from-y (get-in move [0 1])
     to-y (get-in move [1 1])]
    (if (= colour :white)
      (> to-y from-y)
      (< to-y from-y))))

(defn simple-move?
  [move colour]
  (let
    [from (move 0)
     to (move 1)]
    (and
      (move-to-black? to)
      (forward-move? move colour)
      (= (simple-move-distance move) 1))))

(defn valid-move?
  [move colour brd]
  (and
    (not (board/has-piece? (move 1) brd))
    (simple-move? move colour)))
