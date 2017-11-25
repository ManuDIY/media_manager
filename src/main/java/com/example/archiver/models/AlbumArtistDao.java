package com.example.archiver.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface AlbumArtistDao extends JpaRepository<Song, Long> {

    public Song findByArtistId(int artistId);

    public Song findByAlbumArtistId(int albumArtistId);

    @Query(value="SELECT sum(s.raw_length) FROM songs s", nativeQuery = true)
    public List<Song> calcAllRawLength();

    @Query(value="SELECT sum(s.raw_length) FROM songs s WHERE s.album = ?1", nativeQuery = true)
    public List<Song> findAlbumLength();


}