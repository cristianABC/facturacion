package co.edu.uniandes.csw.factura.master.persistence.api;

import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenReaprovicionamientoEntity;
import co.edu.uniandes.csw.ordenreaprovicionamiento.logic.dto.OrdenReaprovicionamientoDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDeFabricacionEntity;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaPlanDeFabricacionEntity;
import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaOrdenDespachoEntity;
import co.edu.uniandes.csw.ordendespacho.logic.dto.OrdenDespachoDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import java.util.List;

public interface _IFacturaMasterPersistence {

    public FacturaOrdenReaprovicionamientoEntity createFacturaOrdenReaprovicionamiento(FacturaOrdenReaprovicionamientoEntity entity);

    public void deleteFacturaOrdenReaprovicionamiento(Long facturaId, Long ordenReaprovicionamientoId);
    
    public List<OrdenReaprovicionamientoDTO> getOrdenReaprovicionamientoListForFactura(Long facturaId);
    public FacturaOrdenDeFabricacionEntity createFacturaOrdenDeFabricacion(FacturaOrdenDeFabricacionEntity entity);

    public void deleteFacturaOrdenDeFabricacion(Long facturaId, Long ordenDeFabricacionId);
    
    public List<OrdenDeFabricacionDTO> getOrdenDeFabricacionListForFactura(Long facturaId);
    public FacturaPlanDeFabricacionEntity createFacturaPlanDeFabricacion(FacturaPlanDeFabricacionEntity entity);

    public void deleteFacturaPlanDeFabricacion(Long facturaId, Long planDeFabricacionId);
    
    public List<PlanDeFabricacionDTO> getPlanDeFabricacionListForFactura(Long facturaId);
    public FacturaOrdenDespachoEntity createFacturaOrdenDespacho(FacturaOrdenDespachoEntity entity);

    public void deleteFacturaOrdenDespacho(Long facturaId, Long ordenDespachoId);
    
    public List<OrdenDespachoDTO> getOrdenDespachoListForFactura(Long facturaId);
    
    public FacturaMasterDTO getFactura(Long facturaId);

}
