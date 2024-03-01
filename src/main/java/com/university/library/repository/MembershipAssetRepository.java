package com.university.library.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.university.library.model.MembershipManagement;

public class MembershipAssetRepository {
    private Map<String, MembershipManagement> membershipByUserId = new HashMap<>();

    private String createKey(String userId, Date startDate, Date endDate) {
        return userId + "-" + startDate + "-" + endDate;
    }

    public MembershipManagement getMembership(String userId) {
        // System.out.println(" membership looking for id " + userId);
        for (String key : membershipByUserId.keySet()) {
            if (key.startsWith(userId + '-')) {
                // System.out.println(membershipByUserId.get(key));

                MembershipManagement membership = membershipByUserId.get(key);
                // System.out.println("membership object values " + membership);

                System.out.println("membership details from repo");
                System.out.println("get userid :  " + membership.getuserId());
                System.out.println("Start date :" + membership.getStartDate());
                System.out.println("Start date :" + membership.getEndDate());
                System.out.println("AmountPaid :" + membership.getMembershipAmountPaid());
                System.out.println("Status :" + membership.isMembershipStatus());
                // System.out.println("get userid " + membership.getuserId());
                return membership;
            }
        }
        System.out.println("no membership found from getmembership function " + userId);

        return null;
    }

    public boolean addMembership(MembershipManagement membership) {
        String userIdDate = createKey(membership.getuserId(), membership.getStartDate(),
                membership.getEndDate());
        // System.out.println(userIdDate);
        // System.out.println("membership details stored before repo from
        // addmemebership");

        // System.out.println(userIdDate);

        System.out.println("Start date :" + membership.getStartDate());
        System.out.println("Start date :" + membership.getEndDate());
        System.out.println("Amount :" + membership.getMembershipAmountPaid());
        System.out.println("Status :" + membership.isMembershipStatus());
        System.out.println("get userid :  " + membership.getuserId());

        // System.out.println("before adding membership " + membershipByUserId);
        membershipByUserId.put(userIdDate, membership);
        // System.out.println("after adding membership " + membershipByUserId);
        return true;
    }
}