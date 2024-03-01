package com.university.library.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.university.library.model.MembershipManagement;

public class MembershipAssetRepository {
    private Map<String, MembershipManagement> membershipUserAndAmount = new HashMap<>();

    private String handlekey(String userId, Date membershipStartDate, Date membershipEndDate) {
        return userId + "-" + membershipEndDate + "-" + membershipStartDate;
    }

    public boolean addMembership(MembershipManagement membership) {
        String userIdDate = handlekey(membership.getuserId(), membership.getStartDate(),
                membership.getEndDate());

        membershipUserAndAmount.put(userIdDate, membership);
        return true;
    }
}