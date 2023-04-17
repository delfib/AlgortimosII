-- EJERCICIO 8 = Decrease & Conquer
-- Algoritmo que encuentra los elementos mayor y menor de una secuencia de n enteros positivos.
-- utilizando la tecnica Decrease & Conquer. El tiempo de ejecucion de este algoritmo es lineal

-- Decrease & Conquer
maxMin' :: [Int] -> (Int, Int)
maxMin' [] = error "Empty list"
maxMin' [x] = (x,x)
maxMin' (x:xs) = (min x (fst p), max x (snd p))
    where p = maxMin' xs

-- Esto no es Decrease & Conquer porque terminas comparando todos con todos
maxMin :: [Int] -> (Int, Int)
maxMin [] = error "Empty list"
maxMin [x] = (x,x)
maxMin (x:y:xs) = (min, max) 
    where (min, max) = aux (x:y:xs) x x

aux :: [Int] -> Int -> Int -> (Int, Int)
aux [] n m = (n,m)
aux [x] n m = (min x n, max x m)
aux (x:y:xs) n m | x < n = aux (y:xs) x m
                 | x > m = aux (y:xs) n x
                 | otherwise = aux (y:xs) n m