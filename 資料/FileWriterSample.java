import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterSample {
	public static FileWriter fw;

	public static void main(String[] args) {

		try {

			//ストリームを開く
			fw = new FileWriter("books.txt");

			//データ書き込み
			fw.write("あああああ\r\n");
			fw.write("いいいいい\r\n");

			//ストリームを閉じる
			fw.close();

			//ファイルがないエラー
		} catch (FileNotFoundException f) {
			System.out.println("ファイルがありません(E502)");

		} catch (NullPointerException n) {

			//ファイルの書き込みに失敗エラー
		} catch (IOException e) {
			System.out.println("ファイルの書き込みに失敗しました(E503)");

		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
