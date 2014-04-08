
package co.edu.uniandes.csw.ordendefabricacion.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.ordendefabricacion.logic.api.IOrdenDeFabricacionLogicService;

@Alternative
@Singleton
public class OrdenDeFabricacionMockLogicService extends _OrdenDeFabricacionMockLogicService implements IOrdenDeFabricacionLogicService {
	
}