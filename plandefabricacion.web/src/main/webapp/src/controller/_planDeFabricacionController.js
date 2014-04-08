define(['model/planDeFabricacionModel'], function(planDeFabricacionModel) {
    App.Controller._PlanDeFabricacionController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#planDeFabricacion').html());
            this.listTemplate = _.template($('#planDeFabricacionList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'planDeFabricacion-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'planDeFabricacion-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'planDeFabricacion-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'planDeFabricacion-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-planDeFabricacion-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'planDeFabricacion-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-planDeFabricacion-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-planDeFabricacion-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-planDeFabricacion-create', {view: this});
                this.currentPlanDeFabricacionModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-planDeFabricacion-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-planDeFabricacion-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-planDeFabricacion-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-planDeFabricacion-list', {view: this, data: data});
                var self = this;
				if(!this.planDeFabricacionModelList){
                 this.planDeFabricacionModelList = new this.listModelClass();
				}
                this.planDeFabricacionModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-planDeFabricacion-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'planDeFabricacion-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-planDeFabricacion-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-planDeFabricacion-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-planDeFabricacion-edit', {view: this, id: id, data: data});
                if (this.planDeFabricacionModelList) {
                    this.currentPlanDeFabricacionModel = this.planDeFabricacionModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-planDeFabricacion-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentPlanDeFabricacionModel = new this.modelClass({id: id});
                    this.currentPlanDeFabricacionModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-planDeFabricacion-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'planDeFabricacion-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-planDeFabricacion-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-planDeFabricacion-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-planDeFabricacion-delete', {view: this, id: id});
                var deleteModel;
                if (this.planDeFabricacionModelList) {
                    deleteModel = this.planDeFabricacionModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-planDeFabricacion-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'planDeFabricacion-delete', view: self, error: error});
                    }
                });
            }
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-planDeFabricacionForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-planDeFabricacion-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-planDeFabricacion-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-planDeFabricacion-save', {view: this, model : model});
                this.currentPlanDeFabricacionModel.set(model);
                this.currentPlanDeFabricacionModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-planDeFabricacion-save', {model: self.currentPlanDeFabricacionModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'planDeFabricacion-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({planDeFabricacions: self.planDeFabricacionModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({planDeFabricacion: self.currentPlanDeFabricacionModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._PlanDeFabricacionController;
});