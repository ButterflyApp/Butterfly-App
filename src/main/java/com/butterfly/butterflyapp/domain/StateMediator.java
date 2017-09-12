package com.butterfly.butterflyapp.domain;

public class StateMediator {

	private long id;
	private String name;
	private int districtCount;
	private long locationCount;
	private byte[] image1;
	private String image1ContentType;
	private byte[] image2;
	private String image2ContentType;
	private byte[] image3;
	private String image3ContentType;
	private byte[] image4;
	private String image4ContentType;
	private byte[] image5;
	private String image5ContentType;

	public String getImage1ContentType() {
		return image1ContentType;
	}

	public void setImage1ContentType(String image1ContentType) {
		this.image1ContentType = image1ContentType;
	}

	public String getImage2ContentType() {
		return image2ContentType;
	}

	public void setImage2ContentType(String image2ContentType) {
		this.image2ContentType = image2ContentType;
	}

	public String getImage3ContentType() {
		return image3ContentType;
	}

	public void setImage3ContentType(String image3ContentType) {
		this.image3ContentType = image3ContentType;
	}

	public String getImage4ContentType() {
		return image4ContentType;
	}

	public void setImage4ContentType(String image4ContentType) {
		this.image4ContentType = image4ContentType;
	}

	public String getImage5ContentType() {
		return image5ContentType;
	}

	public void setImage5ContentType(String image5ContentType) {
		this.image5ContentType = image5ContentType;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public byte[] getImage4() {
		return image4;
	}

	public void setImage4(byte[] image4) {
		this.image4 = image4;
	}

	public byte[] getImage5() {
		return image5;
	}

	public void setImage5(byte[] image5) {
		this.image5 = image5;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistrictCount() {
		return districtCount;
	}

	public void setDistrictCount(int districtCount) {
		this.districtCount = districtCount;
	}

	public long getLocationCount() {
		return locationCount;
	}

	public void setLocationCount(long locationCount) {
		this.locationCount = locationCount;
	}

}
