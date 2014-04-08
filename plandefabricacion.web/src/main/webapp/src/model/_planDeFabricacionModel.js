define([], function() {
    App.Model._PlanDeFabricacionModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'fecha' : ''
 ,  
		 'nombreProducto' : ''
 ,  
		 'cantidad' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
             if(name=='fecha'){
                   var dateConverter = App.Utils.Converter.date;
                   return dateConverter.unserialize(this.get('fecha'), this);
             }
         return this.get(name);
        }
    });

    App.Model._PlanDeFabricacionList = Backbone.Collection.extend({
        model: App.Model._PlanDeFabricacionModel,
        initialize: function() {
        }

    });
    return App.Model._PlanDeFabricacionModel;
});