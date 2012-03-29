package org.sakaiproject.clogdashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sakaiproject.clog.api.ClogFunctions;
import org.sakaiproject.clog.api.ClogManager;
import org.sakaiproject.clog.api.datamodel.Post;
import org.sakaiproject.dash.entity.EntityType;
import org.sakaiproject.site.cover.SiteService;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.util.ResourceLoader;

public abstract class ClogDashboardEntityType implements EntityType{
	
	protected ClogManager clogManager;
	public void setClogManager(ClogManager clogManager) {
		this.clogManager = clogManager;
	}
	
	protected UserDirectoryService userDirectoryService;
	public void setUserDirectoryService(UserDirectoryService userDirectoryService) {
		this.userDirectoryService = userDirectoryService;
	}

	public final String getIconUrl(String subtype) {
        return "/library/image/silk/book_edit.png";
	}

	public final List<List<String>> getOrder(String entityReference, String localeCode) {
		List<List<String>> order = new ArrayList<List<String>>();
		List<String> section0 = new ArrayList<String>();
        section0.add(VALUE_TITLE);
        order.add(section0);
        List<String> section1 = new ArrayList<String>();
        section1.add("clog_metadata-label");
        order.add(section1);
        List<String> section3 = new ArrayList<String>();
        section3.add(VALUE_MORE_INFO);
        order.add(section3);
		return order;
	}

	public final Map<String, String> getProperties(String entityReference, String localeCode) {
		ResourceLoader rl = new ResourceLoader("dashboard");
		Map<String,String> props = new HashMap<String,String>();
		props.put("clog_metadata-label", rl.getString("clog.metadata"));
		return props;
	}

	/**
	 * Implement this to map Sakai events to dashboard display strings
	 */
	public final String getString(String key, String dflt) {
		ResourceLoader rl = new ResourceLoader("dashboard");
		if(ClogManager.CLOG_POST_CREATED.equals(key)) {
			return rl.getString("new_blog_post");
		}
		return null;
	}

	public final boolean isAvailable(String arg0) {
		return true;
	}

	public List<String> getUsersWithAccess(String reference) {
		List<String> users = new ArrayList<String>();
		String postId = reference.substring(reference.lastIndexOf("/") + 1);
		try {
			Post post = clogManager.getPostHeader(postId);
			if(post.isPrivate()) {
				users.add(post.getCreatorId());
				return users;
			}
			String siteId = post.getSiteId();
			users.addAll(SiteService.getSite(siteId).getUsers());
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isUserPermitted(String arg0, String arg1, String arg2) {
		return true;
	}
}
