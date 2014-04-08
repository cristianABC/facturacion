
package co.edu.uniandes.csw.plandefabricacion.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.persistence.entity.PlanDeFabricacionEntity;

public abstract class _PlanDeFabricacionConverter {


	public static PlanDeFabricacionDTO entity2PersistenceDTO(PlanDeFabricacionEntity entity){
		if (entity != null) {
			PlanDeFabricacionDTO dto = new PlanDeFabricacionDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setFecha(entity.getFecha());
				dto.setNombreProducto(entity.getNombreProducto());
				dto.setCantidad(entity.getCantidad());
			return dto;
		}else{
			return null;
		}
	}
	
	public static PlanDeFabricacionEntity persistenceDTO2Entity(PlanDeFabricacionDTO dto){
		if(dto!=null){
			PlanDeFabricacionEntity entity=new PlanDeFabricacionEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setFecha(dto.getFecha());
			entity.setNombreProducto(dto.getNombreProducto());
			entity.setCantidad(dto.getCantidad());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<PlanDeFabricacionDTO> entity2PersistenceDTOList(List<PlanDeFabricacionEntity> entities){
		List<PlanDeFabricacionDTO> dtos=new ArrayList<PlanDeFabricacionDTO>();
		for(PlanDeFabricacionEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<PlanDeFabricacionEntity> persistenceDTO2EntityList(List<PlanDeFabricacionDTO> dtos){
		List<PlanDeFabricacionEntity> entities=new ArrayList<PlanDeFabricacionEntity>();
		for(PlanDeFabricacionDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}