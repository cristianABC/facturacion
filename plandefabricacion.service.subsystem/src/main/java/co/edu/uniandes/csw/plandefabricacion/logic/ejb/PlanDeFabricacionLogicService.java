
package co.edu.uniandes.csw.plandefabricacion.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.plandefabricacion.logic.api.IPlanDeFabricacionLogicService;

@Default
@Stateless
@LocalBean
public class PlanDeFabricacionLogicService extends _PlanDeFabricacionLogicService implements IPlanDeFabricacionLogicService {

}