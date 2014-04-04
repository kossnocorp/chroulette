(ns chroulette.board-spec
  (:require
    [speclj.core :refer :all]
    [chroulette.board :refer :all]))

(describe "valid-position?"
  (it "returns true if position is vector and contain two numbers"
    (should (valid-position? [1 5]))
    (should-not (valid-position? [1]))
    (should-not (valid-position? [1 2 3]))
    (should-not (valid-position? [true \a]))))

(describe "black-square?"
  (it "returns true it square for given position is black"
    (should (black-square? [2 1]))
    (should-not (black-square? [7 7]))))

(describe "on-board?"
  (it "returns true if position is on board"
    (should (on-board? [4 6]))
    (should-not (on-board? [0 1]))
    (should-not (on-board? [1 9]))))

(describe "new-piece"
  (it "returns map with given position and :king false"
    (should= {:position [4 2] :king false} (new-piece [4 2]))))

(describe "next-square"
  (it "returns position of next square"
    (should= [2 1] (next-square [1 1]))
    (should= [1 2] (next-square [8 1]))))

(describe "start-row-for"
  (it "returns start row for given colour"
    (should= 1 (start-row-for :white))
    (should= 6 (start-row-for :black))))

(describe "initial-pieces"
  (it "returns vector of inital state for given colour"
    (should=
      [{:position [2 1] :king false}
       {:position [4 1] :king false}
       {:position [6 1] :king false}
       {:position [8 1] :king false}
       {:position [1 2] :king false}
       {:position [3 2] :king false}
       {:position [5 2] :king false}
       {:position [7 2] :king false}
       {:position [2 3] :king false}
       {:position [4 3] :king false}
       {:position [6 3] :king false}
       {:position [8 3] :king false}]
      (initial-pieces :white))
    (should=
      [{:position [1 6] :king false}
       {:position [3 6] :king false}
       {:position [5 6] :king false}
       {:position [7 6] :king false}
       {:position [2 7] :king false}
       {:position [4 7] :king false}
       {:position [6 7] :king false}
       {:position [8 7] :king false}
       {:position [1 8] :king false}
       {:position [3 8] :king false}
       {:position [5 8] :king false}
       {:position [7 8] :king false}]
      (initial-pieces :black))
    ))

(describe "new-board"
  (it "returns inital state of board"
    (should=
      {:white
       [{:position [2 1] :king false}
        {:position [4 1] :king false}
        {:position [6 1] :king false}
        {:position [8 1] :king false}
        {:position [1 2] :king false}
        {:position [3 2] :king false}
        {:position [5 2] :king false}
        {:position [7 2] :king false}
        {:position [2 3] :king false}
        {:position [4 3] :king false}
        {:position [6 3] :king false}
        {:position [8 3] :king false}]
       :black
       [{:position [1 6] :king false}
        {:position [3 6] :king false}
        {:position [5 6] :king false}
        {:position [7 6] :king false}
        {:position [2 7] :king false}
        {:position [4 7] :king false}
        {:position [6 7] :king false}
        {:position [8 7] :king false}
        {:position [1 8] :king false}
        {:position [3 8] :king false}
        {:position [5 8] :king false}
        {:position [7 8] :king false}]}
      (new-board))))

(describe "piece-on?"
  (it "returns true if piece on position"
    (should (piece-on? {:position [2 3]} [2 3]))
    (should-not (piece-on? {:position [2 3]} [2 5]))))

(describe "has-piece?"
  (it "returns true if board has piece on position"
    (should
      (has-piece?
        [5 4]
        {:white
         [{:position [2 1] :king false}
          {:position [4 1] :king false}
          {:position [5 4] :king false}]
        :black
         [{:position [1 6] :king false}
          {:position [3 6] :king false}
          {:position [7 8] :king false}]}))
    (should-not
      (has-piece?
        [5 4]
        {:white
         [{:position [2 1] :king false}
          {:position [4 1] :king false}
          {:position [6 1] :king false}]
        :black
         [{:position [1 6] :king false}
          {:position [3 6] :king false}
          {:position [7 8] :king false}]}))))

(run-specs)
