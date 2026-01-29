package gestor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//had method katzid  linea
public class GestorTicketFichero {

	private static final String NOMBRE_FICHERO = "ticket.txt";
	private static final String RUTA_ABSOLUTA_FICHERO = "";// se guarda dentro del proyecto

	/**
	 * Aniade un texto al final del fichero
	 * 
	 * @param textoAActualizar
	 */
	//katst9bl string (texto) okatzido f akhir milf 
	public void actualizarFichero(String textoAActualizar) {
		//kanft7o file fin nktno
		FileWriter fileWriter = null;
		//kayshl lktaba b print
		PrintWriter printWriter = null;
		//true:zid
		//false:ms7
		try {
			fileWriter = new FileWriter(RUTA_ABSOLUTA_FICHERO + NOMBRE_FICHERO, true);
			printWriter = new PrintWriter(fileWriter);
			printWriter.println(textoAActualizar);
		} catch (IOException e) {
			System.out.println("Error de escritura en el fichero " + RUTA_ABSOLUTA_FICHERO + NOMBRE_FICHERO);
		} catch (Exception e) {
			System.out.println("Error de escritura en el fichero " + RUTA_ABSOLUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			if (null != printWriter)
				printWriter.close();
			try {
				if (null != fileWriter)
					fileWriter.close();
			} catch (IOException e) {
				// Nos da igual
			}
		}
	}

}
