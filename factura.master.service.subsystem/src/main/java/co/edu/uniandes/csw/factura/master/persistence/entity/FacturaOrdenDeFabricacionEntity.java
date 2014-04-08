package co.edu.uniandes.csw.factura.master.persistence.entity;

import co.edu.uniandes.csw.ordendefabricacion.persistence.entity.OrdenDeFabricacionEntity;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(FacturaOrdenDeFabricacionEntityId.class)
@NamedQueries({
    @NamedQuery(name = "FacturaOrdenDeFabricacionEntity.getOrdenDeFabricacionListForFactura", query = "SELECT  u FROM FacturaOrdenDeFabricacionEntity u WHERE u.facturaId=:facturaId"),
    @NamedQuery(name = "FacturaOrdenDeFabricacionEntity.deleteFacturaOrdenDeFabricacion", query = "DELETE FROM FacturaOrdenDeFabricacionEntity u WHERE u.ordenDeFabricacionId=:ordenDeFabricacionId and  u.facturaId=:facturaId")
})
public class FacturaOrdenDeFabricacionEntity implements Serializable {

    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @Id
    @Column(name = "ordenDeFabricacionId")
    private Long ordenDeFabricacionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "ordenDeFabricacionId", referencedColumnName = "id")
    @JoinFetch
    private OrdenDeFabricacionEntity ordenDeFabricacionEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;

    public FacturaOrdenDeFabricacionEntity() {
    }

    public FacturaOrdenDeFabricacionEntity(Long facturaId, Long ordenDeFabricacionId) {
        this.facturaId = facturaId;
        this.ordenDeFabricacionId = ordenDeFabricacionId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public Long getOrdenDeFabricacionId() {
        return ordenDeFabricacionId;
    }

    public void setOrdenDeFabricacionId(Long ordenDeFabricacionId) {
        this.ordenDeFabricacionId = ordenDeFabricacionId;
    }

    public OrdenDeFabricacionEntity getOrdenDeFabricacionEntity() {
        return ordenDeFabricacionEntity;
    }

    public void setOrdenDeFabricacionEntity(OrdenDeFabricacionEntity ordenDeFabricacionEntity) {
        this.ordenDeFabricacionEntity = ordenDeFabricacionEntity;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

}
