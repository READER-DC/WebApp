package com.sehal.model;

import java.util.Date;

public class Sale {
	private Date DATE_DOC;
	private int FK_K_ID;
	private int FK_G_ID;
	private int QTY;
	private boolean IS_PICKUP;
	private boolean IS_WHOLESALE;

	public Sale() {
	}

	public Date getDATE_DOC() {
		return DATE_DOC;
	}
	public void setDATE_DOC(Date dATE_DOC) {
		DATE_DOC = dATE_DOC;
	}
	public int getFK_K_ID() {
		return FK_K_ID;
	}
	public void setFK_K_ID(int fK_K_ID) {
		FK_K_ID = fK_K_ID;
	}
	public int getFK_G_ID() {
		return FK_G_ID;
	}
	public void setFK_G_ID(int fK_G_ID) {
		FK_G_ID = fK_G_ID;
	}
	public int getQTY() {
		return QTY;
	}
	public void setQTY(int qTY) {
		QTY = qTY;
	}
	public boolean isIS_PICKUP() {
		return IS_PICKUP;
	}
	public void setIS_PICKUP(boolean iS_PICKUP) {
		IS_PICKUP = iS_PICKUP;
	}
	public boolean isIS_WHOLESALE() {
		return IS_WHOLESALE;
	}
	public void setIS_WHOLESALE(boolean iS_WHOLESALE) {
		IS_WHOLESALE = iS_WHOLESALE;
	}

}
