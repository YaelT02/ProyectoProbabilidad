package Negocio;

/*Autores: Sergio Yael Trejo Maya
           Pamela Rosmeri Chavarria Cruz
           kenia Stephania Herrera Arteaga
*/

//Importacion de clases necesaria para el funcionamiento de esta clase
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.modelo;

public class Negocio {
    
    DefaultTableModel model;
    DefaultTableModel model2;
    
    ArrayList GLLimInfe = new ArrayList();
    ArrayList GLLimSup = new ArrayList();
    ArrayList GLMarClas = new ArrayList();
    ArrayList GLFa = new ArrayList();
    ArrayList GLFr = new ArrayList();
    ArrayList GLFrAc = new ArrayList();
    ArrayList GLFaAc = new ArrayList();

    public Negocio(List datos,modelo model) {
        
        //Declaracion de Variables
        double Media, Mediana, Moda1, Desviacion, DesviacionM, Varianza, MediaAG, MedianaAG, ModaAG;
        int intervalo,rango;
        String sesgo1, concadenar;
        double MediaAg, MedianaAg, ModaAg;   
        
        //Declaracion de Array
        double[] datos1; 
        
        //Tablas
        this.model = new DefaultTableModel();
        this.model2 = new DefaultTableModel();
        
        //Datos
        datos1=cambiar(datos);
        
        //Valores Necesarios
        rango=rangoNAG(datos1);
        intervalo=intervalos(datos1,rango);
        
        //Medidas Tendencia Central Para Datos No Agrupados
        Media = mediaNG(datos1);
        Mediana = MedianaNG(datos1);
        Moda1 = moda(datos1);
        
        
        
        //Medidas De Dispercion
        Desviacion = desviacionEstandarNAGP(datos1, Media);
        DesviacionM = desviacionMediaNAGP(datos1, Media);
        sesgo1 = sesgo(Media, Mediana, Desviacion);        
        Varianza=varianzaNAGM(datos1, Media);
        
        //Llenado de Tablas
        final DefaultTableModel tabla = this.TablaDeFrecuencia(datos1, intervalo);
        final DefaultTableModel tabla2 = this.Redatos(datos1);
        
        //Medidas Tendencia Central Para Datos Agrupados
        MediaAg = mediaAG(GLMarClas, GLFa, datos1);
        ModaAg = modaAg(GLFa, intervalo, GLLimInfe);
        MedianaAg = medianaAg(GLFa, intervalo, GLLimInfe, GLFaAc);

        //Invocacion de metodos set desde modelo
        model.setDesviacionM(DesviacionM);
        model.setVarianza(Varianza);
        model.setMedia(Media);
        model.setMediana(Mediana);
        model.setModa1(Moda1);
        model.setRango(rango);
        model.setDesviacion(Desviacion);
        model.setIntervalo(intervalo);
        model.setSesgo(sesgo1);
        model.setModaAG(ModaAg);
        model.setMediaAG(MediaAg);
        model.setMedianaAG(MedianaAg);
       
        concadenar = "La media es de: " + Media + "\nLa mediana es de: " + Mediana + "\nLa moda es de: " + Moda1 + "\nLa desviación es de: " + Desviacion
                + "\n" + sesgo1;
        System.out.println(concadenar);
        
        TablaDeFrecuencia(datos1, intervalo);
        //frecuenAb(datos1, intervalo);
        model.setModel(tabla);
        model.setDTO(tabla2);
                
    }
    
    //Metodos/Operaciones utilizados en el programa
    public static double[] cambiar(List datos) {
        int sum=0;
        for (int i = 0; i < datos.size(); i++) {
            String get = (String) datos.get(i);
            if(Numero(get)){
                sum++;
            }
        }
        double[] prueba = new double[sum];
        int lugar=0;
        for (int i = 0; i < datos.size();i++) {
            if(Numero((String) datos.get(i))){
            if(i!=lugar){
                prueba[lugar] = Double.parseDouble((String) datos.get(i));
            }else{
                prueba[lugar] = Double.parseDouble((String) datos.get(i));
            }
            lugar=lugar+1;
        }
        }
        Arrays.sort(prueba);
        return prueba;

    }

