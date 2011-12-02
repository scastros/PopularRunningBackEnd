package com.popular.running.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.popular.running.listeners.resolvers.HsqlDatabasePathResolver;

/**
 * Determines where in the disk is located the database file
 *
 * @author scastros
 */
public class HsqlDatabaseListener implements ServletContextListener {

    @SuppressWarnings("unused")
	private ServletContext context = null;
	private Log log = LogFactory.getLog(HsqlDatabaseListener.class);

	/**
	 * @param event
	 */
	public void contextInitialized(ServletContextEvent event) {
        context = event.getServletContext();

        String prefix = event.getServletContext().getRealPath("/");

        log.info("Database root " + prefix);
        HsqlDatabasePathResolver.getInstance(prefix);
    }

	/**
	 * @param event
	 */
    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();

    }
}