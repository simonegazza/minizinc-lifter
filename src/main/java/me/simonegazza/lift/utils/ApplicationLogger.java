package me.simonegazza.lift.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * A lightweight application logger built on top of
 * {@link java.util.logging.Logger}.
 * <p>
 * This class provides a centralized and simplified logging API for the
 * application without requiring external logging libraries.
 * </p>
 * <p>
 * Features:
 * </p>
 * <ul>
 * <li>Custom log formatting</li>
 * <li>Centralized logger configuration</li>
 * <li>Console logging support</li>
 * <li>Optional exception logging</li>
 * <li>Wrapper methods for common log levels</li>
 * </ul>
 */
public class ApplicationLogger {

	/**
	 * The singleton instance of this logger.
	 */
	private final Logger logger;

	private ApplicationLogger(String name) {
		logger = Logger.getLogger(name);

		logger.setUseParentHandlers(false);

		// Avoid duplicate handlers
		if (logger.getHandlers().length == 0) {
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new Formatter() {
				private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
					.withZone(ZoneId.systemDefault());

				@Override
				public String format(LogRecord record) {

					String dateTime = dtf.format(
						Instant.ofEpochMilli(record.getMillis()));

					return String.format(
						"[%s, %s, %s] %s%n",
						record.getLevel(),
						record.getLoggerName(),
						dateTime,
						record.getMessage());
				}
			});

			logger.addHandler(consoleHandler);
		}
	}

	/**
	 * Returns a configured {@code ApplicationLogger} instance associated with
	 * the specified logger name.
	 *
	 * @param name the logger name, usually the class name
	 *
	 * @return a configured {@code ApplicationLogger} instance
	 */
	public static ApplicationLogger getLogger(String name) {
		return new ApplicationLogger(name);
	}

	/**
	 * Logs an informational message.
	 *
	 * @param message the message to log
	 */
	public void info(String message) {
		logger.info(message);
	}

	/**
	 * Logs a warning message.
	 *
	 * @param message the warning message
	 */
	public void warning(String message) {
		logger.warning(message);
	}

	/**
	 * Logs an error message.
	 *
	 * @param message the error message
	 */
	public void error(String message) {
		logger.severe(message);
	}

	/**
	 * Logs a debug-level message.
	 *
	 * @param message the debug message
	 */
	public void debug(String message) {
		logger.fine(message);
	}

}
