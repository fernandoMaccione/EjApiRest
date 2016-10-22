package desarrollosMaccione.testLaboralMomentous.modelaje.menu;

import desarrollosMaccione.testLaboralMomentous.modelaje.entidad.EntidadHLP;
import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.ItemMenuVO;

public class ItemMenuHLP extends EntidadHLP<ItemMenuVO>{

	@Override
	protected ItemMenuVO getVO() {		
		return new ItemMenuVO();
	}

}
