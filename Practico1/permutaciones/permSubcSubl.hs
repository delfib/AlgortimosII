-- Programas que dada una lista calcula todas sus PERMUTACIONES y SUBLISTAS, y dado un conjunto calcula todos sus SUBCONJUNTOS

---------------------------------------------------------------------------------------------
-- PERMUTACIONES
---------------------------------------------------------------------------------------------

-- Dada una lista, calcula todas sus permutaciones
perms :: (Eq a) => [a] -> [[a]]
perms [] = [[]]
perms xs = [x:ys | x <- xs, ys <- perms (quitar x xs)]
    where quitar x xs = [y | y <- xs, y /= x]

-- Decidir si dos secuencias son anagramas. Lo seran cuando xs sea una permutacion de ys
anagramas :: (Eq a) => [a] -> [a] -> Bool
anagramas xs ys | length xs > length ys = contains ys (perms xs)
                | length ys >= length xs = contains xs (perms ys)

-- Creo que esta seria la correcta en base a la definicion de permutaciones de una lista
anagramas' :: (Eq a) => [a] -> [a] -> Bool
anagramas' xs ys | length xs /= length ys = False
                 | otherwise = contains xs (perms ys)

contains :: (Eq a) => [a] -> [[a]] -> Bool
contains xs [] = False
contains xs [ys] = xs == ys
contains xs (y:ys) = xs == y || contains xs ys

---------------------------------------------------------------------------------------------
-- SUBLISTAS
---------------------------------------------------------------------------------------------

-- Dada una lista, calcula todas sus SUBLISTAS
-- Input = [1,2,3]      Output = [[1,2,3],[1,2],[1,3],[1],[2,3],[2],[3],[]]
sublists :: [a] -> [[a]]
sublists [] = [[]]
sublists (x:xs) = [x:sublist | sublist <- sublists xs] ++ sublists xs

-- Dadas dos cadenas p y s, decida si p es subcadena de s.
subCadena :: (Eq a) => [a] -> [a] -> Bool
subCadena xs [] = False
subCadena xs ys | xs == ys = True
                | length xs > length ys = False
                | length xs < length ys = contains xs (sublists ys)

---------------------------------------------------------------------------------------------
-- SUBCONJUNTOS
---------------------------------------------------------------------------------------------

-- Dado un conjunto, retorna todos sus SUBCONJUNTOS
-- Input {a,b,c}        Output [{},{a},{b},{c},{a,b},{a,c},{b,c},{a,b,c}]
subSets :: [a] -> [[a]]
subSets [] = [[]]
subSets (x:xs) = subSets xs ++ map (x:) (subSets xs)


-- Dado un conjunto s y un valor n, decidir si existe un subconjunto de s cuya suma sea n.
subSetSumN :: (Num a, Eq a) => [a] -> a -> [a]
subSetSumN [] 0 = []
subSetSumN [] n = error "the set is empty"
subSetSumN xs n = itsSumisN (subSets xs) n

-- Dado una lista de conjuntos y un valor n, retorna el conjunto cuya suma de todos sus elementos es n
itsSumisN :: (Num a, Eq a) => [[a]] -> a -> [a]
itsSumisN [[]] 0 = []
itsSumisN [[]] n = error "the set is empty"
itsSumisN (x:xs) n = if (foldr (+) 0 x) == n then x else itsSumisN xs n

-- Dadas dos cadenas p y s, decida si p es subsecuencia de s (los elementos no necesariamente tienen que aparecer contiguos en s).
subSecuencia :: (Eq a) => [a] -> [a] -> Bool
subSecuencia [] xs = True
subSecuencia xs [] = False
subSecuencia xs ys | length xs > length ys = False 
                   | length xs <= length ys = contains xs (subSets ys)



---------------------------------------------------------------------------------------------
-- COMBINACIONES
---------------------------------------------------------------------------------------------

-- Todas las combinaciones posibles de una lista
combinations :: [a] -> [[a]]
combinations [] = [[]]
combinations (x:xs) = let cs = combinations xs in cs ++ [x:c | c <- cs]

-- Todas las combinaciones de tamaño k para una lista
combinationsK :: Int -> [a] -> [[a]]
combinationsK 0 _ = [[]]
combinationsK _ [] = []
combinationsK k (x:xs) = [x:c | c <- combinationsK (k-1) xs] ++ combinationsK k xs



---------------------------------------------------------------------------------------------
-- ELIMINAR REPETIDOS DE UNA LISTA
---------------------------------------------------------------------------------------------

-- Dada una lista, elimina elementos repetidos de esa lista EJ 7
-- Peor caso para n = 3 [1,1,1] que sean todos repetidos.
-- containss es lineal, elimRep aplica containss a todos sus elementos, en el peor caso elimRep es n^2
elimRep :: (Eq a) => [a] -> [a]
elimRep [] = []
elimRep [x] = [x]
elimRep (x:xs) = if containss x xs then elimRep xs else x: elimRep xs

containss :: (Eq a) => a -> [a] -> Bool
containss x [] = False
containss x [y] = x == y
containss x (y:ys) = x == y || containss x ys





-- Dado un numero retorna True sii n es primo
esPrimo :: Int -> Bool
esPrimo n = cantDiv n n == 2

-- Dado un numero n, calcula todos sus divisores
cantDiv :: Int -> Int -> Int
cantDiv n 1 = 1
cantDiv n m | n `mod` m == 0 = 1 + cantDiv n (m-1)
            | otherwise = cantDiv n (m-1)

-- Dado un numero n, calcula todos los primos menores a el
cantPrimos :: Int -> [Int]
cantPrimos 2 = [2]
cantPrimos n = if esPrimo n then n : cantPrimos (n-1) else cantPrimos (n-1)

-- Dado un numero n, retorna a ese n descompuesto en todos sus factores primos
facPrimos :: Int -> [Int]
facPrimos 2 = [2]
facPrimos n | n `mod` (head (cantPrimos n)) == 0 = (head (cantPrimos n)) : facPrimos (n `mod` (head (cantPrimos n)))
            | not (n `mod` (head (cantPrimos n)) == 0) = facPrimos (n `mod` (head (cantPrimos n)))