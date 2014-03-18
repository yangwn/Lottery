package org.china.lottery.bruce.order3.storage.datasource;

import org.china.lottery.bruce.order3.model.GroupTableData;
import org.china.lottery.bruce.order3.model.PageGroupTableData;

public interface SequnceDataTable {

	public GroupTableData[][] getAllofGroupTableData();
	
	public PageGroupTableData[][] getAllofPageGroupTableData();

}
