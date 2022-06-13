package com.comicall.ComicallApi.services.author;

import com.comicall.ComicallApi.dtos.comics.ComicsRequest;
import com.comicall.ComicallApi.dtos.comics.ComicsResponse;
import com.comicall.ComicallApi.dtos.genres.GenreDTO;
import com.comicall.ComicallApi.dtos.page.PageRequest;
import com.comicall.ComicallApi.dtos.page.PageResponse;
import com.comicall.ComicallApi.entities.Comics;
import com.comicall.ComicallApi.entities.Genre;
import com.comicall.ComicallApi.entities.Page;
import com.comicall.ComicallApi.entities.User;
import com.comicall.ComicallApi.helpers.files.FileUtils;
import com.comicall.ComicallApi.helpers.mappers.comics_mapper.IComicsMapper;
import com.comicall.ComicallApi.helpers.mappers.genre_mapper.IGenreMapper;
import com.comicall.ComicallApi.helpers.mappers.page_mapper.IPageMapper;
import com.comicall.ComicallApi.models.UserDetailsImpl;
import com.comicall.ComicallApi.repositories.ComicsRepository;
import com.comicall.ComicallApi.repositories.GenreRepository;
import com.comicall.ComicallApi.repositories.PageRepository;
import com.comicall.ComicallApi.repositories.UserRepository;
import com.comicall.ComicallApi.services.images.IImagesService;
import com.comicall.ComicallApi.services.session.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthorService implements IAuthorService{

    @Autowired
    private ComicsRepository _comicsRepository;
    @Autowired
    private IGenreMapper _genreMapper;
    @Autowired
    private PageRepository _pageRepository;
    @Autowired
    private ISessionService _sessionService;
    @Autowired
    private IImagesService _imageService;
    @Autowired
    private FileUtils _fileUtils;
    @Autowired
    private IComicsMapper _comicsMapper;

    @Override
    public Optional<ComicsResponse> createComics(ComicsRequest comics) {
        User author = _sessionService.getAuthenticatedUser();
        Comics createdComics = Comics.builder()
                .setName(comics.getName())
                .setAuthor(author)
                .setDescription(comics.getDescription())
                .setPublishYear(comics.getPublishYear())
                .build();
        Comics savedComics = _comicsRepository.save(createdComics);

        try {
            if(comics.getLogo().isEmpty()) return Optional.empty();
            String logoPath = _imageService.store(comics.getLogo(), author.getUsername(), savedComics.getName(), "logo");
            savedComics.setPosterPath(logoPath);
            return Optional.of( _comicsMapper.toDto(_comicsRepository.save(createdComics)));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ComicsResponse> addGenresToComics(Long comicsId, Set<String> genres)  {
        Optional<Comics> comicsOptional = _comicsRepository.findById(comicsId);
        if(comicsOptional.isEmpty()) return Optional.empty();
        Comics comics = comicsOptional.get();
        User author = _sessionService.getAuthenticatedUser();
        if(!comics.getAuthor().equals(author)) return  Optional.empty();
        comics.setGenres(_genreMapper.toEntities(genres));
        return Optional.of(_comicsMapper.toDto(_comicsRepository.save(comics)));
    }

    @Override
    public Optional<ComicsResponse> addPagesToComics(PageRequest pageRequest) {
        User author = _sessionService.getAuthenticatedUser();
        Optional<Comics> comicsBox = _comicsRepository.findById(pageRequest.getComicsId());
        if(comicsBox.isEmpty()) return Optional.empty();
        Comics comics = comicsBox.get();

        List<Page> pages = _pageRepository.findByComics_IdIs(comics.getId(), Sort.by(Sort.Direction.ASC, "pageNumber"));
        int lastPageNumber = pages.size() == 0 ? 0 : pages.get(pages.size()-1).getPageNumber();

        for(MultipartFile image: pageRequest.getImages()){
            int currPageNumber = lastPageNumber + 1;
            try {
                String imagePath = _imageService.store(image, author.getUsername(), comics.getName(), _fileUtils.getRandomName());
                lastPageNumber = currPageNumber;
                Page page = new Page(null, currPageNumber, imagePath ,comics, new HashSet<>());
                pages.add(_pageRepository.save(page));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        return Optional.of(_comicsMapper.toDto(_comicsRepository.save(comics)));
    }


    @Override
    public void removeComics(Long comicsId) {
        Optional<Comics> comicsBox = _comicsRepository.findById(comicsId);
        if(comicsBox.isEmpty()) return;
        Comics comics = comicsBox.get();
        if(!comics.getAuthor().equals(_sessionService.getAuthenticatedUser())) return;
        String  comicsDirectory = Paths
                .get(comics.getAuthor().getUsername())
                .resolve(comics.getName())
                .toString();
        _imageService.delete(comicsDirectory);
        _comicsRepository.delete(comics);
    }

    @Override
    public Optional<ComicsResponse> changeComics(ComicsRequest comicsRequest, Long comicsId) {
        Optional<Comics> comicsOptional = _comicsRepository.findById(comicsId);
        if(comicsOptional.isEmpty()) return Optional.empty();
        Comics comics = comicsOptional.get();
        if(comicsRequest.getDescription() != null) {
            comics.setDescription(comicsRequest.getDescription());
        }
        if(comicsRequest.getLogo() != null) {
            try {
                String logoPath = _imageService.store(comicsRequest.getLogo(), comics.getAuthor().getUsername(), comics.getName(), "logo");
                comics.setPosterPath(logoPath);
            } catch (IOException ignored) {}
        }
        if(comicsRequest.getName() != null) {
            comics.setName(comicsRequest.getName());
        }
        comics.setPublishYear(comicsRequest.getPublishYear());
        comics.setIsReady(comicsRequest.getIsReady());
        return Optional.of(_comicsMapper.toDto(_comicsRepository.save(comics)));
    }

    @Override
    public List<ComicsResponse> getAuthorsComics() {
        return _comicsRepository
                .findByAuthor_IdEquals(_sessionService.getAuthenticatedUser().getId())
                .stream()
                .map(comics -> _comicsMapper.toDto(comics))
                .toList();
    }

    @Override
    public Optional<List<Page>> getComicsPages(Long id) {
        Comics comics = _comicsRepository.getById(id);
        if(!comics.getAuthor().equals(_sessionService.getAuthenticatedUser()))  return Optional.empty();
        return Optional.of( _pageRepository.findByComics_IdIs(id, Sort.by(Sort.Direction.ASC, "pageNumber")));
    }

    @Override
    public ComicsResponse publishComics(Long id, boolean isRead) {
        Comics comics = _comicsRepository.getById(id);
        comics.setIsReady(isRead);
        return _comicsMapper.toDto(comics);
    }
}
