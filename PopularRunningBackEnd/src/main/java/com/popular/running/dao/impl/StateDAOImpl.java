package com.popular.running.dao.impl;

import org.springframework.stereotype.Repository;

import com.popular.running.dao.BaseDAO;
import com.popular.running.dao.StateDAO;
import com.popular.running.model.State;

/**
 * Implements the data access methods for State persistence
 *
 * @author scastros
 */

@Repository( "stateDao" )
public class StateDAOImpl extends BaseDAO<State, Long> implements StateDAO {

}

