package co.edu.uniandes.csw.plandefabricacion.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.plandefabricacion.logic.api.IPlanDeFabricacionLogicService;
import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;


public abstract class _PlanDeFabricacionService {

	@Inject
	protected IPlanDeFabricacionLogicService planDeFabricacionLogicService;
	
	@POST
	public PlanDeFabricacionDTO createPlanDeFabricacion(PlanDeFabricacionDTO planDeFabricacion){
		return planDeFabricacionLogicService.createPlanDeFabricacion(planDeFabricacion);
	}
	
	@DELETE
	@Path("{id}")
	public void deletePlanDeFabricacion(@PathParam("id") Long id){
		planDeFabricacionLogicService.deletePlanDeFabricacion(id);
	}
	
	@GET
	public List<PlanDeFabricacionDTO> getPlanDeFabricacions(){
		return planDeFabricacionLogicService.getPlanDeFabricacions();
	}
	
	@GET
	@Path("{id}")
	public PlanDeFabricacionDTO getPlanDeFabricacion(@PathParam("id") Long id){
		return planDeFabricacionLogicService.getPlanDeFabricacion(id);
	}
	
	@PUT
    @Path("{id}")
	public void updatePlanDeFabricacion(@PathParam("id") Long id, PlanDeFabricacionDTO planDeFabricacion){
		planDeFabricacionLogicService.updatePlanDeFabricacion(planDeFabricacion);
	}
	
}