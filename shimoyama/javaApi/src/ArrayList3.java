import java.util.ArrayList;

public class ArrayList3 {
	public static void main(String[] args) {
		ArrayList<Integer> foo = new ArrayList<Integer>();
		foo.add(10);
		foo.add(20);
		foo.add(40);
		foo.add(30);
		
		showList(foo);
		
		ArrayList<Integer> copyFoo1 = new ArrayList<Integer>(foo);
		
		ArrayList<Integer> copyFoo2 = foo;
				
		foo.remove(1);
		foo.remove(1);
		
		showList(foo);
		showList(copyFoo1);
		showList(copyFoo2);

	}

	public static void showList(ArrayList<Integer> foo) {
		for (int num : foo) {
			System.out.println(num);
		}
		System.out.println("=========");
	}

}
