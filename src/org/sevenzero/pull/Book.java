package org.sevenzero.pull;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-11-12
   *
 */
public class Book {
	
	private String id;
	private Integer id2;
	private String name;
	private float price;
	
	public Book() {}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
