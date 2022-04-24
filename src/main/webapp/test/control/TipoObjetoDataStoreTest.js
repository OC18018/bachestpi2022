import TipoObjetoDataStore from "../../control/TipoObjetoDataStore.js" ;

describe("TipoObjetoDataStore",function(){
    var cut;
    it("Deberia instanciarse",function(){
        cut = new TipoObjetoDataStore();
        assert.isDefined(cut,"El objeto no esta instanciado");
    });

    it("Deberia buscar primero 50",function(){
        cut.findRange(0,50).then(function(respuesta){
            assert.equal(respuesta.status,200);
        });
    });
    
});