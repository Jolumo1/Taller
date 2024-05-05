package pack;

public class Reparacion extends Trabajo {

	public enum TipoReparacion {
		MECANICA, CHAPAPINTURA
	};

	private TipoReparacion tipoReparacion;
	private double costeMateriales;
	private static final double INCREMENTO_MECANICA = 1.10;
	private static final double INCREMENTO_CHAPAPINTURA = .30;

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
		System.out.println("Costo de materiales: " + costeMateriales);
	}
}
