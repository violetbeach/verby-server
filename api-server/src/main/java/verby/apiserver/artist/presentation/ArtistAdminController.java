package verby.apiserver.artist.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import verby.apiserver.artist.command.application.ArtistService;
import verby.apiserver.artist.command.application.CreateArtistRequest;
import verby.apiserver.artist.command.domain.Artist;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/artists")
@RequiredArgsConstructor
public class ArtistAdminController {

    private final ArtistService artistService;

    @PostMapping
    private ResponseEntity<Artist> create(@RequestBody @Valid CreateArtistRequest request) {
        Artist artist = artistService.create(request);
        return new ResponseEntity<>(artist, HttpStatus.CREATED);
    }

}
