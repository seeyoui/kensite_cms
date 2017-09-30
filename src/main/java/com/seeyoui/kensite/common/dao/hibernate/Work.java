package com.seeyoui.kensite.common.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;

public interface Work {
	public void execute(Connection connection) throws SQLException;
}
