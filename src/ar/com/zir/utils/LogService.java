/*
    Copyright 2012 Juan Martín Runge
    
    jmrunge@gmail.com
    http://www.zirsi.com.ar
    
    This file is part of ZirUtils.

    ZirUtils is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    ZirUtils is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ZirUtils.  If not, see <http://www.gnu.org/licenses/>.
*/
package ar.com.zir.utils;

import ar.com.zir.mail.api.Mail;
import ar.com.zir.mail.client.MailClient;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.log4j.*;


/**
 * Singleton utility class for log messages and exceptions using log4j and being able to send
 * the log file via mail
 * 
 * @author jmrunge
 * @version 1.00
 * @see MailClient
 */
public class LogService {
    private Logger logger;
    private String logFile;
    private String logName;
    private Properties props;
    private MailClient client;
    
    /**
     * Singleton constructor
     */
    private LogService() {
    }
    
    /**
     * Method for obtaining sigleton instance
     * 
     * @return the singleton instance
     */
    public static LogService getInstance() {
        return LogServiceHolder.INSTANCE;
    }
    
    /**
     * static class to hold the singleton instance
     */
    private static class LogServiceHolder {
        private static final LogService INSTANCE = new LogService();
    }
    
    /**
     * Method for configuring the log on a daily file rotation basis
     * Should be invoked prior any other invocation to this class
     * 
     * @param logDir the directory where log files will reside
     * @param logName the name for the log files
     * @param props the properties object that contains the log and mail properties (log.level, 
     * mail.server.host, mail.server.port, mail.sender, mail.recipient.x)
     * @throws IOException inherited from DailyRollingFileAppender
     * @see org.apache.log4j.DailyRollingFileAppender
     * @see org.apache.log4j.PatternLayout
     */
    public void configureDailyLog(String logDir, String logName, Properties props) throws IOException {
        this.logName = logName;
        this.props = props;
        logger = Logger.getLogger(logName);
        logger.setLevel(getLogLevel());
        logFile = logDir + "/" + logName + ".log";
        FileAppender appender = new DailyRollingFileAppender(new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} %C %L %-5p: %m%n"), logFile, "'.'dd-MM-yyyy");
        logger.addAppender(appender);
    }
    
    /**
     * Method that logs the specified Throwable with the specified message 
     * at the specified Level and sends a mail if requested
     * 
     * @param level the Level for the log record
     * @param msg the Message for the log record (may be null)
     * @param t the Throwable to log (may be null)
     * @param sendMail send or not an email with the message and (if Level 
     * is ERROR or FATAL) attach the log file to it
     */
    public void log(Level level, String msg, Throwable t, boolean sendMail) {
        String levelString;
        boolean attachLog;
        switch (level.toInt()) {
            case Level.DEBUG_INT:
                logger.debug(msg, t);
                levelString = "DEBUG";
                attachLog = false;
                break;
            case Level.INFO_INT:
                logger.info(msg, t);
                levelString = "INFO";
                attachLog = false;
                break;
            case Level.ERROR_INT:
                logger.error(msg, t);
                levelString = "ERROR";
                attachLog = true;
                break;
            case Level.FATAL_INT:
                logger.fatal(msg, t);
                levelString = "FATAL";
                attachLog = true;
                break;
            case Level.WARN_INT:
                logger.warn(msg, t);
                levelString = "WARNING";
                attachLog = false;
                break;
            case Level.TRACE_INT:
                logger.trace(msg, t);
                levelString = "TRACE";
                attachLog = false;
                break;
            default:
                logger.info(msg, t);
                levelString = "DEBUG";
                attachLog = false;
                break;
        }
        if (sendMail) {
            if (msg == null)
                msg = t.getMessage();
            try {
                sendMail(logName + ": " + levelString, msg, attachLog);
            } catch (Exception ex) {
                log(Level.WARN, "No se pudo enviar el mail solicitado", ex);
            }
        }
    }
    
    /**
     * Method that logs a Throwable at a specified Level and optionally sends a mail
     * 
     * @param level the Level for the log record
     * @param t the Throwable to log
     * @param sendMail send or not the eimail
     * @see log(Level, String, Throwable, boolean)
     */
    public void log(Level level, Throwable t, boolean sendMail) {
        log(level, null, t, sendMail);
    }
    
    /**
     * Method that logs a Message at a specified Level and optionally sends a mail
     * 
     * @param level the Level for the log record
     * @param msg the Message to log
     * @param sendMail send or not the eimail
     * @see log(Level, String, Throwable, boolean)
     */
    public void log(Level level, String msg, boolean sendMail) {
        log(level, msg, null, sendMail);
    }
    
    /**
     * Method that logs a Throwable at a specified Level with the specified message
     * 
     * @param level the Level for the log record
     * @param msg the Message to log
     * @param t the Throwable to log
     * @see log(Level, String, Throwable, boolean)
     */
    public void log(Level level, String msg, Throwable t) {
        log(level, msg, t, false);
    }
    
    /**
     * Method that logs a Throwable at a specified Level
     * 
     * @param level the Level for the log record
     * @param t the Throwable to log
     * @see log(Level, String, Throwable, boolean)
     */
    public void log(Level level, Throwable t) {
        log(level, t, false);
    }
    
    /**
     * Method that logs a Message at a specified Level
     * 
     * @param level the Level for the log record
     * @param msg the Message to log
     * @see log(Level, String, Throwable, boolean)
     */
    public void log(Level level, String msg) {
        log(level, msg, false);
    }
    
    /**
     * Method for obtaining the corresponding Level object of the property "log.level"
     * 
     * @return the Level object obtained or INFO if not found
     */
    private Level getLogLevel() {
        String logLevel = props.getProperty("log.level");
        switch (logLevel) {
            case "debug":
                return Level.DEBUG;
            case "info":
                return Level.INFO;
            case "all":
                return Level.ALL;
            case "error":
                return Level.ERROR;
            case "fatal":
                return Level.FATAL;
            case "off":
                return Level.OFF;
            case "warn":
                return Level.WARN;
            case "trace":
                return Level.TRACE;
            default:
                return Level.INFO;
        }
    }

    /**
     * Method for sending a mail with the specified subject, message and optionally attach the current log file
     * 
     * @param subject the subject of the mail
     * @param message the message of the mail
     * @param attachLog attach or not current log file
     * @throws UnknownHostException inherited from MailClient.sendMail(Mail)
     * @throws IOException inherited from MailClient.sendMail(Mail)
     * @throws ClassNotFoundException inherited from MailClient.sendMail(Mail)
     * @see MailClient#sendMail(ar.com.zir.mail.api.Mail) 
     */
    public void sendMail(String subject, String message, boolean attachLog) throws UnknownHostException, IOException, ClassNotFoundException {
        File attach = null;
        if (attachLog) {
            attach = new File(logName + ".log");
            FileUtils.copyFile(new File(logFile), attach);
        }
        
        if (client == null)
            client = new MailClient(props.getProperty("mail.server.host"), Integer.parseInt(props.getProperty("mail.server.port")));
        Mail mail = new Mail();
        mail.setSender(props.getProperty("mail.sender"));
        mail.setSubject(subject);
        mail.setMessage(message + System.getProperty("line.separator"));
        if (attachLog)
            mail.addAttachment(attach);
        int i = 0;
        while (true) {
            i++;
            String recipient = props.getProperty("mail.recipient." + i);
            if (recipient != null)
                mail.addRecipient(recipient);
            else
                break;
        }
        client.sendMail(mail);
        if (attachLog)
            attach.deleteOnExit();
    }
}
