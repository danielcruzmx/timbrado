package danielcruzmx.main;

import java.util.List;

import danielcruzmx.business.GeneraListaPagos;
import danielcruzmx.domain.DatosXML;

public class App {

	public static void main(String[] args) {
		GeneraListaPagos datos = new GeneraListaPagos(7627);
		
		List <DatosXML> ldatos = datos.getListaDatos();
		
		for(DatosXML d: ldatos){
			System.out.println("Dato ->" + d.getFolio() + " " + d.getRfcReceptor() + " " + d.getNombreReceptor());
		}
		

	}

}
