module Lcs where

import Data.List
import Data.Function

--Get subsequences (dada una secuencia, devuelve todas sus subsecuencias)
subseq :: [a] -> [[a]]
subseq xs = sortBy (compare `on` length) $ subsequences xs

--Longest common subsequence using Brute Force
lcsBf :: Eq a => [a] -> [a] -> [a]
lcsBf xs ys = maximumBy (compare `on` length) (filter (\x -> elem x (subseq ys)) (subseq xs))


--Longest common subsequence using Divide & Conquer
lcsDivide :: Eq a => [a] -> [a] -> [a]
lcsDivide [] ys = []
lcsDivide xs [] = []
lcsDivide (x:xs) (y:ys) -- | (x:xs) == (y:ys) = (x:xs)
                          | x == y = x : lcsDivide xs ys
                          | otherwise = longest (lcsDivide (x:xs) ys) (lcsDivide xs (y:ys))

longest :: (Eq a) => [a] -> [a] -> [a]
longest xs ys = if length xs > length ys then xs else ys


-- Otra forma (NO anda) intento de que sea con Decrease & Conquer
lcsDecrease' :: (Eq a) => [a] -> [a] -> [a]
lcsDecrease' [] ys = []
lcsDecrease' xs [] = []
lcsDecrease' [x] [y] = if x == y then [x] else []
lcsDecrease' (x:xs) (y:ys) | length (x:xs) < length (y:ys) = if elem x (y:ys) then [x] ++ (lcsDecrease' xs (drop ((index (y:ys) x)+1) (y:ys))) else lcsDecrease' xs (y:ys)
                           | length (y:ys) < length (x:xs) = if elem y (x:xs) then [y] ++ (lcsDecrease' ys (drop ((index (x:xs) y)+1) (x:xs))) else lcsDecrease' ys (x:xs)
                           | otherwise = longest (lcsDecrease' xs (y:ys)) (lcsDecrease' (x:xs) ys)


-- Dada una lista y un elemento de esta, retorna el indice de este
index :: (Eq a) => [a] -> a -> Int
index [] n = error "Empty list"
index xs n = index' xs n 0

index' :: (Eq a) => [a] -> a -> Int -> Int
index' [] n c = c
index' [x] n c | n == x = c
               | otherwise = error "Element not in list"
index' (x:y:xs) n c | x == n = c
                    | otherwise = index' (y:xs) n (c+1) 
