package desarrollosMaccione.testLaboralMomentous.modelaje.menu.comparadores;

import java.util.Comparator;

import desarrollosMaccione.testLaboralMomentous.modelaje.menu.model.ItemMenuVO;


public class CompararItemPrecio implements Comparator<ItemMenuVO> {

	 public int compare(ItemMenuVO item1, ItemMenuVO item2) { 
		  return item1.getPrecio().compareTo(item2.getPrecio());
	 }
}
