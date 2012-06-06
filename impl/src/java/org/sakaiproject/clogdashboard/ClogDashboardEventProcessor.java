package org.sakaiproject.clogdashboard;

import org.sakaiproject.clog.api.ClogManager;
import org.sakaiproject.dash.listener.EventProcessor;
import org.sakaiproject.dash.logic.DashboardLogic;

/**
 * Base class so we can get the dependencies in
 * 
 * @author Adrian Fish (adrian.r.fish@gmail.com)
 */
public abstract class ClogDashboardEventProcessor implements EventProcessor{
	
	protected DashboardLogic dashboardLogic;
	public void setDashboardLogic(DashboardLogic dashboardLogic) {
		this.dashboardLogic = dashboardLogic;
	}
	
	protected ClogManager clogManager;
	public void setClogManager(ClogManager clogManager) {
		this.clogManager = clogManager;
	}
}
