package assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TreapMap<K extends Comparable<K>, V> implements Treap<K, V> {
	
	private class TreapIterator implements Iterator<K> {
		private int index=0;
		public boolean hasNext() {
			return index < inOrder.size();
		}
		public K next() {
			index++;
			return inOrder.get(index-1).key;
		}
		
	}

	private class FakeNode<V> {
		
	}
	private int treapSize;
	private String output="";
	private ArrayList<TreapNode> inOrder;
	private ArrayList<TreapNode> list;
	class TreapNode {
		K key;
    	V value;
    	int priority;
    	
    	TreapNode leftChild;
    	TreapNode rightChild;
    	TreapNode parent;
	    
    	private TreapNode() {
    		leftChild = null;
    		rightChild = null;
    		key=null;
	    }
    	    	
	}
    private TreapNode root;
    private TreapNode lastAdded;
    public TreapMap() {
        root = new TreapNode();
        root.key=null;
        treapSize=0;
        list = new ArrayList<TreapNode>();
        inOrder = new ArrayList<TreapNode>();
    }
	
	public V lookup(K key) {
		if(root.key==null) {
			return null;
		}
		if(key==null) {
			return null;
		}
		return find(root, key);
	}
	
	private V find(TreapNode current, K key) {
		if(current.key.equals(key)) {
			return current.value;
		}
		else if(current.key.compareTo(key)>0) { //to the right by BST
			if(current.leftChild==null) {
				return null;
			}
			return find(current.leftChild, key);
		}
		else {
			if(current.rightChild==null) {
				return null;
			}
			return find(current.rightChild, key);
		}
	}

	public void insert(K key, V value) {
		if(key==null||value==null) {
			return;
		}
		if(value instanceof FakeNode) { //for split
			addWithPriority(key, (V) "", MAX_PRIORITY, root);
			percolate(lastAdded);
			return;
		}
		if(root.key!=null&&lookup(key)!=null) {
			findAndReplace(key, value, root);
			return;
		}
		treapSize++;
		if(root.key==null) {
			root = new TreapNode();
			root.key=key;
			root.value=value;
			Random rand = new Random();
	        int priority = rand.nextInt(MAX_PRIORITY);
	        root.priority=priority;
	        lastAdded=root;
	        list.add(root);
		}
		else {
			add(key, value, root); //for BST
			percolate(lastAdded); //for heap
		}
		
	}
	
	private void findAndReplace(K key, V value, TreapNode current) {
		if(current.key.equals(key)) {
			current.value=value;
		}
		else if(current.key.compareTo(key)>0) {
			if(current.leftChild==null) {
				return;
			}
			findAndReplace(key, value, current.leftChild);
		}
		else {
			if(current.rightChild==null) {
				return;
			}
			findAndReplace(key, value, current.rightChild);
		}
	}
	
	private void percolate(TreapNode node) {
		 while (node.parent != null && node.parent.priority < node.priority) {
	       if (node.parent.rightChild!=null && node.parent.rightChild.key.equals(node.key)) {
	         rotateLeft(node.parent);
	       } 
	       else {
	         rotateRight(node.parent);
	       }
	     }
	     if (node.parent == null) {
	         root = node;
	     }
	}
	
	private void rotateLeft(TreapNode node) {
		TreapNode rightChild = node.rightChild;
        rightChild.parent = node.parent;
        if (rightChild.parent != null) {
            if (rightChild.parent.leftChild!=null&&rightChild.parent.leftChild.equals(node)) { //working with left child
                rightChild.parent.leftChild = rightChild;
            } 
            else {
                rightChild.parent.rightChild = rightChild;
            }
        }
        node.rightChild = rightChild.leftChild;
        if (node.rightChild != null) {
            node.rightChild.parent = node;
        }
        node.parent = rightChild;
        rightChild.leftChild = node;
        if (node.equals(root)) { 
        	root = rightChild; 
        	root.parent = null; 
        }
	}
	
	private void rotateRight(TreapNode node) {
		TreapNode leftChild = node.leftChild;
        leftChild.parent = node.parent;
        if (leftChild.parent != null) {
            if (leftChild.parent.leftChild!=null&&leftChild.parent.leftChild.equals(node)) { //working with right child
                leftChild.parent.leftChild = leftChild;
            } 
            else {
                leftChild.parent.rightChild = leftChild;
            }
        }
        node.leftChild = leftChild.rightChild;
        if (node.leftChild != null) {
            node.leftChild.parent = node;
        }
        node.parent = leftChild;
        leftChild.rightChild = node;
        if (node.key.equals(root.key)) { 
        	root = leftChild; 
        	root.parent = null; 
        }
	}
	
	private void addWithPriority(K key, V value, int priority, TreapNode cur) {
		if (cur.key.compareTo(key)>=0) {
	    	if (cur.leftChild == null) {
	           cur.leftChild=new TreapNode();
	           cur.leftChild.key=key;
	           cur.leftChild.value=value;
	           cur.leftChild.priority=priority;
	           cur.leftChild.parent=cur;
	           lastAdded=cur.leftChild;
	           list.add(cur.leftChild);
	           return;
	    	} 
	    	else
	            addWithPriority(key, value, priority, cur.leftChild);
	    } 
	    else if (cur.key.compareTo(key)<0) {
	    	if (cur.rightChild == null) {
	          cur.rightChild=new TreapNode();
	          cur.rightChild.key=key;
	          cur.rightChild.value=value;
	          cur.rightChild.priority=priority;
	          cur.rightChild.parent=cur;
	          lastAdded=cur.rightChild;
	          list.add(cur.rightChild);
	          return;
		    } 
		    else
		    	addWithPriority(key, value, priority, cur.rightChild);
	    }
	}
	
	private void add(K key, V value, TreapNode cur) {
		if (cur.key.compareTo(key)>=0) {
	    	if (cur.leftChild == null) {
	           cur.leftChild=new TreapNode();
	           cur.leftChild.key=key;
	           cur.leftChild.value=value;
	           Random rand = new Random();
	           int priority = rand.nextInt(MAX_PRIORITY);
	           cur.leftChild.priority=priority;
	           cur.leftChild.parent=cur;
	           lastAdded=cur.leftChild;
	           list.add(cur.leftChild);
	           return;
	    	} 
	    	else
	            add(key, value, cur.leftChild);
	    } 
	    else if (cur.key.compareTo(key)<0) {
	    	if (cur.rightChild == null) {
	          cur.rightChild=new TreapNode();
	          cur.rightChild.key=key;
	          cur.rightChild.value=value;
		      Random rand = new Random();
	          int priority = rand.nextInt(MAX_PRIORITY);
	          cur.rightChild.priority=priority;
	          cur.rightChild.parent=cur;
	          lastAdded=cur.rightChild;
	          list.add(cur.rightChild);
	          return;
		    } 
		    else
		    	add(key, value, cur.rightChild);
	    }
	}

	public V remove(K key) {
		if(key==null) {
			return null;
		}
		
		if(lookup(key)==null) {
			return null;
		}
		
		if(root.key.equals(key)&&root.leftChild==null&&root.rightChild==null) {
			V val = root.value;
			root = new TreapNode();
			return val;
		}		
		
		TreapNode node = getNode(root, key);
		while (node.rightChild!=null || node.leftChild!=null) {
			if(node.rightChild!=null && node.leftChild!=null) {
				if(node.rightChild.priority>node.leftChild.priority) {
					rotateLeft(node);
				}
				else {
					rotateRight(node);
				}
			}
			else if (node.rightChild!=null) {
				rotateLeft(node);
			} 
			else {
				rotateRight(node);
			}
		}
		if(node.parent.rightChild!=null&&node.parent.rightChild.key.equals(node.key)) {
			node.parent.rightChild=null;
		}
		else {
			node.parent.leftChild=null;
		}
		return node.value;
	}
	
	private TreapNode getNode(TreapNode current, K key) { //helper method returning ref to node
		if(current.key.equals(key)) {
			return current;
		}
		else if(current.key.compareTo(key)>0) {
			if(current.leftChild==null) {
				return null;
			}
			return getNode(current.leftChild, key);
		}
		else {
			if(current.rightChild==null) {
				return null;
			}
			return getNode(current.rightChild, key);
		}
	}

	public Treap<K, V>[] split(K key) {
		if(key==null) {
			return null;
		}
		if(root.key==null) {
			return null;
		}
		insert(key, (V) new FakeNode());
		TreapMap<K, V> t1 = new TreapMap<K, V>();
		t1.root=this.root.leftChild;
		TreapMap<K, V> t2 = new TreapMap<K, V>();
		t2.root=this.root.rightChild;
		TreapMap<K, V>[] treaps = new TreapMap[2];
		treaps[0]=t1;
		treaps[1]=t2;
		return treaps;
	}

	public void join(Treap<K, V> t) {
		if(t==null) {
			return;
		}
		TreapMap<K, V> t1 = this;
		TreapMap<K, V> t2 = new TreapMap<K, V>();
		t2 = (TreapMap<K, V>) t;
		TreapMap<K, V> newTreap = new TreapMap<K, V>();
		
		if(this.isEmpty()) {
			this.root=t2.root;
			return;
		}
		if(t2.isEmpty()) {
			return;
		}
		
		newTreap.insert((K)"", (V)"");
		newTreap.root.leftChild=t1.root;
		newTreap.root.rightChild=t2.root;
		newTreap.remove((K)"");
		this.root = newTreap.root;
	}

	public void meld(Treap<K, V> t) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public void difference(Treap<K, V> t) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public Iterator<K> iterator() {
		inOrder=new ArrayList<TreapNode>();
		inOrder(root, "");
		TreapIterator it = new TreapIterator();
		return it;
	}

	public double balanceFactor() throws UnsupportedOperationException {
		int height = getHeight()-1;
		double perfectHeight = Math.ceil(Math.log(treapSize) / Math.log(2))-1;
		return height/perfectHeight;
	}
	
	private int getHeight() {
		ArrayList<TreapNode> nodes = new ArrayList<TreapNode>();
		if(root!=null) {
			nodes.add(root);
		}
		
		int level=0;
		while(nodes.size()>0) {
			level++;
			ArrayList<TreapNode> children = new ArrayList<TreapNode>();
			boolean check=false;
			for(int j=0; j<nodes.size(); j++) {
				if(nodes.get(j)!=null) {
					check=true;
				}
			}
			if(check) {
				for(int j=0; j<nodes.size(); j++) {
					if(nodes.get(j)==null) {
						children.add(null);
						children.add(null);
						continue;
					}
				
					children.add(nodes.get(j).leftChild);
					children.add(nodes.get(j).rightChild);
				}
			}
		
			nodes = new ArrayList<TreapNode>();
			nodes.addAll(children);
		}
		return level-1;
	}
	
	
	private boolean isEmpty() {
		if(this.root==null||this.root.key==null) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		output="";
		if(root==null||root.key==null) {
			return "";
		}
		preOrder(root, "");
		return output;
	}
	
	private void preOrder(TreapNode current, String spaces) {
		output+=spaces+"["+current.priority+"] <"+current.key+","+current.value+">\n";
		//System.out.println(spaces+"["+current.priority+"] <"+current.key+","+current.value+">");
		if(current.leftChild!=null) {
			preOrder(current.leftChild, spaces+"\t");
		}
		if(current.rightChild!=null) {
			preOrder(current.rightChild, spaces+"\t");
		}
		
	}
	
	private void inOrder(TreapNode current, String spaces) {
		if(current==null) {
			return;
		}
		if(current.leftChild!=null) {
			inOrder(current.leftChild, spaces+"   ");
		}
		
		//System.out.println(spaces+"["+current.priority+"] <"+current.key+","+current.value+">");
		inOrder.add(current);
		if(current.rightChild!=null) {
			inOrder(current.rightChild, spaces+"   ");
		}
		//spaces are not needed, this is just an option for toString and inOrder
		//unnecessary parameter
	}


	
	
}
