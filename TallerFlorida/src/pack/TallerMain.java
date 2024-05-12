package pack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import pack.Reparacion.TipoReparacion;

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
			System.out.println();
			System.out.println("****************************************************");
			System.out.println("     TALLERES FLORIDA, REPARACIONES A MEDIDA.");
			dibujito1();
			System.out.println("****************************************************");
			System.out.println();
			System.out.println("  Menu de opciones:");
			System.out.println("1 - Agregar trabajo");
			System.out.println("2 - Buscar trabajo");
			System.out.println("3 - Modificar trabajo");
			System.out.println("4 - Finalizar trabajo");
			System.out.println("5 - Cancelar trabajo");
			System.out.println("6 - Listar trabajos");
			System.out.println("7 - Listar trabajos pendientes");
			System.out.println("8 - Mostrar total de trabajos realizados");
			System.out.println("9 - Salir");

			try {
				int opcion;
				opcion = Integer.parseInt(br1.readLine());

				switch (opcion) {
				case 1:

					// Esto lo tenia en un metodo aparte, que a su vez llama al metodo
					// agregarTrabajoAlista, pero como practicamente todo es pedir datos y hace solo
					// una llamada a un metodo,
					// lo he dejado en el main que me parece mas correcto.

					System.out.println("Elije el tipo de trabajo:");
					System.out.println("1- Revisión");
					System.out.println("2- Reparación mecánica");
					System.out.println("3- Reparación de chapa y pintura");
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

						trabajo = new Reparacion(TipoReparacion.MECANICA, materiales, horas);

					} else if (tipoTrabajo == 3) {
						System.out.println("Introduzca el coste de los materiales");
						double materiales = Double.parseDouble(br1.readLine());

						System.out.println("Introduzca las horas de mano de obra");
						double horas = Double.parseDouble(br1.readLine());

						trabajo = new Reparacion(TipoReparacion.CHAPAPINTURA, materiales, horas);
					}

					if (trabajo != null) {
						agregarTrabajoAlista(trabajo);
						System.out.println("Trabajo agregado a la lista.");
					} else {
						System.out.println("Opción inválida.");
					}
					break;

				case 2:
					System.out.println("Buscar trabajo:");
					System.out.println("1- Revisiones");
					System.out.println("2- Reparaciones mecánicas");
					System.out.println("3- Reparaciones de chapa y pintura");
					int indice = Integer.parseInt(br1.readLine());

					buscarTrabajo(indice);
					break;

				case 3:
					System.out.println("Modificar trabajo:");
					System.out.print("Introduce el indice del trabajo a modificar: ");
					indice = Integer.parseInt(br1.readLine());

					System.out.print("Indica las horas que quieres agregar: ");
					double horasAdicionales = Double.parseDouble(br1.readLine());

					System.out.print("Indica el coste de materiales que quieres agregar: ");
					double costeMateriales = Double.parseDouble(br1.readLine());

					modificarTrabajo(indice, horasAdicionales, costeMateriales);
					break;

				case 4:
					System.out.println("Finalizar trabajo:");
					System.out.print("Introduce el indice del trabajo que quieres finalizar: ");
					indice = Integer.parseInt(br1.readLine());

					finalizarTrabajo(indice);
					break;

				case 5:
					System.out.println("Cancelar trabajo:");
					System.out.print("Introduce el indice del trabajo que quieres cancelar: ");
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
					mostrarTotalTrabajos();
					break;

				case 9:
					guardarEnFichero();
					salir = true;

					break;

				default:
					System.out.println("Elige una opciópn correcta.");
					break;
				}

			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error try catch gigante deel menu inicial" + e);
				;
			}

		}

		System.out.println("Saliendo del programa.");
		dibujito2();
	}

	// METTODOS//

	private static void agregarTrabajoAlista(Trabajo trabajo) {
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

	private static void buscarTrabajo(int tipoTrabajoBusqueda) {
		System.out.println("Trabajos encontrados:");

		for (int i = 0; i < contadorTrabajos; i++) {
			Trabajo trabajo1 = listaTrabajos[i];
			boolean mostrar = false;

			// Filtrar por tipo de trabajo, pone a true el booleando si coincide el tipo de
			// busqueda con el del usuario y lo muestra.
			switch (tipoTrabajoBusqueda) {
			case 1:
				if (trabajo1 instanceof Revision) {
					mostrar = true;
				}

				break;
			case 2:
				if (trabajo1 instanceof Reparacion
						&& ((Reparacion) trabajo1).getTipoReparacion() == TipoReparacion.MECANICA) {

					mostrar = true;
				}
				break;
			case 3:
				if (trabajo1 instanceof Reparacion
						&& ((Reparacion) trabajo1).getTipoReparacion() == TipoReparacion.CHAPAPINTURA) {
					mostrar = true;
				}
				break;

			default:
				System.out.println("Elige una opciópn correcta.");
				break;
			}
			
			if (mostrar) {
				System.out.println();
				System.out.print("ID: " + i);
				trabajo1.mostrarInformacion();
				System.out.println("-----------------");

			}

		}

	}

	

	private static void modificarTrabajo(int indiceListaTrabajo, double horasAgregar, double costeMateriales) {
		// Si el indice que quieremos modificar esta entre el rango del contador de
		// trabajo, llamamos a los metodos para modificar, como agregar horas, agregar
		// coste etc...
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
		// recibe el indice del trabajo a finalizar, recorre el array y si encuentra la
		// posicion llama al metodo finalizar trabajo

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
		// lista los trabajos por orden de entrada al registro ( los mas antiguos
		// primero)
		for (int i = 0; i < contadorTrabajos; i++) {
			System.out.println();
			System.out.print("ID: " + i);
			listaTrabajos[i].mostrarInformacion();
			System.out.println("-----------------");
		}
	}

	private static void listarTrabajosPendientes() {
		// recorre el array de trabajos e imprime cada posicion en la que el atributo
		// booleano completado sea false.
		for (int i = 0; i < contadorTrabajos; i++) {
			if (!listaTrabajos[i].estaCompletado()) {
				System.out.println();
				System.out.print("ID: " + i);
				listaTrabajos[i].mostrarInformacion();
				System.out.println("-----------------");
			}
		}
	}

	private static void mostrarTotalTrabajos() {
		System.out.println("El total de trabajos realizado hasta la fecha es: " + totalTrabajos);
		if (totalTrabajos == 0) {
			System.out.println("Así no levantamos el país...");
		}
	}

	private static void rellenarAutomaticamente() {
		// Agregar 10 trabajos automáticamente con valores aleatorios para la mano de
		// obra y el precio, ademas puede añadir los 3 tipos de trabajo a la vez sin
		// hacerle nada.
		// forma de redondear los valores y dejarlo solo en 2 decimales la tuve que
		// buscar, hay que usar math.round multiplicar por 100 y dividir por 100... xD

		Random aleatorio = new Random();

		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				Revision revision = new Revision(Math.round((aleatorio.nextDouble(9) + 1) * 100) / 100d);
				agregarTrabajoAlista(revision);
			} else if (i % 3 == 0) {
				Reparacion reparacion = new Reparacion(TipoReparacion.MECANICA,
						(Math.round((aleatorio.nextDouble(50) + 1) * 100) / 100d),
						(Math.round((aleatorio.nextDouble(9) + 1) * 100) / 100d));
				agregarTrabajoAlista(reparacion);
			} else {
				Reparacion reparacion = new Reparacion(TipoReparacion.CHAPAPINTURA,
						(Math.round((aleatorio.nextDouble(50) + 1) * 100) / 100d),
						(Math.round((aleatorio.nextDouble(9) + 1) * 100) / 100d));
				agregarTrabajoAlista(reparacion);
			}
		}
	}

	private static void dibujito1() {
		System.out.println("   .----.                                .---.");
		System.out.println("  '---,  `.____________________________.'  _  `.");
		System.out.println("       )   ____________________________   <_>  :");
		System.out.println("  .---'  .'                            `.     .'");
		System.out.println("   `----'                                `---'");
		System.out.println("");
	}

	private static void dibujito2() {

		System.out.println("                                     ___________________________");
		System.out.println("    _____                          ,\\\\    ___________________    |");
		System.out.println("   |     `------------------------'  ||  (___________________)   `|");
		System.out.println("   |_____.------------------------._ ||  ____________________     |");
		System.out.println("                                   `//__(____________________)___/");

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
			System.out.println("Fallo al recuperar el fichero o no tiene datos");

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
				rellenarAutomaticamente();
			}

		} catch (IOException e) {
			System.out.println("Error de IO en el arranque" + e);
		}

	}

}
