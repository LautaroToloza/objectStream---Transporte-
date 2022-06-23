package ar.com.lautaro.test;

import ar.com.lautaro.clases.Chofer;
import ar.com.lautaro.clases.Transportes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;

public class testTransporte {

    public static void main(String[] args) {
        /* Consigna: 
        Una empresa de encomienda desea tener una aplicación que cargue tanto un lista 
        de los trasportes que llegan en un registro permanente así como cada día también
        Se carga una lista con los datos de los transportistas que transitaron solo para esa fecha.
        Transporte: contenido, proveedor, idChofer.
        Chofer: id (alfanumérico), nombre, numeroCamion.
         */

        test();

    }

    public static void crearDirectorio(String ruta) {
        File ejemploDir = new File(ruta);
        if (ejemploDir.exists()) {
            if (ejemploDir.isDirectory()) {
                JOptionPane.showInternalMessageDialog(null, "El directorio ya existe!!");
            }

        } else {
            // Si no existe el directorio lo creamos!! 
            ejemploDir.mkdir();
            JOptionPane.showInternalMessageDialog(null, "Se creo el directorio!!");

        }

    }

    public static void cargarDirectorio(File ruta) {
        // Crear archivos dentro del directorio.
        if (ruta.exists()) {
            JOptionPane.showInternalMessageDialog(null, "Los archivos ya existen!!");
        } else {
            try {
                ruta.createNewFile();
                JOptionPane.showInternalMessageDialog(null, "Se crearon los archivos!!");
            } catch (IOException ex) {
                System.out.println("Error!! ");
                ex.printStackTrace(System.out);
            }
        }

    }

    public static void escrituraTransporte(File ruta, List<Transportes> lista) {

        try {
            FileOutputStream fo = new FileOutputStream(ruta, true);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            // writeObject()-> Solo en escritura de objetos 
            oo.writeObject(lista);

            fo.close();
            oo.close();
            JOptionPane.showMessageDialog(null, "Se escribió el archivo con los Transportes!!");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de escritura Transporte.");
            ex.printStackTrace(System.out);
        }
    }

    public static void escrituraChofer(File ruta, List<Chofer> lista) {
        try {
            // no le ponemos el 'true' porque queremos pisar los datos.
            FileOutputStream fo = new FileOutputStream(ruta);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            // writeObject()-> Solo en escritura de objetos 
            oo.writeObject(lista);

            fo.close();
            oo.close();
            JOptionPane.showMessageDialog(null, "Se escribió el archivo con los Choferes!!");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de escritura Choferes.");
            ex.printStackTrace(System.out);
        }

    }

    public static void lecturaArchivos(File rutaUno, File rutaDos) {

        try {
            FileInputStream fip = new FileInputStream(rutaUno);
            ObjectInputStream oip = new ObjectInputStream(fip);
            FileInputStream fi = new FileInputStream(rutaDos);
            ObjectInputStream oi = new ObjectInputStream(fi);
            List<Transportes> listaT = (List<Transportes>) oip.readObject();
            List<Chofer> listaC = (List<Chofer>) oi.readObject();
            listaT.forEach(System.out::println);
            listaC.forEach(System.out::println);
            fip.close();
            oip.close();
            fi.close();
            oi.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de lectura. ");
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error de tipo de clase. ");
            ex.printStackTrace(System.out);
        }

    }

    public static void test() {
        // año: 1900 + 122 = 2022
        // año/mes-1/dia
        Date dia = new Date(122, 5, 22);
        System.out.println(dia);
        String rutaDir = "C:" + File.separator + "Users" + File.separator + "HP" + File.separator + "Documents" + File.separator + "NetBeansProjects" + File.separator + "ProyectoClaseDiez" + File.separator + "directorio_transporte";
        List<Transportes> transporte = new ArrayList<>();
        List<Chofer> chofer = new ArrayList<>();
        transporte.add(new Transportes("Gaseosa", "CocaCola", "RT859P"));
        transporte.add(new Transportes("Golosinas", "Arcor", "NP452M"));
        transporte.add(new Transportes("Ropa", "Nike", "NI77KE"));
        transporte.add(new Transportes("Celulares", "Apple", "AP21LE"));
        chofer.add(new Chofer("CH01", "Lucio", 045));
        chofer.add(new Chofer("CH04", "Mariano", 777));
        chofer.add(new Chofer("CH9", "Marcelo", 014));
        chofer.add(new Chofer("CH11", "Brian", 839));
        chofer.add(new Chofer("CH7", "Nahuel", 053));
        String rutaT = "archivoTransporte.txt";
        String rutaC = "archivoTransportista.dat";
        File archivoUno = new File(rutaDir, rutaT);
        File archivoDos = new File(rutaDir, rutaC);
        crearDirectorio(rutaDir);
        cargarDirectorio(archivoUno);
        cargarDirectorio(archivoDos);
        escrituraTransporte(archivoUno, transporte);
        escrituraChofer(archivoDos, chofer);
        lecturaArchivos(archivoUno, archivoDos);

    }
}
