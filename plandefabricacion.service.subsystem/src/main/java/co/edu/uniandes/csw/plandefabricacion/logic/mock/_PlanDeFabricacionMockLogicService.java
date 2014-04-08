
package co.edu.uniandes.csw.plandefabricacion.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.logic.api._IPlanDeFabricacionLogicService;

public abstract class _PlanDeFabricacionMockLogicService implements _IPlanDeFabricacionLogicService {

	private Long id= new Long(1);
	protected List<PlanDeFabricacionDTO> data=new ArrayList<PlanDeFabricacionDTO>();

	public PlanDeFabricacionDTO createPlanDeFabricacion(PlanDeFabricacionDTO planDeFabricacion){
		id++;
		planDeFabricacion.setId(id);
		return planDeFabricacion;
    }

	public List<PlanDeFabricacionDTO> getPlanDeFabricacions(){
		return data; 
	}

	public PlanDeFabricacionDTO getPlanDeFabricacion(Long id){
		for(PlanDeFabricacionDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deletePlanDeFabricacion(Long id){
	    PlanDeFabricacionDTO delete=null;
		for(PlanDeFabricacionDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updatePlanDeFabricacion(PlanDeFabricacionDTO planDeFabricacion){
	    PlanDeFabricacionDTO delete=null;
		for(PlanDeFabricacionDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(planDeFabricacion);
		} 
	}	
}