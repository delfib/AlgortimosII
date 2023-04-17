-- EJERCICIO 10 = Decrease & Conquer
-- Algoritmo que calcula la distancia Hamming entre dos cadenas de igual longitud.
-- El tiempo de ejecucion de este algoritmo es lineal

-- Supuestamente esto no es Decrease
hamming :: (Eq a) => [a] -> [a] -> Int
hamming xs ys = hamming' xs ys 0

hamming' :: (Eq a) => [a] -> [a] -> Int -> Int
hamming' [] [] n = n
hamming' (x:xs) (y:ys) n | x /= y = hamming' xs ys (n+1)
                         | otherwise = hamming' xs ys n


-- supongo que sigue sin ser decrease
hammingDC :: (Eq a) => [a] -> [a] -> Int
hammingDC xs ys = hammingDC' xs ys 0

hammingDC' :: (Eq a) => [a] -> [a] -> Int -> Int
hammingDC' [x] [y] p = if x /= y then p+1 else p
hammingDC' (x:xs) (y:ys) p | x /= y = hammingDC' xs ys p+1
                           | otherwise = hammingDC' xs ys p
