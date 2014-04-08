
package co.edu.uniandes.csw.plandefabricacion.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.plandefabricacion.logic.dto.PlanDeFabricacionDTO;
import co.edu.uniandes.csw.plandefabricacion.persistence.api.IPlanDeFabricacionPersistence;
import co.edu.uniandes.csw.plandefabricacion.persistence.entity.PlanDeFabricacionEntity;

@RunWith(Arquillian.class)
public class PlanDeFabricacionPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PlanDeFabricacionPersistence.class.getPackage())
				.addPackage(PlanDeFabricacionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPlanDeFabricacionPersistence planDeFabricacionPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from PlanDeFabricacionEntity").executeUpdate();
	}

	private List<PlanDeFabricacionEntity> data=new ArrayList<PlanDeFabricacionEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PlanDeFabricacionEntity entity=new PlanDeFabricacionEntity();
			entity.setName(generateRandom(String.class));
			entity.setFecha(generateRandom(Date.class));
			entity.setNombreProducto(generateRandom(String.class));
			entity.setCantidad(generateRandom(Integer.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createPlanDeFabricacionTest(){
		PlanDeFabricacionDTO dto=new PlanDeFabricacionDTO();
		dto.setName(generateRandom(String.class));
		dto.setFecha(generateRandom(Date.class));
		dto.setNombreProducto(generateRandom(String.class));
		dto.setCantidad(generateRandom(Integer.class));
		
		
		PlanDeFabricacionDTO result=planDeFabricacionPersistence.createPlanDeFabricacion(dto);
		
		Assert.assertNotNull(result);
		
		PlanDeFabricacionEntity entity=em.find(PlanDeFabricacionEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getFecha(), entity.getFecha());	
		Assert.assertEquals(dto.getNombreProducto(), entity.getNombreProducto());	
		Assert.assertEquals(dto.getCantidad(), entity.getCantidad());	
	}
	
	@Test
	public void getPlanDeFabricacionsTest(){
		List<PlanDeFabricacionDTO> list=planDeFabricacionPersistence.getPlanDeFabricacions();
		Assert.assertEquals(list.size(), data.size());
        for(PlanDeFabricacionDTO dto:list){
        	boolean found=false;
            for(PlanDeFabricacionEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPlanDeFabricacionTest(){
		PlanDeFabricacionEntity entity=data.get(0);
		PlanDeFabricacionDTO dto=planDeFabricacionPersistence.getPlanDeFabricacion(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getFecha(), dto.getFecha());
		Assert.assertEquals(entity.getNombreProducto(), dto.getNombreProducto());
		Assert.assertEquals(entity.getCantidad(), dto.getCantidad());
        
	}
	
	@Test
	public void deletePlanDeFabricacionTest(){
		PlanDeFabricacionEntity entity=data.get(0);
		planDeFabricacionPersistence.deletePlanDeFabricacion(entity.getId());
        PlanDeFabricacionEntity deleted=em.find(PlanDeFabricacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePlanDeFabricacionTest(){
		PlanDeFabricacionEntity entity=data.get(0);
		
		PlanDeFabricacionDTO dto=new PlanDeFabricacionDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setFecha(generateRandom(Date.class));
		dto.setNombreProducto(generateRandom(String.class));
		dto.setCantidad(generateRandom(Integer.class));
		
		
		planDeFabricacionPersistence.updatePlanDeFabricacion(dto);
		
		
		PlanDeFabricacionEntity resp=em.find(PlanDeFabricacionEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getFecha(), resp.getFecha());	
		Assert.assertEquals(dto.getNombreProducto(), resp.getNombreProducto());	
		Assert.assertEquals(dto.getCantidad(), resp.getCantidad());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}