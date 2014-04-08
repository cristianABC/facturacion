
package co.edu.uniandes.csw.ordendespacho.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _OrdenDespachoEntity {

	@Id
	@GeneratedValue(generator = "OrdenDespacho")
	private Long id;
	private String name;
	private String destino;
	private Integer cantidad;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Long idProducto;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getDestino(){
		return destino;
	}
	
	public void setDestino(String destino){
		this.destino = destino;
	}
	public Integer getCantidad(){
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad){
		this.cantidad = cantidad;
	}
	public Date getFecha(){
		return fecha;
	}
	
	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
	public Long getIdProducto(){
		return idProducto;
	}
	
	public void setIdProducto(Long idProducto){
		this.idProducto = idProducto;
	}
}