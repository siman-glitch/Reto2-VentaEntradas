package utils;

public class Validaciones {
	public static boolean validarDNI(String dni) {
		if (dni == null)
			return false;
		dni = dni.trim();

		if (dni.length() != 9)
			return false;

		// 8numeros primeros son num
		for (int i = 0; i < 8; i++) {
			// chart i at3tina ga3 num mn 1 l 8
			char c = dni.charAt(i);
			if (c < '0' || c > '9')
				return false;
		}

		// la ultima index = letra
		char letra = dni.charAt(8);
		if (!(letra >= 'A' && letra <= 'z') || (letra >= 'a' && letra <= 'z'))
			return false;
		return true;

	}

	public static boolean validarCorreo(String correo) {
		if (correo == null)
			return false;

		correo = correo.trim();
		int arroba = correo.indexOf("@");
		int punto = correo.indexOf(".");

		if (arroba <= 0)
			return false;
		if (punto <= 0)
			return false;
		if (punto >= correo.length() - 1)
			return false;
		return true;
	}

	// nombre y apellidos
	public static boolean validarNombre(String texto) {
		if (texto == null)
			return false;
		texto = texto.trim();
		if(texto.length()<2) return false;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c >= '0' && c <= '9') return false;
        }
		return true;

	}
    // Password: minimo 4
    public static boolean validarContrasena(String contrasena) {
        if (contrasena == null) return false;

        contrasena = contrasena.trim();
        if (contrasena.length() < 4) return false;

        return true;
    }

}
