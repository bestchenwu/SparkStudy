package leetCode.medium;

import java.util.ArrayList;
import java.util.List;

//设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个
//功能：
//
//
// postTweet(userId, tweetId): 创建一条新的推文
// getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
//
// follow(followerId, followeeId): 关注一个用户
// unfollow(followerId, followeeId): 取消关注一个用户
//
//
// 示例:
//
//
//Twitter twitter = new Twitter();
//
//// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
//twitter.postTweet(1, 5);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//twitter.getNewsFeed(1);
//
//// 用户1关注了用户2.
//twitter.follow(1, 2);
//
//// 用户2发送了一个新推文 (推文id = 6).
//twitter.postTweet(2, 6);
//
//// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
//// 推文id6应当在推文id5之前，因为它是在5之后发送的.
//twitter.getNewsFeed(1);
//
//// 用户1取消关注了用户2.
//twitter.unfollow(1, 2);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//// 因为用户1已经不再关注用户2.
import java.util.*;

class Tweet {

    public int userId;
    public int tweetId;

    public Tweet(int userId, int tweetId) {
        this.userId = userId;
        this.tweetId = tweetId;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "userId=" + userId +
                ", tweetId=" + tweetId +
                '}';
    }
}

public class LeetCode355_20210805 {

    //总共的tweet文章数据库
    private List<Tweet> tweetList = new ArrayList<>();
    private Map<Integer, List<Integer>> userFollows = new HashMap<>();

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(userId, tweetId);
        tweetList.add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        List<Integer> followUsers = userFollows.getOrDefault(userId, new ArrayList<>());
        int count = 0;
        for(int i = tweetList.size()-1;i>=0;i--){
            Tweet tweet = tweetList.get(i);
            if(tweet.userId == userId){
                result.add(tweet.tweetId);
                count++;
                if(count==10){
                    break;
                }
            }
            if(followUsers.contains(tweet.userId)){
                result.add(tweet.tweetId);
                count++;
                if(count==10){
                    break;
                }
            }
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        List<Integer> followUsers = userFollows.get(followerId);
        if(followUsers==null){
            followUsers = new ArrayList<>();
            userFollows.put(followerId,followUsers );
        }
        followUsers.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        List<Integer> followUsers = userFollows.get(followerId);
        if(followUsers == null){
            return;
        }
        followUsers.remove(Integer.valueOf(followeeId));
    }

    public static void main(String[] args) {
        LeetCode355_20210805 twitter = new LeetCode355_20210805();
        twitter.postTweet(2,5 );
        twitter.postTweet(1,3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2,13 );
        twitter.postTweet(2,10 );
        twitter.postTweet(1,2 );
        twitter.postTweet(2,94 );
        twitter.postTweet(2,505 );
        twitter.postTweet(1,333 );
        twitter.postTweet(1,22 );
        List<Integer> feeds = twitter.getNewsFeed(2);
        System.out.println("feeds="+feeds);
        twitter.follow(2,1);
        feeds = twitter.getNewsFeed(2);
        System.out.println("feeds="+feeds);
    }
}
