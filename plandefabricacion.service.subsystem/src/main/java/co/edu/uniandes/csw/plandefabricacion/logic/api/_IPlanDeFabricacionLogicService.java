
package co.edu.uniandes.csw.plandefabricacion.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;

public interface _IPlanDeFabricacionLogicService {

	public PlanDeFabricacionDTO createPlanDeFabricacion(PlanDeFabricacionDTO detail);
	public List<PlanDeFabricacionDTO> getPlanDeFabricacions();
	public PlanDeFabricacionDTO getPlanDeFabricacion(Long id);
	public void deletePlanDeFabricacion(Long id);
	public void updatePlanDeFabricacion(PlanDeFabricacionDTO detail);
	
}