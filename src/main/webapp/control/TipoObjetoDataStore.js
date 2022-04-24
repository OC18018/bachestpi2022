import BachesDataAccess from "./BachesDataAccess.js";

class TipoObjetoDataStore extends BachesDataAccess {
    constructor(){
        super();
    }
    
    async contar(){
        let promesa =fetch(this.BASE_URL+"tipoobjeto/contar",
        {method:"GET"}
        );
        await promesa.then(respuesta=>respuesta.json())
        .then(j=>console.log(j))
        .catch (err=>console.error(err));
        console.log("entro a contar");
    }
}

export default TipoObjetoDataStore;
console.log("antes de contar");
let t=new TipoObjetoDataStore();
t.contar();
console.log("despues de contar");