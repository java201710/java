import java.util.ArrayList;

public class ArrayList2 {
	public static void main(String[] args) {
		ArrayList<Integer> foo = new ArrayList<Integer>();
		foo.add(10);
		foo.add(20);
		foo.add(40);
		foo.add(30);
		
//		foo.set(1, 100);
//		foo.remove(2);
//		foo.add(1,200);
//		foo.remove(1);
//		foo.remove(2);
//		foo.set(1, foo.get(0));

		showList(foo);

	}

	public static void showList(ArrayList<Integer> foo) {
		for (int num : foo) {
			System.out.println(num);
		}
	}

}
