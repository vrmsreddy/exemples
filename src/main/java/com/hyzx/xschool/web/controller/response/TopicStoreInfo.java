package com.hyzx.xschool.web.controller.response;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by jack on 15-11-7.
 */
public class TopicStoreInfo {
	private Long id;
	private String name;
	/**
	 * 题目数量
	 */
	private int topicNum;
	private int taskNum;

	private String goodDescription;
	private String badDescription;
	private String trainPlanName;

	public String getGoodDescription() {
		return goodDescription;
	}

	public void setGoodDescription(String goodDescription) {
		this.goodDescription = goodDescription;
	}

	public String getBadDescription() {
		return badDescription;
	}

	public void setBadDescription(String badDescription) {
		this.badDescription = badDescription;
	}

	public String getTrainPlanName() {
		return trainPlanName;
	}

	public void setTrainPlanName(String trainPlanName) {
		this.trainPlanName = trainPlanName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTopicNum() {
		return topicNum;
	}

	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}

	public int getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(int taskNum) {
		this.taskNum = taskNum;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
