package com.steve.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;


interface Rays {
	String getType();
}


class compareRay implements Comparator<Rays> {

	@Override
	public int compare(Rays arg0, Rays arg1) {
		// TODO Auto-generated method stub
		return arg0.getType().compareTo(arg1.getType());
	}

	
}


class Alpha  implements Rays {
	public String getType() {
		return "alpha";
	}
}

class Beta implements Rays {
	public String getType() {
		return "beta";
	}
}

public class Gamma implements Rays {
	public String getType() {
		return "gamma";
	}

	public static void main(String[] args) {
		
		Rays g1 = new Alpha();
		Rays g2 = new Beta();
		Rays g3 = new Gamma();
		System.out.println(g1.getType() + ", " + g2.getType() + ", " + g3.getType());
		
		System.out.println("using an array");
		
		Rays[] rays = { new Gamma(), new Alpha(), new Beta()};
		
		String s = "";
		for (Rays ray : rays) {
			s += (s.length() == 0 ? "" : ", ") + ray.getType();
		}
		System.out.println("supported types: " + s);
		
		Arrays.sort(rays, new compareRay());
		
		System.out.println("Arrays sort");
		
		s = "";
		for (Rays ray : rays) {
			s += (s.length() == 0 ? "" : ", ") + ray.getType();
		}
		System.out.println("supported types: " + s);
		
		Rays[] rays2 = { new Gamma(), new Alpha(), new Beta()};
		
		List<Rays> list  = Arrays.asList(rays2);
		
		Map<String, String> listSorted = new TreeMap<String, String>();
		
		for (Rays ray : list) {
			listSorted.put(ray.getType(), "");
		}
		
		s = "";
		for (String type : listSorted.keySet()) {
			s += (s.length() == 0 ? "" : ", ") + type;
		}
		System.out.println("supported types (sorted): " + s);
		
	}
}

// Whats failed in the compilation
/*
 class Alpha {
 String getType() {
 return "alpha";
 }
}
class Beta extends Alpha {
 String getType() {
 return "beta";
 }
}
public class Gamma extends Beta {
 String getType() {
 return "gamma";
 }
 public static void main(String[] args) {
 Gamma g1 = new Alpha();
 Gamma g2 = new Beta();
 System.out.println(g1.getType() + " "
 + g2.getType());
 }
}
 */
