package com.sehal.model.services;

import com.sehal.model.Good;

public interface GoodServiceInterface {
	public Good finndByID(int G_ID);
	public void insert(Good good);

}
