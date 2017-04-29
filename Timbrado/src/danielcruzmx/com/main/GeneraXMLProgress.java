package danielcruzmx.com.main;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import danielcruzmx.com.data.TnComprobanteDig;
import danielcruzmx.com.data.TnComprobanteDigPK;
import danielcruzmx.com.model.DatosXMLTimbrado;
import danielcruzmx.com.service.PagoTimbradoService;
import danielcruzmx.com.service.TnComprobanteDigitalService;

public class GeneraXMLProgress {

	private Integer idNomina;

    public GeneraXMLProgress(Integer idNomina) {
    	
    	this.setIdNomina(idNomina);
    	
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception  ex) {
                }

                new BackgroundWorker().execute();

            }

        });
    }

    public Integer getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(Integer idNomina) {
		this.idNomina = idNomina;
	}

	public class BackgroundWorker extends SwingWorker<Void, Void> {

		private ProgressMonitor monitor;

        public BackgroundWorker() {
            addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
                        if (monitor == null) {
                            monitor = new ProgressMonitor(null, "Procesando", null, 0, 99);
                        }
                        monitor.setProgress(getProgress());
                        
        				if(monitor.isCanceled())monitor.close();
                    }
  
                }

            });
        }

        @Override
        protected void done() {
            if (monitor != null) {
                monitor.close();
            }
        }

        @Override
        protected Void doInBackground() throws Exception {
        	
        	PagoTimbradoService datos = new PagoTimbradoService();
        	datos.recuperaDatos(getIdNomina());
        	
        	List <DatosXMLTimbrado> pagos = datos.getPagos();
        	
        	int total = pagos.size();
        	int factor = total / 99 + 1;
        	int index = 0;
        	int contador = 0;
        	
        	System.out.println(" Total de registros -> " + total);
        	
    		try {

    			PrintWriter writeCadenas = null;
        		TnComprobanteDigitalService compDig = new TnComprobanteDigitalService();
    			
    			writeCadenas = new PrintWriter("D:\\RESULTADO_TIMBRADO\\" + idNomina + "_cadenas.txt");
    			compDig.beginTransaction();
    			
    			for(DatosXMLTimbrado pago: pagos){
    				
    				GeneraXML archivo = new GeneraXML();
    				String archivoXML = archivo.getFile(pago, writeCadenas); 
    				
    				if( archivoXML != null){

    					// Guarda en base de datos
    			        TnComprobanteDigPK id = new TnComprobanteDigPK();
    			        id.setCnFolio(pago.getFolio());
    			        id.setCnDifFolio(pago.getDifFolio());
    			        
    			        TnComprobanteDig dbCom = new TnComprobanteDig();
    			        dbCom.setId(id);
    			        dbCom.setCnConsecNomina(new BigDecimal(pago.getSerie()));
    			        dbCom.setCnEjercicio(new BigDecimal(pago.getPuesto().substring(3, 7)));
    			        dbCom.setCnFechaGenerado( new Date());
    			        dbCom.setCnSituacion("G");
    			        dbCom.setCnRfc(pago.getRfcReceptor());
    			        dbCom.setCnPaquete(new BigDecimal(1));
    			        dbCom.setUsuario("danielcruzmx");
    			        dbCom.setFecModifico(new Date());
    			        
    			        //String strResult = archivo.fileXMLToString(archivoXML);
    			        
    			        dbCom.setCnXmlGenerado(archivoXML);
    			        
    			        compDig.save(dbCom);
    					
    				} else {

    					writeCadenas.println(pago.getRfcReceptor() + "|" + "No se genero XML" + "||");
    				};
    				
    				contador = contador + 1;
    				index = contador / factor;
    				
    				if((contador % factor) == 0)System.out.println(" indice -> " + contador);
    				
    				setProgress(index);
    			}
    			
    			writeCadenas.close();
    			compDig.commitTransaction();
    			compDig.close();
    			
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		return null;
        }
    }
}