package com.university.library.action;

import com.university.library.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;

import com.university.library.App;
import com.university.library.model.MembershipManagement;
import com.university.library.repository.MembershipAssetRepository;
import com.university.library.model.users.UserRole;

public class MembershipManager {
    private static Scanner scanner = new Scanner(System.in);
    private static MembershipAssetRepository membershipRepository = new MembershipAssetRepository();

    public static void buyMembership() {

        System.out.println("The membership period is 3 months for 58CAD");
        System.out.println("To continue to purchase click 'b'");

        String keyword = scanner.nextLine();
        if (!keyword.equals("b")) {
            System.out.println("invalid keyword, purchase stopped");
            return;
        }

        System.out.println("enter the membership start date(yyyy-MM-dd)");
        String startDateString = scanner.next();
        Date startDate = parseDate(startDateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 3);
        Date endDate = calendar.getTime();

        MembershipManagement membership = new MembershipManagement();
        membership.setUserId(App.getLoggedInUser().getUserId());
        membership.setMembershipAmountPaid(58.0);
        membership.setStartDate(startDate);
        membership.setEndDate(endDate);
        membership.setMembershipStatus(true);
        System.out.println("membership details");
        System.out.println("Start date : " + formatDate(membership.getStartDate()));
        System.out.println("Start date : " + formatDate(membership.getEndDate()));
        System.out.println("amount paid : " + membership.getMembershipAmountPaid());
        System.out.println("status : " + membership.isMembershipStatus());
        System.out.println("get userid :  " + membership.getuserId());

        membershipRepository.addMembership(membership);

        System.out.println("membership successfully purchased");

        displayMembership(App.getLoggedInUser().getUserId());
        App.getLoggedInUser().setUserRole(UserRole.PAID_USER);
    }

    public static void displayMembership(String userId) {
        MembershipManagement membership = membershipRepository.getMembership(userId);
        if (membership != null) {
            System.out.println("membership details retrieved");
            System.out.println("Start date :" + formatDate(membership.getStartDate()));
            System.out.println("Start date :" + formatDate(membership.getEndDate()));
            System.out.println("Start date :" + membership.getMembershipAmountPaid());
            System.out.println("Start date :" + membership.isMembershipStatus());
        } else {
            System.out.println("no membership found \n" + userId);
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("invalid date format");
            return null;
        }
    }
}