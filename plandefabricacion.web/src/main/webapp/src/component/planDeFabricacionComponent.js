define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/planDeFabricacionModel', 'controller/planDeFabricacionController'], function() {
    App.Component.PlanDeFabricacionComponent = App.Component._CRUDComponent.extend({
        name: 'planDeFabricacion',
        model: App.Model.PlanDeFabricacionModel,
        listModel: App.Model.PlanDeFabricacionList,
        controller : App.Controller.PlanDeFabricacionController
    });
    return App.Component.PlanDeFabricacionComponent;
});