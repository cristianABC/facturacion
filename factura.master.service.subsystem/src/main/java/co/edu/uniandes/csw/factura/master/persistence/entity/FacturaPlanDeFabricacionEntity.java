package co.edu.uniandes.csw.factura.master.persistence.entity;

import co.edu.uniandes.csw.plandefabricacion.persistence.entity.PlanDeFabricacionEntity;
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
@IdClass(FacturaPlanDeFabricacionEntityId.class)
@NamedQueries({
    @NamedQuery(name = "FacturaPlanDeFabricacionEntity.getPlanDeFabricacionListForFactura", query = "SELECT  u FROM FacturaPlanDeFabricacionEntity u WHERE u.facturaId=:facturaId"),
    @NamedQuery(name = "FacturaPlanDeFabricacionEntity.deleteFacturaPlanDeFabricacion", query = "DELETE FROM FacturaPlanDeFabricacionEntity u WHERE u.planDeFabricacionId=:planDeFabricacionId and  u.facturaId=:facturaId")
})
public class FacturaPlanDeFabricacionEntity implements Serializable {

    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @Id
    @Column(name = "planDeFabricacionId")
    private Long planDeFabricacionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "planDeFabricacionId", referencedColumnName = "id")
    @JoinFetch
    private PlanDeFabricacionEntity planDeFabricacionEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;

    public FacturaPlanDeFabricacionEntity() {
    }

    public FacturaPlanDeFabricacionEntity(Long facturaId, Long planDeFabricacionId) {
        this.facturaId = facturaId;
        this.planDeFabricacionId = planDeFabricacionId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public Long getPlanDeFabricacionId() {
        return planDeFabricacionId;
    }

    public void setPlanDeFabricacionId(Long planDeFabricacionId) {
        this.planDeFabricacionId = planDeFabricacionId;
    }

    public PlanDeFabricacionEntity getPlanDeFabricacionEntity() {
        return planDeFabricacionEntity;
    }

    public void setPlanDeFabricacionEntity(PlanDeFabricacionEntity planDeFabricacionEntity) {
        this.planDeFabricacionEntity = planDeFabricacionEntity;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

}
