package src.Jugador;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.model.jibximpl.Agent;
import jadex.runtime.BasicAgentIdentifier;
//import jadex.util.SUtil;
import jadex.util.SimplePropertyChangeSupport;
import src.Jugador.estrategias.AbstractEstrategias;
import src.Jugador.estrategias.IEstrategia;
import src.Mapa.Node;
import src.Mapa.Edge;
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.Construccion;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Recurso;

//import java.beans.PropertyChangeListener;
//import java.awt.Color;
//import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
//import jadex.runtime.*;



public class Jugador extends Agent{
	
	//-------- constructors --------
			public Jugador()
			{
				this(null, null, null);
			}
			/**
			 *  Create a new Player.
			 */
			public Jugador(String name,String strategyname,String grupo)
			{
				this(null,name, strategyname,grupo);
				
			}

			/**
			 *  Create a new Player.
			 */
			public Jugador(AgentIdentifier aid,String nombre, String strategyname,String grupo)
			{
						
				comoquieras=new Zoo(grupo);
				Random rand1=new Random();
				this.id=rand1.nextInt(312312312);
				this.aid	= aid;
				this.nombre	= nombre;
				this.strategyname = strategyname;
				//if(strategyname!=null)
				this.strategy	= AbstractEstrategias.getStrategy(strategyname);
				this.state	= STATE_UNREGISTERED;
				this.pcs = new SimplePropertyChangeSupport(this);
			}
	//-------- constants --------

		/** . */
		public static final String	STATE_UNREGISTERED	= "unregistered";
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setAid(BasicAgentIdentifier aid) {
			this.aid = aid;
		}

		/** State of a player that is not involved in a game. */
		public static final String	STATE_IDLE	= "idle";
		/** State of a player at game start. */
		public static final String	STATE_GAME_STARTED	= "game started";
		/** State of a player, after bet is made. */
		public static final String	STATE_PLAYING	= "playing";
		/** State of a player when all cards are drawn. */
		public static final String	STATE_FINISHED	= "finished";
		
		//-------- attributes --------
		protected String nombre;
		
		protected int id;
		
		protected Zoo comoquieras;
		
		
		
		protected Cartas cartas = new Cartas();
		
	    protected List<Construccion> construcciones = new ArrayList<>();
		
		

		protected int tirada;
		
		protected int posicion_mesa;
		
		protected int puntuacion;
		
		/** The strategyname. */
		protected String strategyname;

		/** The player's strategy. */
		protected IEstrategia strategy;

		/** The player's agent id. */
		protected BasicAgentIdentifier	aid;

		/** The player state. */
		protected String	state;
		
		public SimplePropertyChangeSupport	pcs;

		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Cartas getCartas() {
			return cartas;
		}
		public void setCartas(Cartas cartas) {
			this.cartas = cartas;
		}
		public List<Construccion> getConstrucciones() {
			return construcciones;
		}
		
		public void setConstrucciones(List<Construccion> construcciones) {
			this.construcciones = construcciones;
		}
		
