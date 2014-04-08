
package co.edu.uniandes.csw.ordenreaprovicionamiento.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _OrdenReaprovicionamientoDTO {

	private Long id;
	private String name;
	private Integer cantidad;
	private Date fecha;
	private String nombreMateria;

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
	public String getNombreMateria() {
		return nombreMateria;
	}
 
	public void setNombreMateria(String nombremateria) {
		this.nombreMateria = nombremateria;
	}
	
}