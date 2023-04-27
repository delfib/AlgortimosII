-- programa que dada un conjunto de valores de monedas > 0, dado un precio y el monto pagado
-- se debe encontrar la forma optima de dar el vuelto usnado la menor cantidad de monedas posibles
-- [Int] (conjunto de monedas) -> Int (valor del vuelto) -> Int (cantidad minima de monedas que forman el vuelto)

-- No lo probe, probablemente este MAL
change :: [Int] -> Int -> Int
change xs vuelto = c xs [] vuelto


c :: [Int] -> [Int] -> Int -> Int
c xs ys 0 = length ys
c (x:xs) ys vuelto | vuelto == x && elem x (x:xs) = 1
                   | otherwise = min (c (xs [x]++ys (vuelto - x))) (c xs ys vuelto)