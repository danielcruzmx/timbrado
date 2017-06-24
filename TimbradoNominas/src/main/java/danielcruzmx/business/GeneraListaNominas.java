package danielcruzmx.business;

import java.util.List;

import danielcruzmx.data.NominasEjercicio;
import danielcruzmx.service.NominasEjercicioService;

public class GeneraListaNominas {
	private List <NominasEjercicio>    listaNominas;
	
	public GeneraListaNominas(){
	    setListaNominas(new NominasEjercicioService().Ejerciciofind());
	}
	
	public List <NominasEjercicio> getListaNominas() {
		return listaNominas;
	}

	public void setListaNominas(List <NominasEjercicio> listaNominas) {
		this.listaNominas = listaNominas;
	}
	
}
