package com.popular.running.logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import org.apache.log4j.PropertyConfigurator;

public class Log4JServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		String log4jfile = getInitParameter("log4j-init-file");
		if (log4jfile != null) {
			String propfile = getServletContext().getRealPath(log4jfile);
			PropertyConfigurator.configure(propfile);
		}
	}
}