(ns chroulette.rules-spec
  (:require
    [speclj.core :refer :all]
    [chroulette.rules :refer :all]))

(describe "move-to-black?"
  (it "returns true if piece on board and on black"
    (should (move-to-black? [3 4]))
    (should-not (move-to-black? [3 5]))
    (should-not (move-to-black? [3 9]))
    (should-not (move-to-black? [0 5]))))

(describe "simple-move-distance"
  (it "returns count of squares for given move"
    (should= 0 (simple-move-distance [[2 1] [2 1]]))
    (should= 1 (simple-move-distance [[2 1] [3 2]]))
    (should= 1 (simple-move-distance [[6 7] [5 6]]))
    (should= 3 (simple-move-distance [[2 1] [5 4]]))))

(describe "simple-move?"
  (it "returns true if move on diagonal"
    (should (simple-move? [[2 3] [3 4]] :white))
    (should-not (simple-move? [[2 3] [3 5]] :white))
    (should-not (simple-move? [[2 3] [3 4]] :black))))

(describe "valid-move?"
 (context "when move is simple"
    (def board
      {:white
        [{:position [2 1] :king false}
         {:position [2 3] :king false}
         {:position [1 4] :king false}]
       :black
         [{:position [3 6] :king false}
          {:position [5 6] :king false}]})

    (it "returns true if move is legitimate"
      (should (valid-move? [[2 3] [3 4]] :white board))
      (should (valid-move? [[3 6] [4 5]] :black board)))

    (it "returns false if there is another piece on distanation"
      (should-not (valid-move? [[2 3] [1 4]] :white board)))

    (it "returns false if move is not legitimate"
      (should-not (valid-move? [[2 3] [2 4]] :white board))
      (should-not (valid-move? [[2 3] [1 2]] :white board))
      (should-not (valid-move? [[3 6] [4 7]] :black board)))

    (it "returns false if move distance in bigger than 1"
      (should-not (valid-move? [[2 3] [4 5]] :white board))
      (should-not (valid-move? [[3 6] [5 4]] :black board)))))

(run-specs)
