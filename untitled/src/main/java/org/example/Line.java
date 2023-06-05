package org.example;

public class Line {
    private int id;
    private String name;
    private String town;
    private String continent;

    private String towncode;

    private String continetCode;

    private Double latitude;
    private Double longitude;
    private Integer code;
    private Double temperature;
    private String regionCode;
    private String adress;
    private String complexType;
    private String owned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getTowncode() {
        return towncode;
    }

    public void setTowncode(String towncode) {
        this.towncode = towncode;
    }

    public String getContinetCode() {
        return continetCode;
    }

    public void setContinetCode(String continetCode) {
        this.continetCode = continetCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getComplexType() {
        return complexType;
    }

    public void setComplexType(String complexType) {
        this.complexType = complexType;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        this.owned = owned;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", town='" + town + '\'' +
                ", continent='" + continent + '\'' +
                ", towncode='" + towncode + '\'' +
                ", continetCode='" + continetCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", code=" + code +
                ", temperature=" + temperature +
                ", regionCode='" + regionCode + '\'' +
                ", adress='" + adress + '\'' +
                ", complexType='" + complexType + '\'' +
                ", owned='" + owned + '\'' +
                '}';
    }
}
