package com.university.library.model.assets;

import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.users.User;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public abstract class Asset {
    // Attributes
    private String assetId;
    private String title;
    private String preview;
    private String logo;
    private boolean availability = true;
    private static AssetRepository assetRepository = AssetRepository.getInstance();
    private static LoanAssetRepository loanAssetRepository = LoanAssetRepository.getInstance();

    // Constructors
    public Asset() {
        // Default constructor
    }

    public Asset(String assetId, String title, String preview, String logo, boolean availability) {
        this.assetId = assetId;
        this.title = title;
        this.preview = preview;
        this.logo = logo;
        this.availability = availability;
    }

    // asset functionalities

    
    // Libraran functionalities

    private void addAsset(){
 

    }

    private void removeAsset(){
        List<Asset> allAssets = assetRepository.getAllAssets();
        System.out.println("Following is the list of Assets: " + allAssets);
        System.out.println("Enter the asset ID to delete an asset: " );
        String assetId = scanner.nextLine();
        AssetRepository asset = new AssetRepository();
        asset.asset.delete(assetId);
        System.out.println("Updated list after adding an Asset" + allAssets);
    }

    private void updateBookDetails(){
        System.out.println("Enter the Book Title to update a book details");
        String title = scanner.nextLine();
    }

    private void updateNewsLetter(){

    }

    private void viewLibraryActivities(){

    }
    // Getters and setters
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


    public void updateAsset() {
        assetRepository.update(this);
    }

    public void loanAsset() {
        LoanAsset loanAsset = new LoanAsset();
        Date today = new Date();
        User user = App.getLoggedInUser();
        loanAsset.setAssetId(this.getAssetId());
        loanAsset.setUserId(user.getUserId());
        loanAsset.setLoanDate(today);
        loanAsset.setReturnDate(DateUtils.addDays(today, 30));
        this.availability = false;
        this.updateAsset();
        loanAssetRepository.saveLoanAsset(loanAsset);
        System.out.println("Requested Asset has been borrowed. You have 30 days to return the item.");
    }
}
