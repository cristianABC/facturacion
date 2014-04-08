package co.edu.uniandes.csw.factura.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class FacturaPlanDeFabricacionEntityId implements Serializable{

    private Long facturaId;
    private Long planDeFabricacionId;

    @Override
    public int hashCode() {
        return (int) (facturaId + planDeFabricacionId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FacturaPlanDeFabricacionEntityId) {
            FacturaPlanDeFabricacionEntityId otherId = (FacturaPlanDeFabricacionEntityId) object;
            return (otherId.facturaId == this.facturaId) && (otherId.planDeFabricacionId == this.planDeFabricacionId);
        }
        return false;
    }

}
