
package co.edu.uniandes.csw.plandefabricacion.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.plandefabricacion.persistence.api.IPlanDeFabricacionPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class PlanDeFabricacionPersistence extends _PlanDeFabricacionPersistence  implements IPlanDeFabricacionPersistence {

}