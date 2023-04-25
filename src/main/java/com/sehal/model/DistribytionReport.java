package com.sehal.model;

import java.util.ArrayList;
import java.util.List;

public class DistribytionReport {
	private int k_id;
	private int g_id;
	private int g_id_second;
	private String format;
	private int nd_plan;
	private int k_fact;
	private int minss;
	private double maxs;
	private int adr;
	private int bzx;
	private int qty_stock;
	
	public static List<DistribytionReport> distribytionLines = new ArrayList<>();
	
	public void add() {
		distribytionLines.add(this);
	}
	
	public DistribytionReport() {
		super();
	}
	public int getK_id() {
		return k_id;
	}
	public void setK_id(int k_id) {
		this.k_id = k_id;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public int getG_id_second() {
		return g_id_second;
	}
	public void setG_id_second(int g_id_second) {
		this.g_id_second = g_id_second;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public int getNd_plan() {
		return nd_plan;
	}
	public void setNd_plan(int nd_plan) {
		this.nd_plan = nd_plan;
	}
	public int getK_fact() {
		return k_fact;
	}
	public void setK_fact(int k_fact) {
		this.k_fact = k_fact;
	}
	public int getMinss() {
		return minss;
	}
	public void setMinss(int minss) {
		this.minss = minss;
	}
	public double getMaxs() {
		return maxs;
	}
	public void setMaxs(double maxs) {
		this.maxs = maxs;
	}
	public int getAdr() {
		return adr;
	}
	public void setAdr(int adr) {
		this.adr = adr;
	}
	public int getBzx() {
		return bzx;
	}
	public void setBzx(int bzx) {
		this.bzx = bzx;
	}
	public int getQty_stock() {
		return qty_stock;
	}
	public void setQty_stock(int qty_stock) {
		this.qty_stock = qty_stock;
	}

	@Override
	public String toString() {
		return "DistribytionReport [k_id=" + k_id + ", g_id=" + g_id + ", maxs="
				+ maxs + ", qty_stock=" + qty_stock + "]\n";
	}
	
	

}
