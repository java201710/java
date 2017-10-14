	package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SampleServlet")
//Mitchell;Made a mistake in capital lettering below, but still worked.
public class sampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; //It is okay to remove this

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//運勢をランダムで決定
		String[] luckArray = {"緒スッキリ", "スッキリ", "最悪"};

		//０以上３未満の整数を乱数で生成
		int index = (int) (Math.random() * 3);
		String luck = luckArray[index];

		//実行日を取得
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String today = sdf.format(date);

		//ＨＴＭＬを出力
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>スッキリ占い</title>");
		out.println("</head>");
		out.println("<body>");

		//test
		out.println("<!-- HTML Comment -->");
		for(int i = 0; i < 2; i++) {
		out.println("<p>" + today + "の運勢は" + luck + "です。</p>");
		}

		out.println("</body>");
		out.println("</html>");

	}

}