    public static boolean Numero(String a) {

        boolean resultado = false;
        try {
            Double.parseDouble(a);
            resultado = true;
        } catch (Exception e) {
        }
        return resultado;
    }
    
    
    //MEDIDAS CENTRALES DATOS NO AGRUPADOS
    
    public DefaultTableModel Redatos(final double[] c) {
        model2.setColumnIdentifiers(new Object[]{"Datos Ordenados"});
        Object[] datos = new Object[c.length];
        for (int i = 0; i < c.length; i++) {
            datos[0] = c[i];
            model2.addRow(datos);
        }
        model2.addRow(datos);
        return model2;
    }

    public double mediaNG(double[] a) {
        double media = 0.0;
        for (int i = 0; i < a.length; i++) {
            media = media + a[i];
        }
        media = media / a.length;
        return media;
    }

    public double MedianaNG(double[] a) {
        double mediana = 0;
        if (a.length == 1) {
            mediana = a[0];
        } else {
            if (a.length % 2 == 0) {
                mediana = (a[(a.length / 2) - 1] + a[(a.length / 2)]) / 2;
            } else {
                mediana = a[a.length / 2];
            }

        }
        return mediana;
    }

    public double moda(double[] v) {
        final int moda = 0;
        final int n = v.length;
        int frecModa = 0;
        double moda2 = -1.0;
        for (int i = 0; i < n; ++i) {
            int frecTemp = 1;
            for (int j = i + 1; j < n; ++j) {
                if (v[i] == v[j]) {
                    ++frecTemp;
                }
            }
            if (frecTemp > frecModa) {
                frecModa = frecTemp;
                moda2 = v[i];
            }
        }
        return moda2;
    }    

    
    //DISPERCION
    
    public String sesgo(double media, double mediana, double desviacion) {

        double asimetria = 0.0;
        String men = "";
        asimetria = (3 * media - mediana) / desviacion;

        if (asimetria > 0) {
            men = "Es asimetrica hacia la derecha,\n la asimetria es de: ";
        } else if (asimetria < 0) {
            men = "Es asimetrica hacia la izquierda,\n la asimetria es de: ";
        } else if (asimetria == 0) {
            men = "Es asimetrica,\n la asimetria es de: ";

        }
        return men + asimetria;
    }

    public double varianzaNAGP(double[] a, double media) {
        double div = 0.0, sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            double d = 0.0;
            d = Math.pow((a[i] - media), 2);
            sum = sum + d;
        }
        div = sum / a.length;

