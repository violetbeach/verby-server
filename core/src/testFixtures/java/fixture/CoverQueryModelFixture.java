package fixture;

import com.verby.core.cover.query.dto.CoverQueryModel;

public enum CoverQueryModelFixture {

    NORMAL_COVER_QUERY_MODEL( "테스트 커버 영상", 1L, 2L, "User1", 1L, "Artist1", 1L, "Song1", "content", "/test/video/test.mp4", "/test/highlight/test.mp4", "/image/highlight/test.mp4", 0L);
    ;

    private Long id;
    private final String title;
    private final Long contestId;
    private final Long publisherId;
    private final String publisherName;
    private final Long artistId;
    private final String artistName;
    private final Long songId;
    private final String songName;
    private final String content;
    private final String video;
    private final String highlight;
    private final String image;
    private final Long hits;

    CoverQueryModelFixture(String title, Long contestId, Long publisherId, String publisherName, Long artistId, String artistName, Long songId, String songName, String content, String video, String highlight, String image, Long hits) {
        this.title = title;
        this.contestId = contestId;
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.songId = songId;
        this.songName = songName;
        this.content = content;
        this.video = video;
        this.highlight = highlight;
        this.image = image;
        this.hits = hits;
    }

    public CoverQueryModel getCoverQueryModel(Long id) {
        return new CoverQueryModel(id, contestId, publisherId, publisherName, title, content, video, highlight, image, artistId, artistName, songId, songName, hits);
    }

}
