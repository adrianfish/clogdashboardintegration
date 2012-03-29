package org.sakaiproject.clogdashboard;

import org.sakaiproject.clog.api.ClogManager;
import org.sakaiproject.dash.listener.EventProcessor;
import org.sakaiproject.dash.logic.DashboardLogic;

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
