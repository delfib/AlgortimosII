module Grade where

import Data.Array
import Data.List

-- grading student solutions 
grade :: [Int] -> Int
grade xs = length (gradeDC (Data.List.sort xs) xs)

gradeDC :: (Eq a) => [a] -> [a] -> [a]
gradeDC [] ys = []
gradeDC xs [] = []
gradeDC (x:xs) (y:ys) 
  | x == y    = x : gradeDC xs ys
  | otherwise = longest (gradeDC (x:xs) ys) (gradeDC xs (y:ys))
    where
        longest xs ys = if length xs > length ys then xs else ys



-- Version usando Programacion Dinamica
gradeDP :: [Int] -> Integer
gradeDP xs = gradeMemo (Data.List.sort xs) xs


--grade Optimization using PD
gradeMemo :: [Int] -> [Int] -> Integer
gradeMemo xs ys = table ! (length xs, length ys) where
      bnds = ((0,0),(length xs+1,length ys+1))
      table = listArray bnds (map gradeAux (range bnds))
      gradeAux (n,m) | n == 0 || m == 0 = 0
                     | otherwise = (max (table ! (n-1,m)) (table ! (n,m-1))) + (sumAux (n,m))
      sumAux (n,m) | xs !! (n-1) == ys !! (m-1) = 1
                   | xs !! (n-1) /= ys !! (m-1)  = 0
