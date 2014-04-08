
package co.edu.uniandes.csw.ordendefabricacion.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.entity.OrdenDeFabricacionEntity;

public abstract class _OrdenDeFabricacionConverter {


	public static OrdenDeFabricacionDTO entity2PersistenceDTO(OrdenDeFabricacionEntity entity){
		if (entity != null) {
			OrdenDeFabricacionDTO dto = new OrdenDeFabricacionDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setCantidad(entity.getCantidad());
				dto.setFecha(entity.getFecha());
				dto.setNombreProducto(entity.getNombreProducto());
			return dto;
		}else{
			return null;
		}
	}
	
	public static OrdenDeFabricacionEntity persistenceDTO2Entity(OrdenDeFabricacionDTO dto){
		if(dto!=null){
			OrdenDeFabricacionEntity entity=new OrdenDeFabricacionEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setCantidad(dto.getCantidad());
			entity.setFecha(dto.getFecha());
			entity.setNombreProducto(dto.getNombreProducto());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<OrdenDeFabricacionDTO> entity2PersistenceDTOList(List<OrdenDeFabricacionEntity> entities){
		List<OrdenDeFabricacionDTO> dtos=new ArrayList<OrdenDeFabricacionDTO>();
		for(OrdenDeFabricacionEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<OrdenDeFabricacionEntity> persistenceDTO2EntityList(List<OrdenDeFabricacionDTO> dtos){
		List<OrdenDeFabricacionEntity> entities=new ArrayList<OrdenDeFabricacionEntity>();
		for(OrdenDeFabricacionDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}