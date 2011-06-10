package jp.ne.hatena.syoko_sasaki;

public enum Blood {

	A("A型"), B("B型"), O("O型"), AB("AB型"), OTHER("不明");

	String name;

	Blood(String name){
		this.name = name;
	}

	public String getId() {
		return name();
	}

	public String getName() {
		return this.name;
	}

}
