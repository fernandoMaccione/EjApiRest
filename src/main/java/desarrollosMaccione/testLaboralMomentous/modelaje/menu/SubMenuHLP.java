package desarrollosMaccione.testLaboralMomentous.modelaje.menu;

import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.EntidadHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.SubMemuVO;

public class SubMenuHLP extends EntidadHLP<SubMemuVO>{

	@Override
	protected SubMemuVO getVO() { 
		return new SubMemuVO();
	}

}
