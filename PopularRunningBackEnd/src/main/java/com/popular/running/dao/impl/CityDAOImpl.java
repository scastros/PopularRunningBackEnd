package com.popular.running.dao.impl;

import org.springframework.stereotype.Repository;

import com.popular.running.dao.BaseDAO;
import com.popular.running.dao.CityDAO;
import com.popular.running.model.City;

/**
 * Implements the data access methods for City persistence
 *
 * @author scastros
 */

@Repository( "cityDao" )
public class CityDAOImpl extends BaseDAO<City, Long> implements CityDAO {

}

