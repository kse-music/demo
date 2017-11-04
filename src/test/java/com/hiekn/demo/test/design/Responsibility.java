package com.hiekn.demo.test.design;

abstract class Logger {
	protected Logger nextLogger;

	public void setNextLogger(Logger nextLogger){
		this.nextLogger = nextLogger;
	}

	public void logMessage(String message){
		log(message);
		if(nextLogger != null){
			nextLogger.logMessage(message);
		}
	}
	abstract protected void log(String message);  
}

class ConsoleLogger extends Logger {
	public ConsoleLogger(){
	}
	@Override
	protected void log(String message) {    
		System.out.println("Console::Logger: " + message);
	}
}

class EMailLogger extends Logger {
	public EMailLogger(){
	}
	@Override
	protected void log(String message) {    
		System.out.println("EMail::Logger: " + message);
	}
}
class FileLogger extends Logger {
	public FileLogger(){
	}
	@Override
	protected void log(String message) {    
		System.out.println("File::Logger: " + message);
	}
}

public class Responsibility {  
	
	private static Logger getChainOfLoggers(){
		Logger emailLogger = new EMailLogger();
		Logger fileLogger = new FileLogger();
		Logger consoleLogger = new ConsoleLogger();
		emailLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);
		return emailLogger;  
	}
	
	public static void main(String[] args) {
		Logger loggerChain = getChainOfLoggers();
		loggerChain.logMessage("Null pointer");
		loggerChain.logMessage("Array Index Out of Bound");
		loggerChain.logMessage("Illegal Parameters");
	}
	
}
