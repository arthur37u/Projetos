package aplicativo;
import java.util.*;
import java.util.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.font.*;
import java.text.SimpleDateFormat;
public class App implements ActionListener  {
	Font grande = new Font("Arial", Font.BOLD,50);
	String teste2;
	JTextField text;
	int contador = 0;
	int verificador;
	ArrayList<String> lista = new ArrayList();
	ArrayList<String> listahrs = new ArrayList();
	BufferedImage img = null;
	int x =30;
	int y = 54;
	int z = 190;
	int w = 400;
	Timer timer = new Timer();
	 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
     Date agora = new Date();
     String horaFormatada = dateFormat.format(agora);
     String hr;
     String min;
     String hrs = (hr+":"+min+":"+"00"); 
     int crescendo = -1;
	public App(){
 JFrame jframe = new JFrame(); 
 try {img =  ImageIO.read(new File ("wallpaperapp.jpg"));
 }catch(IOException e) {
	 e.printStackTrace();
 }
 jframe.setVisible(true);
 jframe.setSize(1280, 720);
 jframe.setIconImage(img);
 jframe.setTitle("Note_Task");
 jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 jframe.setResizable(false);
 jframe.setLocationRelativeTo(null);
 jframe.setLayout(null);
 
 JButton nota = new JButton();
 nota.setBounds(150, 200, 200, 100);
 nota.setForeground(new Color(237, 241, 140));
 nota.setBackground(new Color(70, 170, 60));

JFrame aviso = new JFrame();
JLabel avisonote = new JLabel("");

 JButton jbutton = new JButton("criar tabela de rotina");
 jbutton.setBounds(50,50,300,100);
 jbutton.setForeground(new Color(237, 241, 140));
 jbutton.setBackground(new Color(70, 170, 60));
   jframe.add(jbutton);
   jbutton.addActionListener(this);
   
   JLabel anunciohr = new JLabel("horário atual:");
   JLabel horario= new JLabel(horaFormatada.toString());
   anunciohr.setBounds(620, 80, 120, 350);
   horario.setBounds(600, 100, 200, 450);
   horario.setFont(grande);
   jframe.add(anunciohr);
   jframe.add(horario);
   
   TimerTask tarefa = new TimerTask() {
	@Override
	public void run() {
		jframe.dispose(); 
		new App();	
	} 
};
   timer.scheduleAtFixedRate(tarefa, 60000, 60000);
   //relógio funcional para compromissos 
   while(true){
	   dateFormat = new SimpleDateFormat("HH:mm");
	   agora = new Date();
	   horaFormatada = dateFormat.format(agora);
	   if (listahrs.size() >= 1) {
           if (crescendo < listahrs.size()) {
               crescendo++;
           }
           if (crescendo >= listahrs.size()) {
               crescendo = 0;
           }

           if (listahrs.get(crescendo).equals(horaFormatada)) {
               // Your alarm logic goes here
               
           
	   aviso.setVisible(true);
	   aviso.setSize(1280, 720);
	   aviso.setIconImage(img);
	   aviso.setTitle("Uma Nota foi ativada!!!!");
	   aviso.setResizable(false);
	   aviso.setLocationRelativeTo(null);
	   aviso.setLayout(null);
	   avisonote.setBounds(500, 100, 300, 400);
	   avisonote.setFont(grande);
	   avisonote =new JLabel(lista.get(contador));
	   aviso.add(avisonote);}}
	   
       try {
           Thread.sleep(1000); // Aguarda 1 segundo
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
	 	  }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame listatabela = new JFrame();
		JList<String> legenda = new JList(lista.toArray());
		JList<String> hras = new JList(listahrs.toArray());
		try {img =  ImageIO.read(new File ("notas2janela.jpg"));
		 }catch(IOException a) {
			 a.printStackTrace();
		 }
		
		switch(verificador) {
		  case 1:
				listatabela.setVisible(true);
				 listatabela.setSize(1280, 720);
				 listatabela.setTitle("Notas");
				 listatabela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 listatabela.setResizable(false);
				 listatabela.setLocationRelativeTo(null);
				 listatabela.setLayout(null);
				 listatabela.setIconImage(img);
				 legenda.setBounds(x,y,z,w);
				 hras.setBounds(x+300,y,z+20,w);
				 listatabela.add(legenda);
				 listatabela.add(hras);
				 // caso cliquem na nota realizar crud 
				 legenda.addListSelectionListener(c->{
					 int index = legenda.getSelectedIndex();
					 Object[] options = { "alterar", "excluir", "cancelar alteração" };
					int valor = JOptionPane.showOptionDialog(null, "o que deseja realizar com essa anotação?", "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					 switch(valor) {
					 case 0:
						 teste2= JOptionPane.showInputDialog("coloque a nova anotação");
						 hr = (JOptionPane.showInputDialog("digite as novas horas"));
						  min =(JOptionPane.showInputDialog("digite os novos minutos"));
						  String hrs = (hr+":"+min); 
							lista.set(index, teste2); 
							listahrs.set(index, hrs);
							listatabela.dispose();
							App.this.actionPerformed(null);
							break;
							
					 case 1: 
						 lista.remove(index);
						 listahrs.remove(index);
						 listatabela.dispose();
							App.this.actionPerformed(null);
						 break;
				    default:
					 }
					 // fim do crud e continuação do do switch para realização das notações
				 });
		    break;
		  default:
			  do {teste2 = JOptionPane.showInputDialog("digite sua notação:");
			  hr = (JOptionPane.showInputDialog("digite as horas"));
			  min = (JOptionPane.showInputDialog("digite os minutos"));
			  verificador= JOptionPane.showConfirmDialog(null,"deseja colocar mais uma notação?");
			  lista.add(teste2);
			  String hrs = (hr+":"+min); 
			  listahrs.add(hrs);
			  listatabela.dispose();
				App.this.actionPerformed(null);} while(verificador == 0);
		}   
		 
	}
      
	}
	
	
