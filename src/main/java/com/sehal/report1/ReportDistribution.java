package com.sehal.report1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sehal.model.DistribytionReport;
import com.sehal.model.Good;

public class ReportDistribution {
	int kId;
	int categoryId;
	int subCategoryId;
	int groupId;
	int subGroupId;
	LocalDate dateStart;
	LocalDate dateEnd;

	double distributionByKId(String kIdRequest) {
		kId = Integer.valueOf(kIdRequest);
		double distribution = 0d;
		int nd_plan = 0;
		int nd_fact = 0;
		// int qty = 0;
		if (kId == 0) {
			for (DistribytionReport item : DistribytionReport.distribytionLines) {
				nd_plan += item.getNd_plan();
				nd_fact += item.getNd_fact();
				// qty += item.getQty_stock();
			}
			distribution = nd_fact / nd_plan * 100d;
		} else {
			for (DistribytionReport item : DistribytionReport.distribytionLines) {
				if (kId == item.getK_id()) {
					nd_plan += item.getNd_plan();
					nd_fact += item.getNd_fact();
				}
				distribution = nd_fact / nd_plan * 100d;
			}
		}

		return distribution;
	}

	double distributionByCategory(String categoryRequest) {
		categoryId = Integer.valueOf(categoryRequest);
		List<Good> goodsByCategory = new ArrayList<>();
		double distribution = 0d;
		int nd_plan = 0;
		int nd_fact = 0;

		if (categoryId != 0) {
			goodsByCategory.clear();
			for (Good item : Good.goods) {
				if (item.getCAT_ID() == categoryId) {
					goodsByCategory.add(item);
				}
			}
		} else {
			goodsByCategory = Good.categories;
		}

		for (Good item : goodsByCategory) {
			for (DistribytionReport distribytionReport : DistribytionReport.distribytionLines) {
				if (distribytionReport.getG_id() == item.getG_ID()) {
					nd_plan += distribytionReport.getNd_plan();
					nd_fact += distribytionReport.getNd_fact();
				}
			}
		}
		distribution = nd_fact / nd_plan * 100d;
		return distribution;
	}
	double distributionByGroup(String groupIdRequest) {
		groupId = Integer.valueOf(groupIdRequest);
		List<Good> goodsByGroup = new ArrayList<>();
		double distribution = 0d;
		int nd_plan = 0;
		int nd_fact = 0;
		if (groupId != 0) {
			goodsByGroup.clear();
			for (Good good : Good.goods) {
				if (good.getG_ID() == groupId) {
					goodsByGroup.add(good);
				}
			}
		} else {
			distributionByCategory("0");
		}
		for (Good good : goodsByGroup) {
			for (DistribytionReport distribytionReport : DistribytionReport.distribytionLines) {
				if (distribytionReport.getG_id() == good.getG_ID()) {
					nd_plan += distribytionReport.getNd_plan();
					nd_fact += distribytionReport.getNd_fact();
				}
			}
		}
		distribution = nd_fact / nd_plan * 100d;
		return distribution;
	}

	double distributionByKIdAndCategory(String kidRequest,
			String categoryRequest) {
		double distribution = 0d;
		int nd_plan = 0;
		int nd_fact = 0;
		int kId = Integer.valueOf(kidRequest);
		int categoryId = Integer.valueOf(categoryRequest);
		// List<Good> distributionsByKIdAndCategory = new ArrayList<>();
		for (Good good : Good.goods) {
			if (good.getCAT_ID() == categoryId) {
				for (DistribytionReport distribytionReport : DistribytionReport.distribytionLines) {
					if (distribytionReport.getK_id() == kId
							&& good.getG_ID() == distribytionReport.getG_id()) {
						nd_plan += distribytionReport.getNd_plan();
						nd_fact += distribytionReport.getNd_fact();
					}
				}
			}

		}
		distribution = nd_fact / nd_plan * 100d;
		return distribution;
	}
	double distributionByKIdAndGroup(String kidRequest, String groupRequest) {
		double distribution = 0d;
		int nd_plan = 0;
		int nd_fact = 0;
		int kId = Integer.valueOf(kidRequest);
		int groupId = Integer.valueOf(groupRequest);
		for (Good good : Good.goods) {
			if (good.getGR_ID() == groupId) {
				for (DistribytionReport distribytionReport : DistribytionReport.distribytionLines) {
					if (distribytionReport.getK_id() == kId
							&& good.getG_ID() == distribytionReport.getG_id()) {
						nd_plan += distribytionReport.getNd_plan();
						nd_fact += distribytionReport.getNd_fact();
					}
				}
			}

		}
		distribution = nd_fact / nd_plan * 100d;
		return distribution;
	}

	public ReportDistribution() {
		super();
	}
	public int getkId() {
		return kId;
	}
	public void setkId(int kId) {
		this.kId = kId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(int subGroupId) {
		this.subGroupId = subGroupId;
	}
	public LocalDate getDateStart() {
		return dateStart;
	}
	public void setDateStart(LocalDate dateStartDate) {
		this.dateStart = dateStartDate;
	}
	public LocalDate getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(LocalDate dateEnDate) {
		this.dateEnd = dateEnDate;
	}

}
