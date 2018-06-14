package com.hieupham.absolutecleanarchitecture.utils.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hieupham on 6/5/18.
 */

public class CompositeLiveData<T> {

    private MediatorLiveData<T> compositeLiveData = new MediatorLiveData<>();
    private List<LiveData<T>> sources = new ArrayList<>();

    public LiveData<T> addAtomicSource(LiveData<T> source) {
        removeSources();
        return addSource(source);
    }

    public LiveData<T> addSource(final LiveData<T> source) {
        if (!sources.contains(source)) sources.add(source);
        compositeLiveData.addSource(source, resource -> compositeLiveData.setValue(resource));
        return asLiveData();
    }

    public void removeSources() {
        if (sources.isEmpty()) return;
        for (LiveData<T> source : sources) {
            compositeLiveData.removeSource(source);
        }
    }

    public LiveData<T> asLiveData() {
        return compositeLiveData;
    }

    public MutableLiveData<T> asMutableLiveData() {
        return compositeLiveData;
    }
}
