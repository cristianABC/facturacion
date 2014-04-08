
package co.edu.uniandes.csw.ordendefabricacion.persistence;

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


import co.edu.uniandes.csw.ordendefabricacion.logic.dto.OrdenDeFabricacionDTO;
import co.edu.uniandes.csw.ordendefabricacion.persistence.api.IOrdenDeFabricacionPersistence;
import co.edu.uniandes.csw.ordendefabricacion.persistence.entity.OrdenDeFabricacionEntity;

@RunWith(Arquillian.class)
public class OrdenDeFabricacionPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(OrdenDeFabricacionPersistence.class.getPackage())
				.addPackage(OrdenDeFabricacionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IOrdenDeFabricacionPersistence ordenDeFabricacionPersistence;

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
		em.createQuery("delete from OrdenDeFabricacionEntity").executeUpdate();
	}

	private List<OrdenDeFabricacionEntity> data=new ArrayList<OrdenDeFabricacionEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			OrdenDeFabricacionEntity entity=new OrdenDeFabricacionEntity();
			entity.setName(generateRandom(String.class));
			entity.setCantidad(generateRandom(Integer.class));
			entity.setFecha(generateRandom(Date.class));
			entity.setNombreProducto(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createOrdenDeFabricacionTest(){
		OrdenDeFabricacionDTO dto=new OrdenDeFabricacionDTO();
		dto.setName(generateRandom(String.class));
		dto.setCantidad(generateRandom(Integer.class));
		dto.setFecha(generateRandom(Date.class));
		dto.setNombreProducto(generateRandom(String.class));
		
		
		OrdenDeFabricacionDTO result=ordenDeFabricacionPersistence.createOrdenDeFabricacion(dto);
		
		Assert.assertNotNull(result);
		
		OrdenDeFabricacionEntity entity=em.find(OrdenDeFabricacionEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getCantidad(), entity.getCantidad());	
		Assert.assertEquals(dto.getFecha(), entity.getFecha());	
		Assert.assertEquals(dto.getNombreProducto(), entity.getNombreProducto());	
	}
	
	@Test
	public void getOrdenDeFabricacionsTest(){
		List<OrdenDeFabricacionDTO> list=ordenDeFabricacionPersistence.getOrdenDeFabricacions();
		Assert.assertEquals(list.size(), data.size());
        for(OrdenDeFabricacionDTO dto:list){
        	boolean found=false;
            for(OrdenDeFabricacionEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getOrdenDeFabricacionTest(){
		OrdenDeFabricacionEntity entity=data.get(0);
		OrdenDeFabricacionDTO dto=ordenDeFabricacionPersistence.getOrdenDeFabricacion(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getCantidad(), dto.getCantidad());
		Assert.assertEquals(entity.getFecha(), dto.getFecha());
		Assert.assertEquals(entity.getNombreProducto(), dto.getNombreProducto());
        
	}
	
	@Test
	public void deleteOrdenDeFabricacionTest(){
		OrdenDeFabricacionEntity entity=data.get(0);
		ordenDeFabricacionPersistence.deleteOrdenDeFabricacion(entity.getId());
        OrdenDeFabricacionEntity deleted=em.find(OrdenDeFabricacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateOrdenDeFabricacionTest(){
		OrdenDeFabricacionEntity entity=data.get(0);
		
		OrdenDeFabricacionDTO dto=new OrdenDeFabricacionDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setCantidad(generateRandom(Integer.class));
		dto.setFecha(generateRandom(Date.class));
		dto.setNombreProducto(generateRandom(String.class));
		
		
		ordenDeFabricacionPersistence.updateOrdenDeFabricacion(dto);
		
		
		OrdenDeFabricacionEntity resp=em.find(OrdenDeFabricacionEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getCantidad(), resp.getCantidad());	
		Assert.assertEquals(dto.getFecha(), resp.getFecha());	
		Assert.assertEquals(dto.getNombreProducto(), resp.getNombreProducto());	
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