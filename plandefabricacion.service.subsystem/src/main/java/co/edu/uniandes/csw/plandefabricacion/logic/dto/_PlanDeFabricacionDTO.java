
package co.edu.uniandes.csw.plandefabricacion.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _PlanDeFabricacionDTO {

	private Long id;
	private String name;
	private Date fecha;
	private String nombreProducto;
	private Integer cantidad;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Date getFecha() {
		return fecha;
	}
 
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
 
	public void setNombreProducto(String nombreproducto) {
		this.nombreProducto = nombreproducto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
 
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}