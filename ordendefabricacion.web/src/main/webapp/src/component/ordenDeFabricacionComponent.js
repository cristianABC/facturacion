define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/ordenDeFabricacionModel', 'controller/ordenDeFabricacionController'], function() {
    App.Component.OrdenDeFabricacionComponent = App.Component._CRUDComponent.extend({
        name: 'ordenDeFabricacion',
        model: App.Model.OrdenDeFabricacionModel,
        listModel: App.Model.OrdenDeFabricacionList,
        controller : App.Controller.OrdenDeFabricacionController
    });
    return App.Component.OrdenDeFabricacionComponent;
});