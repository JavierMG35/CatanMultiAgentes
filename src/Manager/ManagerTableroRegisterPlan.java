package src.Manager;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.Plan;

import java.awt.EventQueue;

public class ManagerTableroRegisterPlan extends Plan {

	public void body()
	{		
		final AgentIdentifier dealerAID = (AgentIdentifier)getBeliefbase().getBelief("localDealerAID").getFact();
		//final ManagerFrame	gui	= (ManagerFrame)getBeliefbase().getBelief("gui").getFact();
		getLogger().info("Dealer-AID has changed " + dealerAID);
	}
}
