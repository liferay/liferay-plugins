package com.sun.portlet.expensereport;

import com.sun.saw.Workflow;
import com.sun.saw.WorkflowException;
import com.sun.saw.WorkflowFactory;
import com.sun.saw.vo.CheckoutTaskVO;
import com.sun.saw.vo.CompleteTaskVO;
import com.sun.saw.vo.FilterTaskVO;
import com.sun.saw.vo.OutputVO;
import com.sun.saw.vo.SaveTaskVO;
import com.sun.saw.vo.TaskVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WFHelper {

    public static Workflow getWorkflowImpl() throws WorkflowException {
        Workflow workflow = null;
        WorkflowFactory workflowFactory = WorkflowFactory.getInstance();
        workflow = workflowFactory.getWorkflowInstance();
        return workflow;
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
            throw new WorkflowException("Exception while getting Pending tasks", e.getMessage());

        }

    }

    public OutputVO checkOutTask(String userId, TaskVO task) throws com.sun.saw.WorkflowException {
        Workflow workflowManager = WFHelper.getWorkflowImpl();
        CheckoutTaskVO checkoutTaskVO = new CheckoutTaskVO();
        List taskIdList = new ArrayList();
        taskIdList.add(task.getId());
        checkoutTaskVO.setTaskIdList(taskIdList);
        checkoutTaskVO.setUserId(userId);
        return workflowManager.checkoutTasks(checkoutTaskVO);

    }

    public OutputVO saveTask(String userRole, TaskVO task, String output, Map customAttributesMap) throws com.sun.saw.WorkflowException {
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

    public com.sun.saw.vo.OutputVO completeTasks(String userId, TaskVO task) throws com.sun.saw.WorkflowException {

        Workflow workflowManager = WFHelper.getWorkflowImpl();
        List taskIdList = new ArrayList();
        taskIdList.add(task.getId());
        CompleteTaskVO completeTaskVO = new CompleteTaskVO();
        completeTaskVO.setTaskIdList(taskIdList);
        completeTaskVO.setUserId(userId);
        return workflowManager.completeTasks(completeTaskVO);

    }
}
