package com.meuapt.testemeuapt.service;

import android.support.annotation.NonNull;

import rx.Scheduler;

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
