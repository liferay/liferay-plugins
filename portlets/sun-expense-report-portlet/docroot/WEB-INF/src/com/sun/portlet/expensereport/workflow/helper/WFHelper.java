/** Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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
/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.sun.portlet.expensereport.workflow.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sun.saw.Workflow;
import com.sun.saw.WorkflowException;
import com.sun.saw.WorkflowFactory;
import com.sun.saw.vo.CheckoutTaskVO;
import com.sun.saw.vo.CompleteTaskVO;
import com.sun.saw.vo.FilterTaskVO;
import com.sun.saw.vo.OutputVO;
import com.sun.saw.vo.SaveTaskVO;
import com.sun.saw.vo.TaskVO;

/**
 * <a href="WFHelper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Nithya Subramanian
 *
 */

public class WFHelper {

    public OutputVO checkOutTask(String userId, TaskVO task) 
        throws com.sun.saw.WorkflowException {
        
        Workflow workflowManager = WFHelper.getWorkflowImpl();
        CheckoutTaskVO checkoutTaskVO = new CheckoutTaskVO();
        List taskIdList = new ArrayList();
        taskIdList.add(task.getId());
        checkoutTaskVO.setTaskIdList(taskIdList);
        checkoutTaskVO.setUserId(userId);
        return workflowManager.checkoutTasks(checkoutTaskVO);

    }
    
    
    public com.sun.saw.vo.OutputVO completeTasks(String userId, TaskVO task) 
        throws WorkflowException {

        Workflow workflowManager = WFHelper.getWorkflowImpl();
        List taskIdList = new ArrayList();
        taskIdList.add(task.getId());
        CompleteTaskVO completeTaskVO = new CompleteTaskVO();
        completeTaskVO.setTaskIdList(taskIdList);
        completeTaskVO.setUserId(userId);
        return workflowManager.completeTasks(completeTaskVO);

    }

    public List getPendingTasks(String userRole) throws WorkflowException {
        try {
            Workflow workflowManager = WFHelper.getWorkflowImpl();
            FilterTaskVO filterVO = new FilterTaskVO();
            Map statusAttributeMap = new HashMap();
            statusAttributeMap.put("FLEX_STRING8", "Pending");
            filterVO.setCustomAttributesMap(statusAttributeMap);
            filterVO.setUserId(userRole);
            filterVO.setStartRecord(1);
            filterVO.setPageNumber(1);
            filterVO.setRecordsPerPage(5);

            OutputVO output = workflowManager.getTasks(filterVO);
            return output.getTaskVOList();
        } catch (Exception e) {
            throw new WorkflowException("Exception while getting Pending tasks",
                e.getMessage());

        }

    }

    public OutputVO saveTask(
        String userRole, TaskVO task, String output, Map customAttributesMap) 
            throws com.sun.saw.WorkflowException {
        
        Workflow workflowManager = WFHelper.getWorkflowImpl();
        SaveTaskVO saveTaskVO = new SaveTaskVO();
        List taskIdList = new ArrayList();
        taskIdList.add(task.getId());
        saveTaskVO.setTaskIdList(taskIdList);
        saveTaskVO.setUserId(userRole);
        saveTaskVO.setOutput(output);
        saveTaskVO.setCustomAttributesMap(customAttributesMap);
        return workflowManager.saveTasks(saveTaskVO);

    }
    
    private static Workflow getWorkflowImpl() throws WorkflowException {
        Workflow workflow = null;
        WorkflowFactory workflowFactory = WorkflowFactory.getInstance();
        workflow = workflowFactory.getWorkflowInstance();
        return workflow;
    }

    
}
