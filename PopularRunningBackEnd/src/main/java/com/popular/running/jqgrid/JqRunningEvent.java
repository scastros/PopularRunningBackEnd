package com.popular.running.jqgrid;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "items" })
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "jqItems")
public class JqRunningEvent<T> {

	protected ArrayList<T> items;

	@XmlElement(name = "items")
	public ArrayList<T> getItems() {
		if (items == null) {
			items = new ArrayList<T>();
		}
		return items;
	}

	public void setItems(ArrayList<T> newList) {

		items = newList;

	}

}
