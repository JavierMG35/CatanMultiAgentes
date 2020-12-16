package Mapa.Jugador;

import jadex.adapter.fipa.AgentIdentifier;
//import jadex.examples.blackjack.player.strategies.*;
import jadex.runtime.BasicAgentIdentifier;
import jadex.util.SUtil;
import jadex.util.SimplePropertyChangeSupport;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Jugador {

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
		
		protected List cartas;
		
		protected List construcciones;
		
		/** The strategyname. */
		protected String strategyname;

		/** The player's strategy. */
		protected IStrategy	strategy;

		/** The player's agent id. */
		protected BasicAgentIdentifier	aid;

		/** The player state. */
		protected String	state;
		
		//-------- constructors --------
		public Jugador()
		{
			this(null, 0, null, null);
		}
		/**
		 *  Create a new Player.
		 */
		public Jugador(String name, List cartas, List construcciones, String strategyname)
		{
			this(null, name, cartas, construcciones, strategyname);
		}

		/**
		 *  Create a new Player.
		 */
		public Player(AgentIdentifier aid, String nombre, List cartas, List construcciones, String strategyname)
		{
			this.aid	= aid;
			this.nombre	= nombre;
			this.cartas	= cartas;
			this.construcciones = construcciones;
			this.strategyname = strategyname;
			//if(strategyname!=null)
			//	this.strategy	= AbstractStrategy.getStrategy(strategyname);
			this.state	= STATE_UNREGISTERED;
			this.pcs = new SimplePropertyChangeSupport(this);
		}
}
