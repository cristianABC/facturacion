define([], function() {
    App.Model._OrdenDeFabricacionModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'cantidad' : ''
 ,  
		 'fecha' : ''
 ,  
		 'nombreProducto' : ''
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

    App.Model._OrdenDeFabricacionList = Backbone.Collection.extend({
        model: App.Model._OrdenDeFabricacionModel,
        initialize: function() {
        }

    });
    return App.Model._OrdenDeFabricacionModel;
});