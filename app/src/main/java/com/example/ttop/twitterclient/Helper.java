package com.example.ttop.twitterclient;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ListView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class Helper {
    public static final String CONSUMER_KEY = "tzxQjIPywEL8UWYy3AU3CMK2A";
    public static final String CONSUMER_SECRET = "oMGer69mmL0AvnxRA7jbX5BrYGYIw5cOtkkwL6MJb0jkHlvAHG";


    public static final long TWEET_ID = 631879971628183552L;

    public static void createTweetToShow(final ConstraintLayout mainLayout, final Context context) {
        TweetUtils.loadTweet(TWEET_ID, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(context, result.data );
                mainLayout.addView(tweetView);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });
    }

    public static void addTimelineToListView(ListView listView, final Context context, String user) {
        final UserTimeline userTimeline  = new UserTimeline.Builder().screenName(user).build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(context).setTimeline(userTimeline).build();

        listView.setAdapter(adapter);
    }
}
