define(['model/_planDeFabricacionModel'], function() {
    App.Model.PlanDeFabricacionModel = App.Model._PlanDeFabricacionModel.extend({

    });

    App.Model.PlanDeFabricacionList = App.Model._PlanDeFabricacionList.extend({
        model: App.Model.PlanDeFabricacionModel
    });

    return  App.Model.PlanDeFabricacionModel;

});