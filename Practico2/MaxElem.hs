-- Programa que dado una lista de numeros, encuentra el maximo utilizando la tecnica Divide & Conquer
-- O(nlogn) ya que dividir es log n, y calcular el maximo entre todos los numeros es n
maxList :: [Int] -> Int
maxList [] = error "List is empty"
maxList [x] = x
maxList xs = max (maxList izq) (maxList der) 
    where 
        n = length (xs) `div` 2
        izq = take n xs
        der = drop n xs
