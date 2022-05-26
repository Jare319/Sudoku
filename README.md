Wordle but make it sudoku.

Can generate completed puzzles, still need to implement:
- [x] Generating unsolved (but uniquely solveable) puzzles.
- [ ] Difficulty system.
- [ ] GUI
- [ ] Scoring.
- [ ] A betting difficulty system.

Note on removing values: Amount removed seems kinda random, only ever actually achieved removing at most 18 values once when attempting to remove 20.
Must be some edge case where it tries to remove the same value multiple times.

Update: It's more consistent when trying to remove lower ammounts of values.

Update: It now removes the correct number of values. Will add attempts when trying to remove so that if no more can be removed it will stop trying.

Update: Able to remove 60 values so far, not consistent and will sometimes crash, which is why i need above mentioned step
