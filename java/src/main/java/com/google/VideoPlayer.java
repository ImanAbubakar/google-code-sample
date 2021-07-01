package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  Video currentlyPlayingVideo = null;
  boolean nonPaused = false;
  boolean playing = false;
  HashMap playList= new HashMap<String, ArrayList<Video>>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public String videoString(Video video) {
	  
	  String playingVideo = null;
	  if (video != null) {
		  playingVideo = video.getTitle() + " (" +
				  video.getVideoId() + ") [" ;
		  if (video.getTags().size() > 0) {
			  playingVideo = playingVideo + video.getTags().get(0);
		  }
		  for (int i = 1; i < video.getTags().size(); i++) {
			  playingVideo = playingVideo + " " + video.getTags().get(i);
		  }
		  playingVideo = playingVideo + "]"; 
	  }
	  return playingVideo;
  }
  
  public void showAllVideos() {
    //System.out.println("showAllVideos needs implementation");
    System.out.println("Here's a list of all available videos:");
   
    ArrayList<String>
    videoNames = new ArrayList<String>();

    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
    	videoNames.add(videoString(videoLibrary.getVideos().get(i)));
    }
    Collections.sort(videoNames);
    
    for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
    	System.out.println(videoNames.get(i));
    }
  }
  
  public void playVideo(String videoId) {
    //System.out.println("playVideo needs implementation");
	  Video video = videoLibrary.getVideo(videoId);
	  if (playing && video != null) {
		  stopVideo();
	  }
	  if (video != null) {
		  String name = video.getTitle();
		  System.out.println("Playing video: " + name); 
		  currentlyPlayingVideo = video;
		  nonPaused = true;
		  playing = true;
	  }else {
		  System.out.println("Cannot play video: Video does not exist");
	  }
  }

  public void stopVideo() {
    //System.out.println("stopVideo needs implementation");
	  if (currentlyPlayingVideo != null && playing) {
		  System.out.println("Stopping video: " + currentlyPlayingVideo.getTitle() );
		  nonPaused = false;
		  playing = false;
	  }else {
		  System.out.println("Cannot stop video: No video is currently playing");
	  }
	  
  }

  public void playRandomVideo() {
    //System.out.println("playRandomVideo needs implementation");
	  ArrayList<String>
	  videoNames = new ArrayList<String>();

	  for (int i = 0; i < videoLibrary.getVideos().size(); i++) {
		  videoNames.add(videoString(videoLibrary.getVideos().get(i)));
	  }
	  
	  Random rand = new Random();
	  System.out.println(videoNames.get(rand.nextInt(videoNames.size())));
	  
  }

  public void pauseVideo() {
    //System.out.println("pauseVideo needs implementation");
	  if (nonPaused) {
		  System.out.println("Pausing video: " + currentlyPlayingVideo.getTitle());
		  nonPaused = false;
	  }else if (currentlyPlayingVideo != null) {
		  System.out.println("Video already paused: " + currentlyPlayingVideo.getTitle());
	  }else {
		  System.out.println("Cannot pause video: No video is currently playing");
	  } 
  }

  public void continueVideo() {
    //System.out.println("continueVideo needs implementation");
	  if (currentlyPlayingVideo != null && !nonPaused) {
		  System.out.println("Continuing video: " + currentlyPlayingVideo.getTitle());
		  nonPaused = true;
	  }else if(nonPaused) {
		  System.out.println("Cannot continue video: Video is not paused");
	  }else {
		  System.out.println("Cannot continue video: No video is currently playing");
	  }
	  
  }

  public void showPlaying() {
    //System.out.println("showPlaying needs implementation");
	  String playingVideo = null;
	  if (currentlyPlayingVideo == null) {
		  System.out.println("No video is currently playing");
	  }else {
		  playingVideo = currentlyPlayingVideo.getTitle() + " (" +
				  currentlyPlayingVideo.getVideoId() + ") [" + 
				  currentlyPlayingVideo.getTags().get(0);
		  for (int i = 1; i < currentlyPlayingVideo.getTags().size(); i++) {
			  playingVideo = playingVideo + " " + currentlyPlayingVideo.getTags().get(i);
		  }
		  playingVideo = playingVideo + "]";
		  if (nonPaused) {
			  System.out.println("Currently playing: " + playingVideo);
		  }else {
			  System.out.println("Currently playing: " + playingVideo + " - PAUSED");
		  }
	  }
	 
  }

  public void createPlaylist(String playlistName) {
    //System.out.println("createPlaylist needs implementation");
	  if (playList.containsKey(playlistName.toLowerCase())) {
		  System.out.println("Cannot create playlist: A playlist with the same name already exists");
	  }else {
		  ArrayList newPlayList = new ArrayList<Video>();
		  playList.put(playlistName.toLowerCase(), newPlayList);
		  System.out.println("Successfully created new playlist: "+ playlistName);
	  }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    //System.out.println("addVideoToPlaylist needs implementation");
	  if (playList.containsKey(playlistName.toLowerCase())) {
		  if (videoLibrary.getVideos().contains(videoLibrary.getVideo(videoId))) {
			  ArrayList<Video> play = (ArrayList<Video>)playList.get(playlistName.toLowerCase());
			  Video video = videoLibrary.getVideo(videoId);
			  if(play.contains(video)) {
				  System.out.println("Cannot add video to my_PLAYlist: Video already added");
			  }else {
				  play.add(video);
				  playList.put(playlistName.toLowerCase(), play);
				  System.out.println("Added video to " + playlistName + ": " + video.getTitle());
			  }
		  }else {
			  System.out.println("Cannot add video to my_playlist: Video does not exist");
		  }
	  }else {
		 System.out.println("Cannot add video to another_playlist: Playlist does not exist");
	  }
    
  }

  public void showAllPlaylists() {
    //System.out.println("showAllPlaylists needs implementation");
	  if (!playList.isEmpty()) {
		  ArrayList PlayListName = new ArrayList<String>(); 
		  
		  for (Object key : playList.keySet()) {
			  PlayListName.add(key.toString());
		  }
		  Collections.sort(PlayListName);
		  System.out.println("Showing all playlists:");
		  for (int i = 0; i< PlayListName.size(); i++) {
			 System.out.println(PlayListName.get(i));
		  }
	  }else {
		  System.out.println("No playlists exist yet");
	  }
  }

  public void showPlaylist(String playlistName) {
    //System.out.println("showPlaylist needs implementation");
	  if (playList.containsKey(playlistName.toLowerCase())) {
		  System.out.println("Showing playlist: " + playlistName);
		  ArrayList<Video> play = (ArrayList<Video>)playList.get(playlistName.toLowerCase());
		  if (!play.isEmpty()) {
			  for (int i = 0; i< play.size(); i++) {
				  System.out.println(videoString(play.get(i)));
			  }
		  }else {
			  System.out.println("No videos here yet");
		  }
	  }else {
		  System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
	  }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    //System.out.println("removeFromPlaylist needs implementation");
	  if (playList.containsKey(playlistName.toLowerCase())) {
		  ArrayList<Video> play = (ArrayList<Video>)playList.get(playlistName.toLowerCase());
		  if (play.contains(videoLibrary.getVideo(videoId))) {
			  play.remove(videoLibrary.getVideo(videoId));
			  playList.put(playlistName.toLowerCase(), play);
			  System.out.println("Removed video from " + playlistName + ": " + videoLibrary.getVideo(videoId).getTitle());
		  }else if (videoLibrary.getVideo(videoId) == null){
			  System.out.println("Cannot remove video from my_playlist: Video does not exist");
		  }else {
			  System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
		  }
	  }else {
		  System.out.println("Cannot remove video from my_cool_playlist: Playlist does not exist");
	  }
    
  }

  public void clearPlaylist(String playlistName) {
    //System.out.println("clearPlaylist needs implementation");
	  if (playList.keySet().contains(playlistName.toLowerCase())) {
		  ArrayList newPlayList = new ArrayList<Video>();
		  playList.put(playlistName.toLowerCase(), newPlayList);
		  System.out.println("Successfully removed all videos from " + playlistName);
	  }else {
		  System.out.println("Cannot clear playlist " + playlistName + ": Playlist does not exist");
	  }
  }

  public void deletePlaylist(String playlistName) {
    //System.out.println("deletePlaylist needs implementation");
	  if (playList.keySet().contains(playlistName.toLowerCase())) {
		  playList.remove(playlistName.toLowerCase());
	  
		  System.out.println("Deleted playlist: " + playlistName);
	  }else {
		  System.out.println("Cannot delete playlist " + playlistName + ": Playlist does not exist");
	  }
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}
