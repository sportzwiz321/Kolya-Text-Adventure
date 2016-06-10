public class StoreTest {

	public static void main(String[] args) {
		Store abc = new Store();

		System.out.println(abc);
		System.out.println("Empty: " + abc.isEmpty());
		System.out.println("Store Size: " + abc.storeSize());

		abc.insert(new Item("Big F Sword", 13, "AD", 5));
		abc.insert(new Item("Tri-Force", 38, "AD", 15));
		abc.insert(new Item("Stinger", 11, "AD", 5));
		abc.insert(new Item("Boots of Speed", 3, "HP", 2));
		abc.insert(new Item("Warmog's Armor", 29, "HP", 10));
		abc.insert(new Item("Blade of the Ruined King", 34, "AD", 10));
		abc.insert(new Item("Dead Man's Plate", 29, "ARMOR", 10));
		abc.insert(new Item("Giant's Belt", 10, "HP", 5));
		abc.insert(new Item("Runic Echoes", 26, "AD", 10));

		// System.out.println(abc.lookupCost("BORK"));

		System.out.println();
		System.out.println(abc);
		System.out.println("Empty: " + abc.isEmpty());
		System.out.println("Store Size: " + abc.storeSize());

		abc.delete("Tri-Force");
		abc.delete("Big F Sword");

		System.out.println();
		System.out.println(abc);
		System.out.println("Empty: " + abc.isEmpty());
		System.out.println("Store Size: " + abc.storeSize());

		Item[] items = abc.itemStats();

		for (int x = 1; x < items.length + 1; x++) {
			System.out.println(x + ". " + items[x-1].getName());
		}

		System.out.println("\n" + abc.selectItem(6));

		System.out.print(abc.lookupItemCost("Stinger") + " " + abc.lookupItemStat("Stinger") + " " + abc.lookupItemStatBoost("Stinger"));

		abc.emptyStore();
		System.out.println(abc);
		System.out.println("Empty: " + abc.isEmpty());
		System.out.println("Store Size: " + abc.storeSize());


	}

}