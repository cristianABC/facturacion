
package co.edu.uniandes.csw.plandefabricacion.logic.ejb;

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
import co.edu.uniandes.csw.plandefabricacion.logic.api.IPlanDeFabricacionLogicService;
import co.edu.uniandes.csw.plandefabricacion.persistence.PlanDeFabricacionPersistence;
import co.edu.uniandes.csw.plandefabricacion.persistence.api.IPlanDeFabricacionPersistence;
import co.edu.uniandes.csw.plandefabricacion.persistence.entity.PlanDeFabricacionEntity;

@RunWith(Arquillian.class)
public class PlanDeFabricacionLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PlanDeFabricacionLogicService.class.getPackage())
				.addPackage(PlanDeFabricacionPersistence.class.getPackage())
				.addPackage(PlanDeFabricacionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPlanDeFabricacionLogicService planDeFabricacionLogicService;
	
	@Inject
	private IPlanDeFabricacionPersistence planDeFabricacionPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<PlanDeFabricacionDTO> dtos=planDeFabricacionPersistence.getPlanDeFabricacions();
		for(PlanDeFabricacionDTO dto:dtos){
			planDeFabricacionPersistence.deletePlanDeFabricacion(dto.getId());
		}
	}

	private List<PlanDeFabricacionDTO> data=new ArrayList<PlanDeFabricacionDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PlanDeFabricacionDTO pdto=new PlanDeFabricacionDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setFecha(generateRandom(Date.class));
			pdto.setNombreProducto(generateRandom(String.class));
			pdto.setCantidad(generateRandom(Integer.class));
			pdto=planDeFabricacionPersistence.createPlanDeFabricacion(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createPlanDeFabricacionTest(){
		PlanDeFabricacionDTO ldto=new PlanDeFabricacionDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setFecha(generateRandom(Date.class));
		ldto.setNombreProducto(generateRandom(String.class));
		ldto.setCantidad(generateRandom(Integer.class));
		
		
		PlanDeFabricacionDTO result=planDeFabricacionLogicService.createPlanDeFabricacion(ldto);
		
		Assert.assertNotNull(result);
		
		PlanDeFabricacionDTO pdto=planDeFabricacionPersistence.getPlanDeFabricacion(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getFecha(), pdto.getFecha());	
		Assert.assertEquals(ldto.getNombreProducto(), pdto.getNombreProducto());	
		Assert.assertEquals(ldto.getCantidad(), pdto.getCantidad());	
	}
	
	@Test
	public void getPlanDeFabricacionsTest(){
		List<PlanDeFabricacionDTO> list=planDeFabricacionLogicService.getPlanDeFabricacions();
		Assert.assertEquals(list.size(), data.size());
        for(PlanDeFabricacionDTO ldto:list){
        	boolean found=false;
            for(PlanDeFabricacionDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPlanDeFabricacionTest(){
		PlanDeFabricacionDTO pdto=data.get(0);
		PlanDeFabricacionDTO ldto=planDeFabricacionLogicService.getPlanDeFabricacion(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getFecha(), ldto.getFecha());
		Assert.assertEquals(pdto.getNombreProducto(), ldto.getNombreProducto());
		Assert.assertEquals(pdto.getCantidad(), ldto.getCantidad());
        
	}
	
	@Test
	public void deletePlanDeFabricacionTest(){
		PlanDeFabricacionDTO pdto=data.get(0);
		planDeFabricacionLogicService.deletePlanDeFabricacion(pdto.getId());
        PlanDeFabricacionDTO deleted=planDeFabricacionPersistence.getPlanDeFabricacion(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePlanDeFabricacionTest(){
		PlanDeFabricacionDTO pdto=data.get(0);
		
		PlanDeFabricacionDTO ldto=new PlanDeFabricacionDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setFecha(generateRandom(Date.class));
		ldto.setNombreProducto(generateRandom(String.class));
		ldto.setCantidad(generateRandom(Integer.class));
		
		
		planDeFabricacionLogicService.updatePlanDeFabricacion(ldto);
		
		
		PlanDeFabricacionDTO resp=planDeFabricacionPersistence.getPlanDeFabricacion(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getFecha(), resp.getFecha());	
		Assert.assertEquals(ldto.getNombreProducto(), resp.getNombreProducto());	
		Assert.assertEquals(ldto.getCantidad(), resp.getCantidad());	
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