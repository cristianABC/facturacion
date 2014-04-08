
package co.edu.uniandes.csw.ordendefabricacion.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.ordendefabricacion.persistence.api.IOrdenDeFabricacionPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class OrdenDeFabricacionPersistence extends _OrdenDeFabricacionPersistence  implements IOrdenDeFabricacionPersistence {

}