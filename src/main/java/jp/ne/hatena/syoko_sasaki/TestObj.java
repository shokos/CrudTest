package jp.ne.hatena.syoko_sasaki;

import java.io.Serializable;

public class TestObj implements Serializable {

	private static final long serialVersionUID = 1L;

	String name;
	Integer old;
	Blood blood;
	/**
	 * @param name
	 * @param old
	 * @param blood
	 */

	public TestObj() {
		super();
	}

	public TestObj(String name, Integer old, Blood blood) {
		super();
		this.name = name;
		this.old = old;
		this.blood = blood;
	}


}
