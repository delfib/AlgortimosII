one sig BinaryTree {
  root: lone Node
}

sig Node {
  left, right: lone Node,
  elem: Int 
}

// All nodes are in the tree.
fact Reachable {
  Node = BinaryTree.root.*(left + right)
}

fact Acyclic {
  all n : Node {
    // There are no directed cycles, i.e., a node is not reachable
    // from itself along one or more traversals of left or right.
    n !in n.^(left + right)
    // A node cannot have more than one parent.
    lone n.~(left + right)
    // A node cannot have another node as both its left child and
    // right child.
    no n.left & n.right
  } 
}

run {} for 3 but exactly 3 Node



// Encuentre una instancia donde los valores de los 3 nodos sean iguales.
run allAreEquals {
  some n1, n2, n3 : Node |
    n1.elem = n2.elem and n2.elem = n3.elem
	and n1 != n2 and n2 != n3 and n1 != n3
} for exactly 3 Node



// Encuentre una instancia donde los valores de los 3 nodos esten en orden creciente (consideramos
// un arbol ordenado, si respeta la propiedad de orden de un arbol binario de busqueda).
run {
	some n1, n2, n3: Node |
		n1 != n2 and n2 != n3 and n1 != n3
		and n1.left = n2 and n1.right = n3 
		and n1.elem > n2.elem and n1.elem < n3.elem
} for exactly 3 Node




// Encuentre una instancia donde los valores de 3 nodos esten en orden creciente y ademas ese orden
// se corresponda con los nombres de los nodos (ej: Node0 -> -2, Node1 -> 0, Node2 ->4)
run {
	some n1, n2, n3: Node |
		n1 != n2 and n1 != n3 and n2 != n3
		and n2.left = n1 and n2.right = n3
		and n1.elem < n2.elem and n2.elem < n3.elem
} for exactly 3 Node




