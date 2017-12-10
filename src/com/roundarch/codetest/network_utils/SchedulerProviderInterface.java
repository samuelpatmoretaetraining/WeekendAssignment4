package com.roundarch.codetest.network_utils;

import io.reactivex.Scheduler;

/**
 * Created by hrskrs on 5/8/2017.
 */

public interface SchedulerProviderInterface {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
