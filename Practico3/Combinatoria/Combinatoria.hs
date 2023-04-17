import Data.Array

-- Calculo del numero combinatorio entre n y m de manera ineficiente
comb :: Int -> Int -> Int
comb n m | m == 0 = 1
         | n == m = 1
         | m > n = 0  
         | otherwise = comb(n-1) (m-1) + (comb (n-1) m)


-- Dinamic Programming version
combPD :: Int -> Int -> Integer
combPD n m = table ! (n,m) where    -- devuelve el valor que se encuentra en el indice (n,m) de la matriz table siendo n la fila y m la columna
    bnds = ((0,0), (n,m))   -- los indices de la matriz van desde (0,0) hasta (n,m)
    table = listArray bnds ( map combAux (range bnds))
    combAux (n,m) 
        | m == 0 || n == m = 1 
        | otherwise = table ! (n-1,m-1) + table ! (n-1,m)


{-
    table = listArray bnds ( map combAux (range bnds))
Se crea un array bidimensional (matriz) table utilizando la función listArray del módulo Data.Array.
La función listArray toma dos argumentos: los límites inferior y superior de los índices del array repersentados por bnds,
y una lista de valores para inicializar el array que se generan a partir de la función combAux aplicada 
a cada índice en el rango bnds.
}