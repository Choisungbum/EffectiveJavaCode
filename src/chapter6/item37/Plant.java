package chapter6.item37;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Plant {
	enum LifeCycle {ANNUAL, PERENNIAL, BIENNIAL}
	
	final String name;
	final LifeCycle lifeCycle;
	
	Plant(String name, LifeCycle lifeCycle) {
		this.name = name;
		this.lifeCycle = lifeCycle;
	}
	
	@Override public String toString() {
		return name;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Set<Plant>[] plantsByLifeCycle = 
//				(Set<Plant>[])	new Set[Plant.LifeCycle.values().length];
//			for (int i = 0; i < plantsByLifeCycle.length; i++)
//				plantsByLifeCycle[i] = new HashSet<>();
//			
//			Object garden;
//			for (Plant P : garden) {
//				plantsByLifeCycle[p.lifeCycle.ordinal()].app(p);
//			}
//			// 결과 춫력
//			for (int i = 0; i < plantsByLifeCycle.length; i++) {
//				System.out.printf("%s: %s%n",Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
//			}
		
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = 
				new EnumMap<>(Plant.LifeCycle.class);
		
		for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
			plantsByLifeCycle.put(lc, new HashSet<>());
		}
		
		Object garden;
		for(Plant P : garden)
			plantsByLifeCycle.get(p.LifeCycle).add(p);
	}

}
