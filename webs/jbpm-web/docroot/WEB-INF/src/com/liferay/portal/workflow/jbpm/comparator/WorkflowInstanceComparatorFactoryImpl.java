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
import com.liferay.portal.kernel.workflow.comparator.WorkflowInstanceComparatorFactory;

/**
 * <a href="WorkflowInstanceComparatorFactoryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowInstanceComparatorFactoryImpl
	implements WorkflowInstanceComparatorFactory {

	public OrderByComparator getInstanceStateComparator(boolean ascending) {
		return new WorkflowInstanceStateComparator(ascending);

	}

	public OrderByComparator getInstanceStartDateComparator(boolean ascending) {
		return new WorkflowInstanceStartDateComparator(ascending);

	}

	public OrderByComparator getInstanceEndDateComparator(boolean ascending) {
		return new WorkflowInstanceEndDateComparator(ascending);

	}
}