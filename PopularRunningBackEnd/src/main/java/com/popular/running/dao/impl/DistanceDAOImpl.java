package com.popular.running.dao.impl;

import org.springframework.stereotype.Repository;

import com.popular.running.dao.BaseDAO;
import com.popular.running.dao.DistanceDAO;
import com.popular.running.model.Distance;

/**
 * Implements the data access methods for Distance persistence
 *
 * @author scastros
 */

@Repository( "distanceDao" )
public class DistanceDAOImpl extends BaseDAO<Distance, Long> implements DistanceDAO {

}

