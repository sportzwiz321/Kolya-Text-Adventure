public class StoreTest {

	public static void main(String[] args) {
		Store abc = new Store();

		System.out.println(abc);
		System.out.println("Empty: " + abc.isEmpty());
		System.out.println("Store Size: " + abc.storeSize());

		abc.insert("Big F Sword", 13, "AD", 5);
		abc.insert("Tri-Force", 38, "AD", 15);
		abc.insert("Stinger", 11, "AD", 5);
		abc.insert("Boots of Speed", 3, "HP", 2);
		abc.insert("Warmog's Armor", 29, "HP", 10);
		abc.insert("Blade of the Ruined King", 34, "AD", 10);
		abc.insert("Dead Man's Plate", 29, "ARMOR", 10);
		abc.insert("Giant's Belt", 10, "HP", 5);
		abc.insert("Runic Echoes", 26, "AD", 10);

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