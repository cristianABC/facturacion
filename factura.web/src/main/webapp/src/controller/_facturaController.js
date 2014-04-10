define(['model/facturaModel'], function(facturaModel) {
    App.Controller._FacturaController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#factura').html());
            this.listTemplate = _.template($('#facturaList').html());
            this.searchTemplate = _.template($('#facturaSearch').html() + $('#facturaList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'factura-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'factura-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'factura-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'factura-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-factura-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'toolbar-search', function(params) {
                self.search(params);
            });
            Backbone.on(this.componentId + '-factura-search', function(params) {
                self.facturaSearch(params);
            });
            
            Backbone.on(this.componentId + '-' + 'factura-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-factura-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-factura-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-factura-create', {view: this});
                this.currentFacturaModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-factura-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-factura-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-factura-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-factura-list', {view: this, data: data});
                var self = this;
				if(!this.facturaModelList){
                 this.facturaModelList = new this.listModelClass();
				}
                this.facturaModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-factura-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-factura-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-factura-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-factura-edit', {view: this, id: id, data: data});
                if (this.facturaModelList) {
                    this.currentFacturaModel = this.facturaModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-factura-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentFacturaModel = new this.modelClass({id: id});
                    this.currentFacturaModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-factura-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-factura-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-factura-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-factura-delete', {view: this, id: id});
                var deleteModel;
                if (this.facturaModelList) {
                    deleteModel = this.facturaModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-factura-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-facturaForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-factura-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-factura-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-factura-save', {view: this, model : model});
                this.currentFacturaModel.set(model);
                this.currentFacturaModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-factura-save', {model: self.currentFacturaModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({facturas: self.facturaModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
         search: function() {
            this.currentFacturaModel = new App.Model.FacturaModel();
            this.facturaModelList = new this.listModelClass();
            this._renderSearch();
        },
         searchi: function(user, callback, callbackError) {
            console.log('Factura Search: ');
            $.ajax({
                url: '/factura.service.subsystem.web/webresources/Factura/search',
                type: 'POST',
                data: JSON.stringify(user),
                contentType: 'application/json'
            }).done(_.bind(function(data) {
                callback(data);
            }, this)).error(_.bind(function(data) {
                callbackError(data);
            }, this));
        },
        facturaSearch: function(params) {
            var self = this;
            var model = $('#' + this.componentId + '-facturaSearch').serializeObject();
            this.currentFacturaModel.set(model);
            this.searchi(self.currentFacturaModel, function(data) {
                self.facturaModelList = new App.Model.FacturaList();
                _.each(data, function(d) {
                    var model = new App.Model.FacturaModel(d);
                    self.facturaModelList.models.push(model);
                });
                self._renderSearch(params);
            }, function(data) {
                Backbone.trigger(self.componentId + '-' + 'error', {event: 'factura-search', view: self, id: '', data: data, error: {textResponse: 'Error in factura search'}});
            });
        },
 
        _renderSearch: function(params) {
 
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.searchTemplate({componentId: self.componentId,
                    facturas: self.facturaModelList.models,
                    factura: self.currentFacturaModel,
                    showEdit: false,
                    showDelete: false
                }));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({factura: self.currentFacturaModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._FacturaController;
});