		public int getTirada() {
			return tirada;
		}
		public void  printID() {
			System.out.println("soy "+ this.nombre +"my id es: "+this.id);
		}
		public void setTirada(int tirada) {
			this.tirada = tirada;
		}
		public int getPosicion_mesa() {
			return posicion_mesa;
		}
		public void setPosicion_mesa(int posicion_mesa) {
			this.posicion_mesa = posicion_mesa;
		}
		public int getPuntuacion() {
			return puntuacion;
		}
		public void setPuntuacion(int puntuacion) {
			this.puntuacion = puntuacion;
		}
		public String getStrategyname() {
			return strategyname;
		}
		public void setStrategyname(String strategyname) {
			this.strategyname = strategyname;
		}
		public BasicAgentIdentifier getAid() {
			return this.aid;
		}
		public void setAgentID(BasicAgentIdentifier aid) {
			this.aid = aid;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		
		public Zoo getComoquieras() {
			return comoquieras;
		}
		public void setComoquieras(Zoo comoquieras) {
			this.comoquieras = comoquieras;
		}
		public EstadoJuego setMyself(EstadoJuego estado) {
			
			//Jugador myself = new Jugador();
			int posicion = 0;
			for(int i=0; i<estado.getJugadores().size();i++) {
				if(estado.getJugadores().get(i).getNombre().equals(this.getNombre())) {
					estado.getJugadores().set(i, this);
				 posicion = i;
				}
			}			
			return estado;
		}
		
		public boolean PermitirCartaDesarrollo() 
		{
			if (this.cartas.getPaja().size()>1 && this.cartas.getLana().size()>1 && this.cartas.getPiedra().size()>1)return true;
			return false;
		}
		
		public IEstrategia getStrategy() {
			return strategy;
		}
		public void setStrategy(IEstrategia strategy) {
			this.strategy = strategy;
		}
		public Recurso cuatroCartasIguales(String tipo_buscar) {
			Recurso recurso = new Recurso();			
			if (getCartas().getArcilla().size()>3 && tipo_buscar.equals("Arcilla")) 	recurso.setTipo("Arcilla");
			if (getCartas().getLana().size()>3 		&& 	tipo_buscar.equals("Lana"))		recurso.setTipo("Lana");
			if (getCartas().getPiedra().size()>3 && 	tipo_buscar.equals("Piedra"))	recurso.setTipo("Piedra");
			if (getCartas().getMadera().size()>3 && 	tipo_buscar.equals("Madera"))	recurso.setTipo("Madera");
			if (getCartas().getPaja().size()>3	 && tipo_buscar.equals("Paja"))		recurso.setTipo("Paja");
			return recurso;
			
		}
		
		public int [] getNumeroConstrucciones() {
            int [] numero = new int [3];
            List<Construccion> construcciones = this.getConstrucciones();

            for (int i = 0; i < construcciones.size(); i++) {
                if(construcciones.get(i).getTipo() == "Poblado") numero[0] = numero[0]+1;
                if(construcciones.get(i).getTipo() == "Ciudad") numero[1] = numero[1]+1;
                if(construcciones.get(i).getTipo() == "Carretera") numero[2] = numero[2]+1;
            }
            return numero;
    }
	public void ConstruirPoblado(Node nodo) {
	        Recurso paja = new Recurso();
	        paja.setTipo("Paja");
	        Recurso madera = new Recurso();
	        madera.setTipo("Madera");
	        Recurso arcilla = new Recurso();
	        arcilla.setTipo("Arcilla");
	        Recurso lana = new Recurso();
	        lana.setTipo("Lana");
	        cartas.removeRecurso(paja);
	        cartas.removeRecurso(madera);
	        cartas.removeRecurso(arcilla);
	        cartas.removeRecurso(lana);
	        this.setPuntuacion(this.getPuntuacion() + 1);
	        Construccion poblado = new Construccion("Poblado", nodo, this, null);
	        this.getConstrucciones().add(poblado);
	    }
	
	    public void ConstruirCiudad(Node nodo) {
	        Recurso paja = new Recurso();
	        paja.setTipo("Paja");
	        Recurso piedra = new Recurso();
	        piedra.setTipo("Piedra");
	        cartas.removeRecurso(paja);
	        cartas.removeRecurso(paja);
	        cartas.removeRecurso(piedra);
	        cartas.removeRecurso(paja);
	        this.setPuntuacion(this.getPuntuacion() + 1);
	        Construccion ciudad = new Construccion("Ciudad", nodo, this, null);
	        this.getConstrucciones().add(ciudad);
	    }
	
	    public void ConstruirCarretera(Edge edge) {
	        Recurso arcilla = new Recurso();
	        arcilla.setTipo("Arcilla");
	        Recurso madera = new Recurso();
	        madera.setTipo("Madera");
	        cartas.removeRecurso(arcilla);
	        cartas.removeRecurso(madera);
	        Construccion carretera = new Construccion("Carretera", null, this, edge);
	        this.getConstrucciones().add(carretera);
	    }
			
}
