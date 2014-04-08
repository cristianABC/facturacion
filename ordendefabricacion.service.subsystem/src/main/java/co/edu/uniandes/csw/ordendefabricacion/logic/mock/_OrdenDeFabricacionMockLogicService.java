
package co.edu.uniandes.csw.ordendefabricacion.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.logic.api._IOrdenDeFabricacionLogicService;

public abstract class _OrdenDeFabricacionMockLogicService implements _IOrdenDeFabricacionLogicService {

	private Long id= new Long(1);
	protected List<OrdenDeFabricacionDTO> data=new ArrayList<OrdenDeFabricacionDTO>();

	public OrdenDeFabricacionDTO createOrdenDeFabricacion(OrdenDeFabricacionDTO ordenDeFabricacion){
		id++;
		ordenDeFabricacion.setId(id);
		return ordenDeFabricacion;
    }

	public List<OrdenDeFabricacionDTO> getOrdenDeFabricacions(){
		return data; 
	}

	public OrdenDeFabricacionDTO getOrdenDeFabricacion(Long id){
		for(OrdenDeFabricacionDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteOrdenDeFabricacion(Long id){
	    OrdenDeFabricacionDTO delete=null;
		for(OrdenDeFabricacionDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateOrdenDeFabricacion(OrdenDeFabricacionDTO ordenDeFabricacion){
	    OrdenDeFabricacionDTO delete=null;
		for(OrdenDeFabricacionDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(ordenDeFabricacion);
		} 
	}	
}