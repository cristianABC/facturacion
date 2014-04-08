
package co.edu.uniandes.csw.ordendefabricacion.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;

public interface _IOrdenDeFabricacionPersistence {

	public OrdenDeFabricacionDTO createOrdenDeFabricacion(OrdenDeFabricacionDTO detail);
	public List<OrdenDeFabricacionDTO> getOrdenDeFabricacions();
	public OrdenDeFabricacionDTO getOrdenDeFabricacion(Long id);
	public void deleteOrdenDeFabricacion(Long id);
	public void updateOrdenDeFabricacion(OrdenDeFabricacionDTO detail);
	
}