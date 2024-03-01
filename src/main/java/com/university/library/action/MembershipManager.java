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

        membershipRepository.addMembership(membership);

        System.out.println("membership purchased");
        App.getLoggedInUser().setUserRole(UserRole.PAID_USER);
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