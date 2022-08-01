package Contolador;

/*Autores: Sergio Yael Trejo Maya
           Pamela Rosmeri Chavarria Cruz
           kenia Stephania Herrera Arteaga
*/

//Importacion de clases necesaria para el funcionamiento de esta clase
import Vista.InformacionNoAg;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import Vista.importar;
import Vista.importarNoAg;
import modelo.modelo;
import java.awt.event.ActionListener;

//Clase dedicada al procesamiento de los dato ingresados mediante un archivo de excel

//Carga de archivo desde la interfaz de importar
public class controNoAg implements ActionListener{
     modelo mod = new modelo();
     importarNoAg VistaEX=new importarNoAg();
     
    JFileChooser SelectArchivo=new JFileChooser();
    File archivo;
    int contador=0;
    
    public controNoAg(importarNoAg VistaEX){
        this.VistaEX=VistaEX;
        this.VistaEX.btnImportar.addActionListener(this);
        VistaEX.setVisible(true);
        VistaEX.setLocationRelativeTo(null);        
    }
    
    //Filtro de tipo de archivo
    public void AgregarFiltro(){
        SelectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)","xls"));
        SelectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)","xlsx"));
    }
    
    //Lectura de datos
    @Override
    public void actionPerformed(ActionEvent e) {
        contador++;
        if(contador==1)AgregarFiltro();
        if(e.getSource()==VistaEX.btnImportar){
            if(SelectArchivo.showDialog(null, "Seleccionar Archivo")==JFileChooser.APPROVE_OPTION){
                archivo=SelectArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls")||archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, mod.leer2(archivo,mod));
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccionar formato Valido");
                }
            }
            
        }
        InformacionNoAg inf= new InformacionNoAg(mod);
        inf.setVisible(true);
    }
}