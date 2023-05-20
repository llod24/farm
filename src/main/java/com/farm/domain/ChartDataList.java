package com.farm.domain;

import java.util.List;

public class ChartDataList {
	private List<String> labels;
    private List<Integer> workloadData;

    public ChartDataList(List<String> labels, List<Integer> workloadData) {
        this.labels = labels;
        this.workloadData = workloadData;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Integer> getWorkloadData() {
        return workloadData;
    }
}
