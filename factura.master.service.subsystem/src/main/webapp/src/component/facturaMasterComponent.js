define(['controller/selectionController', 'model/cacheModel', 'model/facturaMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/facturaComponent',
 'component/ordenReaprovicionamientoComponent'
 ,
 'component/ordenDeFabricacionComponent'
 ,
 'component/planDeFabricacionComponent'
 ,
 'component/ordenDespachoComponent'
 
 ],function(SelectionController, CacheModel, FacturaMasterModel, CRUDComponent, TabController, FacturaComponent,
 OrdenReaprovicionamientoComponent
 ,
 OrdenDeFabricacionComponent
 ,
 PlanDeFabricacionComponent
 ,
 OrdenDespachoComponent
 ) {
    App.Component.FacturaMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('facturaMaster');
            var uComponent = new FacturaComponent();
            uComponent.initialize();
            uComponent.render('main');
            Backbone.on(uComponent.componentId + '-post-factura-create', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-post-factura-edit', function(params) {
                self.renderChilds(params);
            });
            Backbone.on(uComponent.componentId + '-pre-factura-list', function() {
                self.hideChilds();
            });
            Backbone.on('factura-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'factura-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-factura-save', function(params) {
                self.model.set('facturaEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var ordenReaprovicionamientoModels = self.ordenReaprovicionamientoComponent.componentController.ordenReaprovicionamientoModelList;
                self.model.set('listOrdenReaprovicionamiento', []);
                self.model.set('createOrdenReaprovicionamiento', []);
                self.model.set('updateOrdenReaprovicionamiento', []);
                self.model.set('deleteOrdenReaprovicionamiento', []);
                for (var i = 0; i < ordenReaprovicionamientoModels.models.length; i++) {
                    var m = ordenReaprovicionamientoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createOrdenReaprovicionamiento').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateOrdenReaprovicionamiento').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < ordenReaprovicionamientoModels.deletedModels.length; i++) {
                    var m = ordenReaprovicionamientoModels.deletedModels[i];
                    self.model.get('deleteOrdenReaprovicionamiento').push(m.toJSON());
                }
                var ordenDeFabricacionModels = self.ordenDeFabricacionComponent.componentController.ordenDeFabricacionModelList;
                self.model.set('listOrdenDeFabricacion', []);
                self.model.set('createOrdenDeFabricacion', []);
                self.model.set('updateOrdenDeFabricacion', []);
                self.model.set('deleteOrdenDeFabricacion', []);
                for (var i = 0; i < ordenDeFabricacionModels.models.length; i++) {
                    var m = ordenDeFabricacionModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createOrdenDeFabricacion').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateOrdenDeFabricacion').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < ordenDeFabricacionModels.deletedModels.length; i++) {
                    var m = ordenDeFabricacionModels.deletedModels[i];
                    self.model.get('deleteOrdenDeFabricacion').push(m.toJSON());
                }
                var planDeFabricacionModels = self.planDeFabricacionComponent.componentController.planDeFabricacionModelList;
                self.model.set('listPlanDeFabricacion', []);
                self.model.set('createPlanDeFabricacion', []);
                self.model.set('updatePlanDeFabricacion', []);
                self.model.set('deletePlanDeFabricacion', []);
                for (var i = 0; i < planDeFabricacionModels.models.length; i++) {
                    var m = planDeFabricacionModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createPlanDeFabricacion').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updatePlanDeFabricacion').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < planDeFabricacionModels.deletedModels.length; i++) {
                    var m = planDeFabricacionModels.deletedModels[i];
                    self.model.get('deletePlanDeFabricacion').push(m.toJSON());
                }
                var ordenDespachoModels = self.ordenDespachoComponent.componentController.ordenDespachoModelList;
                self.model.set('listOrdenDespacho', []);
                self.model.set('createOrdenDespacho', []);
                self.model.set('updateOrdenDespacho', []);
                self.model.set('deleteOrdenDespacho', []);
                for (var i = 0; i < ordenDespachoModels.models.length; i++) {
                    var m = ordenDespachoModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createOrdenDespacho').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateOrdenDespacho').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < ordenDespachoModels.deletedModels.length; i++) {
                    var m = ordenDespachoModels.deletedModels[i];
                    self.model.get('deleteOrdenDespacho').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-master-save', view: self, error: error});
                    }
                });
            });
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "OrdenReaprovicionamiento", name: "ordenReaprovicionamiento", enable: true},
                            ,
                            {label: "OrdenDeFabricacion", name: "ordenDeFabricacion", enable: true},
                            ,
                            {label: "PlanDeFabricacion", name: "planDeFabricacion", enable: true},
                            ,
                            {label: "OrdenDespacho", name: "ordenDespacho", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.FacturaMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
					self.ordenReaprovicionamientoComponent = new OrdenReaprovicionamientoComponent();
                    self.ordenReaprovicionamientoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.OrdenReaprovicionamientoModel), self.model.get('listOrdenReaprovicionamiento'));
                    self.ordenReaprovicionamientoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.OrdenReaprovicionamientoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.OrdenReaprovicionamientoModel, App.Model.OrdenReaprovicionamientoList, self.ordenReaprovicionamientoModels)
                    });
                    self.ordenReaprovicionamientoComponent.render(self.tabs.getTabHtmlId('ordenReaprovicionamiento'));
                    Backbone.on(self.ordenReaprovicionamientoComponent.componentId + '-post-ordenReaprovicionamiento-create', function(params) {
                        params.view.currentOrdenReaprovicionamientoModel.setCacheList(params.view.ordenReaprovicionamientoModelList);
                    });
					self.ordenDeFabricacionComponent = new OrdenDeFabricacionComponent();
                    self.ordenDeFabricacionModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.OrdenDeFabricacionModel), self.model.get('listOrdenDeFabricacion'));
                    self.ordenDeFabricacionComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.OrdenDeFabricacionModel),
                        listModelClass: App.Utils.createCacheList(App.Model.OrdenDeFabricacionModel, App.Model.OrdenDeFabricacionList, self.ordenDeFabricacionModels)
                    });
                    self.ordenDeFabricacionComponent.render(self.tabs.getTabHtmlId('ordenDeFabricacion'));
                    Backbone.on(self.ordenDeFabricacionComponent.componentId + '-post-ordenDeFabricacion-create', function(params) {
                        params.view.currentOrdenDeFabricacionModel.setCacheList(params.view.ordenDeFabricacionModelList);
                    });
					self.planDeFabricacionComponent = new PlanDeFabricacionComponent();
                    self.planDeFabricacionModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.PlanDeFabricacionModel), self.model.get('listPlanDeFabricacion'));
                    self.planDeFabricacionComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.PlanDeFabricacionModel),
                        listModelClass: App.Utils.createCacheList(App.Model.PlanDeFabricacionModel, App.Model.PlanDeFabricacionList, self.planDeFabricacionModels)
                    });
                    self.planDeFabricacionComponent.render(self.tabs.getTabHtmlId('planDeFabricacion'));
                    Backbone.on(self.planDeFabricacionComponent.componentId + '-post-planDeFabricacion-create', function(params) {
                        params.view.currentPlanDeFabricacionModel.setCacheList(params.view.planDeFabricacionModelList);
                    });
					self.ordenDespachoComponent = new OrdenDespachoComponent();
                    self.ordenDespachoModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.OrdenDespachoModel), self.model.get('listOrdenDespacho'));
                    self.ordenDespachoComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.OrdenDespachoModel),
                        listModelClass: App.Utils.createCacheList(App.Model.OrdenDespachoModel, App.Model.OrdenDespachoList, self.ordenDespachoModels)
                    });
                    self.ordenDespachoComponent.render(self.tabs.getTabHtmlId('ordenDespacho'));
                    Backbone.on(self.ordenDespachoComponent.componentId + '-post-ordenDespacho-create', function(params) {
                        params.view.currentOrdenDespachoModel.setCacheList(params.view.ordenDespachoModelList);
                    });
                    self.ordenReaprovicionamientoToolbarModel = self.ordenReaprovicionamientoComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.ordenReaprovicionamientoComponent.setToolbarModel(self.ordenReaprovicionamientoToolbarModel);                    
                    self.ordenDeFabricacionToolbarModel = self.ordenDeFabricacionComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.ordenDeFabricacionComponent.setToolbarModel(self.ordenDeFabricacionToolbarModel);                    
                    self.planDeFabricacionToolbarModel = self.planDeFabricacionComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.planDeFabricacionComponent.setToolbarModel(self.planDeFabricacionToolbarModel);                    
                    self.ordenDespachoToolbarModel = self.ordenDespachoComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.ordenDespachoComponent.setToolbarModel(self.ordenDespachoToolbarModel);                    
                	
                     
                
                    $('#tabs').show();
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.FacturaMasterModel({id: params.id});
                self.model.fetch(options);
            } else {
                self.model = new App.Model.FacturaMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.FacturaMasterComponent;
});