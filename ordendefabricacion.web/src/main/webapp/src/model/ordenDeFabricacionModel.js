define(['model/_ordenDeFabricacionModel'], function() {
    App.Model.OrdenDeFabricacionModel = App.Model._OrdenDeFabricacionModel.extend({

    });

    App.Model.OrdenDeFabricacionList = App.Model._OrdenDeFabricacionList.extend({
        model: App.Model.OrdenDeFabricacionModel
    });

    return  App.Model.OrdenDeFabricacionModel;

});