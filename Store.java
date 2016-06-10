// Binary Search Tree implementation of a key pairing store

public class Store {

	private class Node {

		Item stick;
		Node left;
		Node right;

		public Node(Item stick) {
			this.stick = stick;
			left = null;
			right = null;
		}

		public String getItemName() {
			return stick.getName();
		}

		public int getItemCost() {
			return stick.getCost();
		}

		public String getItemStat() {
			return stick.getStat();
		}

		public int getItemStatBoost() {
			return stick.getStatBoost();
		}

	}

	private int numItems;
	private Node root;

	public Store() {

		numItems = 0;
		root = null;

	}

	private Node findKey(String item) {

		Node n = root;

		while(n != null) {
			if(item == n.getItemName()) {
				return n;
			} else if (item.compareTo(n.getItemName()) > 0) {
				n = n.right;
			} else {
				n = n.left;
			}
		}

		return n;

	}

	private Node findParent(Node n, Node r) {

		Node p = null;
		if (n != r) {
			p = r;
			while(p.right != n && p.left != n) {
				if (n.getItemName().compareTo(p.getItemName()) > 0) {
					p = p.right;
				} else {
					p = p.left;
				}
			}
		}

		return p;

	}

	private Node findLeftmost(Node r) {

		Node n = r;

		while(n.left != null) {
			n = n.left;
		}

		return n;

	}

	private StringBuffer printInOrder(Node r, StringBuffer sb) {

		StringBuffer a = new StringBuffer(sb);

		if (r != null) {
			StringBuffer sbl = printInOrder(r.left, a);
			StringBuffer sbr = printInOrder(r.right, a);
			a.append(sbl);
			a.append(r.stick + "\n");
			a.append(sbr);
		}

		return a;

	}

	private void placeInOrder(Node r, Item[] items) {

		if (r != null) {
			placeInOrder(r.left, items);
			boolean placed = false;
			int x = 0;
			while(!placed) {
				if (items[x] == null) {
					items[x] = r.stick;
					placed = true;
				} else {
					x++;
				}
			}
			placeInOrder(r.right, items);
		}

	}

	private void storeInOrder(Node r, String[] items) {

		if (r != null) {
			storeInOrder(r.left, items);
			boolean placed = false;
			int x = 0;
			while(!placed) {
				if (items[x] == null) {
					items[x] = r.stick.toString();
					placed = true;
				} else {
					x++;
				}
			}
			storeInOrder(r.right, items);
		}

	}

	private Item[] storeInCostOrder(Node r) {

		Item[] items = new Item[numItems];
		placeInOrder(r, items);

		for (int j = 1; j < numItems; j++) {
			int i = j-1;
			Item temp = items[j];
			while(i >= 0 && temp.getCost() < items[i].getCost()) {
				items[i+1] = items[i];
				i--;
			}
			items[i+1] = temp;

		}

		return items;

	}

	public int storeSize() {

		return numItems;

	}

	public boolean isEmpty() {

		return (numItems == 0);

	}

	public Item retrieveItem(int x) {

		if (x <= numItems && x > 0) {
			Item[] items = new Item[numItems];
			placeInOrder(root, items);
			return items[x-1];	
		} else {
			return null;
		}

	}

	public String selectItem(int x) {

		if (x <= numItems && x > 0) {
			String[] items = new String[numItems];
			storeInOrder(root, items);
			return items[x-1];	
		} else {
			return null;
		}

	}

	public int lookupItemCost(String item) {

		Node n = findKey(item);

		if (n != null) {
			return n.getItemCost();
		} else {
			return -1;
		}

	}

	public String lookupItemStat(String item) {

		Node n = findKey(item);

		if (n != null) {
			return n.getItemStat();
		} else {
			return null;
		}

	}

	public int lookupItemStatBoost(String item) {

		Node n = findKey(item);

		if (n != null) {
			return n.getItemStatBoost();
		} else {
			return -1;
		}

	}

	public void insert(Item stick) throws DuplicateItemException {

		Node n = findKey(stick.getName());

		if (n != null) {
			throw new DuplicateItemException("Store error: Called insert() on pre-existing item");
		} else {

			n = new Node(stick);
			
			if (root == null) {
				root = n;
			} else {

				Node x = root;
				boolean placed = false;

				while(!placed) {
					if (stick.getName().compareTo(x.getItemName()) > 0) {
						if (x.right == null) {
							x.right = n;
							placed = true;
						} else {
							x = x.right;
						}
					} else {
						if (x.left == null) {
							x.left = n;
							placed = true;
						} else {
							x = x.left;
						}
					}
				}

			}

			numItems++;
			

		}

	}

	public void delete(String item) throws ItemNotFoundException {

		Node n = findKey(item);

		if (n == null) {
			throw new ItemNotFoundException("Store error: Called delete() on non-existent item");
		} else {

			if (n.right == null && n.left == null) {

				if (n == root) {
					root = null;
				} else {
					Node p = findParent(n, root);
					if (p.right == n) {
						p.right = null;
					} else {
						p.left = null;
					}
				}

			} else if (n.right == null && n.left != null) {
				
				if (n == root) {
					root = root.left;
				} else {
					Node p = findParent(n, root);
					if (p.right == n) {
						p.right = n.left;
						n = null;
					} else {
						p.left = n.left;
						n = null;
					}
				}

			} else if (n.right != null && n.left == null) {

				if (n == root) {
					root = root.right;
				} else {
					Node p = findParent(n, root);
					if (p.right == n) {
						p.right = n.right;
						n = null;
					} else {
						p.left = n.right;
						n = null;
					}
				}

			} else {

				Node l = findLeftmost(n.right);
				Node p = findParent(l, root);
				n.stick = l.stick;
				if (p.right == l) {
					p.right = l.right;
				} else {
					p.left = l.right;
				}

			}

			numItems--;

		}

	}

	public void emptyStore() {

		numItems = 0;
		root = null;

	}

	public String toString() {

		StringBuffer keys = new StringBuffer();
		keys = printInOrder(root, keys);
		return new String(keys);

	}

	public Item[] itemStats() {

		Item[] items = new Item[numItems];
		placeInOrder(root, items);
		return items;
		
	}

	public Item[] itemCostList() {

		return storeInCostOrder(root);

	}

}