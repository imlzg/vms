package org.gaozou.jimmy.vms.action;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import org.gaozou.jimmy.base.Helper;
import org.gaozou.jimmy.vms.domain.Ad;
import org.gaozou.jimmy.vms.manager.AdManager;

import java.util.List;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class AdAction extends Action<Ad> {
    private AdManager adManager;
    private Ad ad;

    private List<Ad> ads;

    public AdManager getAdManager() {
        return adManager;
    }
    public void setAdManager(AdManager adManager) {
        this.adManager = adManager;
    }

    public Ad getAd() {
        return ad;
    }
    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public List<Ad> getAds() {
        return ads;
    }
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }


    public void doPrepare() throws Exception {
        if (! Helper.isEmpty(ad,      "id"))  ad = adManager.get(ad.getId());
        else if (! Helper.isEmpty(ad, "idc")) ad = adManager.get(ad.getIdc());
    }




    ////
    public String manage() {
        ads = adManager.get();
        return SUCCESS;
    }

    public String update() {
        redirect = referer;
        return SUCCESS;
    }
    @Validations(
            requiredStrings={
                    @RequiredStringValidator(fieldName="ad.idc", message="You must enter a value for ad idc", trim=true, shortCircuit=true),
                    @RequiredStringValidator(fieldName="ad.name", message="You must enter a value for ad name.", shortCircuit=true)
                    }
    )
    public String persist() {
        ad.setOpen(open);
        adManager.save(ad);
        return SUCCESS;
    }
    public String remove() {
        adManager.remove(ad);
        return SUCCESS;
    }
}
