package co.edu.uniandes.csw.factura.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class FacturaOrdenDeFabricacionEntityId implements Serializable{

    private Long facturaId;
    private Long ordenDeFabricacionId;

    @Override
    public int hashCode() {
        return (int) (facturaId + ordenDeFabricacionId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FacturaOrdenDeFabricacionEntityId) {
            FacturaOrdenDeFabricacionEntityId otherId = (FacturaOrdenDeFabricacionEntityId) object;
            return (otherId.facturaId == this.facturaId) && (otherId.ordenDeFabricacionId == this.ordenDeFabricacionId);
        }
        return false;
    }

}
