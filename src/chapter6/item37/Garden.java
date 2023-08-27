package chapter6.item37;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Garden {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = 
				new EnumMap<>(Plant.LifeCycle.class);
		
		for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
			plantsByLifeCycle.put(lc, new HashSet<>());
		}
		
		Garden garden = new Garden();
		for(Plant P : garden)
			plantsByLifeCycle.get(plantsByLifeCycle.LifeCycle.add(p));
	}

	}

}
