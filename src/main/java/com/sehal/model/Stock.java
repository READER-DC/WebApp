package com.sehal.model;

import java.sql.Date;

public class Stock {

	private Date DATE_STOCK;
	private int FK_CFO_ID;
	private int FK_G_ID;
	private int FK_WH_DOG_ID;
	private int FK_WH_K_ID;
	private int FK_TYPE_DOG_ID;
	private int STATUS_GOODS_ID;
	private int FK_LOT_ID;
	private Date DATE_LOT;
	private int QTY;
	private int QTY_REZERV;
	private double S_COST;
	private double S_COST_NONDS;
	private double S_COST_VAL;
	private double S_COST_NONDS_VAL;
	private double S_COSTIN_VAL;
	private double S_COSTIN_NONDS_VAL;

	public Stock() {
		super();
	}

	public Date getDATE_STOCK() {
		return DATE_STOCK;
	}

	public void setDATE_STOCK(Date dATE_STOCK) {
		DATE_STOCK = dATE_STOCK;
	}

	public int getFK_CFO_ID() {
		return FK_CFO_ID;
	}

	public void setFK_CFO_ID(int fK_CFO_ID) {
		FK_CFO_ID = fK_CFO_ID;
	}

	public int getFK_G_ID() {
		return FK_G_ID;
	}

	public void setFK_G_ID(int fK_G_ID) {
		FK_G_ID = fK_G_ID;
	}

	public int getFK_WH_DOG_ID() {
		return FK_WH_DOG_ID;
	}

	public void setFK_WH_DOG_ID(int fK_WH_DOG_ID) {
		FK_WH_DOG_ID = fK_WH_DOG_ID;
	}

	public int getFK_WH_K_ID() {
		return FK_WH_K_ID;
	}

	public void setFK_WH_K_ID(int fK_WH_K_ID) {
		FK_WH_K_ID = fK_WH_K_ID;
	}

	public int getFK_TYPE_DOG_ID() {
		return FK_TYPE_DOG_ID;
	}

	public void setFK_TYPE_DOG_ID(int fK_TYPE_DOG_ID) {
		FK_TYPE_DOG_ID = fK_TYPE_DOG_ID;
	}

	public int getSTATUS_GOODS_ID() {
		return STATUS_GOODS_ID;
	}

	public void setSTATUS_GOODS_ID(int sTATUS_GOODS_ID) {
		STATUS_GOODS_ID = sTATUS_GOODS_ID;
	}

	public int getFK_LOT_ID() {
		return FK_LOT_ID;
	}

	public void setFK_LOT_ID(int fK_LOT_ID) {
		FK_LOT_ID = fK_LOT_ID;
	}

	public Date getDATE_LOT() {
		return DATE_LOT;
	}

	public void setDATE_LOT(Date dATE_LOT) {
		DATE_LOT = dATE_LOT;
	}

	public int getQTY() {
		return QTY;
	}

	public void setQTY(int qTY) {
		QTY = qTY;
	}

	public int getQTY_REZERV() {
		return QTY_REZERV;
	}

	public void setQTY_REZERV(int qTY_REZERV) {
		QTY_REZERV = qTY_REZERV;
	}

	public double getS_COST() {
		return S_COST;
	}

	public void setS_COST(double s_COST) {
		S_COST = s_COST;
	}

	public double getS_COST_NONDS() {
		return S_COST_NONDS;
	}

	public void setS_COST_NONDS(double s_COST_NONDS) {
		S_COST_NONDS = s_COST_NONDS;
	}

	public double getS_COST_VAL() {
		return S_COST_VAL;
	}

	public void setS_COST_VAL(double s_COST_VAL) {
		S_COST_VAL = s_COST_VAL;
	}

	public double getS_COST_NONDS_VAL() {
		return S_COST_NONDS_VAL;
	}

	public void setS_COST_NONDS_VAL(double s_COST_NONDS_VAL) {
		S_COST_NONDS_VAL = s_COST_NONDS_VAL;
	}

	public double getS_COSTIN_VAL() {
		return S_COSTIN_VAL;
	}

	public void setS_COSTIN_VAL(double s_COSTIN_VAL) {
		S_COSTIN_VAL = s_COSTIN_VAL;
	}

	public double getS_COSTIN_NONDS_VAL() {
		return S_COSTIN_NONDS_VAL;
	}

	public void setS_COSTIN_NONDS_VAL(double s_COSTIN_NONDS_VAL) {
		S_COSTIN_NONDS_VAL = s_COSTIN_NONDS_VAL;
	}

}
