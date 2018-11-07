package com.yixin.dsc.dto.rule.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 规则批量匹配结果
 * Package : com.yixin.dsc.dto.rule.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午7:29:22
 *
 */
public class BatchMatchResult {
	
	//资方ID
	private String capitalId;
	
	//资方code
	private String capitalCode;
	
	//是否全部匹配
    private boolean flag = true;
    //匹配规则集合
    private List<DscMatchResult> machRuleList= null;
    //未匹配规则集合
    private List<DscMatchResult> noMachRuleList= null;

    public BatchMatchResult(){
    	  machRuleList= Collections.synchronizedList(new ArrayList<DscMatchResult>());
		  noMachRuleList= Collections.synchronizedList(new ArrayList<DscMatchResult>());
    }
    
    public String getCapitalId() {
		return capitalId;
	}
	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}
	public String getCapitalCode() {
		return capitalCode;
	}
	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}
	public void addnoMachRule(DscMatchResult matchResult){
        this.noMachRuleList.add(matchResult);
        this.setFlag(false);
    }
    public void addMachRule(DscMatchResult matchResult){
        this.machRuleList.add(matchResult);
    }

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<DscMatchResult> getMachRuleList() {
		return machRuleList;
	}

	public void setMachRuleList(List<DscMatchResult> machRuleList) {
		this.machRuleList = machRuleList;
	}

	public List<DscMatchResult> getNoMachRuleList() {
		return noMachRuleList;
	}

	public void setNoMachRuleList(List<DscMatchResult> noMachRuleList) {
		this.noMachRuleList = noMachRuleList;
	}

}