        return div;
    }

    public double varianzaNAGM(double[] a, double media) {
        double div = 0.0, sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            double d = 0.0;
            d = Math.pow((a[i] - media), 2);
            sum = sum + d;
        }
        div = sum / (a.length - 1);

        return div;
    }

    public double desviacionEstandarNAGP(double[] a, double media) {
        double desEs = 0.0, div = 0.0, sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            double d = 0.0;
            d = Math.pow((a[i] - media), 2);
            sum = sum + d;
        }
        div = sum / a.length;
        desEs = Math.sqrt(div);
        return desEs;
    }
    
    public double desviacionMediaNAGP(double[] a, double media) {
        double desEs = 0.0, div = 0.0, sum = 0.0, med = 0.0;
        for (int i = 0; i < a.length; i++) {
            double d = 0.0;
            d = Math.pow((a[i] - media), 2);
            sum = sum + d;
        }
        div = sum / a.length;
        desEs = Math.sqrt(div);
        med = desEs / 2;
        return med;
    }
    
    
    //OPERACIONES NECESARIAS
    
    public int rangoNAG(double[] a) {

        int v = (int) (a[a.length - 1] - a[0]);
        return (int) (a[a.length - 1] - a[0]);
    }

    public int intervalos(double[] a, int rango) {

        double log = Math.log10(a.length);
        double intervalo = 1 + ((3.322) * (log));
        Math.round(intervalo);
        double op = rango / intervalo;
        return (int) Math.round(op);
    }

    //TABLA DE FRECUENCIAS
    
    public DefaultTableModel TablaDeFrecuencia(double[] a, int intervalo) {
        int c = 0;
        double g = 0, dat = 0, dat2 = 0.0, Fr = 0, FrA = 0;
        String inter;
        model.setColumnIdentifiers(new Object[]{"Intervalos", "Marca de Clase", "Fa", "Fa Acumulada", "Fr", "Fr Acumulada"});
        for (int i = 0; dat < a[a.length - 1];) {
            double d = 0;
            if (i == 0) {
                dat = a[i];
                dat2 = dat + intervalo;
                double Xi = (dat + dat2) / 2;
                for (int j = 0; j < a.length; j++) {
                    if (a[j] >= dat && a[j] < dat2) {
                        d++;
                    }
                }
                Fr = (d / a.length) * 100;

                g = (g + d);
                FrA = FrA + Fr;
                inter = dat + "--" + dat2;
                model.addRow(new Object[]{inter, Xi, Math.round(d), Math.round(g), Math.round(Fr) + "%", Math.round(FrA) + "%"});
                GLLimInfe.add(dat);
                GLLimSup.add(dat2);
                GLMarClas.add(Xi);
                GLFa.add(d);
                GLFaAc.add(g);
                GLFr.add(Fr);
                GLFrAc.add(FrA);
                dat = dat2;
            } else {
                dat2 = dat + intervalo;
                double Xi = (dat + dat2) / 2;
                for (int j = 0; j < a.length; j++) {
                    if (a[j] >= dat && a[j] < dat2) {
                        d++;
                    } else if (dat2 == a[a.length - 1] && a[j] == dat2) {
                        d++;
                    }
                }
                Fr = (d / a.length) * 100;
                g = (g + d);
                FrA = FrA + Fr;
                inter = dat + "--" + dat2;
                model.addRow(new Object[]{inter, Xi, Math.round(d), Math.round(g), Math.round(Fr) + "%", Math.round(FrA) + "%"});
                GLLimInfe.add(dat);
                GLLimSup.add(dat2);
                GLMarClas.add(Xi);
                GLFa.add(d);
                GLFaAc.add(g);
                GLFr.add(Fr);
                GLFrAc.add(FrA);
                dat = dat2;
            }
            i++;
            c++;
        }
        System.out.println("Fue un exito");
        return model;
    }

    
    //MEDIDAS CENTRALES DATOS AGRUPADOS
    
    public static double[] cambiarv2(List datos) {
        double[] prueba = new double[datos.size()];

        for (int i = 0; i < datos.size(); i++) {
            prueba[i] = (double) datos.get(i);
        }

        return prueba;

    }
   
    public double mediaAG(ArrayList MarcClas, ArrayList Fa, double[] a) {
        double med = 0, sum = 0, g, b;
        double[] FaC = cambiarv2(Fa);
        double[] MarcClasC = cambiarv2(MarcClas);

        for (int i = 0; i < Fa.size(); i++) {
            g = MarcClasC[i];
            b = FaC[i];
            sum = sum + (g * b);
        }
        med = sum / a.length;
        return med;
    }

    public double modaAg(ArrayList Fa, int amplitud, ArrayList LimIf) {
        double[] Fra = cambiarv2(Fa);
        double[] LimInf = cambiarv2(LimIf);
        int posicion = 0;
        double mayor = Fra[0];
        for (int i = 1; i < Fra.length; i++) {
            if (Fra[i] > mayor) {
                mayor = Fra[i];
                posicion = i;
            }
        }
        double LiInf = LimInf[posicion], DtAnt = Fra[(posicion - 1)], DtSeg = Fra[(posicion + 1)], moda;
        moda = LiInf + (((mayor - DtAnt) / ((mayor - DtAnt) + (mayor - DtSeg))) * amplitud);
        return moda;

    }

    public double medianaAg(ArrayList Fa, int amplitud, ArrayList LimIf, ArrayList FaAc) {
        double[] Fra = cambiarv2(Fa);
        double[] LimI = cambiarv2(LimIf);
        double[] FraAC = cambiarv2(FaAc);
        double posicion = FraAC[FraAC.length - 1] / 2;
        int indicador = 1;

        for (int i = 0; i < FraAC.length; i++) {
            if (FraAC[i] >= posicion) {
                indicador = i;
                break;
            }
        }
        double mediana;
        mediana = LimI[indicador] + amplitud * ((posicion - FraAC[indicador - 1]) / Fra[indicador]);

        return mediana;
    }
}



