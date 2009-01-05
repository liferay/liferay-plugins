/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wol.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.AggregateClassLoader;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.wol.service.WallEntryLocalServiceUtil;

/**
 * <a href="GroupListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Ganesh Ram
 *
 */
public class GroupListener extends BaseModelListener {

	public void onBeforeRemove(BaseModel model) throws ModelListenerException {
		ClassLoader currentCL = null;
		try {
			Group group = (Group)model;
			
			// LPS-803 - setting the ClassLoader to WOL-Portlet's CL and 
			//restoring the original CL in the finally block
			
			currentCL = _getCurrentClassLoader();
			ClassLoader wolCL = _getWOLPortletClassLoader();
			_setClassLoader(wolCL);

			if (group.isUser()) {
				WallEntryLocalServiceUtil.deleteWallEntries(group.getGroupId());
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}finally{
			_setClassLoader(currentCL);
		}
	}
	
	private ClassLoader _getCurrentClassLoader(){
		return Thread.currentThread().getContextClassLoader();
		
	}
	private ClassLoader _getWOLPortletClassLoader(){
		return GroupListener.class.getClassLoader();
	}
	private void _setClassLoader(ClassLoader cl){
		Thread.currentThread().setContextClassLoader(cl);
	}
	

}