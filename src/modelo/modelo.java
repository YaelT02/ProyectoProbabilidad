package modelo;

/*Autores: Sergio Yael Trejo Maya
           Pamela Rosmeri Chavarria Cruz
           kenia Stephania Herrera Arteaga
*/

//Importacion de clases necesaria para el funcionamiento de esta clase
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Negocio.Negocio;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class modelo {

    //Declaracion de variables de forma privada
    private double Moda1;
    private double Media;

    private double Mediana;

    private double Desviacion;
    private double DesviacionM;
    private int intervalo;
    private int rango;
    private double varianza;
       
    private String sesgo;
    
    private double mediaAg;
    private double medianaAg;
    private double modaAg;
    
    private DefaultTableModel model;
    private DefaultTableModel DTO;
    
    //Metodo de lectura y procesamiento de datos
    public String leer2(File file, modelo model) {
        String mensaje = "Error importacion";
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator iterator = firstSheet.iterator();

            DataFormatter formatter = new DataFormatter();
            List<String> dato = new ArrayList();
            while (iterator.hasNext()) {
                Row nextRow = (Row) iterator.next();
                Iterator cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    String contenidoCelda = formatter.formatCellValue(cell);
                    dato.add(contenidoCelda);
                }
                mensaje = "Exitoso";

            }
            new Negocio(dato,model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensaje;

    }
    
    //Metodos setter y getter
    public String getSesgo() {
        return sesgo;
    }

    public void setSesgo(String sesgo1) {
        this.sesgo = sesgo1;
    }
    

    public double getMedia() {
        System.out.println("Media modelo "+Media);
        return Media;
    }

    public void setMedia(double Media1) {
        this.Media = Media1;
    }

    public double getMediana() {
        System.out.println("Mediana modelo "+Mediana);
        return Mediana;
    }

    public void setMediana(double Mediana1) {
        this.Mediana = Mediana1;
    }

    public double getModa1() {
        System.out.println("Moda modelo "+Moda1);
        return Moda1;
    }

    public void setModa1(double Moda11) {
        this.Moda1 = Moda11;
    }

    public double getDesviacion() {
        return Desviacion;
    }

    public void setDesviacion(double Desviacion1) {
        this.Desviacion = Desviacion1;
    }

    public double getDesviacionM() {
        return DesviacionM;
    }
    
    public void setDesviacionM(double DesviacionM1) {
        this.DesviacionM = DesviacionM1;
    }
    
    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo1) {
        this.intervalo = intervalo1;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango1) {
        this.rango = rango1;
    }
   
    public double getVarianza() {
        return varianza;
    }
    
    public void setVarianza(double varianza1) {
        this.varianza = varianza1;
    }
    
    public DefaultTableModel getModel() {
        return this.model;
    }
    
    public void setModel(final DefaultTableModel model) {
        this.model = model;
    }
    
     public DefaultTableModel getDTO() {
        return DTO;
    }

    public void setDTO(DefaultTableModel DTO) {
        this.DTO = DTO;
    }
    
    public double getMediaAG(){
        return mediaAg;
    }
    
    public void setMediaAG(double mediaAG){
        this.mediaAg = mediaAG;
    }
    
    public double getMedianaAG(){
        return medianaAg;
    }
    
    public void setMedianaAG(double medianaAG){
        this.medianaAg = medianaAG;
    }
    
    public double getModaAG(){
        return modaAg;
    }
    
    public void setModaAG(double modaAG){
        this.modaAg = modaAG;
    }
    
    
}
