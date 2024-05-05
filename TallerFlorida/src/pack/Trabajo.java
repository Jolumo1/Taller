package pack;

public abstract class Trabajo {

	protected enum TipoTrabajo {
		REVISION, REPARACION
	};

	protected TipoTrabajo tipoTrabajo;
	protected double horasTrabajadas;
	protected static final double PRECIO_HORA = 45.0; // Dejamos fijo el precio de la mano de obra
	protected boolean completado;
	protected double precioFinal = 0.0;

	public Trabajo(double horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;

	}

	/// METODOS ////

	public abstract double calcularImporte();

	public void finalizarTrabajo() {
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
		System.out.println("Tipo de trabajo: " + tipoTrabajo);
		System.out.println("Horas trabajadas: " + horasTrabajadas);
		System.out.println("Precio por hora: " + PRECIO_HORA);
		System.out.println("Precio final: " + precioFinal);
		System.out.println("Completado: " + (completado ? "Sí" : "No"));
	}
}
