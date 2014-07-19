package it.aive.utility;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * FileUtil.java Contiene funzioni di utilita per la gestione dei file <br>
 * <b> Storia delle Modifiche </b><br>
 * 01/12/2002 Prima versione <br>
 *
 * @author Baraldi Marco
 * @version 1.0
 */
public class FileUtil
{
    //~ Methods ----------------------------------------------------------------
public static final String FILE_ON_FILESYSTEM="P";
 public static final String FILE_ON_CLASSPATH="C";  
    /**
     * Dato il nome di un file  ritorna il suo contenuto  come stringa. Se il
     * flagWhere vale FILE_ON_CLASSPATH allora il file verra cercato nel classpath e quindi
     * non e' necessario definire il classpath (es pippo.xml)  se invece il
     * flagWhere vale FILE_ON_FILESYSTEM ha pisogno di tutto il path (es c:\prova\pippo.xml)
     *
     * @param fileName
     * @param flagWhere vale FILE_ON_FILESYSTEM o FILE_ON_CLASSPATH
     *
     * @return
     *
     * @throws IOException
     */
    public static String readFile(String fileName, String flagWhere) throws Exception
    {   
        BufferedReader dis = new BufferedReader(new InputStreamReader(findInputStream(fileName, flagWhere)));
        StringBuffer buff = new StringBuffer("");
        String input;

        while ((input = dis.readLine()) != null)
        {
            buff.append(input);
            buff.append("\n");
        }

        dis.close();

        return buff.toString();
    }

    public static void writeFile(String fileName,String Content, String flagWhere) throws Exception
    {   
        BufferedOutputStream dis = new BufferedOutputStream(findOutputStream(fileName, flagWhere));
       dis.write(Content.getBytes());
       dis.flush();
       dis.close();
       
    }
    
    public static void writeFile(String fileName,byte[] content, String flagWhere) throws Exception
    {   
        BufferedOutputStream dis = new BufferedOutputStream(findOutputStream(fileName, flagWhere));
       dis.write(content);
       dis.flush();
       dis.close();
       
    }

    /**
     * Stampa un file parametrizzato e una map ritorna in un altro file il
     * risultato
     *
     * @param fileInputName DOCUMENT ME!
     * @param fileOutputName DOCUMENT ME!
     * @param parametri DOCUMENT ME!
     *
     * @return
     *
     * @throws IOException
     */
    public static void stampaFileParametrizzato(String fileInputName, String fileOutputName, Hashtable parametri,String flagWhereInputFile) throws Exception
    {
		  String template=readFile(fileInputName,flagWhereInputFile);
      	Enumeration names = parametri.keys();
				while ( names.hasMoreElements() ) {
           String	key;
           Object value;
					 key =	(String)names.nextElement();
					 value	=parametri.get(key);
           template=StringUtil.substitute(key,value.toString(),template);
        }
    writeFile(fileOutputName,template,flagWhereInputFile);
    }

    /**
     * DOCUMENT ME!
     *
     * @param fileName
     * @param searchFlag
     *
     * @return
     *
     * @throws Exception
     */
    public static InputStream findInputStream(String fileName, String searchFlag) throws Exception
    {
        InputStream fileResource = null;
        
        if (searchFlag.equalsIgnoreCase(FILE_ON_CLASSPATH))
        {
            // cerco il file nel Class Path
            FileUtil tmp1 = new FileUtil();
            ClassLoader loader = tmp1.getClass().getClassLoader();
            fileResource = loader.getResourceAsStream(fileName);
        } else    if (searchFlag.equalsIgnoreCase(FILE_ON_FILESYSTEM))
        {
            fileResource = new FileInputStream(fileName);
        } else {
         throw new Exception("Il flag deve essere C oppure P");
        }

        return fileResource;
    }

 public static FileOutputStream findOutputStream(String fileName, String searchFlag) throws Exception
    {
        FileOutputStream fileResource = null;
        
        if (searchFlag.equalsIgnoreCase(FILE_ON_CLASSPATH))
        {
            // cerco il file nel Class Path
            FileUtil tmp1 = new FileUtil();
            ClassLoader loader = tmp1.getClass().getClassLoader();
            //fileResource = loader.getResourceAsStream(fileName);
        } else    if (searchFlag.equalsIgnoreCase(FILE_ON_FILESYSTEM))
        {
            fileResource = new FileOutputStream(fileName);
        } else {
         throw new Exception("Il flag deve essere C oppure P");
        }

        return fileResource;
    }

    /**
     * Dato il nome di un file lo ricerca nel classpath  e ritorna il suo
     * contenuto  come stringa.
     *
     * @param fileName
     *
     * @return
     *
     * @throws Exception
     */
    public static String readFileFromClassPath(String fileName) throws Exception
    {
        FileUtil tmp1 = new FileUtil();
        ClassLoader loader = tmp1.getClass().getClassLoader();
        InputStream fileResource = loader.getResourceAsStream(fileName);
        InputStreamReader tmp = new InputStreamReader(fileResource);
        BufferedReader dis = new BufferedReader(tmp);
        StringBuffer buff = new StringBuffer("");
        String input;

        while ((input = dis.readLine()) != null)
        {
            buff.append(input);
            buff.append("\n");
        }

        dis.close();

        return buff.toString();
    }

    /**
     * Ritorna la lista dei file e delle sottodirectory presenti in  una
     * directory data. Non e' ricorsivo
     *
     * @param dir directory
     *
     * @return array di stringhe contenenti i nomi dei file
     */
    public static String[] returnFileList(String dir)
    {
        File stream = new File(dir);
        String[] listFiles = null;

        if (stream.isDirectory())
        {
            listFiles = stream.list();
        }

        return listFiles;
    }

