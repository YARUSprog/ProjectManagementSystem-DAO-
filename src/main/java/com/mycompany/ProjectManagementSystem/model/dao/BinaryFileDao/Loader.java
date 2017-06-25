
package com.mycompany.ProjectManagementSystem.model.dao.BinaryFileDao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YARUS
 */
public class Loader<T> {
    
    public void saveToXML(List<T> object, String file) {
        XStream xstream = new XStream(new DomDriver());
        String xmlText = xstream.toXML(object);        
        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(xmlText);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<T> loadFromXML(String file) {
        XStream xstream = new XStream(new DomDriver());
        String tempText;
        StringBuffer xmlText;
        try (BufferedReader in = new BufferedReader(new FileReader(file)))
        {            
            xmlText = new StringBuffer();
            while ((tempText = in.readLine()) != null) {
                xmlText.append(tempText).append("\n") ;
            }
        } catch(FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex){
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
        
        List<T> res = (List<T>) xstream.fromXML(xmlText.toString());
        //List<T> res = new ArrayList<>(Arrays.asList(res0));
        return res;
    }
    
    public void saveToBinaryFile(List<T> object, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null)
                    oos.close();
                if(fos != null)
                    fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<T> loadFromBinaryFile(String file) {
        FileInputStream fis = null;
        List<T> object = null;
        ObjectInputStream oin = null;
        try {
            fis = new FileInputStream(file);
            oin = new ObjectInputStream(fis);
            object = (List<T>) oin.readObject();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oin != null)
                    oin.close();
                if(fis != null)
                    fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return object;
    }
    
    public void saveToJSON(List<T> object, String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;
        String jsonText = gson.toJson(object);        
        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(jsonText);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<T> loadFromJSON(final Class<T[]> clazz, String file) {
        Gson gson = new Gson();
        Type type = clazz;
        String tempText;
        StringBuffer jsonText;
        try (BufferedReader in = new BufferedReader(new FileReader(file)))
        {            
            jsonText = new StringBuffer();
            while ((tempText = in.readLine()) != null) {
                jsonText.append(tempText).append("\n") ;
            }
        } catch(FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex){
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
        T[] res0 = gson.fromJson(jsonText.toString(), type);        
        List<T> res = new ArrayList<>(Arrays.asList(res0));
        return res;
    } 
}


