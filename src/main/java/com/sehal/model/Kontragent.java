package com.sehal.model;

import java.util.ArrayList;
import java.util.List;

public class Kontragent {

	private int K_ID;
	private String K_NAME;
	private int CH_ID;
	private int BG_CH_ID;
	private String BG_CH_NAME;
	private int BG_FMT_ID;
	private String BG_FMT_NAME;
	private int BG_REG_ID;
	private String BG_REG_NAME;
	
	public static List<Kontragent> kontragents = new ArrayList<>();

	public Kontragent() {
		super();
	}
	
	public void addKontragent() {
		kontragents.add(this);
	}
	
	public List<Kontragent> getAll(){
		return kontragents;
	}
	
	public String toString() {
		return K_NAME;
	}
	
	public int getK_ID() {
		return K_ID;
	}
	public void setK_ID(int k_ID) {
		K_ID = k_ID;
	}
	public String getK_NAME() {
		return K_NAME;
	}
	public void setK_NAME(String k_NAME) {
		K_NAME = k_NAME;
	}
	public int getCH_ID() {
		return CH_ID;
	}
	public void setCH_ID(int cH_ID) {
		CH_ID = cH_ID;
	}
	public int getBG_CH_ID() {
		return BG_CH_ID;
	}
	public void setBG_CH_ID(int bG_CH_ID) {
		BG_CH_ID = bG_CH_ID;
	}
	public String getBG_CH_NAME() {
		return BG_CH_NAME;
	}
	public void setBG_CH_NAME(String bG_CH_NAME) {
		BG_CH_NAME = bG_CH_NAME;
	}
	public int getBG_FMT_ID() {
		return BG_FMT_ID;
	}
	public void setBG_FMT_ID(int bG_FMT_ID) {
		BG_FMT_ID = bG_FMT_ID;
	}
	public String getBG_FMT_NAME() {
		return BG_FMT_NAME;
	}
	public void setBG_FMT_NAME(String bG_FMT_NAME) {
		BG_FMT_NAME = bG_FMT_NAME;
	}
	public int getBG_REG_ID() {
		return BG_REG_ID;
	}
	public void setBG_REG_ID(int bG_REG_ID) {
		BG_REG_ID = bG_REG_ID;
	}
	public String getBG_REG_NAME() {
		return BG_REG_NAME;
	}
	public void setBG_REG_NAME(String bG_REG_NAME) {
		BG_REG_NAME = bG_REG_NAME;
	}

}
