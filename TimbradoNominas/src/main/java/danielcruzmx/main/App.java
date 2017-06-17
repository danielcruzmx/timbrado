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
		
		boolean generados = true;
		
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
				
				if(resultXML != null && xml.valida(resultXML, 
												   datos.getConstantes().getFileEsquemaCFDIV33(),
												   datos.getConstantes().getFileEsquemaNominaV12()))
				{
					writeCadenas.println(d.getCadenaOriginal());
					compDig.save(comprobante(d, resultXML));
				} else {
					generados = false;
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
		
		if(generados){
		
			System.out.println("GENERANDO PAQUETE ");
		
			GeneraPaquete paq = new GeneraPaquete(idNomina);
			paq.getPaquete("E:\\RESULTADO_TIMBRADO\\envio_id_"+ idNomina +".xml");
			
			System.out.println("ARCHIVO DE ENVIO "+ idNomina + " GENERADO ....");
			
		}	else {
			
			System.out.println("NO SE GENERO ARCHIVO DE ENVIO -> "+ idNomina + " !!!!!!!!");
		}	
		
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
