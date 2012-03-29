/** * Copyright 2009 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sakaiproject.clogdashboard;

import org.apache.log4j.Logger;

import org.sakaiproject.clog.api.ClogManager;
import org.sakaiproject.dash.logic.DashboardLogic;
import org.sakaiproject.user.api.UserDirectoryService;

public class ClogDashboardIntegration {

	private Logger logger = Logger.getLogger(ClogDashboardIntegration.class);

    private DashboardLogic dashboardLogic;
    public void setDashboardLogic(DashboardLogic dashboardLogic) {
        this.dashboardLogic = dashboardLogic;
    }

    private ClogManager clogManager;
    public void setClogManager(ClogManager clogManager) {
        this.clogManager = clogManager;
    }

    private UserDirectoryService userDirectoryService;
    public void setUserDirectoryService(UserDirectoryService userDirectoryService) {
        this.userDirectoryService = userDirectoryService;
    }

	public void init() {

		if (logger.isDebugEnabled())
			logger.debug("init()");
		
		ClogPostEntityType fet = new ClogPostEntityType();
		fet.setClogManager(clogManager);
		fet.setUserDirectoryService(userDirectoryService);
		dashboardLogic.registerEntityType(fet);

		ClogNewPostEventProcessor fproc = new ClogNewPostEventProcessor();
		fproc.setClogManager(clogManager);
		fproc.setDashboardLogic(dashboardLogic);
		fproc.init();
		dashboardLogic.registerEventProcessor(fproc);
	}
}
