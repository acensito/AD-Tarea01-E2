/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import jaxb.cursos.Alumnos;
import jaxb.cursos.Alumnos.Alumno;
import jaxb.cursos.CursoType;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Clase ModificaCurso
 * @author Felipon
 */
public class ModificaCurso {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Creamos una instancia de JAXBContext para manipular las clases de jaxb.cursos
            JAXBContext jaxbContext = JAXBContext.newInstance("jaxb.cursos");
            
            //Creamos un objeto Unmarshaller para transformar un documento XML a arbol de objetos JAVA
            Unmarshaller u = jaxbContext.createUnmarshaller();

            //Cargamos el documento XML como un objeto JAVA, en este caso el elemento clase.xml
            JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("resultado/clase.xml"));
            
            //Creamos un objeto cursoType, al que le asignamos los valores de los elementos del documento y atributos
            CursoType cursoType = (CursoType) jaxbElement.getValue();
            
            // Creamos un objeto alumnos para cargar los elementos de tipo alumno que contiene cursoType
            Alumnos alumnos = cursoType.getAlumnos();
            
            //Ahora creamos un Objeto alumno utilizando la clase generada Alumno
            Alumno alumno = new Alumnos.Alumno();
            
            //Asignamos los datos del nuevo alumno al objeto creado usando los setters del metodo
            alumno.setNombreAlumno("Federica Maria");
            alumno.setEdad(22);
            alumno.setDireccion("Calle Fuentealamo 37, Villaverde del Rio");
            alumno.setComentario("Sin comentarios");
            alumno.setTelefono(new BigInteger("767676767"));
            alumno.setEmailAlumno("quintocorreo@hotmail.com");
            
            //Añadimos este alumno al objeto alumnos
            alumnos.getAlumno().add(alumno);
            
            //Ahora invertimos la operación realizada
            //Creamos el objeto Marshaller para convertir el objeto Java a uno XML
            Marshaller m = jaxbContext.createMarshaller();
            //Estableciemos la propiedad del objeto Marshaller para que salga formateado
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            //Mostramos los cambios por consola
            m.marshal(jaxbElement, System.out);
            
            //Guardamos los cambios al archivo
            m.marshal(jaxbElement, new FileOutputStream("resultado/clase.xml"));
            
        //En caso de errores JAXB, sean mostrados por consola
        } catch (JAXBException je) {
            System.out.println(je.getCause()) ;
        //En caso de errores de E/S, que sean mostrados
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }    
}
