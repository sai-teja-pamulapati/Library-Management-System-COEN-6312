package com.university.library.model;

import java.util.Date;

public class MembershipManagement {
    private boolean membershipStatus;
    private double membershipAmountPaid;
    private Date startDate;
    private String userId;
    private Date endDate;

    public MembershipManagement(boolean membershipStatus, double membershipAmountPaid, Date startDate, String userId,
            Date endDate) {
        this.membershipStatus = membershipStatus;
        this.membershipAmountPaid = membershipAmountPaid;
        this.startDate = startDate;
        this.userId = userId;
        this.endDate = endDate;
    }

    public MembershipManagement() {
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        System.out.println("userid" + userId);
        this.userId = userId;
    }

    public Date getstartDate() {
        return startDate;
    }

    public void setstartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getendDate() {
        return endDate;
    }

    public void setendDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(boolean membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getMembershipAmountPaid() {
        return membershipAmountPaid;
    }

    public void setMembershipAmountPaid(double membershipAmountPaid) {
        this.membershipAmountPaid = membershipAmountPaid;
    }

}
