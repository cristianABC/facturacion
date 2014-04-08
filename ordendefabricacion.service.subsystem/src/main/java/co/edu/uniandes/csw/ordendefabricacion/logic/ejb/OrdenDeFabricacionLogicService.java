
package co.edu.uniandes.csw.ordendefabricacion.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.ordendefabricacion.logic.api.IOrdenDeFabricacionLogicService;

@Default
@Stateless
@LocalBean
public class OrdenDeFabricacionLogicService extends _OrdenDeFabricacionLogicService implements IOrdenDeFabricacionLogicService {

}