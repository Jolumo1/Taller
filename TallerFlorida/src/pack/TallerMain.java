package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TallerMain {

	private static int contadorTrabajos = 0;
	private static int totalTrabajos = 0;
	private static int maxTrabajos = 100;
	// array de tiradas con 100 posiciones
	private static Trabajo[] listaTrabajos = new Trabajo[maxTrabajos];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		boolean salir = false;

		System.out.println("Iniciando el programa: ");
		arranqueDelPrograma();

		while (!salir) {
			System.out.println("\nMenú:");
			System.out.println("1. Agregar trabajo");
			System.out.println("2. Buscar trabajo");
			System.out.println("3. Modificar trabajo");
			System.out.println("4. Finalizar trabajo");
			System.out.println("5. Cancelar trabajo");
			System.out.println("6. Listar trabajos");
			System.out.println("7. Listar trabajos pendientes");
			System.out.println("8. Salir");

			try {
				int opcion;
				opcion = Integer.parseInt(br1.readLine());

				switch (opcion) {
				case 1:
					System.out.println("Agregar trabajo:");
					System.out.println("1. Revisión");
					System.out.println("2. Reparación mecánica");
					System.out.println("3. Reparación de chapa y pintura");
					int tipoTrabajo = Integer.parseInt(br1.readLine());

					Trabajo trabajo = null;

					if (tipoTrabajo == 1) {

						System.out.println("Introduzca las horas de mano de obra de la revisión");
						double horas = Double.parseDouble(br1.readLine());
						trabajo = new Revision(horas);

					} else if (tipoTrabajo == 2) {
						System.out.println("Introduzca el coste de los materiales");
						double materiales = Double.parseDouble(br1.readLine());

						System.out.println("Introduzca las horas de mano de obra");
						double horas = Double.parseDouble(br1.readLine());

						trabajo = new Reparacion(Reparacion.TipoReparacion.MECANICA, materiales, horas);

					} else if (tipoTrabajo == 3) {
						System.out.println("Introduzca el coste de los materiales");
						double materiales = Double.parseDouble(br1.readLine());

						System.out.println("Introduzca las horas de mano de obra");
						double horas = Double.parseDouble(br1.readLine());

						trabajo = new Reparacion(Reparacion.TipoReparacion.CHAPAPINTURA, materiales, horas);
					}

					if (trabajo != null) {
						agregarTrabajo(trabajo);
					} else {
						System.out.println("Opción inválida.");
					}
					break;

				case 2:
					System.out.println("Buscar trabajo:");
					System.out.print("Índice del trabajo: ");
					int indice = Integer.parseInt(br1.readLine());
					buscarTrabajo(indice);
					break;

				case 3:
					System.out.println("Modificar trabajo:");
					System.out.print("Índice del trabajo: ");
					indice = Integer.parseInt(br1.readLine());

					System.out.print("Horas adicionales: ");
					double horasAdicionales = Double.parseDouble(br1.readLine());

					System.out.print("Costo de materiales adicionales: ");
					double costeMateriales = Double.parseDouble(br1.readLine());

					modificarTrabajo(indice, horasAdicionales, costeMateriales);
					break;

				case 4:
					System.out.println("Finalizar trabajo:");
					System.out.print("Índice del trabajo: ");
					indice = Integer.parseInt(br1.readLine());

					finalizarTrabajo(indice);
					break;

				case 5:
					System.out.println("Cancelar trabajo:");
					System.out.print("Índice del trabajo: ");
					indice = Integer.parseInt(br1.readLine());

					cancelarTrabajo(indice);
					break;

				case 6:
					System.out.println("Listar trabajos:");
					listarTrabajos();
					break;

				case 7:
					System.out.println("Listar trabajos pendientes:");
					listarTrabajosPendientes();
					break;

				case 8:
					guardarEnFichero();
					salir = true;
					break;

				default:
					System.out.println("Elige una opciópn correcta.");
					break;
				}

			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error try catch gigante Menu inicial" + e);;
			}

		}

		System.out.println("Saliendo del programa.");
	}

	// METTODOS//

	private static void agregarTrabajo(Trabajo trabajo) {
		if (contadorTrabajos < maxTrabajos) {
			listaTrabajos[contadorTrabajos] = trabajo;
			contadorTrabajos++;
		} else {
			// Si llegamos al límite, desplazamos los trabajos una posición hacia la
			// izquierda, asi se van guardando los nuevos y se pierden los mas viejos
			for (int i = 1; i < maxTrabajos; i++) {
				listaTrabajos[i - 1] = listaTrabajos[i];
			}
			listaTrabajos[maxTrabajos - 1] = trabajo;
		}
		totalTrabajos++;
	}

	private static void buscarTrabajo(int indiceListaTrabajos) {
		// Busca en el array el trabajo con el numero que se indique e imprime sus
		// datos.

		if (indiceListaTrabajos >= 0 && indiceListaTrabajos < contadorTrabajos) {
			listaTrabajos[indiceListaTrabajos].mostrarInformacion();
		} else {
			System.out.println("Índice inválido. No se encontró el trabajo.");
		}
	}

	private static void modificarTrabajo(int indiceListaTrabajo, double horasAgregar, double costeMateriales) {
		if (indiceListaTrabajo >= 0 && indiceListaTrabajo < contadorTrabajos) {
			listaTrabajos[indiceListaTrabajo].agregarHoras(horasAgregar);
			if (listaTrabajos[indiceListaTrabajo] instanceof Reparacion) {
				Reparacion reparacion = (Reparacion) listaTrabajos[indiceListaTrabajo];
				reparacion.agregarCosteMateriales(costeMateriales);
			}
		} else {
			System.out.println("Indice incorrecto. No se ha podido modificar el trabajo.");
		}
	}

	private static void finalizarTrabajo(int indiceListaTrabajo) {
		if (indiceListaTrabajo >= 0 && indiceListaTrabajo < contadorTrabajos) {
			listaTrabajos[indiceListaTrabajo].finalizarTrabajo();
		} else {
			System.out.println("Indice incorrecto. No se ha podido modificar el trabajo.");
		}
	}

	private static void cancelarTrabajo(int indiceListaTrabajo) {
		if (indiceListaTrabajo >= 0 && indiceListaTrabajo < contadorTrabajos) {
			// Pisamos el trabajo del indice que se pide elimintar desplazando los trabajos
			// posteriores una posición hacia la izquierda, luego el trabajo final se pone a
			// null
			// y se baja uno el contrador de trabajos para que no queden saltos.

			for (int i = indiceListaTrabajo + 1; i < contadorTrabajos; i++) {
				listaTrabajos[i - 1] = listaTrabajos[i];
			}
			listaTrabajos[contadorTrabajos - 1] = null;
			contadorTrabajos--;
		} else {
			System.out.println("Índice inválido. No se pudo cancelar el trabajo.");
		}
	}

	private static void listarTrabajos() {
		for (int i = 0; i < contadorTrabajos; i++) {
			listaTrabajos[i].mostrarInformacion();
			System.out.println("-----------------");
		}
	}

	private static void listarTrabajosPendientes() {
		for (int i = 0; i < contadorTrabajos; i++) {
			if (!listaTrabajos[i].estaCompletado()) {
				listaTrabajos[i].mostrarInformacion();
				System.out.println("-----------------");
			}
		}
	}

	private static void listarTrabajosPendientes2() {
		for (int i = 0; i < contadorTrabajos; i++) {
			if (!listaTrabajos[i].estaCompletado()) {
				listaTrabajos[i].mostrarInformacion();
				System.out.println("-----------------");
			}
		}
	}

	// --------------------mETODOS FICHERO----------------------------------//
	private static void guardarEnFichero() {

		// metodo que guarda el array con el que trabajamos en el fichero indicado

		try {

			FileOutputStream fos = new FileOutputStream("trabajos.dat", false);

			ObjectOutputStream salida = new ObjectOutputStream(fos);

			salida.writeObject(listaTrabajos);
			salida.writeInt(totalTrabajos);
			salida.writeInt(contadorTrabajos);

			salida.close();
			fos.close();
			System.out.println("Datos almacenados con éxito");

		} catch (IOException e) {
			System.out.println("Error al guardar el fichero" + e);
		}
	}

	private static void recuperarDelFichero() {

		// metodo que recupera el objeto del fichero indicado y lo guarda en el array

		try {

			FileInputStream fis1 = new FileInputStream("trabajos.dat");
			ObjectInputStream entrada = new ObjectInputStream(fis1);
			Object objeto = entrada.readObject();

			if (objeto instanceof Trabajo[]) {

				listaTrabajos = (Trabajo[]) objeto;
			}

			totalTrabajos = entrada.readInt();
			contadorTrabajos = entrada.readInt();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Fallo al recuperar el fichero");

		}

	}

	private static void arranqueDelPrograma() {

		// metodo que comprueba que el fichero trabajos.dat está creado, si lo está,
		// recupera la información, si no, lo crea.

		File fichero = new File("trabajos.dat");

		try {

			if (fichero.exists() && fichero.isFile()) {
				recuperarDelFichero();

			} else {
				fichero.createNewFile();
			}

		} catch (IOException e) {
			System.out.println("Error de IO en el arranque" + e);
		}

	}

}
