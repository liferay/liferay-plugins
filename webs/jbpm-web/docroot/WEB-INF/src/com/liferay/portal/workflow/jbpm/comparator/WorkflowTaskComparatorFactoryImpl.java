/*
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.workflow.jbpm.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskComparatorFactory;

/**
 * <a href="WorkflowTaskComparatorFactoryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowTaskComparatorFactoryImpl
	implements WorkflowTaskComparatorFactory {

	public OrderByComparator getCompletionDateComparator(boolean ascending) {
		return new WorkflowTaskCompletionDateComparator(ascending);

	}

	public OrderByComparator getCreateDateComparator(boolean ascending) {
		return new WorkflowTaskCreateDateComparator(ascending);

	}

	public OrderByComparator getDueDateComparator(boolean ascending) {
		return new WorkflowTaskDueDateComparator(ascending);

	}

	public OrderByComparator getTaskNameComparator(boolean ascending) {
		return new WorkflowTaskNameComparator(ascending);

	}

	public OrderByComparator getUserIdComparator(boolean ascending) {
		return new WorkflowTaskUserIdComparator(ascending);

	}
}