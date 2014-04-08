define(['model/ordenDeFabricacionModel'], function(ordenDeFabricacionModel) {
    App.Controller._OrdenDeFabricacionController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#ordenDeFabricacion').html());
            this.listTemplate = _.template($('#ordenDeFabricacionList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'ordenDeFabricacion-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'ordenDeFabricacion-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'ordenDeFabricacion-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'ordenDeFabricacion-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-ordenDeFabricacion-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'ordenDeFabricacion-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-ordenDeFabricacion-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-ordenDeFabricacion-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-ordenDeFabricacion-create', {view: this});
                this.currentOrdenDeFabricacionModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-ordenDeFabricacion-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-ordenDeFabricacion-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-ordenDeFabricacion-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-ordenDeFabricacion-list', {view: this, data: data});
                var self = this;
				if(!this.ordenDeFabricacionModelList){
                 this.ordenDeFabricacionModelList = new this.listModelClass();
				}
                this.ordenDeFabricacionModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-ordenDeFabricacion-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'ordenDeFabricacion-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-ordenDeFabricacion-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-ordenDeFabricacion-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-ordenDeFabricacion-edit', {view: this, id: id, data: data});
                if (this.ordenDeFabricacionModelList) {
                    this.currentOrdenDeFabricacionModel = this.ordenDeFabricacionModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-ordenDeFabricacion-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentOrdenDeFabricacionModel = new this.modelClass({id: id});
                    this.currentOrdenDeFabricacionModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-ordenDeFabricacion-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'ordenDeFabricacion-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-ordenDeFabricacion-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-ordenDeFabricacion-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-ordenDeFabricacion-delete', {view: this, id: id});
                var deleteModel;
                if (this.ordenDeFabricacionModelList) {
                    deleteModel = this.ordenDeFabricacionModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-ordenDeFabricacion-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'ordenDeFabricacion-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-ordenDeFabricacionForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-ordenDeFabricacion-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-ordenDeFabricacion-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-ordenDeFabricacion-save', {view: this, model : model});
                this.currentOrdenDeFabricacionModel.set(model);
                this.currentOrdenDeFabricacionModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-ordenDeFabricacion-save', {model: self.currentOrdenDeFabricacionModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'ordenDeFabricacion-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({ordenDeFabricacions: self.ordenDeFabricacionModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({ordenDeFabricacion: self.currentOrdenDeFabricacionModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._OrdenDeFabricacionController;
});