package com.popular.running.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author scastros
 * 
 */
public class HsqlDatabasePathResolver {

	private static HsqlDatabasePathResolver instance;
	private static String applicationPath = "";
	private static Log log = LogFactory.getLog(HsqlDatabasePathResolver.class);

	private HsqlDatabasePathResolver() {
	}

	/** Get Instance */
	static public synchronized HsqlDatabasePathResolver getInstance(String applicationPath) {

		if (instance == null) {

			HsqlDatabasePathResolver.applicationPath = HsqlDatabasePathResolver.normalizePath(applicationPath);
			instance = new HsqlDatabasePathResolver();

			log.info("Initializing path : "	+ HsqlDatabasePathResolver.applicationPath);
		}
		return instance;
	}

	public String getApplicationPath() {
		return applicationPath;
	}

	public String getUrlDatabase(String urlDatabase) {

		return HsqlDatabasePathResolver.replaceAll(urlDatabase, "{apppath}", applicationPath);
	}

	/**
	 * 
	 * replace the "\" character by "/" and remove relative paths
	 * 
	 * @param path
	 * @return
	 */
	public static String normalizePath(String path) {
		if (path == null) {
			return null;
		}
		String normalized = path;
		if (normalized.equals("/.")) {
			return "/";
		}
		if (normalized.indexOf('\\') >= 0) {
			normalized = normalized.replace('\\', '/');
		}
		if (!normalized.startsWith("/") && normalized.indexOf(':') < 0) {
			normalized = "/" + normalized;
		}
		do {
			int index = normalized.indexOf("//");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 1);
		} while (true);
		do {
			int index = normalized.indexOf("/./");
			if (index < 0) {
				break;
			}
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 2);
		} while (true);
		do {
			int index = normalized.indexOf("/../");
			if (index >= 0) {
				if (index == 0) {
					return null;
				}
				int index2 = normalized.lastIndexOf('/', index - 1);
				normalized = normalized.substring(0, index2)
						+ normalized.substring(index + 3);
			} else {
				return normalized;
			}
		} while (true);
	}

	/**
	 * 
	 * @param str
	 * @param match
	 * @param replace
	 * @return
	 */
	public static String replaceAll(String str, String match, String replace) {
		if (match == null || match.length() == 0) {
			return str;
		}
		if (replace == null) {
			replace = "";
		}
		if (match.equals(replace))
			return str;
		StringBuffer ret = new StringBuffer();
		int i = str.indexOf(match);
		int y = 0;
		while (i >= 0) {
			ret.append(str.substring(y, i));
			ret.append(replace);
			y = i + match.length();
			i = str.indexOf(match, y);
		}
		ret.append(str.substring(y));
		return ret.toString();
	}
}
