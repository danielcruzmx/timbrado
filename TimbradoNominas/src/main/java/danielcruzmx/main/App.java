package danielcruzmx.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import danielcruzmx.business.GeneraComprobante;
import danielcruzmx.business.GeneraListaPagos;
import danielcruzmx.business.GeneraPaquete;
import danielcruzmx.data.ComprobanteDigital;
import danielcruzmx.data.ComprobanteDigitalPK;
import danielcruzmx.domain.DatosXML;
import danielcruzmx.service.ComprobanteDigitalService;

public class App {

	public static void main(String[] args) {
		
		Integer idNomina = 7627; 
		
		GeneraListaPagos datos = new GeneraListaPagos(idNomina);
		List <DatosXML> ldatos = datos.getListaDatos();
		PrintWriter writeCadenas = null;
		
		try {

			ComprobanteDigitalService compDig = new ComprobanteDigitalService();
			compDig.beginTransaction();

			writeCadenas = new PrintWriter("E:\\RESULTADO_TIMBRADO\\" + idNomina + "_cadenas.txt");
		
			for(DatosXML d: ldatos)
			{
				System.out.println("Dato -> " + d.getFolio() + " " + d.getRfcReceptor() + " " + d.getNombreReceptor());
				GeneraComprobante xml = new GeneraComprobante();
				String resultXML = xml.getXML(d, 
		    		      				datos.getConstantes().getFileCSDp12(), 
		    		      				datos.getConstantes().getContrasenia(), 
		    		      				datos.getConstantes().getFileTransformCadOrig());
				
				if(resultXML != null)
				{
					writeCadenas.println(d.getCadenaOriginal());
					compDig.save(comprobante(d, resultXML));

				} else {
					writeCadenas.println(d.getRfcReceptor() + "|" + "No se genero XML" + "||");
				}
			}

			writeCadenas.close();
			
			compDig.commitTransaction();
			compDig.close();
			
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("FIN DEL PROCESO DE GENERACION DE XML's");
		
		System.out.println("GENERANDO PAQUETES Y VALIDANDO XML's ");
		
		GeneraPaquete paq = new GeneraPaquete(idNomina);
		
		if(paq.validaXMLs(datos.getConstantes().getFileEsquemaCFDIV33(),
						   datos.getConstantes().getFileEsquemaNominaV12())){
			
			System.out.println("XML's CORRECTOS ");
			
			// GENERA LISTA DE PAQUETES Y PAQUETE DE ENVIO 
		};
		
		
		
		
		
	}
	
	
	private static ComprobanteDigital comprobante(DatosXML dato, String xml){

        ComprobanteDigitalPK id = new ComprobanteDigitalPK();
        String[] llave = dato.getFolio().split("_");
        id.setCnFolio(Long.parseLong(llave[0]));
        id.setCnDifFolio(Long.parseLong(llave[1]));
        
        ComprobanteDigital dbCom = new ComprobanteDigital();
        dbCom.setId(id);
        dbCom.setCnConsecNomina(new BigDecimal(dato.getSerie()));
        dbCom.setCnEjercicio(new BigDecimal(dato.getPuesto().substring(3, 7)));
        dbCom.setCnFechaGenerado( new Date());
        dbCom.setCnSituacion("G");
        dbCom.setCnRfc(dato.getRfcReceptor());
        dbCom.setCnPaquete(new BigDecimal(1));
        dbCom.setUsuario("danielcruz");
        dbCom.setFecModifico(new Date());
        dbCom.setCnXmlGenerado(xml);
        
        return dbCom;
        
	}

}
