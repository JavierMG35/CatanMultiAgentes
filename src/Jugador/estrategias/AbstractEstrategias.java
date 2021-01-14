package src.Jugador.estrategias;

import java.util.*;
import jadex.util.SReflect;
import src.Jugador.Jugador;
import src.ontologia.actions.RealizarOferta;
import src.ontologia.concepts.*;
import src.ontologia.concepts.Recurso;

public abstract class AbstractEstrategias implements IEstrategia{
	

		//constantes

		public static final String GRAN_RUTA_COMERCIAL = "GranRutaComercial";
		public static final String COSMOPOLITA = "Cosmopolita";
		public static final String EJERCITO_DE_CABALLERIA = "EjercitoDeCaballeria";
		public static final String MONOPOLIO = "Monopolio";

		//atributos

		/** The strategy name. */
		protected String name;

		/** The strategies. */
		protected static List strategies;

		//-------- constructors --------

		/**
		 *  Create a new strategy.
		 */
		public AbstractEstrategias()
		{
		}
		
		public AbstractEstrategias(String name)
		{
			this.name = name;
		}

		//-------- methods --------

		
		public abstract List<Recurso> propuestaNegociarJugadorOfrecer(Jugador jugador);
		
		public abstract Recurso propuestaNegociarJugadorRecibir(Jugador jugador);
		
		public abstract Recurso propuestaNegociarBanca(Cartas carta);
		
		public abstract boolean aceptarOferta(RealizarOferta oferta, Cartas mis_cartas);

		public abstract Construccion decidirConstruccion(Mapa mapa, Cartas cartas, String nombre);
		
		public abstract Boolean decidirCompra();

		public String getName()
		{
			return name;
		}

		
		
		
		public String toString()
		{
			return SReflect.getInnerClassName(this.getClass());
		}

		public int hashCode()
		{
			return getClass().hashCode();
		}

		/**
		 * Test if two strategies are equal.
		 */
		/*
		public boolean equals(Object o)
		{
			return o instanceof IEstrategia && o.getClass().equals(getName());
		}*/

		static
		{
			strategies = new ArrayList();
			strategies.add(new GranRutaComeracialEstrategia(AbstractEstrategias.GRAN_RUTA_COMERCIAL));
			strategies.add(new CosmopolitaEstrategia(AbstractEstrategias.COSMOPOLITA));
			strategies.add(new CaballeriaEstrategia(AbstractEstrategias.EJERCITO_DE_CABALLERIA));
			
		}

		
		public static String[] getStrategyNames()
		{
			List ret = new ArrayList();
			for(int i=0; i<strategies.size(); i++)
				ret.add(((IEstrategia)strategies.get(i)).getName());
			return (String[])ret.toArray(new String[ret.size()]);
		}

		public static IEstrategia getStrategy(String name)
		{
			IEstrategia ret = null;

			for(int i=0; i<strategies.size() && ret==null; i++)
			{
				IEstrategia tmp = (IEstrategia)strategies.get(i);
				if(tmp.getName().equals(name))
					ret = tmp;
			}

			return ret;
		}

		public static IEstrategia[] getStrategies()
		{
			return (IEstrategia[])strategies.toArray(new IEstrategia[strategies.size()]);
		}
	}


