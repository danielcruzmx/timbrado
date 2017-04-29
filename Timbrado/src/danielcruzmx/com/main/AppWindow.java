package danielcruzmx.com.main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class AppWindow {


	JFrame ventana; 
	JLabel etiqueta; 
	JTextField campo; 
	JButton btnGenera; 
	JButton btnEnvio;
	
	public AppWindow(){

		JFrame ventana = new JFrame("SHCP - Timbrado de nominas"); 
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		ventana.setLayout(new FlowLayout()); 
		
		etiqueta = new JLabel("Id de Nómina");
		ventana.add(etiqueta);
		
		campo = new JTextField(10);
		ventana.add(campo);
		
		btnGenera =  new JButton("Generar XML");
		ventana.add(btnGenera);
		
		btnEnvio =  new JButton("Envio SAT");
		ventana.add(btnEnvio);

		ventana.pack(); 
		
		listenerGUI();
		
		ventana.setVisible(true); 
		ventana.setLocationRelativeTo(null);

	}
	
	private void listenerGUI(){
		  btnGenera.addActionListener(new ActionListener(){ 
			  public void actionPerformed(ActionEvent e) { 
				  new GeneraXMLProgress(Integer.parseInt(campo.getText()));
		   }}); 

		  btnEnvio.addActionListener(new ActionListener(){ 
			  public void actionPerformed(ActionEvent e) { 
				  new GeneraPaqueteEnvio(Integer.parseInt(campo.getText()));
		   }}); 

	}
	
	public static void main(String [] args){
		new AppWindow();
	}
	
}
