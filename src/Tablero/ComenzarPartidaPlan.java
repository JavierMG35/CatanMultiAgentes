package src.Tablero;

import jadex.runtime.*;
import src.Jugador.*;
import jadex.util.SUtil;
import java.util.List;
import jadex.adapter.fipa.*;

public class ComenzarPartidaPlan extends Plan{
	
	public List<Integer> valores;
	public int max_valor = 0;
	public void body() {
		
		Jugador[]	jugadores	= (Jugador[])getBeliefbase().getBeliefSet("jugador").getFacts();
		Tablero	yo	= (Tablero)getBeliefbase().getBelief("myself").getFact();
		System.out.println("A");
		System.out.println(jugadores);
		
		for(int i=0;i<4;i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(jugadores[i]);
			mensaje_enviar.getParameterSet(SFipa.SENDER).addValue(yo);
			System.out.println("Enviado de tablero a jugador");
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar, 15000);		
			System.out.println("Recibido de jugador a tablero (o no)");
			System.out.println(respuesta);
			Dados dados = (Dados)respuesta.getContent();
			valores.add(dados.getDados());
			getLogger().info("Mensaje de TirarDados enviado a" + i);
		}
		for(int i=0;i<valores.size();i++) {
			if(valores.get(i)>max_valor) {
				max_valor = valores.get(i);
			}
		}
		
		System.out.println("we made it motherfuckers");
		
	}

}
