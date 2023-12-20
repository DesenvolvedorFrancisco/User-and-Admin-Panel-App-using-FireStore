package dev.francisco.firestoreapp2

class User {
    var user:String?=null
    var password:String?=null
    var id:String?=null

    // para gerar este trecho use o GENERATOR e escolha o SECONDARY depois selecione
    // os 3 campos ou mais que voce criou, depois acima escreva o segundo constructor
constructor(){}
    constructor(user: String?, password: String?, id: String?) {
        this.user = user
        this.password = password
        this.id = id
    }
}