package src.ontologia.actions;
import src.ontologia.concepts.Recurso;

public class ObtencionRecursos {

	Recurso recurso;
	
	public ObtencionRecursos() {
		// TODO Auto-generated constructor stub
	}
	public ObtencionRecursos(Recurso recurso) {
		// TODO Auto-generated constructor stub
		this.recurso=recurso;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
}
