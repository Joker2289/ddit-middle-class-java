package homework;

import java.util.HashSet;
import java.util.TreeSet;

public class add_test {

	public static void main(String[] args) {
		
		HashSet<TreeSet> ht = new HashSet<>();
		
		TreeSet<Integer> t = new TreeSet<>();
		HashSet<Integer> h1 = new HashSet<>();
		t.add(123);
		ht.add(t);
		h1.add(123);
		System.out.println(ht);
		
		HashSet<HashSet> tt = new HashSet<>();
		tt.add(h1);
		
		
	}

}
