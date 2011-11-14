package com.popular.running.dao.impl;

import org.springframework.stereotype.Repository;

import com.popular.running.dao.BaseDAO;
import com.popular.running.dao.RunningEventDAO;
import com.popular.running.model.RunningEvent;

/**
 * Implements the data access methods for RunningEvent persistence
 *
 * @author scastros
 */

@Repository( "runningEventDao" )
public class RunningEventDAOImpl extends BaseDAO<RunningEvent, Long> implements RunningEventDAO {

}

