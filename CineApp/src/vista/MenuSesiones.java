package vista;

import java.util.ArrayList;
import java.util.Scanner;

import dao.SesionDAO;
import pojos.Pelicula;
import pojos.Sesion;

public class MenuSesiones {
	private final Scanner sc;
	private final SesionDAO sesiondao;

	public MenuSesiones(Scanner sc) {
	    this.sc =  sc;
		this.sesiondao = new SesionDAO();
	}

	public ArrayList<Sesion> mostrarsesiones(Pelicula peliElegida) {
		
		if (peliElegida == null) {
			System.out.println("No has elegido ninguna película.");
			return null;
		}
		// hemos cogido el id de la pelicula que ha elegido el usuario
		// kansufto id l dao kan9olo 3tini rir sesiones li3ndom had id
		ArrayList<Sesion> sesiones = sesiondao.getSesionesPorPeliculaOrdenadas(peliElegida.getId());
		if (sesiones == null || sesiones.isEmpty()) {
			System.out.println("No hay Sesiones  disponibles.");
			return null;
		}
		System.out.println("******************** las fechas disponibles *********************");
		for (int i = 0; i < sesiones.size(); i++) {
            System.out.print((i + 1) + " - ");
			imprimirSesion(sesiones.get(i));	
			}

		return sesiones;
	}

	public Sesion sesionelegida(ArrayList<Sesion> sesiones) {
		// ila khtar no ayb9a null oila khtar si ayrja3 peli

		Sesion ret = null;
		if (sesiones == null || sesiones.isEmpty()) {
			System.out.println("No hay sesiones disponibles.");
			return null;
		}
		System.out.print("¿Podemos continuar seleccionando una sesión? (si/no): ");
		String resp = sc.nextLine().trim().toLowerCase();

		if (resp.equals("si")) {
			System.out.println("");
			// opcion mosta7il tkon -1 donc kandiroha bash nrj3o que ralat opcion
			int opcion = -1;
			// ila usuario khtar ra9m ralat atb9a tswloooo
			while (opcion < 1 || opcion > sesiones.size()) {
				try {
					// usuario dkhl abc ayti7 f exeption -1 ralat donc ayrjaa lwhile
					System.out.println("Elige una sesion  (1-" + sesiones.size() + "):");
					opcion = Integer.parseInt(sc.nextLine().trim());

				} catch (Exception e) {
					opcion = -1;
				}

			}
			// aryylist katkhdm b -1
			ret = sesiones.get(opcion - 1);
		} else {
			System.out.println("Selección de sesión finalizada.");

		}
		return ret;

	}

	private void imprimirSesion(Sesion sesion) {
	    System.out.println(
	        sesion.getFecha() + "|" +
	        sesion.getHoraInicio() + "|" +
	        sesion.getHoraFin() + "|" +
	        sesion.getPrecio() + "€|" +
	        "Sala: " + sesion.getSala().getNombre()
	    );
	}

	private int opcionSesionvalida(int min,int max) 	{
	
		int opcion=-1;
		while(opcion<min || opcion>max) {
			try {
            System.out.print("Elige una opción (" + min + "-" + max + "): ");
            //leer el numero 
            opcion = Integer.parseInt(sc.nextLine().trim());
			}catch(Exception e) {
//		bash fahs kaydkhl usuaripo abc kayti7 f cathc -1 bash n3lmoh bli khtiyar ralat oyrjaa lwhile		
				opcion=-1;
			}
			
			
			
			
		}
		return opcion;
		
	}
	
		
	
}

