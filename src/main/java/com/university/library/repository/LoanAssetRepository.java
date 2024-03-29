package com.university.library.repository;

import com.university.library.model.LoanAsset;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LoanAssetRepository {
    private static final AtomicInteger assetIdGenerator = new AtomicInteger(0);

    private static LoanAssetRepository instance;
    private HashMap<String, LoanAsset> loans = new HashMap<>();

    private LoanAssetRepository() {
    }

    public static synchronized LoanAssetRepository getInstance() {
        if (instance == null) {
            instance = new LoanAssetRepository();
        }
        return instance;
    }

    public List<LoanAsset> getLoanedItemsForUser(String userId) {
        return loans.values().stream().filter(Objects::nonNull).filter(loanAsset -> Objects.equals(loanAsset.getUserId() , userId)).collect(Collectors.toList());
    }

    // public void saveLoanAsset(LoanAsset loanAsset) {
    //     loanAsset.setLoanAssetId(String.valueOf(assetIdGenerator.getAndIncrement()));
    //     loans.put(loanAsset.getLoanAssetId(), loanAsset);
    // }
    public void saveLoanAsset(LoanAsset loanAsset) {
    if (loanAsset.getLoanAssetId() == null) {
        loanAsset.setLoanAssetId(String.valueOf(assetIdGenerator.getAndIncrement()));
    }
    loans.put(loanAsset.getLoanAssetId(), loanAsset);
}

     public long countActiveLoansByUserId(String userId) {
        return loans.values().stream()
                     .filter(loanAsset -> Objects.equals(loanAsset.getUserId(), userId))
                     .filter(loanAsset -> loanAsset.getExpectedReturnDate() == null || loanAsset.getExpectedReturnDate().after(new Date()))
                     .count();
    }

}
