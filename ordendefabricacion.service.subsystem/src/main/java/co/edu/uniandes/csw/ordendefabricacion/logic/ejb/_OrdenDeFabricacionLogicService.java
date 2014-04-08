
package co.edu.uniandes.csw.ordendefabricacion.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.logic.api._IOrdenDeFabricacionLogicService;
import co.edu.uniandes.csw.ordendefabricacion.persistence.api.IOrdenDeFabricacionPersistence;

public abstract class _OrdenDeFabricacionLogicService implements _IOrdenDeFabricacionLogicService {

	@Inject
	protected IOrdenDeFabricacionPersistence persistance;

	public OrdenDeFabricacionDTO createOrdenDeFabricacion(OrdenDeFabricacionDTO ordenDeFabricacion){
		return persistance.createOrdenDeFabricacion( ordenDeFabricacion); 
    }

	public List<OrdenDeFabricacionDTO> getOrdenDeFabricacions(){
		return persistance.getOrdenDeFabricacions(); 
	}

	public OrdenDeFabricacionDTO getOrdenDeFabricacion(Long id){
		return persistance.getOrdenDeFabricacion(id); 
	}

	public void deleteOrdenDeFabricacion(Long id){
	    persistance.deleteOrdenDeFabricacion(id); 
	}

	public void updateOrdenDeFabricacion(OrdenDeFabricacionDTO ordenDeFabricacion){
	    persistance.updateOrdenDeFabricacion(ordenDeFabricacion); 
	}	
}