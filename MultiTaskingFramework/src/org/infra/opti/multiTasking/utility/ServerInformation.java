package org.infra.opti.multiTasking.utility;

public class ServerInformation {
	private String cpuId;
	private String serverName;
	private int numberOfProcessors;
	private static ServerInformation serverInfo;
	
	private ServerInformation(){
		numberOfProcessors = Runtime.getRuntime().availableProcessors();
		serverName = "KuldeepJS";
		cpuId = "232s3ASSdsA1";
	}
	
	public static synchronized ServerInformation getServerInformation(){
		if(serverInfo == null){
			serverInfo = new ServerInformation();
		}
		return serverInfo;
	}
	
	public String getCpuId() {
		return cpuId;
	}
	
	public int getMaxNumberOfThreads(boolean isIOInvolved, long waitTime, long serviceTime) {
		if(!isIOInvolved)
			return numberOfProcessors + 1;
		else{
			return numberOfProcessors * (1 + (int)(waitTime/serviceTime));
		}
	}
	
	public String getServerName() {
		return serverName;
	}
	public int getNumberOfProcessors() {
		return numberOfProcessors;
	}

	
}
