package pack;

public class Revision extends Trabajo {
	private static final double INCREMENTO_REVISION = 30.0;

	public Revision(double horasTrabajadas) {
		super(horasTrabajadas);
		this.tipoTrabajo = TipoTrabajo.REVISION;
		
	}

	@Override
	public double calcularImporte() {
		return (this.horasTrabajadas * PRECIO_HORA) + INCREMENTO_REVISION;
	}
}
