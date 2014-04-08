package co.edu.uniandes.csw.factura.master.persistence;
import co.edu.uniandes.csw.ordenreaprovicionamiento.logic.dto.OrdenReaprovicionamientoDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenReaprovicionamientoEntity;
import co.edu.uniandes.csw.ordenreaprovicionamiento.persistence.converter.OrdenReaprovicionamientoConverter;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.ordendefabricacion.persistence.converter.OrdenDeFabricacionConverter;
import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaPlanDeFabricacionEntity;
import co.edu.uniandes.csw.plandefabricacion.persistence.converter.PlanDeFabricacionConverter;
import co.edu.uniandes.csw.ordendespacho.logic.dto.OrdenDespachoDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDespachoEntity;
import co.edu.uniandes.csw.ordendespacho.persistence.converter.OrdenDespachoConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.master.persistence.api._IFacturaMasterPersistence;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _FacturaMasterPersistence implements _IFacturaMasterPersistence {

    @PersistenceContext(unitName = "FacturaMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IFacturaPersistence facturaPersistence;  

    public FacturaMasterDTO getFactura(Long facturaId) {
        FacturaMasterDTO facturaMasterDTO = new FacturaMasterDTO();
        FacturaDTO factura = facturaPersistence.getFactura(facturaId);
        facturaMasterDTO.setFacturaEntity(factura);
        facturaMasterDTO.setListOrdenReaprovicionamiento(getOrdenReaprovicionamientoListForFactura(facturaId));
        facturaMasterDTO.setListOrdenDeFabricacion(getOrdenDeFabricacionListForFactura(facturaId));
        facturaMasterDTO.setListPlanDeFabricacion(getPlanDeFabricacionListForFactura(facturaId));
        facturaMasterDTO.setListOrdenDespacho(getOrdenDespachoListForFactura(facturaId));
        return facturaMasterDTO;
    }

    public FacturaOrdenReaprovicionamientoEntity createFacturaOrdenReaprovicionamiento(FacturaOrdenReaprovicionamientoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteFacturaOrdenReaprovicionamiento(Long facturaId, Long ordenReaprovicionamientoId) {
        Query q = entityManager.createNamedQuery("FacturaOrdenReaprovicionamientoEntity.deleteFacturaOrdenReaprovicionamiento");
        q.setParameter("facturaId", facturaId);
        q.setParameter("ordenReaprovicionamientoId", ordenReaprovicionamientoId);
        q.executeUpdate();
    }

    public List<OrdenReaprovicionamientoDTO> getOrdenReaprovicionamientoListForFactura(Long facturaId) {
        ArrayList<OrdenReaprovicionamientoDTO> resp = new ArrayList<OrdenReaprovicionamientoDTO>();
        Query q = entityManager.createNamedQuery("FacturaOrdenReaprovicionamientoEntity.getOrdenReaprovicionamientoListForFactura");
        q.setParameter("facturaId", facturaId);
        List<FacturaOrdenReaprovicionamientoEntity> qResult =  q.getResultList();
        for (FacturaOrdenReaprovicionamientoEntity facturaOrdenReaprovicionamientoEntity : qResult) { 
            if(facturaOrdenReaprovicionamientoEntity.getOrdenReaprovicionamientoEntity()==null){
                entityManager.refresh(facturaOrdenReaprovicionamientoEntity);
            }
            resp.add(OrdenReaprovicionamientoConverter.entity2PersistenceDTO(facturaOrdenReaprovicionamientoEntity.getOrdenReaprovicionamientoEntity()));
        }
        return resp;
    }
    public FacturaOrdenDeFabricacionEntity createFacturaOrdenDeFabricacion(FacturaOrdenDeFabricacionEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteFacturaOrdenDeFabricacion(Long facturaId, Long ordenDeFabricacionId) {
        Query q = entityManager.createNamedQuery("FacturaOrdenDeFabricacionEntity.deleteFacturaOrdenDeFabricacion");
        q.setParameter("facturaId", facturaId);
        q.setParameter("ordenDeFabricacionId", ordenDeFabricacionId);
        q.executeUpdate();
    }

    public List<OrdenDeFabricacionDTO> getOrdenDeFabricacionListForFactura(Long facturaId) {
        ArrayList<OrdenDeFabricacionDTO> resp = new ArrayList<OrdenDeFabricacionDTO>();
        Query q = entityManager.createNamedQuery("FacturaOrdenDeFabricacionEntity.getOrdenDeFabricacionListForFactura");
        q.setParameter("facturaId", facturaId);
        List<FacturaOrdenDeFabricacionEntity> qResult =  q.getResultList();
        for (FacturaOrdenDeFabricacionEntity facturaOrdenDeFabricacionEntity : qResult) { 
            if(facturaOrdenDeFabricacionEntity.getOrdenDeFabricacionEntity()==null){
                entityManager.refresh(facturaOrdenDeFabricacionEntity);
            }
            resp.add(OrdenDeFabricacionConverter.entity2PersistenceDTO(facturaOrdenDeFabricacionEntity.getOrdenDeFabricacionEntity()));
        }
        return resp;
    }
    public FacturaPlanDeFabricacionEntity createFacturaPlanDeFabricacion(FacturaPlanDeFabricacionEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteFacturaPlanDeFabricacion(Long facturaId, Long planDeFabricacionId) {
        Query q = entityManager.createNamedQuery("FacturaPlanDeFabricacionEntity.deleteFacturaPlanDeFabricacion");
        q.setParameter("facturaId", facturaId);
        q.setParameter("planDeFabricacionId", planDeFabricacionId);
        q.executeUpdate();
    }

    public List<PlanDeFabricacionDTO> getPlanDeFabricacionListForFactura(Long facturaId) {
        ArrayList<PlanDeFabricacionDTO> resp = new ArrayList<PlanDeFabricacionDTO>();
        Query q = entityManager.createNamedQuery("FacturaPlanDeFabricacionEntity.getPlanDeFabricacionListForFactura");
        q.setParameter("facturaId", facturaId);
        List<FacturaPlanDeFabricacionEntity> qResult =  q.getResultList();
        for (FacturaPlanDeFabricacionEntity facturaPlanDeFabricacionEntity : qResult) { 
            if(facturaPlanDeFabricacionEntity.getPlanDeFabricacionEntity()==null){
                entityManager.refresh(facturaPlanDeFabricacionEntity);
            }
            resp.add(PlanDeFabricacionConverter.entity2PersistenceDTO(facturaPlanDeFabricacionEntity.getPlanDeFabricacionEntity()));
        }
        return resp;
    }
    public FacturaOrdenDespachoEntity createFacturaOrdenDespacho(FacturaOrdenDespachoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteFacturaOrdenDespacho(Long facturaId, Long ordenDespachoId) {
        Query q = entityManager.createNamedQuery("FacturaOrdenDespachoEntity.deleteFacturaOrdenDespacho");
        q.setParameter("facturaId", facturaId);
        q.setParameter("ordenDespachoId", ordenDespachoId);
        q.executeUpdate();
    }

    public List<OrdenDespachoDTO> getOrdenDespachoListForFactura(Long facturaId) {
        ArrayList<OrdenDespachoDTO> resp = new ArrayList<OrdenDespachoDTO>();
        Query q = entityManager.createNamedQuery("FacturaOrdenDespachoEntity.getOrdenDespachoListForFactura");
        q.setParameter("facturaId", facturaId);
        List<FacturaOrdenDespachoEntity> qResult =  q.getResultList();
        for (FacturaOrdenDespachoEntity facturaOrdenDespachoEntity : qResult) { 
            if(facturaOrdenDespachoEntity.getOrdenDespachoEntity()==null){
                entityManager.refresh(facturaOrdenDespachoEntity);
            }
            resp.add(OrdenDespachoConverter.entity2PersistenceDTO(facturaOrdenDespachoEntity.getOrdenDespachoEntity()));
        }
        return resp;
    }

}
