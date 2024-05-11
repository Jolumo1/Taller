package pack;

public class Reparacion extends Trabajo {

	//clase que hereda de trabajo, con sus atributos propios, un enum para el tipo de reparacion y los incrementos del precio los he puesto como final ya que serán constantes. 
	// desarrolla el metodo abstractoq ue hereda de trabajo para calcular el importe segun las horas, el precio hora, los incrementos, ewtc..
	
	public enum TipoReparacion {
		MECANICA, CHAPAPINTURA
	};

	private TipoReparacion tipoReparacion;
	private double costeMateriales;
	private static final double INCREMENTO_MECANICA = 1.10;
	private static final double INCREMENTO_CHAPAPINTURA = 1.30;

	public Reparacion(TipoReparacion tipoReparacion, double costeMateriales, double horasTrabajadas) {
		super(horasTrabajadas);
		this.tipoReparacion = tipoReparacion;
		this.costeMateriales = costeMateriales;
		tipoTrabajo = TipoTrabajo.REPARACION;
	}

	@Override
	public double calcularImporte() {
		double total = this.horasTrabajadas * PRECIO_HORA + this.costeMateriales;
		if (tipoReparacion == TipoReparacion.MECANICA) {
			total += total * INCREMENTO_MECANICA;
		} else if (tipoReparacion == TipoReparacion.CHAPAPINTURA) {
			total += total * INCREMENTO_CHAPAPINTURA;
		}
		return total;
	}

	public void agregarCosteMateriales(double costes) {
		if (!completado) {
			costeMateriales += costes;
		} else {
			System.out.println("El trabajo ya está completado, no se puede agregar más costes.");
		}
	}

	@Override
	public void mostrarInformacion() {
		super.mostrarInformacion();
		System.out.println("Tipo de reparación: " + tipoReparacion);
		System.out.println("Coste de los materiales: " + costeMateriales);
	}
}
