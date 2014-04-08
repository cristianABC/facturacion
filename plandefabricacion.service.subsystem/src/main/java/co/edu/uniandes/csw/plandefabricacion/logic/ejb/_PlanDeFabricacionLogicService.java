
package co.edu.uniandes.csw.plandefabricacion.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.logic.api._IPlanDeFabricacionLogicService;
import co.edu.uniandes.csw.plandefabricacion.persistence.api.IPlanDeFabricacionPersistence;

public abstract class _PlanDeFabricacionLogicService implements _IPlanDeFabricacionLogicService {

	@Inject
	protected IPlanDeFabricacionPersistence persistance;

	public PlanDeFabricacionDTO createPlanDeFabricacion(PlanDeFabricacionDTO planDeFabricacion){
		return persistance.createPlanDeFabricacion( planDeFabricacion); 
    }

	public List<PlanDeFabricacionDTO> getPlanDeFabricacions(){
		return persistance.getPlanDeFabricacions(); 
	}

	public PlanDeFabricacionDTO getPlanDeFabricacion(Long id){
		return persistance.getPlanDeFabricacion(id); 
	}

	public void deletePlanDeFabricacion(Long id){
	    persistance.deletePlanDeFabricacion(id); 
	}

	public void updatePlanDeFabricacion(PlanDeFabricacionDTO planDeFabricacion){
	    persistance.updatePlanDeFabricacion(planDeFabricacion); 
	}	
}