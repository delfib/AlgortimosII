-- EJERCICIO 12 = Decrease & Conquer
-- Programa que dadas dos secuencias p y t, determina si p es subsecuencia (de elementos contiguos) de t 
-- utilizando la tecnica Decrease & Conquer

subSeq :: (Eq a) => [a] -> [a] -> Bool
subSeq xs ys = subSeq' xs ys True

subSeq' :: (Eq a) => [a] -> [a] -> Bool -> Bool
subSeq' [] xs flag = True
subSeq' xs [] flag = False
subSeq' [x] [y] flag = x == y && flag
subSeq' (x:xs) (y:ys) flag | length (x:xs) > length (y:ys) = False
                           | x == y = subSeq' xs ys True
                           | otherwise = subSeq' (x:xs) ys False

