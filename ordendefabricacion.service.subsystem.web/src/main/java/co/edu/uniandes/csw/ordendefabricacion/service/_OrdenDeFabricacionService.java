package co.edu.uniandes.csw.ordendefabricacion.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.ordendefabricacion.logic.api.IOrdenDeFabricacionLogicService;
import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;


public abstract class _OrdenDeFabricacionService {

	@Inject
	protected IOrdenDeFabricacionLogicService ordenDeFabricacionLogicService;
	
	@POST
	public OrdenDeFabricacionDTO createOrdenDeFabricacion(OrdenDeFabricacionDTO ordenDeFabricacion){
		return ordenDeFabricacionLogicService.createOrdenDeFabricacion(ordenDeFabricacion);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteOrdenDeFabricacion(@PathParam("id") Long id){
		ordenDeFabricacionLogicService.deleteOrdenDeFabricacion(id);
	}
	
	@GET
	public List<OrdenDeFabricacionDTO> getOrdenDeFabricacions(){
		return ordenDeFabricacionLogicService.getOrdenDeFabricacions();
	}
	
	@GET
	@Path("{id}")
	public OrdenDeFabricacionDTO getOrdenDeFabricacion(@PathParam("id") Long id){
		return ordenDeFabricacionLogicService.getOrdenDeFabricacion(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateOrdenDeFabricacion(@PathParam("id") Long id, OrdenDeFabricacionDTO ordenDeFabricacion){
		ordenDeFabricacionLogicService.updateOrdenDeFabricacion(ordenDeFabricacion);
	}
	
}