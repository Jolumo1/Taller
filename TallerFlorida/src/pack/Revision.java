package pack;

public class Revision extends Trabajo {

	// clase que hereda de trabajo, con sus atributos propios, un enum para el tipo de reparacion y el incremento del precio los he puesto como final ya que serán constantes.
	// desarrolla el metodo abstractoq ue hereda de trabajo para calcular el importe segun las horas, el precio hora, los incrementos, ewtc..
	
	
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
