package org.gaozou.jimmy.vms.action;

import org.gaozou.jimmy.vms.domain.Options;
import org.gaozou.jimmy.vms.domain.User;
import org.gaozou.jimmy.vms.manager.OptionsManager;
import org.gaozou.jimmy.vms.manager.UserManager;
import org.gaozou.jimmy.base.Helper;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class OptionsAction extends Action<Options> {
    private OptionsManager optionsManager;


    public OptionsManager getOptionsManager() {
        return optionsManager;
    }
    public void setOptionsManager(OptionsManager optionsManager) {
        this.optionsManager = optionsManager;
    }




    public void doPrepare() throws Exception {
    }



    public String manage() {
        return SUCCESS;
    }

    public String persist() {
        optionsManager.save(options);
        return SUCCESS;
    }




}
