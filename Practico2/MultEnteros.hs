-- Programa que dado dos enteros, realiza su multiplicacion con la tecnica de Decrease & Conquer
multInt :: Int -> Int -> Int
multInt _ 0 = 0
multInt 0 _ = 0
multInt n m | mod n 2 == 0 = (n `div` 2) * (2*m)
            | mod n 2 /= 0 = ((n-1) `div` 2) * (2*m) + m
