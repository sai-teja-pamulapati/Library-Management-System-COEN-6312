package com.university.library.model.assets;

import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.users.User;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class Asset {
    
    private String assetId;
    private String title;
    private String preview;
    private String logo;
    private boolean availability = true;
    private static AssetRepository assetRepository = AssetRepository.getInstance();
    private static LoanAssetRepository loanAssetRepository = LoanAssetRepository.getInstance();

    public Asset() {
        
    }

    public Asset(String assetId, String title, String preview, String logo, boolean availability) {
        this.assetId = assetId;
        this.title = title;
        this.preview = preview;
        this.logo = logo;
        this.availability = availability;
    }

    
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId='" + assetId + '\'' +
                ", title='" + title + '\'' +
                ", preview='" + preview + '\'' +
                ", logo='" + logo + '\'' +
                ", availability=" + availability +
                '}';
    }

    public void addAsset() {
        assetRepository.addAsset(this);
    }

    public void removeAsset() {
        assetRepository.removeAsset(this.getAssetId());
    }

    public void updateAsset() {
        assetRepository.update(this);
    }

    

    public LoanAsset loanAsset() {
    LoanAsset loanAsset = new LoanAsset();
    Date today = new Date();
    User user = App.getLoggedInUser();
    loanAsset.setAssetId(this.getAssetId());
    loanAsset.setUserId(user.getUserId());
    loanAsset.setLoanDate(today);
    
    
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(today);
    calendar.add(Calendar.MINUTE, 1);//retrun window 
    Date expectedReturnDate = calendar.getTime();
    
    loanAsset.setExpectedReturnDate(expectedReturnDate);
    this.availability = false;
    this.updateAsset();
    loanAssetRepository.saveLoanAsset(loanAsset);
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    System.out.println("Requested Asset has been borrowed. Please return the item by " + dateFormat.format(expectedReturnDate) + ".");
    
    return loanAsset;
}
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Asset)) return false;
        Asset asset = (Asset) obj;
        return availability == asset.availability && Objects.equals(assetId , asset.assetId) && Objects.equals(title , asset.title) && Objects.equals(preview , asset.preview) && Objects.equals(logo , asset.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetId , title , preview , logo , availability);
    }
}
