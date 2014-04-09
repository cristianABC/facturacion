define(['controller/_facturaController'], function() {
    App.Controller.FacturaController = App.Controller._FacturaController.extend({
       
        postInit: function(options){
            var self = this;
            Backbone.on('factura-model-error', function(params) {
                var error = params.error;
                Backbone.trigger(self.componentId + '-' + 'error',
                         {event: 'facturaq-model', view: self, error:{ responseText: error}});
            });
        }
       
    });
    return App.Controller.FacturaController;
});