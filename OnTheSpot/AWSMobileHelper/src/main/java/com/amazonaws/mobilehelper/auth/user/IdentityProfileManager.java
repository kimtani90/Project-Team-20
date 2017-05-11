package com.amazonaws.mobilehelper.auth.user;
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.16
//

import com.amazonaws.mobilehelper.auth.IdentityProvider;
import com.amazonaws.mobilehelper.auth.IdentityProviderType;

import java.util.HashMap;
import java.util.Map;

/**
 * Manage retrieving User Profiles
 */
public class IdentityProfileManager {
    private Map<IdentityProviderType, Class<? extends IdentityProfile>> providerTypeProfileClassMap
        = new HashMap<>();

    /**
     * Register a Profile class to handle getting profiles for a particular provider type.
     **/
    public void registerIdentityProfileClass(final IdentityProviderType providerType,
                                      final Class<? extends IdentityProfile> identityProfileClass) {
        providerTypeProfileClassMap.put(providerType, identityProfileClass);
    }

    /**
     * Get the current user profile for the specified identity provider.
     * @param provider the identity provider.
     * @return the User Identity Profile.
     */
    public IdentityProfile getIdentityProfile(final IdentityProvider provider)
        throws ProfileRetrievalException {
        final Class<? extends IdentityProfile> profileClass =
            providerTypeProfileClassMap.get(provider.getProviderType());
        final Exception exception;
        try {
            return profileClass.newInstance().loadProfileInfo(provider);
        } catch (final InstantiationException ex) {
            exception = ex;

        } catch (final IllegalAccessException ex) {
            exception = ex;
        }
        throw new RuntimeException("Can't create IdentityProfile Instance", exception);
    }
}
