package pack;

import pack.Reparacion.TipoReparacion;

public class TallerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Revision revision1 = new Revision(12);

		revision1.mostrarInformacion();

		System.out.println();

		Reparacion reparacion1 = new Reparacion(TipoReparacion.MECANICA, 150, 12);

		reparacion1.mostrarInformacion();
	}

}
