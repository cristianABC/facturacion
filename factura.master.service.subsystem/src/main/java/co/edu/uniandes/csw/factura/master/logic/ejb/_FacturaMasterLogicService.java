package co.edu.uniandes.csw.factura.master.logic.ejb;

import co.edu.uniandes.csw.ordenreaprovicionamiento.logic.dto.OrdenReaprovicionamientoDTO;
import co.edu.uniandes.csw.ordenreaprovicionamiento.persistence.api.IOrdenReaprovicionamientoPersistence;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.api.IOrdenDeFabricacionPersistence;
import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.persistence.api.IPlanDeFabricacionPersistence;
import co.edu.uniandes.csw.ordendespacho.logic.dto.OrdenDespachoDTO;
import co.edu.uniandes.csw.ordendespacho.persistence.api.IOrdenDespachoPersistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.api._IFacturaMasterLogicService;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.master.persistence.api.IFacturaMasterPersistence;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenReaprovicionamientoEntity;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaPlanDeFabricacionEntity;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDespachoEntity;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import javax.inject.Inject;

public abstract class _FacturaMasterLogicService implements _IFacturaMasterLogicService {

    @Inject
    protected IFacturaPersistence facturaPersistance;
    @Inject
    protected IFacturaMasterPersistence facturaMasterPersistance;
    @Inject
    protected IOrdenReaprovicionamientoPersistence ordenReaprovicionamientoPersistance;
    @Inject
    protected IOrdenDeFabricacionPersistence ordenDeFabricacionPersistance;
    @Inject
    protected IPlanDeFabricacionPersistence planDeFabricacionPersistance;
    @Inject
    protected IOrdenDespachoPersistence ordenDespachoPersistance;

