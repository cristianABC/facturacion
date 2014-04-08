package co.edu.uniandes.csw.factura.master.logic.dto;

import co.edu.uniandes.csw.ordenreaprovicionamiento.logic.dto.OrdenReaprovicionamientoDTO;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.ordendespacho.logic.dto.OrdenDespachoDTO;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _FacturaMasterDTO {

 
    protected FacturaDTO facturaEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public FacturaDTO getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaDTO facturaEntity) {
        this.facturaEntity = facturaEntity;
    }
    
    public List<OrdenReaprovicionamientoDTO> createOrdenReaprovicionamiento;
    public List<OrdenReaprovicionamientoDTO> updateOrdenReaprovicionamiento;
    public List<OrdenReaprovicionamientoDTO> deleteOrdenReaprovicionamiento;
    public List<OrdenReaprovicionamientoDTO> listOrdenReaprovicionamiento;	
    public List<OrdenDeFabricacionDTO> createOrdenDeFabricacion;
    public List<OrdenDeFabricacionDTO> updateOrdenDeFabricacion;
    public List<OrdenDeFabricacionDTO> deleteOrdenDeFabricacion;
    public List<OrdenDeFabricacionDTO> listOrdenDeFabricacion;	
    public List<PlanDeFabricacionDTO> createPlanDeFabricacion;
    public List<PlanDeFabricacionDTO> updatePlanDeFabricacion;
    public List<PlanDeFabricacionDTO> deletePlanDeFabricacion;
    public List<PlanDeFabricacionDTO> listPlanDeFabricacion;	
    public List<OrdenDespachoDTO> createOrdenDespacho;
    public List<OrdenDespachoDTO> updateOrdenDespacho;
    public List<OrdenDespachoDTO> deleteOrdenDespacho;
    public List<OrdenDespachoDTO> listOrdenDespacho;	
	
	
	
    public List<OrdenReaprovicionamientoDTO> getCreateOrdenReaprovicionamiento(){ return createOrdenReaprovicionamiento; };
    public void setCreateOrdenReaprovicionamiento(List<OrdenReaprovicionamientoDTO> createOrdenReaprovicionamiento){ this.createOrdenReaprovicionamiento=createOrdenReaprovicionamiento; };
    public List<OrdenReaprovicionamientoDTO> getUpdateOrdenReaprovicionamiento(){ return updateOrdenReaprovicionamiento; };
    public void setUpdateOrdenReaprovicionamiento(List<OrdenReaprovicionamientoDTO> updateOrdenReaprovicionamiento){ this.updateOrdenReaprovicionamiento=updateOrdenReaprovicionamiento; };
    public List<OrdenReaprovicionamientoDTO> getDeleteOrdenReaprovicionamiento(){ return deleteOrdenReaprovicionamiento; };
    public void setDeleteOrdenReaprovicionamiento(List<OrdenReaprovicionamientoDTO> deleteOrdenReaprovicionamiento){ this.deleteOrdenReaprovicionamiento=deleteOrdenReaprovicionamiento; };
    public List<OrdenReaprovicionamientoDTO> getListOrdenReaprovicionamiento(){ return listOrdenReaprovicionamiento; };
    public void setListOrdenReaprovicionamiento(List<OrdenReaprovicionamientoDTO> listOrdenReaprovicionamiento){ this.listOrdenReaprovicionamiento=listOrdenReaprovicionamiento; };	
    public List<OrdenDeFabricacionDTO> getCreateOrdenDeFabricacion(){ return createOrdenDeFabricacion; };
    public void setCreateOrdenDeFabricacion(List<OrdenDeFabricacionDTO> createOrdenDeFabricacion){ this.createOrdenDeFabricacion=createOrdenDeFabricacion; };
    public List<OrdenDeFabricacionDTO> getUpdateOrdenDeFabricacion(){ return updateOrdenDeFabricacion; };
    public void setUpdateOrdenDeFabricacion(List<OrdenDeFabricacionDTO> updateOrdenDeFabricacion){ this.updateOrdenDeFabricacion=updateOrdenDeFabricacion; };
    public List<OrdenDeFabricacionDTO> getDeleteOrdenDeFabricacion(){ return deleteOrdenDeFabricacion; };
    public void setDeleteOrdenDeFabricacion(List<OrdenDeFabricacionDTO> deleteOrdenDeFabricacion){ this.deleteOrdenDeFabricacion=deleteOrdenDeFabricacion; };
    public List<OrdenDeFabricacionDTO> getListOrdenDeFabricacion(){ return listOrdenDeFabricacion; };
    public void setListOrdenDeFabricacion(List<OrdenDeFabricacionDTO> listOrdenDeFabricacion){ this.listOrdenDeFabricacion=listOrdenDeFabricacion; };	
    public List<PlanDeFabricacionDTO> getCreatePlanDeFabricacion(){ return createPlanDeFabricacion; };
    public void setCreatePlanDeFabricacion(List<PlanDeFabricacionDTO> createPlanDeFabricacion){ this.createPlanDeFabricacion=createPlanDeFabricacion; };
    public List<PlanDeFabricacionDTO> getUpdatePlanDeFabricacion(){ return updatePlanDeFabricacion; };
    public void setUpdatePlanDeFabricacion(List<PlanDeFabricacionDTO> updatePlanDeFabricacion){ this.updatePlanDeFabricacion=updatePlanDeFabricacion; };
    public List<PlanDeFabricacionDTO> getDeletePlanDeFabricacion(){ return deletePlanDeFabricacion; };
    public void setDeletePlanDeFabricacion(List<PlanDeFabricacionDTO> deletePlanDeFabricacion){ this.deletePlanDeFabricacion=deletePlanDeFabricacion; };
    public List<PlanDeFabricacionDTO> getListPlanDeFabricacion(){ return listPlanDeFabricacion; };
    public void setListPlanDeFabricacion(List<PlanDeFabricacionDTO> listPlanDeFabricacion){ this.listPlanDeFabricacion=listPlanDeFabricacion; };	
    public List<OrdenDespachoDTO> getCreateOrdenDespacho(){ return createOrdenDespacho; };
    public void setCreateOrdenDespacho(List<OrdenDespachoDTO> createOrdenDespacho){ this.createOrdenDespacho=createOrdenDespacho; };
    public List<OrdenDespachoDTO> getUpdateOrdenDespacho(){ return updateOrdenDespacho; };
    public void setUpdateOrdenDespacho(List<OrdenDespachoDTO> updateOrdenDespacho){ this.updateOrdenDespacho=updateOrdenDespacho; };
    public List<OrdenDespachoDTO> getDeleteOrdenDespacho(){ return deleteOrdenDespacho; };
    public void setDeleteOrdenDespacho(List<OrdenDespachoDTO> deleteOrdenDespacho){ this.deleteOrdenDespacho=deleteOrdenDespacho; };
    public List<OrdenDespachoDTO> getListOrdenDespacho(){ return listOrdenDespacho; };
    public void setListOrdenDespacho(List<OrdenDespachoDTO> listOrdenDespacho){ this.listOrdenDespacho=listOrdenDespacho; };	
	
	
}

