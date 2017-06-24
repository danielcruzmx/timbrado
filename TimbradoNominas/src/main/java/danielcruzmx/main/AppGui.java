package danielcruzmx.main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import danielcruzmx.business.GeneraComprobante;
import danielcruzmx.business.GeneraListaNominas;
import danielcruzmx.business.GeneraListaPagos;
import danielcruzmx.business.GeneraPaquete;
import danielcruzmx.data.ComprobanteDigital;
import danielcruzmx.data.ComprobanteDigitalPK;
import danielcruzmx.data.NominasEjercicio;
import danielcruzmx.domain.DatosXML;
import danielcruzmx.service.ComprobanteDigitalService;

public class AppGui {

	private JFrame frmShcpTimbradoDe;
	private JTextField textField;
	private JTextArea textArea;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GeneraListaNominas nominas = new GeneraListaNominas();
					List <NominasEjercicio> nom = nominas.getListaNominas();
					
					AppGui window = new AppGui(nom);
					window.frmShcpTimbradoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppGui(List <NominasEjercicio> nom) {
		initialize(nom);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(List <NominasEjercicio> nom) {
		frmShcpTimbradoDe = new JFrame();
		frmShcpTimbradoDe.setTitle("SHCP Timbrado de Nominas");
		frmShcpTimbradoDe.setBounds(100, 100, 450, 300);
		frmShcpTimbradoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShcpTimbradoDe.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		frmShcpTimbradoDe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblListaDeNominas = new JLabel("Nominas del ejercicio");
		lblListaDeNominas.setBounds(18, 23, 154, 14);
		panel.add(lblListaDeNominas);
		
		final JComboBox comboBox = new JComboBox();
		
		for(NominasEjercicio n: nom){
			comboBox.addItem(n);
		}
		
		comboBox.setBounds(182, 20, 231, 20);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						NominasEjercicio nominaSeleccionada = (NominasEjercicio)comboBox.getSelectedItem();
						textField.setText(nominaSeleccionada.getDescripcion());
			}
		});
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(18, 51, 395, 20);
		panel.add(textField);
		textField.setColumns(40);
		
		JButton btnNewButton = new JButton("Genera");
		btnNewButton.setBounds(152, 82, 129, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NominasEjercicio nominaSeleccionada = (NominasEjercicio)comboBox.getSelectedItem();
				Genera(nominaSeleccionada.getId());
			}
		});
		panel.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(18, 116, 395, 134);
		panel.add(textArea);
	}
	
	private void Genera(Integer idNomina){
		
		GeneraListaPagos datos = new GeneraListaPagos(idNomina);
		List <DatosXML> ldatos = datos.getListaDatos();
		PrintWriter writeCadenas = null;
		
		boolean generados = true;
		
		textArea.setText("INICIO ...\n");
		
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
		
			textArea.append("GENERANDO PAQUETE....\n");
		
			GeneraPaquete paq = new GeneraPaquete(idNomina);
			paq.getPaquete("E:\\RESULTADO_TIMBRADO\\envio_id_"+ idNomina +".xml");
			
			textArea.append("ARCHIVO DE ENVIO "+ idNomina + " GENERADO ....\n");
			
		}	else {
			
			textArea.append("NO SE GENERO ARCHIVO DE ENVIO -> "+ idNomina + " !!!!!!!!\n");
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
