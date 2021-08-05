package leetCode.medium;

import org.junit.Test;

import java.util.Collections;

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
//twitter.getNewsFeed(1);
import java.util.*;
class TwitterModel implements Comparable<TwitterModel>{
    public Integer twitterId;
    public Long createTs;

    @Override
    public int compareTo(TwitterModel other) {
        return this.createTs.compareTo(other.createTs);
    }

    @Override
    public String toString() {
        return "TwitterModel{" +
                "twitterId=" + twitterId +
                ", createTs=" + createTs +
                '}';
    }
}

public class LeetCode355 {


    private Map<Integer,List<TwitterModel>> userMap ;
    private Map<Integer,Set<Integer>> followMap;
    private Long index = 0l;

    /** Initialize your data structure here. */
    public LeetCode355() {
        this.userMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<TwitterModel> tweetList = userMap.get(userId);
        if(tweetList==null){
            tweetList = new ArrayList<>();
            userMap.put(userId,tweetList);
        }
        TwitterModel newModel = new TwitterModel();
        newModel.twitterId = tweetId;
        newModel.createTs = index++;
        tweetList.add(newModel);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<TwitterModel> resultList = new ArrayList<>();
        List<TwitterModel> userPost = userMap.getOrDefault(userId,new ArrayList<>());
        if(userPost.size()>0){
            resultList.addAll(userPost);
        }
        Set<Integer> followUserList = followMap.getOrDefault(userId,new HashSet<>());
        for(Integer i : followUserList){
            List<TwitterModel> followPost = userMap.getOrDefault(i,new ArrayList<>());
            if(followPost.size()>0){
                resultList.addAll(followPost);
            }
        }
        Collections.sort(resultList);
        Collections.reverse(resultList);
        if(resultList.size()>10){
            resultList = resultList.subList(0,10);
        }
        List<Integer> result = new ArrayList<>();
        for(TwitterModel twitterModel : resultList){
            result.add(twitterModel.twitterId);
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followList = followMap.getOrDefault(followerId,new HashSet<>());
        followList.add(followeeId);
        followMap.put(followerId,followList);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followList = followMap.get(followerId);
        if(followList!=null){
            followList.remove(Integer.valueOf(followeeId));
        }
    }

    public static void main(String[] args) {
        LeetCode355 twitter = new LeetCode355();
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
//        twitter.postTweet(1, 5);
//        List<Integer> feeds = twitter.getNewsFeed(1);
//        System.out.println("feeds="+feeds);
//        twitter.follow(1, 2);
//        twitter.postTweet(2, 6);
//        feeds =  twitter.getNewsFeed(1);
//        System.out.println("feeds="+feeds);
//        twitter.postTweet(1, 7);
//        feeds =  twitter.getNewsFeed(1);
//        System.out.println("feeds="+feeds);
//        twitter.unfollow(1, 2);
//        feeds = twitter.getNewsFeed(1);
//        System.out.println("feeds="+feeds);
    }
}
