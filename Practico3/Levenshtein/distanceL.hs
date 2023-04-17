-- Calculo de la distancia Levenshtein de manera recurisva
-- Es muy ineficiente esta solucion
distance :: String -> String -> Int
distance xs [] = length xs
distance [] xs = length xs
distance (x:xs) (y:ys) | x == y = min (distance xs ys) (min ((distance (x:xs) ys)+1) ((distance xs (y:ys))+1))
                       | otherwise = min ((distance xs ys)+1) (min ((distance (x:xs) ys)+1) ((distance xs (y:ys))+1))