    public FacturaMasterDTO createMasterFactura(FacturaMasterDTO factura) {
        FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(factura.getFacturaEntity());
        if (factura.getCreateOrdenReaprovicionamiento() != null) {
            for (OrdenReaprovicionamientoDTO ordenReaprovicionamientoDTO : factura.getCreateOrdenReaprovicionamiento()) {
                OrdenReaprovicionamientoDTO persistedOrdenReaprovicionamientoDTO = ordenReaprovicionamientoPersistance.createOrdenReaprovicionamiento(ordenReaprovicionamientoDTO);
                FacturaOrdenReaprovicionamientoEntity facturaOrdenReaprovicionamientoEntity = new FacturaOrdenReaprovicionamientoEntity(persistedFacturaDTO.getId(), persistedOrdenReaprovicionamientoDTO.getId());
                facturaMasterPersistance.createFacturaOrdenReaprovicionamiento(facturaOrdenReaprovicionamientoEntity);
            }
        }
        if (factura.getCreateOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : factura.getCreateOrdenDeFabricacion()) {
                OrdenDeFabricacionDTO persistedOrdenDeFabricacionDTO = ordenDeFabricacionPersistance.createOrdenDeFabricacion(ordenDeFabricacionDTO);
                FacturaOrdenDeFabricacionEntity facturaOrdenDeFabricacionEntity = new FacturaOrdenDeFabricacionEntity(persistedFacturaDTO.getId(), persistedOrdenDeFabricacionDTO.getId());
                facturaMasterPersistance.createFacturaOrdenDeFabricacion(facturaOrdenDeFabricacionEntity);
            }
        }
        if (factura.getCreatePlanDeFabricacion() != null) {
            for (PlanDeFabricacionDTO planDeFabricacionDTO : factura.getCreatePlanDeFabricacion()) {
                PlanDeFabricacionDTO persistedPlanDeFabricacionDTO = planDeFabricacionPersistance.createPlanDeFabricacion(planDeFabricacionDTO);
                FacturaPlanDeFabricacionEntity facturaPlanDeFabricacionEntity = new FacturaPlanDeFabricacionEntity(persistedFacturaDTO.getId(), persistedPlanDeFabricacionDTO.getId());
                facturaMasterPersistance.createFacturaPlanDeFabricacion(facturaPlanDeFabricacionEntity);
            }
        }
        if (factura.getCreateOrdenDespacho() != null) {
            for (OrdenDespachoDTO ordenDespachoDTO : factura.getCreateOrdenDespacho()) {
                OrdenDespachoDTO persistedOrdenDespachoDTO = ordenDespachoPersistance.createOrdenDespacho(ordenDespachoDTO);
                FacturaOrdenDespachoEntity facturaOrdenDespachoEntity = new FacturaOrdenDespachoEntity(persistedFacturaDTO.getId(), persistedOrdenDespachoDTO.getId());
                facturaMasterPersistance.createFacturaOrdenDespacho(facturaOrdenDespachoEntity);
            }
        }
        return factura;
    }

    public FacturaMasterDTO getMasterFactura(Long id) {
        return facturaMasterPersistance.getFactura(id);
    }

    public void deleteMasterFactura(Long id) {
        facturaPersistance.deleteFactura(id);
    }

    public void updateMasterFactura(FacturaMasterDTO factura) {
        facturaPersistance.updateFactura(factura.getFacturaEntity());

        //---- FOR RELATIONSHIP
        // persist new ordenReaprovicionamiento
        if (factura.getCreateOrdenReaprovicionamiento() != null) {
            for (OrdenReaprovicionamientoDTO ordenReaprovicionamientoDTO : factura.getCreateOrdenReaprovicionamiento()) {
                OrdenReaprovicionamientoDTO persistedOrdenReaprovicionamientoDTO = ordenReaprovicionamientoPersistance.createOrdenReaprovicionamiento(ordenReaprovicionamientoDTO);
                FacturaOrdenReaprovicionamientoEntity facturaOrdenReaprovicionamientoEntity = new FacturaOrdenReaprovicionamientoEntity(factura.getFacturaEntity().getId(), persistedOrdenReaprovicionamientoDTO.getId());
                facturaMasterPersistance.createFacturaOrdenReaprovicionamiento(facturaOrdenReaprovicionamientoEntity);
            }
        }
        // update ordenReaprovicionamiento
        if (factura.getUpdateOrdenReaprovicionamiento() != null) {
            for (OrdenReaprovicionamientoDTO ordenReaprovicionamientoDTO : factura.getUpdateOrdenReaprovicionamiento()) {
                ordenReaprovicionamientoPersistance.updateOrdenReaprovicionamiento(ordenReaprovicionamientoDTO);
            }
        }
        // delete ordenReaprovicionamiento
        if (factura.getDeleteOrdenReaprovicionamiento() != null) {
            for (OrdenReaprovicionamientoDTO ordenReaprovicionamientoDTO : factura.getDeleteOrdenReaprovicionamiento()) {
                facturaMasterPersistance.deleteFacturaOrdenReaprovicionamiento(factura.getFacturaEntity().getId(), ordenReaprovicionamientoDTO.getId());
                ordenReaprovicionamientoPersistance.deleteOrdenReaprovicionamiento(ordenReaprovicionamientoDTO.getId());
            }
        }
        // persist new ordenDeFabricacion
        if (factura.getCreateOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : factura.getCreateOrdenDeFabricacion()) {
                OrdenDeFabricacionDTO persistedOrdenDeFabricacionDTO = ordenDeFabricacionPersistance.createOrdenDeFabricacion(ordenDeFabricacionDTO);
                FacturaOrdenDeFabricacionEntity facturaOrdenDeFabricacionEntity = new FacturaOrdenDeFabricacionEntity(factura.getFacturaEntity().getId(), persistedOrdenDeFabricacionDTO.getId());
                facturaMasterPersistance.createFacturaOrdenDeFabricacion(facturaOrdenDeFabricacionEntity);
            }
        }
        // update ordenDeFabricacion
        if (factura.getUpdateOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : factura.getUpdateOrdenDeFabricacion()) {
                ordenDeFabricacionPersistance.updateOrdenDeFabricacion(ordenDeFabricacionDTO);
            }
        }
        // delete ordenDeFabricacion
        if (factura.getDeleteOrdenDeFabricacion() != null) {
            for (OrdenDeFabricacionDTO ordenDeFabricacionDTO : factura.getDeleteOrdenDeFabricacion()) {
                facturaMasterPersistance.deleteFacturaOrdenDeFabricacion(factura.getFacturaEntity().getId(), ordenDeFabricacionDTO.getId());
                ordenDeFabricacionPersistance.deleteOrdenDeFabricacion(ordenDeFabricacionDTO.getId());
            }
        }
        // persist new planDeFabricacion
        if (factura.getCreatePlanDeFabricacion() != null) {
            for (PlanDeFabricacionDTO planDeFabricacionDTO : factura.getCreatePlanDeFabricacion()) {
                PlanDeFabricacionDTO persistedPlanDeFabricacionDTO = planDeFabricacionPersistance.createPlanDeFabricacion(planDeFabricacionDTO);
                FacturaPlanDeFabricacionEntity facturaPlanDeFabricacionEntity = new FacturaPlanDeFabricacionEntity(factura.getFacturaEntity().getId(), persistedPlanDeFabricacionDTO.getId());
                facturaMasterPersistance.createFacturaPlanDeFabricacion(facturaPlanDeFabricacionEntity);
            }
        }
        // update planDeFabricacion
        if (factura.getUpdatePlanDeFabricacion() != null) {
            for (PlanDeFabricacionDTO planDeFabricacionDTO : factura.getUpdatePlanDeFabricacion()) {
                planDeFabricacionPersistance.updatePlanDeFabricacion(planDeFabricacionDTO);
            }
        }
        // delete planDeFabricacion
        if (factura.getDeletePlanDeFabricacion() != null) {
            for (PlanDeFabricacionDTO planDeFabricacionDTO : factura.getDeletePlanDeFabricacion()) {
                facturaMasterPersistance.deleteFacturaPlanDeFabricacion(factura.getFacturaEntity().getId(), planDeFabricacionDTO.getId());
                planDeFabricacionPersistance.deletePlanDeFabricacion(planDeFabricacionDTO.getId());
            }
        }
        // persist new ordenDespacho
        if (factura.getCreateOrdenDespacho() != null) {
            for (OrdenDespachoDTO ordenDespachoDTO : factura.getCreateOrdenDespacho()) {
                OrdenDespachoDTO persistedOrdenDespachoDTO = ordenDespachoPersistance.createOrdenDespacho(ordenDespachoDTO);
                FacturaOrdenDespachoEntity facturaOrdenDespachoEntity = new FacturaOrdenDespachoEntity(factura.getFacturaEntity().getId(), persistedOrdenDespachoDTO.getId());
                facturaMasterPersistance.createFacturaOrdenDespacho(facturaOrdenDespachoEntity);
            }
        }
        // update ordenDespacho
        if (factura.getUpdateOrdenDespacho() != null) {
            for (OrdenDespachoDTO ordenDespachoDTO : factura.getUpdateOrdenDespacho()) {
                ordenDespachoPersistance.updateOrdenDespacho(ordenDespachoDTO);
            }
        }
        // delete ordenDespacho
        if (factura.getDeleteOrdenDespacho() != null) {
            for (OrdenDespachoDTO ordenDespachoDTO : factura.getDeleteOrdenDespacho()) {
                facturaMasterPersistance.deleteFacturaOrdenDespacho(factura.getFacturaEntity().getId(), ordenDespachoDTO.getId());
                ordenDespachoPersistance.deleteOrdenDespacho(ordenDespachoDTO.getId());
            }
        }
    }
}
