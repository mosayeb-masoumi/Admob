package com.example.admob;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class MainActivity extends AppCompatActivity {


    //https://www.youtube.com/watch?v=zz7V0ggh458


    Button btn_interstitialAd;
    Button btn_rewarded_video;
    AdView adView;
    public InterstitialAd interstitialAd;
    public RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_interstitialAd = findViewById(R.id.btn_interstitialAd);
        btn_rewarded_video = findViewById(R.id.btn_rewarded_video);
        adView = findViewById(R.id.adView);

        MobileAds.initialize(this);  // initialize admob

        /*****************************banner*************************************************************/
        // show banner
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            // and ...other methods
        });
        /**********************************************/


        /*******************************intrestitialAd********************************************************************/

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

        // show in button click listener

        /**********************************************/


        /****************************rewardedVideoAd******************************************************/

          rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
          rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

          rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
              @Override
              public void onRewardedVideoAdLoaded() {

              }

              @Override
              public void onRewardedVideoAdOpened() {

              }

              @Override
              public void onRewardedVideoStarted() {

              }

              @Override
              public void onRewardedVideoAdClosed() {

              }

              @Override
              public void onRewarded(RewardItem rewardItem) {
                 // video finished we can give score to user
                  Log.i("TAG", "video finished");
              }

              @Override
              public void onRewardedVideoAdLeftApplication() {

              }

              @Override
              public void onRewardedVideoAdFailedToLoad(int i) {

              }

              @Override
              public void onRewardedVideoCompleted() {

              }
          });

        /**********************************************/





        btn_interstitialAd.setOnClickListener(view -> {

            if(interstitialAd.isLoaded()){
                interstitialAd.show();
            }else{
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "failed to load interstitialAd");
            }

            // to load for next time
            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            interstitialAd.loadAd(new AdRequest.Builder().build());

        });


        btn_rewarded_video.setOnClickListener(view -> {


            if(rewardedVideoAd.isLoaded()){
                rewardedVideoAd.show();
            }else{
               Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "failed to load rewarded video");
            }

            // to load for next time
            rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
            rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
        });

    }
}