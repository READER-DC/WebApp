package com.sehal.report1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sehal.model.DistribytionReport;
import com.sehal.model.Good;

import jakarta.ejb.Stateless;

@Stateless
public class ReportDistribution {
	int kId;
	int categoryId;
	int subCategoryId;
	int groupId;
	int subGroupId;
	LocalDate dateStart;
	LocalDate dateEnd;

	public double distribution(String kId, String categotyId) {
		int ndPlan = 0;
		int ndFact = 0;
		double distribution = 0d;
		List<DistribytionReport> distribytionReports = distributionsByKIdAndCategory(
				kId, categotyId);

		for (DistribytionReport distribytionReport : distribytionReports) {
			ndPlan += distribytionReport.getNd_plan();
			ndFact += distribytionReport.getNd_fact();
		}
		distribution = 100d * ndFact / ndPlan;
		return distribution;
	}
	public List<DistribytionReport> distributionsByKId(String kIdRequest) {
		kId = Integer.valueOf(kIdRequest);
		List<DistribytionReport> distributionsByKId = new ArrayList<>();
		if (kId == 0) {
			distributionsByKId = DistribytionReport.distribytionLines;
		} else {
			for(DistribytionReport distribytionReport : DistribytionReport.distribytionLines) {
				if(distribytionReport.getK_id() == kId) {
					distributionsByKId.add(distribytionReport);
				}
			}
		}
		return distributionsByKId;		
	}

	public List<Integer> distributionsByCategory(String categoryRequest) {
		categoryId = Integer.valueOf(categoryRequest);
		List<Integer> skuByCategory = new ArrayList<>();
		if(categoryId == 0) {
			for (Good item : Good.goods) 
				skuByCategory.add(item.getG_ID());
		} else {
			for (Good item : Good.goods) {
				if (item.getCAT_ID() == categoryId) {
					skuByCategory.add(item.getG_ID());
				}
			}
		}
		return skuByCategory;
	}

	public List<Integer> distributionsByGroup(
			String groupIdRequest) {
		groupId = Integer.valueOf(groupIdRequest);
		List<Integer> skusByGroup = new ArrayList<>();
		if(categoryId == 0) {
			for (Good good : Good.goods) 
				skusByGroup.add(good.getG_ID());
		} else {
			for (Good good : Good.goods) {
				if (good.getCAT_ID() == categoryId) {
					skusByGroup.add(good.getG_ID());
				}
			}
		}
		return skusByGroup;
	}

	public List<DistribytionReport> distributionsByKIdAndCategory(
			String kidRequest, String categoryRequest) {
		System.out.println("Start distributionsByKIdAndCategory");
		List<DistribytionReport> distributionsByKIdAndCategory = new ArrayList<>();
		List<DistribytionReport> distributionsByKId = distributionsByKId(
				kidRequest);
		List<Integer> skusByCategory = distributionsByCategory(categoryRequest);
		System.out.println(
				"distributionsByKId size:" + distributionsByKId.size());
		System.out.println(
				"distributionsByCategory size:" + skusByCategory.size());
		for (DistribytionReport distribytionByKId : distributionsByKId) {
			for (Integer skuByCategory : skusByCategory) {
				if (distribytionByKId.getG_id() == skuByCategory) {
					distributionsByKIdAndCategory.add(distribytionByKId);
				}
			}
		}
		return distributionsByKIdAndCategory;
	}

	public List<DistribytionReport> distributionsByKIdAndGroup(
			String kidRequest, String groupRequest) {
		List<DistribytionReport> distribytionReports = new ArrayList<>();

		return distribytionReports;
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
