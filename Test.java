package assignment;


public class Test {
	public static void main(String[] args) {
		TreapMap<Integer, String> x = new TreapMap<Integer, String>();
		TreapMap<Integer, String> x2 = new TreapMap<Integer, String>();
		//x.insert(0, null);
		//System.out.println(x.lookup(0));
		x.insert(-1, "b");
		x.insert(0, "a");		
		x2.insert(4, "a");		
		x2.insert(5, "c");
		System.out.println(x.toString());
		System.out.println(x2.toString());
		x2.join(x);
		System.out.println(x.toString());
		/*x.remove(2);
		System.out.println();
		Iterator<Integer> xIt2 = x.iterator();
		while(xIt2.hasNext()) {
			Integer key = xIt2.next();
			System.out.println(key);
		}*/
		//System.out.println(x.toString());
		//x.insert(0, null);
		
		//x.insert(2, "d");
		//x.insert(3, "d");
		//x.insert(4, "d");
		//System.out.println(x.toString());
		//x.remove(4);
		//System.out.println(x.toString());
		//x.debug(x.root, 0);
		/*Treap[] treaps = x.split(0);
		for(int i=0; i<treaps.length; i++) {
			TreapMap t = (TreapMap) treaps[i];
			Iterator<Integer> xIt = t.iterator();
			while(xIt.hasNext()) {
				Integer key = xIt.next();
				System.out.println(key);
			}
			System.out.println();
		}*/
		/*TreapMap<Integer, String> treapMap = new TreapMap<>();
		System.out.println(treapMap.balanceFactor());
        treapMap.insert(2, null);
        treapMap.insert(null, "a");
        treapMap.insert(4, "c");
        treapMap.insert(3, "c");
        
        treapMap.insert(5, "c");
        System.out.println(treapMap.toString());
        System.out.println(treapMap.remove(3));
        System.out.println(treapMap.remove(3));*/
		//System.out.println(x.toString());
		/*Iterator<Integer> xIt = x.iterator();
		while(xIt.hasNext()) {
			Integer key = xIt.next();
			System.out.println(key);
		}
		for(int i=0; i<x.list.size(); i++) {
			System.out.println(x.list.get(i).key+" "+x.list.get(i).value+" "+x.list.get(i).priority);
		}*/
	}
}
