package com.sehal.report1;

import java.util.ArrayList;
import java.util.List;

import com.sehal.model.Good;
import com.sehal.model.Sale;

public class ReportSales {
	// private LocalDate startDate;
	// private LocalDate endDate;
	int sale = 0;

	public int getSale() {
		for (Sale sales : Sale.sales)
			sale += sales.getQTY();
		return sale;
	}
	public int getSaleByKontragent(String kId) {
		List<Sale> salesByKontragentId = getSalesByKontragentId(kId);
		for (Sale sales : salesByKontragentId)
			sale += sales.getQTY();
		return sale;
	}

	public int getSaleCategory(String categoryId) {
		List<Sale> salesByCategoryId = getSalesByKontragentId(categoryId);
		for (Sale sales : salesByCategoryId)
			sale += sales.getQTY();
		return sale;
	}

	public int getSaleKontragentAndCategory(String kId, String categoryId) {
		List<Sale> salesKontragentAndCategory = getSalesByKontragentId(
				categoryId);
		for (Sale sales : salesKontragentAndCategory)
			sale += sales.getQTY();
		return sale;
	}

	public List<Sale> getSalesByKontragentId(String kId) {
		int id = Integer.valueOf(kId);
		List<Sale> salesByKontragentId = new ArrayList<>();
		if (id == 0) {
			salesByKontragentId = Sale.sales;
		} else {
			for (Sale sale : Sale.sales) {
				if (sale.getFK_K_ID() == id) {
					salesByKontragentId.add(sale);
				}
			}
		}

		return salesByKontragentId;
	}

	public List<Sale> getSalesByCategoryId(String categoryId) {
		int id = Integer.valueOf(categoryId);
		List<Sale> salesByCategoryId = new ArrayList<>();

		if (id == 0) {
			salesByCategoryId = Sale.sales;
		} else {
			for (Good good : Good.goods) {
				if (good.getCAT_ID() == id) {
					for (Sale sale : Sale.sales) {
						if (sale.getFK_G_ID() == good.getG_ID()) {
							salesByCategoryId.add(sale);
						}
					}
				}
			}
		}

		return salesByCategoryId;
	}

	public List<Sale> getSalesByKontragentIdAndCategoryId(String kId,
			String categoryId) {
		List<Sale> salesByCategoryIdAndKontragentId = new ArrayList<>();
		List<Sale> salesByKontragentId = getSalesByKontragentId(kId);
		List<Sale> salesByCategoryId = getSalesByCategoryId(categoryId);
		for (Sale saleKontragent : salesByKontragentId) {
			for (Sale saleCategory : salesByCategoryId) {
				if (saleKontragent.getFK_K_ID() == saleCategory.getFK_K_ID()) {
					salesByCategoryIdAndKontragentId.add(saleKontragent);
				}
			}
		}

		return salesByCategoryIdAndKontragentId;
	}

}
