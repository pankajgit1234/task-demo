package com.glaucus.task.dto;

/*
 * @author pankaj This DTO works as Model class for number increment task .
 */

import java.io.Serializable;

public class NumberDto implements Serializable {

	private static final long serialVersionUID = 5058846541881527259L;

	private Integer id;
	private Integer value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
