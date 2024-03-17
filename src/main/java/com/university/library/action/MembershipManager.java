package com.university.library.action;

import com.university.library.App;
import com.university.library.model.Membership;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.model.users.nonacademic.FreeUser;
import com.university.library.model.users.nonacademic.PaidUser;
import com.university.library.repository.MembershipAssetRepository;
import com.university.library.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MembershipManager {
    private static Scanner scanner = new Scanner(System.in);
    private static MembershipAssetRepository membershipRepository = new MembershipAssetRepository();
    private static UserRepository userRepository = new UserRepository();

    public static void buyMembership() {
        Membership exisiMembership = membershipRepository.getMembership(App.getLoggedInUser().getUserId());
        if (exisiMembership != null && exisiMembership.isMembershipStatus()) {
            System.out.println("You already have a membership, free user can only have membership");
            return;

        }

        System.out.println("The membership period is 3 months for 58CAD");
        System.out.println("To continue to purchase click 'b'");

        String keyword = scanner.nextLine();
        if (!keyword.equals("b")) {
            System.out.println("invalid keyword, purchase stopped");
            return;
        }
        User userRole = App.getLoggedInUser();

        Calendar dobCalendar = Calendar.getInstance();

        dobCalendar.setTime(userRole.getDateOfBirthAsDate());
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) < dobCalendar.get(Calendar.MONTH)) {
            age--;
        }

        if (userRole instanceof FreeUser && age < 12) {
            System.out.println("Sorry, you must be atleast 12 years old to buy membership");
            return;
        }

        // System.out.println("enter the membership start date(yyyy-MM-dd)");
        // String startDateString = scanner.next();
        Date startDate = new Date(); // parseDate(startDateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, 3);
        Date endDate = calendar.getTime();

        Membership membership = new Membership();
        membership.setUserId(App.getLoggedInUser().getUserId());
        membership.setMembershipAmountPaid(58.0);
        membership.setStartDate(startDate);
        membership.setEndDate(endDate);
        membership.setMembershipStatus(true);
        System.out.println("membership details");
        System.out.println("Start date : " + formatDate(membership.getStartDate()));
        System.out.println("End date : " + formatDate(membership.getEndDate()));
        System.out.println("amount paid : " + membership.getMembershipAmountPaid());
        System.out.println("statusOFMembership : " + membership.isMembershipStatus());
        System.out.println("get userid :  " + membership.getuserId());

        membershipRepository.addMembership(membership);

        FreeUser loggedInUser = (FreeUser) App.getLoggedInUser();

        System.out.println("membership successfully purchased. Please logout and login to continue.");
        User paidUser = new PaidUser(loggedInUser.getUserId(), loggedInUser.getName(), loggedInUser.getPassword(),
                loggedInUser.getEmailId(),
                loggedInUser.getMobileNumber(), loggedInUser.getAddress(), loggedInUser.getDateOfBirth(),
                loggedInUser.getGender(), loggedInUser.getOrganisation());
        userRepository.updateUser(paidUser);

        // displayMembership(App.getLoggedInUser().getUserId());
    }

    public static void displayMembership(String userId) {
        Membership membership = membershipRepository.getMembership(userId);
        if (membership != null) {
            if (membership.isMembershipStatus()) {
                System.out.println("membership details retrieved");
                System.out.println("Start date :" + formatDate(membership.getStartDate()));
                System.out.println("End date :" + formatDate(membership.getEndDate()));
                System.out.println("AmountPaid  :" + membership.getMembershipAmountPaid());
                System.out.println("statusOFMembership  :" + membership.isMembershipStatus());
            } else {
                System.out.println("Membership is cancelled \n");
            }
        } else {
            System.out.println("Membership not found  \n");
        }
    }

    public static void renewMembership(String userId) {
        Membership membership = membershipRepository.getMembership(userId);
        // .out.println(" membership looking for id " + membership);
        if (membership != null) {
            System.out.println("are you sure you want to renew ur membership ? (yes/no)");
            String response = scanner.nextLine();
            System.out.println(response);
            if (response.equals("yes")) {

                Date endDate = membership.getEndDate();

                membership.setStartDate(endDate);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(Calendar.MONTH, 3);
                Date newEndDate = calendar.getTime();

                membership.setStartDate(membership.getEndDate());
                membership.setEndDate(newEndDate);
                membershipRepository.addMembership(membership);

                System.out.println("renewal successful");
                // displayMembership(userId);
            } else {
                System.out.println("renewal aborted");
            }
        } else {
            System.out.println("no membership was found to renew");
        }

    }

    public static void cancelMembership(String userId) {
        Membership membership = membershipRepository.getMembership(userId);
        if (membership != null) {
            System.out.println("are you sure you want to cancel ur membership ? (yes/no)");
            String response = scanner.nextLine();

            if (response.equals("yes")) {
                Date currenDate = new Date();
                Date startDate = membership.getStartDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                Date cancellationDeadline = calendar.getTime();
                if (currenDate.before(cancellationDeadline)) {
                    if (membershipRepository.removeMembership(userId)) {
                        System.out.println("cancellation successful, sorry to see you go.");
                    } else {
                        System.out.println("no active cancellation Thank god");
                    }
                } else {
                    System.out.println("cancellation deadline exceeded , cannot cancel the memberhsip");
                }

            } else {
                System.out.println("cancellation aborted");
            }
        } else {
            System.out.println("No membership found for the user");

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
