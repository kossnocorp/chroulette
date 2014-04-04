(ns chroulette.board)

(defn valid-position?
  [position]
  (and
    (= (count position) 2)
    (number? (position 0))
    (number? (position 1))))

(defn black-square?
  [position]
  (let
    [column (position 0)
     row (position 1)]
    (or
      (and (odd? column) (even? row))
      (and (even? column) (odd? row)))))

(defn on-board?
  [position]
  (let
    [column (position 0)
     row (position 1)]
    (and
      (and (> column 0) (< column 9))
      (and (> row 0) (< row 9)))))

(defn new-piece [position] {:position position :king false})

(defn start-row-for [colour] ({:white 1 :black 6} colour))

(defn next-square
  [position]
  (let
    [column (position 0)
     row (position 1)
     square-on-rigth [(inc column) row]
     ]
    (if (on-board? square-on-rigth)
      square-on-rigth
      [1 (inc row)])))

(defn initial-pieces
  [colour]
  (loop
    [position [1 (start-row-for colour)]
     pieces []]
    (if (= 12 (count pieces))
      pieces
      (recur
        (next-square position)
        (if (black-square? position)
          (conj pieces (new-piece position))
          pieces)))))

(defn new-board []
  {:white (initial-pieces :white)
   :black (initial-pieces :black)})

(defn piece-on?
  [piece position]
  (= (piece :position) position))

(defn has-piece?
  [position board]
  (defn piece-on-position? [piece] (piece-on? piece position))
  (or
    (some piece-on-position? (board :white))
    (some piece-on-position? (board :black))))
