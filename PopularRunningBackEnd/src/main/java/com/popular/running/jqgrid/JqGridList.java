package com.popular.running.jqgrid;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "root", "page", "total", "records" })
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "root")

/**
 * Default JqGrid JSONReader
 * jsonReader : {
 *   root: "rows",
 *   page: "page",
 *   total: "total",
 *   records: "records",
 *   repeatitems: true,
 *   cell: "cell",
 *   id: "id",
 *   userdata: "userdata",
 *   subgrid: {root:"rows", 
 *      repeatitems: true, 
 *      cell:"cell"
 *   }
 * }
 * 
 * 
 */
public class JqGridList<T> {

	protected ArrayList<T> items;
	private int page;
	private int total;
	private int records;

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
	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	@XmlElement(name = "page")
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	@XmlElement(name = "total")
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the records
	 */
	public int getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	@XmlElement(name = "records")
	public void setRecords(int records) {
		this.records = records;
	}
}
