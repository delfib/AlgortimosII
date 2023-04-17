module ListSorter where

-- Test ordered list
isOrdered :: Ord a => [a] -> Bool
isOrdered [] = True
isOrdered [x] = True
isOrdered (x:(y :ys)) = if x<=y then isOrdered(y:ys)  else False

-- Remove list members that satisfy the predicate
remove :: (a -> Bool) -> [a] -> [a]
remove p [] = []
remove p (x:xs) = if p x then xs else x : remove p xs


-- Sort a list by selectionSort algorithm
selectionSort :: (Ord a) => [a] -> [a]
selectionSort [] = []
selectionSort xs = selectionSort (remove (== x) xs) ++ [x]
  where x = maximum xs


-- Sort a list by Slowsort algorithm
slowSort :: (Ord a) => [a] -> [a]
slowSort xs = findSorted (perms xs)

-- Dada una lista de listas, devuelve aquella que esta ordenada
findSorted :: (Ord a) => [[a]] -> [a]
findSorted [] = []
findSorted (x:xs) = if isOrdered x then x else findSorted xs


-- Dada una lista, calcula todas sus permutaciones
perms :: (Ord a) => [a] -> [[a]]
perms [] = [[]]
perms xs = [x:ys | x <- xs, ys <- perms (quitar x xs)]
    where quitar x xs = [y | y <- xs, y /= x]
