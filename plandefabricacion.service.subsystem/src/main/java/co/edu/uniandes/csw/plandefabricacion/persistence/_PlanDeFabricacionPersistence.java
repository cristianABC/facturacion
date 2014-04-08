
package co.edu.uniandes.csw.plandefabricacion.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.persistence.api._IPlanDeFabricacionPersistence;
import co.edu.uniandes.csw.plandefabricacion.persistence.converter.PlanDeFabricacionConverter;
import co.edu.uniandes.csw.plandefabricacion.persistence.entity.PlanDeFabricacionEntity;

public abstract class _PlanDeFabricacionPersistence implements _IPlanDeFabricacionPersistence {

	@PersistenceContext(unitName="PlanDeFabricacionPU")
	protected EntityManager entityManager;
	
	public PlanDeFabricacionDTO createPlanDeFabricacion(PlanDeFabricacionDTO planDeFabricacion) {
		PlanDeFabricacionEntity entity=PlanDeFabricacionConverter.persistenceDTO2Entity(planDeFabricacion);
		entityManager.persist(entity);
		return PlanDeFabricacionConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<PlanDeFabricacionDTO> getPlanDeFabricacions() {
		Query q = entityManager.createQuery("select u from PlanDeFabricacionEntity u");
		return PlanDeFabricacionConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public PlanDeFabricacionDTO getPlanDeFabricacion(Long id) {
		return PlanDeFabricacionConverter.entity2PersistenceDTO(entityManager.find(PlanDeFabricacionEntity.class, id));
	}

	public void deletePlanDeFabricacion(Long id) {
		PlanDeFabricacionEntity entity=entityManager.find(PlanDeFabricacionEntity.class, id);
		entityManager.remove(entity);
	}

	public void updatePlanDeFabricacion(PlanDeFabricacionDTO detail) {
		PlanDeFabricacionEntity entity=entityManager.merge(PlanDeFabricacionConverter.persistenceDTO2Entity(detail));
		PlanDeFabricacionConverter.entity2PersistenceDTO(entity);
	}

}