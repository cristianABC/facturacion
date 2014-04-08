package co.edu.uniandes.csw.factura.master.persistence.converter;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenReaprovicionamientoEntity;
import co.edu.uniandes.csw.ordenreaprovicionamiento.logic.dto.OrdenReaprovicionamientoDTO;
import co.edu.uniandes.csw.ordenreaprovicionamiento.persistence.converter.OrdenReaprovicionamientoConverter;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.converter.OrdenDeFabricacionConverter;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaPlanDeFabricacionEntity;
import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.persistence.converter.PlanDeFabricacionConverter;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDespachoEntity;
import co.edu.uniandes.csw.ordendespacho.logic.dto.OrdenDespachoDTO;
import co.edu.uniandes.csw.ordendespacho.persistence.converter.OrdenDespachoConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _FacturaMasterConverter {

    public static FacturaMasterDTO entity2PersistenceDTO(FacturaEntity facturaEntity 
    ,List<FacturaOrdenReaprovicionamientoEntity> facturaOrdenReaprovicionamientoEntity 
    ,List<FacturaOrdenDeFabricacionEntity> facturaOrdenDeFabricacionEntity 
    ,List<FacturaPlanDeFabricacionEntity> facturaPlanDeFabricacionEntity 
    ,List<FacturaOrdenDespachoEntity> facturaOrdenDespachoEntity 
    ) {
        FacturaDTO facturaDTO = FacturaConverter.entity2PersistenceDTO(facturaEntity);
        ArrayList<OrdenReaprovicionamientoDTO> ordenReaprovicionamientoEntities = new ArrayList<OrdenReaprovicionamientoDTO>(facturaOrdenReaprovicionamientoEntity.size());
        for (FacturaOrdenReaprovicionamientoEntity facturaOrdenReaprovicionamiento : facturaOrdenReaprovicionamientoEntity) {
            ordenReaprovicionamientoEntities.add(OrdenReaprovicionamientoConverter.entity2PersistenceDTO(facturaOrdenReaprovicionamiento.getOrdenReaprovicionamientoEntity()));
        }
        ArrayList<OrdenDeFabricacionDTO> ordenDeFabricacionEntities = new ArrayList<OrdenDeFabricacionDTO>(facturaOrdenDeFabricacionEntity.size());
        for (FacturaOrdenDeFabricacionEntity facturaOrdenDeFabricacion : facturaOrdenDeFabricacionEntity) {
            ordenDeFabricacionEntities.add(OrdenDeFabricacionConverter.entity2PersistenceDTO(facturaOrdenDeFabricacion.getOrdenDeFabricacionEntity()));
        }
        ArrayList<PlanDeFabricacionDTO> planDeFabricacionEntities = new ArrayList<PlanDeFabricacionDTO>(facturaPlanDeFabricacionEntity.size());
        for (FacturaPlanDeFabricacionEntity facturaPlanDeFabricacion : facturaPlanDeFabricacionEntity) {
            planDeFabricacionEntities.add(PlanDeFabricacionConverter.entity2PersistenceDTO(facturaPlanDeFabricacion.getPlanDeFabricacionEntity()));
        }
        ArrayList<OrdenDespachoDTO> ordenDespachoEntities = new ArrayList<OrdenDespachoDTO>(facturaOrdenDespachoEntity.size());
        for (FacturaOrdenDespachoEntity facturaOrdenDespacho : facturaOrdenDespachoEntity) {
            ordenDespachoEntities.add(OrdenDespachoConverter.entity2PersistenceDTO(facturaOrdenDespacho.getOrdenDespachoEntity()));
        }
        FacturaMasterDTO respDTO  = new FacturaMasterDTO();
        respDTO.setFacturaEntity(facturaDTO);
        respDTO.setListOrdenReaprovicionamiento(ordenReaprovicionamientoEntities);
        respDTO.setListOrdenDeFabricacion(ordenDeFabricacionEntities);
        respDTO.setListPlanDeFabricacion(planDeFabricacionEntities);
        respDTO.setListOrdenDespacho(ordenDespachoEntities);
        return respDTO;
    }

}