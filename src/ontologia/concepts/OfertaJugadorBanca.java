package src.ontologia.concepts;

public class OfertaJugadorBanca {
	String[] Oferta={"",""};
	
	
	
	public OfertaJugadorBanca() {
		// TODO Auto-generated constructor stub
	}
	
	public OfertaJugadorBanca(String deseo, String oferta) {
		this.Oferta[0]=deseo;
		this.Oferta[0]=oferta;
			
			// TODO Auto-generated constructor stub
		}
	
	public OfertaJugadorBanca(String [] oferta) {
		this.Oferta[0]=oferta[0];
		this.Oferta[0]=oferta[1];
			
			// TODO Auto-generated constructor stub
		}

	public String[] getOferta() {
		return Oferta;
	}

	public void setOferta(String[] oferta) {
		Oferta = oferta;
	}
}