    /**
     * Ritorna la lista dei file e delle sottodirectory presenti in  una
     * directory data. Non e' ricorsivo
     *
     * @param dir directory
     * @param ext DOCUMENT ME!
     *
     * @return array di stringhe contenenti i nomi dei file
     */
    public String[] returnFileList(String dir, String ext)
    {
        ExtFilter filter = new ExtFilter(ext);
        File stream = new File(dir);
        String[] listFiles = stream.list(filter);
        ;

        return listFiles;
    }

    /**
     * Data una directory ritona la lista di tutti i file conetuti in quella
     * directory e nelle sottodirectory
     *
     * @param dir di partenza
     *
     * @return Array di stringhe contenenti i nomi dei file
     */
    public static String[] returnFileListRec(String dir)
    {
        File stream = new File(dir);
        String[] listFiles = null;

        // Se e una directory....
        if (stream.isDirectory())
        {
            // Ho la lista di tutti i file della directory
            String[] fileFigli = stream.list();
            String[][] tutto = new String[fileFigli.length][1];

            // Richiamo ricorsivamente la funzione su tutti i figli
            for (int i = 0; i < fileFigli.length; i++)
            {
                String tmp = dir + File.separatorChar + fileFigli[i];
                tutto[i] = returnFileListRec(tmp);
            }

            // Linearizzo la matrice. Mi serve
            int dimensioneTotale = 0;

            for (int i = 0; i < tutto.length; i++)
            {
                dimensioneTotale += tutto[i].length;
            }

            listFiles = new String[dimensioneTotale];

            int count = 0;

            for (int i = 0; i < tutto.length; i++)
            {
                for (int j = 0; j < tutto[i].length; j++)
                {
                    listFiles[count] = tutto[i][j];
                    count++;
                }
            }
        }
        else
        {
            // Se e un file ritorna se stesso
            listFiles = new String[1];
            listFiles[0] = dir;
        }

        return listFiles;
    }

    //~ Inner Classes ----------------------------------------------------------

    class ExtFilter implements FilenameFilter
    {
        //~ Instance fields ----------------------------------------------------

        String afn;

        //~ Constructors -------------------------------------------------------

        ExtFilter(String afn)
        {
            this.afn = afn;
        }

        //~ Methods ------------------------------------------------------------

        public boolean accept(File dir, String name)
        {
            String f = new File(name).getName();

            return f.trim().endsWith(afn);
        }
    }
    
    public static boolean rename(String oldName,String newName){
     File tmp1 = new File(oldName);
     File tmp2 = new File(newName);
     return tmp1.renameTo(tmp2);
    
    }
    
     public static void copyFile(String nomeOriginale, String nomeFinale ) throws Exception {
       File in  = new File(nomeOriginale);
       
       File out = new File(nomeFinale);
    FileInputStream fis  = new FileInputStream(in);
    FileOutputStream fos = new FileOutputStream(out);
    byte[] buf = new byte[1024];
    int i = 0;
    while((i=fis.read(buf))!=-1) {
      fos.write(buf, 0, i);
      }
    fis.close();
    fos.close();
    }
    
  /**
	 * Move a file from one location to another.  An attempt is made to rename
	 * the file and if that fails, the file is copied and the old file deleted.
	 *
	 * If the destination file already exists, an exception will be thrown.
	 *
	 * @param from file which should be moved.
	 * @param to desired destination of the file.
	 * @throws IOException if an error occurs.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public static void move(File from, File to) throws IOException {
		move(from, to, false);
	}

	/**
	 * Move a file from one location to another.  An attempt is made to rename
	 * the file and if that fails, the file is copied and the old file deleted.
	 *
	 * @param from file which should be moved.
	 * @param to desired destination of the file.
	 * @param overwrite If false, an exception will be thrown rather than overwrite a file.
	 * @throws IOException if an error occurs.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	public static void move(File from, File to, boolean overwrite) throws IOException {
		if (to.exists()){
			if (overwrite){
				if (!to.delete()){
					throw new IOException("Non riesco a cancellare il file");
					
				}
			} else {
				throw new IOException("Il file Esiste gia");
			
			}
		}

		if (from.renameTo(to)) return;

		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(from);
			out = new FileOutputStream(to);
			copy(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			if (!from.delete()){
				throw new IOException("Errore non riesco a cancellare il file");
			}
		} finally {
			if (in != null){
				in.close();
				in = null;
			}
			if (out != null){
				out.flush();
				out.close();
				out = null;
			}
		}
	}

	/**
	 * Buffer size when reading from input stream.
	 *
	 * @since ostermillerutils 1.00.00
	 */
	private final static int BUFFER_SIZE = 1024;

	/**
	 * Copy the data from the input stream to the output stream.
	 *
	 * @param in data source
	 * @param out data destination
	 * @throws IOException in an input or output error occurs
	 *
	 * @since ostermillerutils 1.00.00
	 */
	private static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}
  
  	private static void copyDir(InputStream in, OutputStream out) throws IOException {
    //To do
    }

	public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
	
	public static byte[] getBytesFromFile(File file) throws IOException{
        InputStream is = new FileInputStream(file);
    
        // recupero la grandezza del file
        long length = file.length();
    
        if (length > Integer.MAX_VALUE) {
        	throw new IOException("Il file è troppo grande");
        }
    
        // creo l'array di byte che conterrà i dati
        byte[] bytes = new byte[(int)length];
    
        // Leggo i byte
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
    
        // controllo che tutti i byte siano stati letti
        if (offset < bytes.length) {
            throw new IOException("Il file non è stato letto completamente "+file.getName());
        }
        is.close();
        return bytes;
    }
}
/* End of File */
