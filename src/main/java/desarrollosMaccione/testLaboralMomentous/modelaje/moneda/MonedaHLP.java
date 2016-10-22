package desarrollosMaccione.testLaboralMomentous.modelaje.moneda;

import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.EntidadHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.moneda.model.MonedaVO;

public class MonedaHLP extends EntidadHLP<MonedaVO>{

	@Override
	protected MonedaVO getVO() {
		
		return new MonedaVO();
	}
}
