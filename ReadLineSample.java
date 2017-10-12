import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadLineSample {

	public static void main(String[] args) {

		String data;

		FileReader in = null;
		BufferedReader buf = null;

		try {
			in = new FileReader("books.txt");

			buf = new BufferedReader(in);

			//BufferedReader buf = new BufferedReader(new FileReader("test.txt"));

			while (true) {
				data = buf.readLine();

				if (data == null) {
					break;
				}
				System.out.println(data);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (buf != null) {
					buf.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
