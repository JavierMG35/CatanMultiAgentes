package src.ontologia.predicates;

public class ComerciadoConBanca {
	String[] Oferta={"",""};
	
	
	
	public ComerciadoConBanca() {
		// TODO Auto-generated constructor stub
	}
	
	public ComerciadoConBanca(String deseo, String oferta) {
		this.Oferta[0]=deseo;
		this.Oferta[1]=oferta;
			
			// TODO Auto-generated constructor stub
		}
	
	public ComerciadoConBanca(String [] oferta) {
		this.Oferta[0]=oferta[0];
		this.Oferta[1]=oferta[1];
			
			// TODO Auto-generated constructor stub
		}

	public String[] getOferta() {
		return Oferta;
	}

	public void setOferta(String[] oferta) {
		Oferta = oferta;
	}
}
