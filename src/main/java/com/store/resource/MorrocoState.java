package com.store.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorrocoState {

	public final static String US="";
	
	public final static Map<String,String> mapOfMoStates = new HashMap<String,String>() {{
		
					put("16","Oued Ed-Dahab - Lagouira");
					put("15","Laâyoune - Boujdour - Sakia El Hamra");
					put("14","Guelmim - Es-Semara");
					put("13","Souss - Massa - Daraa");
					put("12","Gharb - Chrarda - Béni Hssen");
					put("11","Chaouia - Ouardigha");
					put("10","Marrakech - Tensift - Al Haouz");
					put("9","Oriental");
					put("8","Grand Casablanca");
					put("7","Rabat - Salé - Zemmour - Zaer");
					put("6","Doukala - Abda");
					put("5","Tadla Azilal");
					put("4","Meknès - Tafilalet");
					put("3","Fès - Boulmane");
					put("2","Taza - Al Hoceima - Taounate");
					put("1","Tanger - Tétouan");

	}};
	
	
	public final static List<String> nameOfStates = new ArrayList<>(mapOfMoStates.values());
	public final static List<String> codeOfStates = new ArrayList<>(mapOfMoStates.keySet());
}
