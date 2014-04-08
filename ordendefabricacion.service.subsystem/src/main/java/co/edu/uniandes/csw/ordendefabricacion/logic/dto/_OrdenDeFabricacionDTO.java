
package co.edu.uniandes.csw.ordendefabricacion.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _OrdenDeFabricacionDTO {

	private Long id;
	private String name;
	private Integer cantidad;
	private Date fecha;
	private String nombreProducto;

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
	public Integer getCantidad() {
		return cantidad;
	}
 
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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
	
}