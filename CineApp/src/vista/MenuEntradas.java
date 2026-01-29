package vista;

import java.util.Scanner;

import pojos.Entrada;

public class MenuEntradas {
private static Scanner sc = null;
	
    public MenuEntradas () {
		sc = new Scanner(System.in);
    }
    public int numerodepersonas() {
		int numpersonas = 0;
		do {

			System.out.println("   Introducir numero de personas :             ");
			// solo numeros
			while (!sc.hasNextInt()) {
				System.out.print("Por favor, introduce un número válido: ");
				sc.next();
			}
			numpersonas = sc.nextInt();
			sc.nextLine();

			// seguir preguntado si ha escribido 0 o menos de 0
		} while (numpersonas <= 0);

		  return numpersonas;

	}

}
