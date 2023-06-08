module addressBook


sig Name{}
sig Address{}

sig AddressBook{
		names: set Name, 
    addr:names -> Address
}

fact  domAddrIsNames{
	all b:AddressBook | b.addr.Address = b.names
}

fact addrIsFuncional{
		all b: AddressBook | all n: b.names | one n.(b.addr)
}

// ELIMINACION
// Se elimina una direccion del AddressBook
pred remAddress(b,b': AddressBook, n: Name, a:Address) {
	b'.addr = b.addr - (n->a)
}

//CONSULTA
// Dado un nombre, obtenemos las direcciones a la que esta asociado ese nombre
fun getAddress(b: AddressBook, n: Name, a,a':Address) : set Address {
	n.(b.addr)
}


// AGREGACION de una nueva persona al AddressBook
pred addAddress(b,b': AddressBook, n: Name, a: Address) {
	b'.names = b.names + n and
	b'.addr = b.addr ++ (n->a)
}

// ELIMINACION de una persona del AddressBook
pred remName(b,b': AddressBook, n: Name, a: Address) {
	//b'.addr = b.addr - (n->a)
	a in n.(b.addr) and remAddress[b,b',n,a]
	b'.names = b.names - n
}


// si se elimina una persona de la agenda, esta ya no pertenece a la misma
assert rmvdDoesntExistAnymore {
		all b,b': AddressBook | all n: Name | all a: Address | remName[b,b',n,a] => n not in b'.names
}

// si se agrega una direccion y luego se elimina, volvemos a la agenda original
assert addAndRmvAddressStaysSame {	
	all b,b': AddressBook | all n: Name | all a: Address | addAddress[b,b',n,a] and remAddress[b,b',n,a] => b' = b
}


// sobreescribir una direccion en la agenda pierde el primer valor.
assert overwriteLosesFirstValue {
    //all b,b': AddressBook | all n: Name | all a: Address | let b'' = b + (n->a') | addAddress[b'',b',n,a] => a' not in b'.addr[n]
	all disj a1,a2: Address | all b,b': AddressBook | all n: Name |
	 addAddress[b,b',n,a1] and addAddress[b,b',n,a2] implies a2 in n.(b.addr) and a1 not in n.(b.addr)
	// se puede poner en vez de disj un a1 != a2
}

pred show[] { 
}

run show
check overwriteLosesFirstValue
run remName
