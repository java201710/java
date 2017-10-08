import java.util.ArrayList;

public class ArrayList1 {
	public static void main(String[] args) {
		ArrayList<Integer> foo = new ArrayList<Integer>();
		foo.add(10);
		foo.add(20);
		foo.add(40);
		foo.add(2,30);
		foo.set(1,5);
		
		showList(foo);

		System.out.println(foo.get(3));
		System.out.println("=========");
		
		ArrayList<ArrayList> foo2 = new ArrayList<ArrayList>();
//		ArrayList<ArrayList<Integer>> foo2 = new ArrayList<ArrayList<Integer>>();
		
		foo2.add(new ArrayList<Integer>(foo));
		
		foo.remove(2);
		
		foo2.add(new ArrayList<Integer>(foo));
		
		foo2.add(new ArrayList<Integer>(foo));

		for (ArrayList<Integer> sfoo : foo2) {
			showList(sfoo);
		}
		System.out.println(foo2.get(1).get(1));
		System.out.println("=========");
	}

	public static void showList(ArrayList<Integer> foo) {
		for (int num : foo) {
			System.out.println(num);
		}
		System.out.println("=========");
	}

}
