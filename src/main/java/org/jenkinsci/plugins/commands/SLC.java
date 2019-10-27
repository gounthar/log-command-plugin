package org.jenkinsci.plugins.commands;

import java.io.IOException;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;

public class SLC {
	
	public static String showLog(AbstractProject<?, ?> job, int buildNumber, int maxLines) throws WrongBuildNumberException {		
		AbstractBuild<?, ?> build = buildNumber > 0 ? job.getBuildByNumber(buildNumber) : job.getLastBuild();
		
		StringBuffer toRet = new StringBuffer();
		if(null != build)
		{		
			try{				
				for (String logLine : build.getLog(maxLines + 1)) {
					toRet.append(logLine + "\n");
				}				
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else{
			throw new WrongBuildNumberException();
		}
		return toRet.toString();
	}
}
