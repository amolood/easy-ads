package com.iamhabib.easyads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by HABIB on 10/29/2016.
 */

public class EasyAds {
    static Handler delayHandler = new Handler();
    static Runnable delayRunnable;

    public static BuilderBanner forBanner(Context context) {
        return new BuilderBanner(context);
    }

    public static BuilderNative forNative(Context context){
        return new BuilderNative(context);
    }

    public static BuilderInterstitial forInterstitial(Context context) {
        return new BuilderInterstitial(context);
    }

    public static class BuilderBanner {

        private long delayTime=0;
        Context context;
        AdView adViewBanner;
        AdRequest.Builder adRequestBuilder;

        private BuilderBanner(final Context context) {
            this.context = context;
            adRequestBuilder = new AdRequest.Builder();
            delayRunnable=new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).isFinishing()) {
                        return;
                    }
                    adViewBanner.loadAd(adRequestBuilder.build());
                }
            };
        }

        public BuilderBanner banner(AdView adViewBanner){
            this.adViewBanner = adViewBanner;
            return this;
        }

        public BuilderBanner testDevice(String code){
            adRequestBuilder.addTestDevice(code);
            return this;
        }

        public BuilderBanner delay(int timeInMilis){
            this.delayTime=timeInMilis;
            return this;
        }

        public BuilderBanner listener(AdListener adListener){
            adViewBanner.setAdListener(adListener);
            return this;
        }

        public BuilderBanner listener(AdView.OnClickListener clickListener){
            adViewBanner.setOnClickListener(clickListener);
            return this;
        }

        public BuilderBanner show(){
            delayHandler.postDelayed(delayRunnable, delayTime);
            return this;
        }

    }

    public static class BuilderNative {

        private long delayTime=0;
        Context context;
        NativeExpressAdView adViewNative;
        AdRequest.Builder adRequestBuilder;

        private BuilderNative(final Context context) {
            this.context = context;
            adRequestBuilder = new AdRequest.Builder();
            delayRunnable=new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).isFinishing()) {
                        return;
                    }
                    adViewNative.loadAd(adRequestBuilder.build());
                }
            };
        }

        public BuilderNative banner(NativeExpressAdView adViewNative){
            this.adViewNative = adViewNative;
            return this;
        }

        public BuilderNative testDevice(String code){
            adRequestBuilder.addTestDevice(code);
            return this;
        }

        public BuilderNative delay(int timeInMilis){
            this.delayTime=timeInMilis;
            return this;
        }

        public BuilderNative listener(AdListener adListener){
            adViewNative.setAdListener(adListener);
            return this;
        }

        public BuilderNative listener(AdView.OnClickListener clickListener){
            adViewNative.setOnClickListener(clickListener);
            return this;
        }

        public BuilderNative show(){
            delayHandler.postDelayed(delayRunnable, delayTime);
            return this;
        }

    }

    public static class BuilderInterstitial {
        private long delayTime=0;
        Context context;
        InterstitialAd interstitialAd;
        AdRequest.Builder adRequestBuilder;

        private BuilderInterstitial(final Context context) {
            this.context = context;
            interstitialAd=new InterstitialAd(context);
            adRequestBuilder = new AdRequest.Builder();
            delayRunnable=new Runnable() {
                @Override
                public void run() {
                    if (((Activity) context).isFinishing()) {
                        return;
                    }
                    interstitialAd.loadAd(adRequestBuilder.build());
                }
            };
        }

        public BuilderInterstitial testDevice(String code){
            adRequestBuilder.addTestDevice(code);
            return this;
        }

        public BuilderInterstitial delay(int timeInMilis){
            this.delayTime=timeInMilis;
            return this;
        }

        public BuilderInterstitial listener(AdListener adListener){
            interstitialAd.setAdListener(adListener);
            return this;
        }

        public BuilderInterstitial show(){
            delayHandler.postDelayed(delayRunnable, delayTime);
            return this;
        }

    }
}