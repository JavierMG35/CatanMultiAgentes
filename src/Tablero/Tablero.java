package src.Tablero;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.model.jibximpl.Agent;
//import jadex.examples.blackjack.player.strategies.IStrategy;
//import jadex.examples.blackjack.player.strategies.*;
import jadex.runtime.BasicAgentIdentifier;
//import jadex.util.SUtil;
import jadex.util.SimplePropertyChangeSupport;

//import java.beans.PropertyChangeListener;
//import java.awt.Color;
//import java.util.ArrayList;
import java.util.List;
//import jadex.runtime.*;


public class Tablero extends Agent{
	
	//-------- constructors --------
			public Tablero()
			{
				this(null, null, null);
			}
			/**
			 *  Create a new Player.
			 */
			public Tablero(String name,String strategyname)
			{
				this(null, name, strategyname);
			}

			/**
			 *  Create a new Player.
			 */
			public Tablero(AgentIdentifier aid,String nombre, String strategyname)
			{
				this.aid	= aid;
				this.nombre	= nombre;
				this.strategyname = strategyname;
				//if(strategyname!=null)
				//	this.strategy	= AbstractStrategy.getStrategy(strategyname);
				this.state	= STATE_UNREGISTERED;
				this.pcs = new SimplePropertyChangeSupport(this);
			}

	//-------- constants --------

		/** . */
		public static final String	STATE_UNREGISTERED	= "unregistered";
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
		
		protected int[] puntuaciones;
		
		protected List construcciones;
		
		protected int tirada;
		
		protected int posicion_mesa;
		
		protected int puntuacion;
		
		/** The strategyname. */
		protected String strategyname;
		
		protected int ready = 0;

		/** The player's strategy. */
		//protected IStrategy	strategy;

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
		
		public List getConstrucciones() {
			return construcciones;
		}
		public void setConstrucciones(List construcciones) {
			this.construcciones = construcciones;
		}
		public int getTirada() {
			return tirada;
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
			return aid;
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
		public int getReady() {
			return ready;
		}
		public void setReady(int ready) {
			this.ready = ready;
		}
		
		
		
}
