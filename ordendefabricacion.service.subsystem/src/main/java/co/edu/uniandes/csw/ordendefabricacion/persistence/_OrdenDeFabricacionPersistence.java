
package co.edu.uniandes.csw.ordendefabricacion.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.api._IOrdenDeFabricacionPersistence;
import co.edu.uniandes.csw.ordendefabricacion.persistence.converter.OrdenDeFabricacionConverter;
import co.edu.uniandes.csw.ordendefabricacion.persistence.entity.OrdenDeFabricacionEntity;

public abstract class _OrdenDeFabricacionPersistence implements _IOrdenDeFabricacionPersistence {

	@PersistenceContext(unitName="OrdenDeFabricacionPU")
	protected EntityManager entityManager;
	
	public OrdenDeFabricacionDTO createOrdenDeFabricacion(OrdenDeFabricacionDTO ordenDeFabricacion) {
		OrdenDeFabricacionEntity entity=OrdenDeFabricacionConverter.persistenceDTO2Entity(ordenDeFabricacion);
		entityManager.persist(entity);
		return OrdenDeFabricacionConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<OrdenDeFabricacionDTO> getOrdenDeFabricacions() {
		Query q = entityManager.createQuery("select u from OrdenDeFabricacionEntity u");
		return OrdenDeFabricacionConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public OrdenDeFabricacionDTO getOrdenDeFabricacion(Long id) {
		return OrdenDeFabricacionConverter.entity2PersistenceDTO(entityManager.find(OrdenDeFabricacionEntity.class, id));
	}

	public void deleteOrdenDeFabricacion(Long id) {
		OrdenDeFabricacionEntity entity=entityManager.find(OrdenDeFabricacionEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateOrdenDeFabricacion(OrdenDeFabricacionDTO detail) {
		OrdenDeFabricacionEntity entity=entityManager.merge(OrdenDeFabricacionConverter.persistenceDTO2Entity(detail));
		OrdenDeFabricacionConverter.entity2PersistenceDTO(entity);
	}

}