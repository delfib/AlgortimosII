-- F0 = 0, F1 = 1, F2 = 1, F3 = 2, F4 = 3, F5 = 5, F6 = 8

-- Fibonacci sequence (inefficient version)
fib :: Int -> Int
fib 0 = 0
fib 1 = 1
fib n = (fib (n-1)) + (fib (n-2))

-- Memoization version of Fibonacci sequence
fibo :: Int -> Int
fibo x = lookUp x (memoFibo x [])

memoFibo :: Int -> [(Int, Int)] -> [(Int,Int)]
memoFibo x cache | defined cache x = cache
                 | x<0 = error "undefined"
                 | x<=1 = store cache x 1
                 | otherwise = store cacheSnd x (y1+y2)
    where cacheFst = memoFibo (x-1) cache
          cacheSnd = memoFibo (x-2) cacheFst
          y1 = lookUp (x-1) cacheSnd
          y2 = lookUp (x-2) cacheSnd

lookUp :: Int -> [(Int, Int)] -> Int
lookUp x [] = error "undefined"
lookUp x1 ((x2,y):ps) | x1==x2 = y
                      | x1/=x2 = lookUp x1 ps

defined :: [(Int,Int)] -> Int -> Bool
defined ps x = elem x (map fst ps)

store :: [(Int, Int)] -> Int -> Int -> [(Int,Int)]
store ps x y | defined ps x = ps
             | otherwise = (x,y):ps

