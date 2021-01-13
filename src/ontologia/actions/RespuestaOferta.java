package src.ontologia.actions;

public class RespuestaOferta {
	
	boolean acepto = false;
	
	public RespuestaOferta(boolean decision) {
		this.acepto = decision;
	}

	public boolean isAcepto() {
		return acepto;
	}

	public void setAcepto(boolean acepto) {
		this.acepto = acepto;
	}
	

}
