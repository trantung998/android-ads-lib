package com.tungt.lib;

import android.app.Activity;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

public class AdsService {
    public static void InitAdsService(Activity activity, String appKey) {
        /**
         *Ad Units should be in the type of IronSource.Ad_Unit.AdUnitName, example
         */
        IronSource.init(activity, appKey, IronSource.AD_UNIT.OFFERWALL, IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);

        IntegrationHelper.validateIntegration(activity);
    }

    public void ShowVideoAds() {
        if(IronSource.isRewardedVideoAvailable()){
            IronSource.showRewardedVideo();
        }
    }

    public static void RegisterIronSourceListener() {
        IronSource.setRewardedVideoListener(new RewardedVideoListener() {
            /**
             * Invoked when the RewardedVideo ad view has opened.
             * Your Activity will lose focus. Please avoid performing heavy
             * tasks till the video ad will be closed.
             */
            @Override
            public void onRewardedVideoAdOpened() {
            }

            /*Invoked when the RewardedVideo ad view is about to be closed.
            Your activity will now regain its focus.*/
            @Override
            public void onRewardedVideoAdClosed() {
            }

            /**
             * Invoked when there is a change in the ad availability status.
             *
             * @param - available - value will change to true when rewarded videos are *available.
             *          You can then show the video by calling showRewardedVideo().
             *          Value will change to false when no videos are available.
             */
            @Override
            public void onRewardedVideoAvailabilityChanged(boolean available) {
                //Change the in-app 'Traffic Driver' state according to availability.
            }

            /**
             /**
             * Invoked when the user completed the video and should be rewarded.
             * If using server-to-server callbacks you may ignore this events and wait *for the callback from the ironSource server.
             *
             * @param - placement - the Placement the user completed a video from.
             */
            @Override
            public void onRewardedVideoAdRewarded(Placement placement) {
                /** here you can reward the user according to the given amount.
                 String rewardName = placement.getRewardName();
                 int rewardAmount = placement.getRewardAmount();
                 */
            }

            /* Invoked when RewardedVideo call to show a rewarded video has failed
             * IronSourceError contains the reason for the failure.
             */
            @Override
            public void onRewardedVideoAdShowFailed(IronSourceError error) {
            }

            /*Invoked when the end user clicked on the RewardedVideo ad
             */
            @Override
            public void onRewardedVideoAdClicked(Placement placement) {
            }

            /* Note: the events AdStarted and AdEnded below are not available for all supported rewarded video
                    ad networks. Check which events are available per ad network you choose
                    to include in your build.
                    We recommend only using events which register to ALL ad networks you
                    include in your build.
                            * Invoked when the video ad starts playing.
            */
            @Override
            public void onRewardedVideoAdStarted() {
            }

            /* Invoked when the video ad finishes plating. */
            @Override
            public void onRewardedVideoAdEnded() {
            }
        });
    }
}
