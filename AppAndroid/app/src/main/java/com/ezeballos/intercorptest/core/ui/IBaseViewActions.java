package com.ezeballos.intercorptest.core.ui;

/**
 * Interface for basic actions for View
 */
public interface IBaseViewActions {

    /**
     * Initialize views
     */
    default void initViews(){
        // Do nothing for default
    }

    /**
     * Set observers on ViewModel
     */
    void initObserversOfViewModel();
}

