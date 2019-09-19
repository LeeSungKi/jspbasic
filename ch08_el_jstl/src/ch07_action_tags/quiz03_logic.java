package ch07_action_tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.Name;

/**
 * Servlet implementation class quiz03_logic
 */
@WebServlet("/quiz03_logic")
public class quiz03_logic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pokemon> List = new ArrayList<>();
		Pokemon p = new Pokemon();
		p.setName("ÇÇÄ«Ãò");
		p.setHp(1000);
		p.setLv(4);
		List.add(p);
		p = new Pokemon();
		p.setName("ÇÇÃò");
		p.setHp(1555);
		p.setLv(3);
		List.add(p);
		p = new Pokemon();
		p.setName("Çª¸°");
		p.setHp(1000);
		p.setLv(5);
		List.add(p);
		p = new Pokemon();
		p.setName("ÇÇ°í");
		p.setHp(1005);
		p.setLv(4);
		List.add(p);
		p = new Pokemon();
		p.setName("Ãò");
		p.setHp(100);
		p.setLv(4);
		List.add(p);

		request.setAttribute("pList", List);
		request.getRequestDispatcher("quiz03_result.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
