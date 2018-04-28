package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.IIOException;

public class FilesManager {

	private static Map<String, Integer> kindCodes;

	/**
	 * Retorna el código de tipo de agente para el tipo de agente pasado. Toma la
	 * información del fichero amestro tipos.csv
	 * 
	 * @param kind Tipo de agente (textual)
	 * @return Tipo de agente (código entero)
	 * @throws IOException
	 *             Exception de entrada salida
	 */
	public static int getKindCode(String kind) throws IOException {
		if (kindCodes == null) {
			getKindcodes();
		}
		Integer kindCode = kindCodes.get(kind);
		if(kindCode!=null)
		return kindCode;
		else
		return 0;
	}
	
	private static void getKindcodes() throws IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("tipos.csv")); 	
			while (br.ready()) {
				String[] line = br.readLine().split(",");
				map.put(line[1], Integer.parseInt(line[0]));
			}
			br.close();
			kindCodes = map;}
		
	 catch(IOException e){
		  e.printStackTrace();
		}
	}

	private FilesManager() {
	}

}
