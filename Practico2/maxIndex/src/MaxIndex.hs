module MaxIndex where

-- EJERCICIO 9 = Decrease & Conquer
-- maxindex retorna la posicion del maximo elemento de la lista

-- DECREASE 
maxIndexDC :: [Int] -> Int
maxIndexDC [] = error "List is empty"
maxIndexDC xs = snd (maxIndexx' xs)

maxIndexDC' :: [Int] -> (Int,Int)
maxIndexDC' [x] = (x,0)
maxIndexDC' (x:xs) | x > fst solveSub = (x,0)
                   | otherwise = (fst solveSub, snd solveSub + 1)
    where solveSub = maxIndexDC' xs



-- -- Solucion FUERZA BRUTA. (esta la hice pensando que era decrease pero no es decrease, es FB supuestamente)
maxindexFB :: [Int] -> Int
maxindexFB [] = error "List is empty"
maxindexFB xs = aux xs (head xs) 0 0

aux :: [Int] -> Int -> Int -> Int -> Int
aux [] m i c = i
aux [x] m i c | x > m = c 
              | otherwise = i
aux (x:y:xs) m i c | x > m = aux (y:xs) x c (c+1)
                   | otherwise = aux (y:xs) m i (c+1)




-- Solucion FUERZA BRUTA. Tiempo de ejecucion: n^2 en el peor caso
maxindexFB :: [Int] -> Int
maxindexFB [] = error "List is empty"
maxindexFB xs = index xs (maxx xs)

-- Dada una lista de enteros, retorna su maximo
maxx :: [Int] -> Int 
maxx [] = error "Empty list"
maxx xs = maxx' xs (head xs)

maxx' :: [Int] -> Int -> Int
maxx' [] n = n 
maxx' [x] n | x > n = x
            | otherwise = n 
maxx' (x:y:xs) n | x > n = maxx' (y:xs) x
                 | otherwise = maxx' (y:xs) n

-- Dada una lista y un elemento de esta, retorna su indice
index :: [Int] -> Int -> Int
index [] n = error "Empty list"
index xs n = index' xs n 0

index' :: [Int] -> Int -> Int -> Int
index' [] n c = c
index' [x] n c | n == x = c
               | otherwise = error "Element not in list"
index' (x:y:xs) n c | x == n = c
                    | otherwise = index' (y:xs) n (c+1) 