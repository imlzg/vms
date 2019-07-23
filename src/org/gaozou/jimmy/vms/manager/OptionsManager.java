package org.gaozou.jimmy.vms.manager;

import org.gaozou.jimmy.vms.domain.Options;
import org.gaozou.jimmy.base.Helper;

/**
 * Author: george
 * Powered by GaoZou group.
 */
public class OptionsManager extends Manager<Options> {
    public static final String DEFAULT   = "default";
    public static final String STAGE_NEW = "NEW";
    public static final String STAGE_USE = "USE";

    public Options getDefault() {
        Options options = get(DEFAULT);
        if (Helper.isTransient(options)) {
            options = new Options();
            options.setIdc(DEFAULT);
            options.setStage(STAGE_NEW);

            options.setName("GAOZOU");
            options.setDomain("gaozou.com");

            options.setCookie("authz_gozou");
            options.setPhrase("this_is_your_phrase_for_des.");

            save(options);
        }
        return options;
    }
}
