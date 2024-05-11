package pack;

import java.io.Serializable;

public abstract class Trabajo implements Serializable{

	protected enum TipoTrabajo {
		REVISION, REPARACION
	};
	
	protected TipoTrabajo tipoTrabajo;
	protected double horasTrabajadas;
	protected static final double PRECIO_HORA = 45.0; // Dejamos fijo el precio de la mano de obra
	protected boolean completado;
	protected double precioFinal = 0.0; // El precio final no se calcula hasta que el trabajo no esté completado.

	public Trabajo(double horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;

	}

	/// METODOS ////

	public abstract double calcularImporte();

	public void finalizarTrabajo() {
		// Aqui cambiamos el estado completado a true y llamamos al metodo para calcular
		// el precio final en funcion de los valores que haya en el trabajo al cerrarlo
		completado = true;
		precioFinal = calcularImporte();
	}

	public void agregarHoras(double horas) {
		if (!completado) {
			horasTrabajadas += horas;
		} else {
			System.out.println("El trabajo ya está completado, no se pueden agregar más horas.");
		}
	}

	public boolean estaCompletado() {
		return completado;
	}

	public void mostrarInformacion() {
		System.out.println();
		System.out.println("Tipo de trabajo: " + tipoTrabajo);
		System.out.println("Horas trabajadas: " + horasTrabajadas);
		System.out.println("Precio por hora: " + PRECIO_HORA);
		System.out.println("Precio final: " + precioFinal);
		System.out.println("Completado: " + (completado ? "Sí" : "No"));
	}

}
