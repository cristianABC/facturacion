
package co.edu.uniandes.csw.plandefabricacion.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.plandefabricacion.logic.api.IPlanDeFabricacionLogicService;

@Alternative
@Singleton
public class PlanDeFabricacionMockLogicService extends _PlanDeFabricacionMockLogicService implements IPlanDeFabricacionLogicService {
	
}