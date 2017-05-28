package com.zilong.demo.changpiandian.model;

/**
 * 音乐实体类
 */
public class MusicMedia {
    // 音乐id
    private int mMusicId;
    // 路径
    private String mFileName;
    // 名字
    private String mMusicName;
    // 时间
    private int mMusicDuration;
    // Artist
    private String mMusicArtist;
    // Album
    private String mMusicAlbum;
    // year
    private String mMusicYear;
    // file type
    private String mFileType;
    // fize size
    private String mFileSize;
    // path
    private String mFilePath;
    //专辑ID
    private int mAlbuId;
    private String mSingerName;

    private int title_key;
    private int singer_id;

    private int album_key;

    public int getAlbum_key() {
        return album_key;
    }

    public void setAlbum_key(int album_key) {
        this.album_key = album_key;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public int getTitle_key() {
        return title_key;
    }

    public void setTitle_key(int title_key) {
        this.title_key = title_key;
    }

    public int getMusicId() {
        return mMusicId;
    }

    public void setMusicId(int musicId) {
        mMusicId = musicId;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getMusicName() {
        if (mMusicName == null) {
            return "Unknown";
        }
        return mMusicName;
    }

    public void setMusicName(String musicName) {
        mMusicName = musicName;
    }

    public int getMusicDuration() {
        return mMusicDuration;
    }

    public void setMusicDuration(int musicDuration) {
        mMusicDuration = musicDuration;
    }

    public String getMusicArtist() {
        if (mMusicArtist == null) {
            return "Unknown";
        }
        return mMusicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        mMusicArtist = musicArtist;
    }

    public String getMusicAlbum() {
        if (mMusicAlbum == null) {
            return "Unknown";
        }
        return mMusicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        mMusicAlbum = musicAlbum;
    }

    public String getMusicYear() {
        if (mMusicYear == null) {
            return "Unknown";
        }
        return mMusicYear;
    }

    public void setMusicYear(String musicYear) {
        mMusicYear = musicYear;
    }

    public String getFileType() {
        if (mFileType == null) {
            return  "Unknown";
        }
        return mFileType;
    }

    public void setFileType(String fileType) {
        mFileType = fileType;
    }

    public String getFileSize() {
        if (mFileSize == null) {
            return "Unknown";
        }
        return mFileSize;
    }

    public void setFileSize(String fileSize) {
        mFileSize = fileSize;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    public int getmAlbuId() {
        return mAlbuId;
    }

    public void setmAlbuId(int mAlbuId) {
        this.mAlbuId = mAlbuId;
    }

    public String getmSingerName() {
        return mSingerName;
    }

    public void setmSingerName(String mSingerName) {
        this.mSingerName = mSingerName;
    }
}
