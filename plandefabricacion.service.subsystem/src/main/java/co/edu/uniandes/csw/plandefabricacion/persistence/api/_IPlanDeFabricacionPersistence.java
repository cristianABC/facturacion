
package co.edu.uniandes.csw.plandefabricacion.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;

public interface _IPlanDeFabricacionPersistence {

	public PlanDeFabricacionDTO createPlanDeFabricacion(PlanDeFabricacionDTO detail);
	public List<PlanDeFabricacionDTO> getPlanDeFabricacions();
	public PlanDeFabricacionDTO getPlanDeFabricacion(Long id);
	public void deletePlanDeFabricacion(Long id);
	public void updatePlanDeFabricacion(PlanDeFabricacionDTO detail);
	
}